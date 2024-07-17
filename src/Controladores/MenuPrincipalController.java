/**
 * Contiene las clases que hacen funcionar las ventanas
 *  
*/
package Controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import alarmas.AlarmaList;
import cartaManagement.CartaGradeo;
import dbmanager.CartaManagerDAO;
import dbmanager.ConectManager;
import dbmanager.DBUtilidades;
import dbmanager.DBUtilidades.TipoBusqueda;
import dbmanager.ListasCartasDAO;
import dbmanager.SelectManager;
import ficherosFunciones.FuncionesExcel;
import funcionesAuxiliares.Utilidades;
import funcionesAuxiliares.Ventanas;
import funcionesInterfaz.AccionControlUI;
import funcionesInterfaz.FuncionesComboBox;
import funcionesInterfaz.FuncionesManejoFront;
import funcionesInterfaz.FuncionesTableView;
import funcionesManagment.AccionEliminar;
import funcionesManagment.AccionFuncionesComunes;
import funcionesManagment.AccionModificar;
import funcionesManagment.AccionReferencias;
import funcionesManagment.AccionSeleccionar;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Esta clase sirve viajar a las diferentes ventanas del programa, asi como
 * realizar diferentes diferentes funciones
 *
 * @author Alejandro Rodriguez
 */
public class MenuPrincipalController implements Initializable {
	// Labels
	@FXML
	private Label alarmaConexionInternet;

	// AnchorPanes
	@FXML
	private AnchorPane anchoPaneInfo;
	@FXML
	private AnchorPane rootAnchorPane;

	// ImageViews
	@FXML
	private ImageView backgroundImage;
	@FXML
	private ImageView imagenCarta;

	// Rectangles
	@FXML
	private Rectangle barraCambioAltura;

	// Buttons
	@FXML
	private Button botonCancelarSubida;
	@FXML
	private Button botonIntroducir;
	@FXML
	private Button botonLimpiar;
	@FXML
	private Button botonModificar;
	@FXML
	private Button botonMostrarParametro;
	@FXML
	private Button botonbbdd;

	// TextFields
	@FXML
	private TextField busquedaGeneral;

	// TableColumns
	@FXML
	private TableColumn<CartaGradeo, String> columnaCertificacion;
	@FXML
	private TableColumn<CartaGradeo, String> columnaColeccion;
	@FXML
	private TableColumn<CartaGradeo, String> columnaEdicion;
	@FXML
	private TableColumn<CartaGradeo, String> columnaEmpresa;
	@FXML
	private TableColumn<CartaGradeo, String> columnaId;
	@FXML
	private TableColumn<CartaGradeo, String> columnaNombre;
	@FXML
	private TableColumn<CartaGradeo, String> columnaNumero;
	@FXML
	private TableColumn<CartaGradeo, String> columnaReferencia;

	// ComboBoxes
	@FXML
	private ComboBox<String> comboboxNombreCarta;
	@FXML
	private ComboBox<String> comboboxNumeroCarta;
	@FXML
	private ComboBox<String> comboboxEdicionCarta;
	@FXML
	private ComboBox<String> comboboxColeccionCarta;
	@FXML
	private ComboBox<String> comboboxGradeoCarta;
	@FXML
	private ComboBox<String> comboboxEmpresaCarta;

	// VBoxes
	@FXML
	private VBox comboboxVbox;
	@FXML
	private VBox rootVBox;
	@FXML
	private VBox vboxContenido;
	@FXML
	private VBox vboxImage;

	// MenuItems
	@FXML
	private MenuItem menuArchivoAvanzado;
	@FXML
	private MenuItem menuArchivoCerrar;
	@FXML
	private MenuItem menuArchivoDelete;
	@FXML
	private MenuItem menuArchivoDesconectar;
	@FXML
	private MenuItem menuArchivoExcel;
	@FXML
	private MenuItem menuArchivoImportar;
	@FXML
	private MenuItem menuArchivoSobreMi;
	@FXML
	private MenuItem menuCartaAniadir;
	@FXML
	private MenuItem menuCartaEliminar;
	@FXML
	private MenuItem menuCartaModificar;
	@FXML
	private MenuItem menuEstadisticaEstadistica;
	@FXML
	private MenuItem menuPrecioTotal;

	// Menus
	@FXML
	private Menu navegacionCarta;
	@FXML
	private Menu navegacionCerrar;
	@FXML
	private Menu navegacionEstadistica;

	// MenuBars
	@FXML
	private MenuBar menuNavegacion;

	// ProgressIndicators
	@FXML
	private ProgressIndicator progresoCarga;

	// TextAreas
	@FXML
	private TextArea prontInfo;

	// TableViews
	@FXML
	private TableView<CartaGradeo> tablaBBDD;

	public CartaGradeo cartaCache;

	/**
	 * Instancia de la clase Ventanas para la navegación.
	 */
	private static Ventanas nav = new Ventanas();

	/**
	 * Instancia de FuncionesComboBox para funciones relacionadas con ComboBox.
	 */
	private static FuncionesComboBox funcionesCombo = new FuncionesComboBox();

	public static final AccionReferencias referenciaVentana = new AccionReferencias();

	public static CompletableFuture<List<Entry<String, String>>> urlPreviews;

	public static final AlarmaList alarmaList = new AlarmaList();

	double y = 0;

	public AccionReferencias guardarReferencia() {
		// Labels
		referenciaVentana.setAlarmaConexionInternet(alarmaConexionInternet);

		referenciaVentana.setBotonLimpiar(botonLimpiar);
		referenciaVentana.setBotonbbdd(botonbbdd);
		referenciaVentana.setBotonMostrarParametro(botonMostrarParametro);
		referenciaVentana.setBotonModificar(botonModificar);
		referenciaVentana.setBotonIntroducir(botonIntroducir);
		referenciaVentana.setBotonCancelarSubida(botonCancelarSubida);

		referenciaVentana.setBusquedaGeneralTextField(busquedaGeneral);

		// ImageViews
		referenciaVentana.setImagenCarta(imagenCarta);
		referenciaVentana.setBackgroundImage(backgroundImage);

		// MenuItems
		referenciaVentana.setMenuArchivoCerrar(menuArchivoCerrar);
		referenciaVentana.setMenuArchivoDelete(menuArchivoDelete);
		referenciaVentana.setMenuArchivoDesconectar(menuArchivoDesconectar);
		referenciaVentana.setMenuArchivoExcel(menuArchivoExcel);
		referenciaVentana.setMenuArchivoImportar(menuArchivoImportar);
		referenciaVentana.setMenuArchivoSobreMi(menuArchivoSobreMi);
		referenciaVentana.setMenuCartaAniadir(menuCartaAniadir);
		referenciaVentana.setMenuCartaEliminar(menuCartaEliminar);
		referenciaVentana.setMenuCartaModificar(menuCartaModificar);
		referenciaVentana.setMenuEstadisticaEstadistica(menuEstadisticaEstadistica);
		referenciaVentana.setMenuArchivoAvanzado(menuArchivoAvanzado);
		referenciaVentana.setMenuEstadisticaSumaTotal(menuPrecioTotal);

		// Menus
		referenciaVentana.setMenuNavegacion(menuNavegacion);
		referenciaVentana.setNavegacionCerrar(navegacionCerrar);
		referenciaVentana.setNavegacionCarta(navegacionCarta);
		referenciaVentana.setNavegacionEstadistica(navegacionEstadistica);

		// TableColumns
		referenciaVentana.setiDColumna(columnaId);
		referenciaVentana.setNombreColumna(columnaNombre);
		referenciaVentana.setNumeroColumna(columnaNumero);
		referenciaVentana.setEdicionColumna(columnaEdicion);
		referenciaVentana.setColeccionColumna(columnaColeccion);
		referenciaVentana.setEmpresaColumna(columnaEmpresa);
		referenciaVentana.setCertificacionColumna(columnaCertificacion);
		referenciaVentana.setReferenciaColumna(columnaReferencia);

		// ComboBoxes
		referenciaVentana.setNombreCartaCombobox(comboboxNombreCarta);
		referenciaVentana.setNumeroCartaCombobox(comboboxNumeroCarta);
		referenciaVentana.setNombreEdicionCombobox(comboboxEdicionCarta);
		referenciaVentana.setNombreColeccionCombobox(comboboxColeccionCarta);
		referenciaVentana.setNombreGradeoCombobox(comboboxGradeoCarta);
		referenciaVentana.setNombreEmpresaCombobox(comboboxEmpresaCarta);

		// Others
		referenciaVentana.setProntInfoTextArea(prontInfo);
		referenciaVentana.setProgresoCarga(progresoCarga);
		referenciaVentana.setTablaBBDD(tablaBBDD);
		referenciaVentana.setRootVBox(rootVBox);
		referenciaVentana.setVboxContenido(vboxContenido);
		referenciaVentana.setVboxImage(vboxImage);
		referenciaVentana.setAnchoPaneInfo(anchoPaneInfo);
		referenciaVentana.setRootAnchorPane(rootAnchorPane);
		referenciaVentana.setBarraCambioAltura(barraCambioAltura);
		referenciaVentana.setStageVentana(estadoStage());

		// ComboBox List
		AccionReferencias.setListaComboboxes(Arrays.asList(comboboxNombreCarta, comboboxNumeroCarta,
				comboboxEdicionCarta, comboboxColeccionCarta, comboboxGradeoCarta));

		// FXCollections Lists
		AccionReferencias.setListaElementosFondo(FXCollections.observableArrayList(backgroundImage, menuNavegacion));
		AccionReferencias.setListaBotones(
				FXCollections.observableArrayList(botonLimpiar, botonMostrarParametro, botonbbdd, botonCancelarSubida));

		AccionReferencias.setListaColumnasTabla(Arrays.asList(columnaNombre, columnaNumero, columnaEdicion,
				columnaColeccion, columnaEmpresa, columnaId, columnaCertificacion, columnaReferencia));

		return referenciaVentana;
	}

	public void enviarReferencias() {
		AccionControlUI.setReferenciaVentana(guardarReferencia());
		AccionFuncionesComunes.setReferenciaVentana(guardarReferencia());
		FuncionesTableView.setReferenciaVentana(guardarReferencia());
		FuncionesManejoFront.setReferenciaVentana(guardarReferencia());
		AccionSeleccionar.setReferenciaVentana(guardarReferencia());
		AccionEliminar.setReferenciaVentana(guardarReferencia());
		AccionModificar.setReferenciaVentana(guardarReferencia());
		Utilidades.setReferenciaVentana(guardarReferencia());
		Utilidades.setReferenciaVentanaPrincipal(guardarReferencia());
		VentanaAccionController.setReferenciaVentana(guardarReferencia());
		OpcionesAvanzadasController.setReferenciaVentanaPrincipal(guardarReferencia());
		Ventanas.setReferenciaVentanaPrincipal(guardarReferencia());
		DBUtilidades.setReferenciaVentana(guardarReferencia());
	}

	/**
	 * Inicializa el controlador cuando se carga la vista.
	 *
	 * @param location  la ubicación del archivo FXML
	 * @param resources los recursos utilizados por la vista
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		menuArchivoExcel.setGraphic(Utilidades.createIcon("/Icono/Archivo/exportar.png", 16, 16));
		menuArchivoImportar.setGraphic(Utilidades.createIcon("/Icono/Archivo/importar.png", 16, 16));
		menuArchivoDelete.setGraphic(Utilidades.createIcon("/Icono/Archivo/basura.png", 16, 16));
		menuArchivoSobreMi.setGraphic(Utilidades.createIcon("/Icono/Archivo/about.png", 16, 16));
		menuArchivoAvanzado.setGraphic(Utilidades.createIcon("/Icono/Archivo/configuraciones.png", 16, 16));
		menuArchivoDesconectar.setGraphic(Utilidades.createIcon("/Icono/Archivo/apagado.png", 16, 16));
		menuArchivoCerrar.setGraphic(Utilidades.createIcon("/Icono/Archivo/salir.png", 16, 16));

		menuCartaAniadir.setGraphic(Utilidades.createIcon("/Icono/Ventanas/add.png", 16, 16));
		menuCartaEliminar.setGraphic(Utilidades.createIcon("/Icono/Ventanas/delete.png", 16, 16));
		menuCartaModificar.setGraphic(Utilidades.createIcon("/Icono/Ventanas/modify.png", 16, 16));

		menuPrecioTotal.setGraphic(Utilidades.createIcon("/Icono/Estadistica/posesion.png", 16, 16));
		menuEstadisticaEstadistica.setGraphic(Utilidades.createIcon("/Icono/Estadistica/descarga.png", 16, 16));

		Platform.runLater(() -> {
			estadoStage().setOnCloseRequest(event -> stop());

			alarmaList.setAlarmaConexionInternet(alarmaConexionInternet);
			alarmaList.iniciarThreadChecker();

			enviarReferencias();

			establecerDinamismoAnchor();

			cambiarTamanioTable();

			FuncionesTableView.ajustarAnchoVBox();
			FuncionesTableView.seleccionarRaw();

			FuncionesTableView.modificarColumnas(true);
			AccionControlUI.controlarEventosInterfazPrincipal(guardarReferencia());
			FuncionesManejoFront.getStageVentanas().add(estadoStage());
			cargarDatosDataBase();
			AccionSeleccionar.actualizarRefrenciaClick(guardarReferencia());
		});

		AccionControlUI.establecerTooltips();

		formatearTextField();

	}

	@FXML
	void ampliarImagen(MouseEvent event) {
		enviarReferencias();
		CartaGradeo carta = guardarReferencia().getTablaBBDD().getSelectionModel().getSelectedItem();
		ImagenAmpliadaController.setCartaCache(carta);
		if (ImagenAmpliadaController.getCartaCache() != null) {

			if (guardarReferencia().getImagenCarta().getOpacity() != 0) {
				Ventanas.verVentanaImagen();
			}
		}
	}

	@FXML
	public void cambiarTamanioTable() {

		if (!barraCambioAltura.isDisable()) {
			// Vincular el ancho de barraCambioAltura con el ancho de rootVBox
			barraCambioAltura.widthProperty().bind(rootVBox.widthProperty());

			// Configurar eventos del ratón para redimensionar el rootVBox desde la parte
			// superior
			barraCambioAltura.setOnMousePressed(event -> y = event.getScreenY());

			barraCambioAltura.setOnMouseDragged(event -> {
				double deltaY = event.getScreenY() - y;
				double newHeight = rootVBox.getPrefHeight() - deltaY;
				double maxHeight = calcularMaxHeight(); // Calcula el máximo altura permitido
				double minHeight = 200; // Límite mínimo de altura

				if (newHeight > minHeight && newHeight <= maxHeight) {
					rootVBox.setPrefHeight(newHeight);
					rootVBox.setLayoutY(tablaBBDD.getLayoutY() + deltaY);
					tablaBBDD.setPrefHeight(newHeight);
					tablaBBDD.setLayoutY(tablaBBDD.getLayoutY() + deltaY);

					y = event.getScreenY();
				}
			});

			// Cambiar el cursor cuando se pasa sobre la barra de redimensionamiento
			barraCambioAltura.setOnMouseMoved(event -> {
				if (event.getY() <= 5) {
					barraCambioAltura.setCursor(Cursor.N_RESIZE);
				} else {
					barraCambioAltura.setCursor(Cursor.DEFAULT);
				}
			});

			rootAnchorPane.heightProperty()
					.addListener((observable, oldValue, newHeightValue) -> rootVBox.setMaxHeight(calcularMaxHeight()));

			rootAnchorPane.widthProperty().addListener((observable, oldValue, newWidthValue) -> {
				double newWidth = newWidthValue.doubleValue();

				if (newWidth <= 1130) {

					botonIntroducir.setLayoutX(231);
					botonIntroducir.setLayoutY(159);

					botonModificar.setLayoutX(231);
					botonModificar.setLayoutY(197);

				} else if (newWidth >= 1131) {

					botonIntroducir.setLayoutX(329);
					botonIntroducir.setLayoutY(31);

					botonModificar.setLayoutX(329);
					botonModificar.setLayoutY(72);

				}
			});
		}

	}

	// Método para calcular el máximo altura permitido
	private double calcularMaxHeight() {
		// Obtener el tamaño actual de la ventana
		Stage stage = (Stage) rootVBox.getScene().getWindow();
		double windowHeight = stage.getHeight();

		// Ajustar el máximo altura permitido según la posición del AnchorPane
		// numeroCaja
		return windowHeight - vboxContenido.getLayoutY() - 400;
	}

	/**
	 * Carga los datos de la base de datos en los ComboBox proporcionados después de
	 * un segundo de retraso. Esta función utiliza un ScheduledExecutorService para
	 * programar la tarea.
	 *
	 * @param comboboxes Una lista de ComboBox que se actualizarán con los datos de
	 *                   la base de datos.
	 */
	public void cargarDatosDataBase() {
		tablaBBDD.refresh();
		prontInfo.setOpacity(0);
		imagenCarta.setImage(null);

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		try {
			scheduler.schedule(() -> Platform.runLater(() -> {
				ListasCartasDAO.listasAutoCompletado();

				Task<Void> task = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						funcionesCombo.rellenarComboBox(AccionReferencias.getListaComboboxes());
						funcionesCombo.lecturaComboBox(AccionReferencias.getListaComboboxes());
						return null;
					}
				};

				// Iniciar el Task en un nuevo hilo
				Thread thread = new Thread(task);
				thread.setDaemon(true);
				thread.start();

				// Manejar la cancelación
				botonCancelarSubida.setOnAction(ev -> {
					botonCancelarSubida.setVisible(false);
					task.cancel();
				});

				// Cuando la tarea haya terminado, apaga el scheduler
				task.setOnSucceeded(event -> {
					botonCancelarSubida.setVisible(false);
					scheduler.shutdown();
				});

				// Cuando la tarea haya terminado, apaga el scheduler
				task.setOnRunning(event -> botonCancelarSubida.setVisible(true));
			}), 0, TimeUnit.SECONDS);
		} catch (Exception e) {
			Utilidades.manejarExcepcion(e);
		} finally {
			scheduler.shutdown();
		}
	}

	/**
	 * Establece el dinamismo en la interfaz gráfica ajustando propiedades de
	 * elementos como tamaños, anchos y máximos.
	 */
	public void establecerDinamismoAnchor() {

		FuncionesManejoFront.establecerFondoDinamico();

		FuncionesManejoFront.establecerAnchoColumnas(13);

		FuncionesManejoFront.establecerAnchoMaximoBotones(102.0);

		FuncionesManejoFront.establecerAnchoMaximoCamposTexto(162.0);

		FuncionesManejoFront.establecerAnchoMaximoComboBoxes(162.0);

		FuncionesManejoFront.establecerTamanioMaximoImagen(252.0, 325.0);
	}

	/**
	 * Funcion que permite restringir entrada de datos de todo aquello que no sea un
	 * numero entero en los comboBox numeroCarta y caja_comic
	 */
	public void formatearTextField() {
//		comboboxNumeroCarta.getEditor().setTextFormatter(FuncionesComboBox.validadorNenteros());
	}

	/////////////////////////////////
	//// METODOS LLAMADA A VENTANAS//
	/////////////////////////////////

	/**
	 * Permite el cambio de ventana a la ventana de SobreMiController
	 *
	 * @param event
	 */
	@FXML
	void verSobreMi(ActionEvent event) {
		enviarReferencias();
		nav.verSobreMi();
	}

	/**
	 * Metodo que mostrara los comics o comic buscados por parametro
	 *
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	void mostrarPorParametro(ActionEvent event) {
		enviarReferencias();
		mostrarCartas(false);
		modificarEstadoTabla(349, 1);
	}

	/**
	 * Metodo que muestra toda la base de datos.
	 *
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	void verTodabbdd(ActionEvent event) {
		enviarReferencias();
		mostrarCartas(true);
		modificarEstadoTabla(349, 1);
	}

	public void modificarEstadoTabla(double altura, double opacidad) {
		rootVBox.setPrefHeight(altura);
		tablaBBDD.setPrefHeight(altura);
		tablaBBDD.setOpacity(opacidad);
	}

	private void mostrarCartas(boolean esCompleto) {

		if (esCompleto) {
			AccionSeleccionar.verBasedeDatos(esCompleto, false, null);
		} else {
			List<String> controls = new ArrayList<>();
			List<ComboBox<String>> listaComboboxes = AccionReferencias.getListaComboboxes();

			// Iterar sobre los ComboBox en orden
			for (ComboBox<String> comboBox : listaComboboxes) {
				controls.add(comboBox.getSelectionModel().getSelectedItem());
			}

			CartaGradeo comic = AccionControlUI.camposCarta(controls, false);

			AccionSeleccionar.verBasedeDatos(esCompleto, false, comic);
		}
	}

	/**
	 * Funcion que al pulsar el boton de 'botonVentas' se muestran aquellos comics
	 * que han sido vendidos
	 *
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	void comicsComprados(ActionEvent event) {
		enviarReferencias();
		imprimirCartasEstado(TipoBusqueda.COMPLETA);
	}

	@FXML
	void comicsGuardados(ActionEvent event) {
		enviarReferencias();
		imprimirCartasEstado(null);

	}

	@FXML
	void verOpcionesAvanzadas(ActionEvent event) {
		enviarReferencias();
		nav.verOpcionesAvanzadas();

	}

	private void imprimirCartasEstado(TipoBusqueda tipoBusqueda) {
		enviarReferencias();
		limpiezaDeDatos();
		limpiarComboBox();
		ListasCartasDAO.reiniciarListaCartas();
		FuncionesTableView.nombreColumnas();
		FuncionesTableView.actualizarBusquedaRaw();
		List<CartaGradeo> listaCartas;

		String sentenciaSQL = DBUtilidades.construirSentenciaSQL(tipoBusqueda);

		listaCartas = SelectManager.verLibreria(sentenciaSQL, false);

		FuncionesTableView.tablaBBDD(listaCartas);
	}

	////////////////////////////
	/// METODOS PARA EXPORTAR///
	////////////////////////////

	/**
	 * Importa un fichero CSV compatible con el programa para copiar la informacion
	 * a la base de datos
	 *
	 * @param event
	 * @throws SQLException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@FXML
	void importCSV(ActionEvent event) {
		enviarReferencias();
		limpiezaDeDatos();
		limpiarComboBox();

		guardarDatosCSV();

		ListasCartasDAO.listasAutoCompletado();

	}

	/**
	 * Exporta un fichero CSV compatible con el programa que copia el contenido de
	 * la base de datos en un fichero CSV
	 *
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	void exportCSV(ActionEvent event) {
		enviarReferencias();
		String mensaje = "";
		if (!ListasCartasDAO.listaNombre.isEmpty()) {
			limpiezaDeDatos();
			limpiarComboBox();
			String sentenciaSQL = DBUtilidades.construirSentenciaSQL(DBUtilidades.TipoBusqueda.COMPLETA);

			List<CartaGradeo> listaCartas = SelectManager.verLibreria(sentenciaSQL, false);

			cargaExportExcel(listaCartas, DBUtilidades.TipoBusqueda.COMPLETA.toString());
		} else {
			mensaje = "La base de datos esta vacia. No hay nada que exportar";
			AlarmaList.mostrarMensajePront(mensaje, false, prontInfo);
		}

	}

	/**
	 * Limpia los campos de pantalla donde se escriben los datos.
	 *
	 * @param event
	 */
	@FXML
	void limpiarDatos(ActionEvent event) {

		enviarReferencias();
		limpiezaDeDatos();
		limpiarComboBox();
	}

	/**
	 * Se llama a funcion que permite ver las estadisticas de la bbdd
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void verEstadistica(ActionEvent event) {
		enviarReferencias();
		AlarmaList.iniciarAnimacionEstadistica(prontInfo);
		ListasCartasDAO.generar_fichero_estadisticas();
		AlarmaList.detenerAnimacionPront(prontInfo);
		String mensaje = "Fichero creado correctamente";

		AlarmaList.mostrarMensajePront(mensaje, true, prontInfo);
	}

	/////////////////////////////////
	//// FUNCIONES////////////////////
	/////////////////////////////////

	/**
	 * Funcion que permite mostrar la imagen de portada cuando clickeas en una
	 * tabla.
	 *
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	void clickRaton(MouseEvent event) {
		enviarReferencias();
		if (!tablaBBDD.isDisabled()) {

			CartaGradeo carta = guardarReferencia().getTablaBBDD().getSelectionModel().getSelectedItem();
			ImagenAmpliadaController.setCartaCache(carta);
			AccionSeleccionar.seleccionarCartas(true);
		}
	}

	/**
	 * Funcion que permite mostrar la imagen de portada cuando usas las teclas de
	 * direccion en una tabla.
	 *
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	void teclasDireccion(KeyEvent event) {
		enviarReferencias();
		if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) && !tablaBBDD.isDisabled()) {

			CartaGradeo carta = guardarReferencia().getTablaBBDD().getSelectionModel().getSelectedItem();
			ImagenAmpliadaController.setCartaCache(carta);
			AccionSeleccionar.seleccionarCartas(true);
		}

	}

	/////////////////////////////////
	//// FUNCIONES CREACION FICHEROS//
	/////////////////////////////////

	@FXML
	void borrarContenidoTabla(ActionEvent event) {
		enviarReferencias();
		try {
			Thread borradoTablaThread = new Thread(() -> {
				try {
					FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
					boolean confirmacionBorrado = nav.borrarContenidoTabla().get();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
					if (confirmacionBorrado) {

						AlarmaList.iniciarAnimacionCarga(referenciaVentana.getProgresoCarga());
						String sentenciaSQL = DBUtilidades.construirSentenciaSQL(DBUtilidades.TipoBusqueda.COMPLETA);

						List<CartaGradeo> listaCartas = SelectManager.verLibreria(sentenciaSQL, false);
						FuncionesExcel excelFuntions = new FuncionesExcel();
						// Configuración de la tarea para crear el archivo Excel

						Task<Boolean> crearExcelTask = excelFuntions.crearExcelTask(listaCartas,
								TipoBusqueda.ELIMINAR.toString(), dateFormat);
						Thread excelThread = new Thread(crearExcelTask);

						if (crearExcelTask == null) {
							botonCancelarSubida.setVisible(false);
							FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
							AlarmaList.detenerAnimacionPront(prontInfo);
							AlarmaList.detenerAnimacionCarga(progresoCarga);

							// Detener el hilo de la tarea
							excelThread.interrupt();
						} else {

							crearExcelTask.setOnRunning(e -> {

								estadoStage().setOnCloseRequest(closeEvent -> {
									crearExcelTask.cancel(true);
									excelThread.interrupt(); // Interrumpir el hilo
									Utilidades.cerrarCargaCartas();
								});

								cerradoPorOperaciones();
								botonCancelarSubida.setVisible(true);
								FuncionesManejoFront.cambiarEstadoMenuBar(true, guardarReferencia());
								limpiezaDeDatos();
							});

							crearExcelTask.setOnSucceeded(e -> {

								botonCancelarSubida.setVisible(false);
								boolean deleteCompleted;
								try {
									deleteCompleted = CartaManagerDAO.deleteTable().get();
									String mensaje = deleteCompleted
											? "Base de datos borrada y reiniciada correctamente"
											: "ERROR. No se ha podido eliminar y reiniciar la base de datos";

									if (deleteCompleted) {
										AlarmaList.detenerAnimacionCarga(referenciaVentana.getProgresoCarga());
										Utilidades.eliminarContenidoCarpeta(FuncionesExcel.DEFAULT_PORTADA_IMAGE_PATH);
									}
									FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
									AlarmaList.mostrarMensajePront(mensaje, deleteCompleted, prontInfo);

								} catch (InterruptedException | ExecutionException e1) {
									crearExcelTask.cancel(true);
									excelThread.interrupt();
									Utilidades.manejarExcepcion(e1);
								}
							});

							crearExcelTask.setOnFailed(e -> {
								botonCancelarSubida.setVisible(false);
								FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
							});

							crearExcelTask.setOnCancelled(e -> {
								FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
								AlarmaList.detenerAnimacionCarga(referenciaVentana.getProgresoCarga());
								String mensaje = "Has cancelado el borrado de la base de datos";
								AlarmaList.mostrarMensajePront(mensaje, true, prontInfo);

							});

							// Manejar la cancelación
							botonCancelarSubida.setOnAction(ev -> {
								botonCancelarSubida.setVisible(false);
								AlarmaList.detenerAnimacionCarga(referenciaVentana.getProgresoCarga());

								crearExcelTask.cancel(true);
								excelThread.interrupt();
							});
						}
						// Iniciar la tarea principal de creación de Excel en un hilo separado
						excelThread.start();

					} else {
						AlarmaList.detenerAnimacionCarga(referenciaVentana.getProgresoCarga());
						String mensaje = "ERROR. Has cancelado el borrado de la base de datos";
						AlarmaList.mostrarMensajePront(mensaje, false, prontInfo);
						FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
					}
				} catch (InterruptedException | ExecutionException e) {
					Utilidades.manejarExcepcion(e);
				}
			});
			borradoTablaThread.setDaemon(true); // Marcar el hilo como demonio
			borradoTablaThread.start();

		} catch (Exception e) {
			Utilidades.manejarExcepcion(e);
		}
	}

	/**
	 * Carga y ejecuta una tarea para exportar datos a un archivo Excel.
	 *
	 * @param fichero     El archivo Excel de destino.
	 * @param listaCartas La lista de cómics a exportar.
	 */
	private void cargaExportExcel(List<CartaGradeo> listaCartas, String tipoBusqueda) {
		enviarReferencias();
		FuncionesExcel excelFuntions = new FuncionesExcel();
		String mensajeErrorExportar = "ERROR. No se ha podido exportar correctamente.";
		String mensajeCancelarExportar = "ERROR. Se ha cancelado la exportación.";
		String mensajeValido = "Has exportado el fichero excel correctamente";

		prontInfo.setText(null);
		prontInfo.setOpacity(0);
		tablaBBDD.getItems().clear();
		imagenCarta.setImage(null);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		// Configuración de la tarea para crear el archivo Excel
		Task<Boolean> crearExcelTask = excelFuntions.crearExcelTask(listaCartas, tipoBusqueda, dateFormat);
		Thread excelThread = new Thread(crearExcelTask);

		if (crearExcelTask == null) {
			botonCancelarSubida.setVisible(false);
			FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
			AlarmaList.detenerAnimacionPront(prontInfo);
			AlarmaList.detenerAnimacionCarga(progresoCarga);

			// Detener el hilo de la tarea
			excelThread.interrupt();
			AlarmaList.mostrarMensajePront(mensajeCancelarExportar, false, prontInfo);
		} else {
			crearExcelTask.setOnRunning(e -> {

				estadoStage().setOnCloseRequest(event -> {
					crearExcelTask.cancel(true);
					Utilidades.cerrarCargaCartas();
				});

				cerradoPorOperaciones();
				botonCancelarSubida.setVisible(true);
				FuncionesManejoFront.cambiarEstadoMenuBar(true, guardarReferencia());
				AlarmaList.iniciarAnimacionCarga(progresoCarga);
				limpiezaDeDatos();

			});

			crearExcelTask.setOnSucceeded(event -> {
				botonCancelarSubida.setVisible(false);
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.mostrarMensajePront(mensajeValido, true, prontInfo);
				AlarmaList.detenerAnimacionCarga(progresoCarga);
			});

			// Configuración del comportamiento cuando la tarea falla
			crearExcelTask.setOnFailed(event -> {
				botonCancelarSubida.setVisible(false);
				procesarResultadoImportacion(false);
				AlarmaList.detenerAnimacionPront(prontInfo);
				AlarmaList.detenerAnimacionCarga(progresoCarga);

				// Detener el hilo de la tarea
				excelThread.interrupt();
				alarmaList.manejarFallo(mensajeErrorExportar, prontInfo);
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.detenerAnimacionCarga(progresoCarga);
				AlarmaList.mostrarMensajePront(mensajeCancelarExportar, false, prontInfo);
			});

			// Configuración del comportamiento cuando la tarea es cancelada
			crearExcelTask.setOnCancelled(event -> {
				alarmaList.manejarFallo(mensajeCancelarExportar, prontInfo);
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.detenerAnimacionCarga(progresoCarga);
				AlarmaList.mostrarMensajePront(mensajeCancelarExportar, false, prontInfo);
				// Detener el hilo de la tarea
				excelThread.interrupt();
			});
		}

		// Manejar la cancelación
		botonCancelarSubida.setOnAction(ev -> {
			botonCancelarSubida.setVisible(false);

			crearExcelTask.cancel(true);
			excelThread.interrupt();
		});
		excelThread.setDaemon(true); // Establecer como daemon
		// Iniciar la tarea principal de creación de Excel en un hilo separado
		excelThread.start();
	}

	public void guardarDatosCSV() {
		enviarReferencias();
		String frase = "Fichero CSV";
		String formatoFichero = "*.csv";

		File fichero = Utilidades.tratarFichero(frase, formatoFichero, false);

		// Verificar si se obtuvo un objeto FileChooser válido
		if (fichero != null) {

			String mensajeValido = "Has importado correctamente la lista de comics en la base de datos";

			Task<Boolean> lecturaTask = FuncionesExcel.procesarArchivoCSVTask(fichero);

			lecturaTask.setOnSucceeded(e -> {
				cargarDatosDataBase();
				AlarmaList.detenerAnimacion();
				AlarmaList.detenerAnimacionCarga(progresoCarga);
				botonCancelarSubida.setVisible(false);
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.mostrarMensajePront(mensajeValido, true, prontInfo);
			});

			lecturaTask.setOnRunning(e -> {
				estadoStage().setOnCloseRequest(event -> {
					lecturaTask.cancel(true);
					Utilidades.cerrarCargaCartas();
				});
				cerradoPorOperaciones();
				FuncionesManejoFront.cambiarEstadoMenuBar(true, guardarReferencia());
				botonCancelarSubida.setVisible(true);
				AlarmaList.iniciarAnimacionCarga(progresoCarga);
				limpiezaDeDatos();
			});

			lecturaTask.setOnFailed(e -> {
				botonCancelarSubida.setVisible(false);
				procesarResultadoImportacion(lecturaTask.getValue());
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.detenerAnimacionCarga(progresoCarga);
			});

			// Manejar la cancelación
			botonCancelarSubida.setOnAction(ev -> {
				lecturaTask.cancel(true); // true indica que la tarea debe ser interrumpida si ya está en ejecución
				botonCancelarSubida.setVisible(false);
				FuncionesManejoFront.cambiarEstadoMenuBar(false, guardarReferencia());
				AlarmaList.detenerAnimacionCarga(progresoCarga);

				procesarResultadoImportacion(false);
			});

			// Iniciar la tarea principal de importación en un hilo separado
			Thread hiloImportacion = new Thread(lecturaTask);
			hiloImportacion.setDaemon(true); // Marcar el hilo como demonio
			hiloImportacion.start();
		}
	}

	private void procesarResultadoImportacion(Boolean resultado) {
		String mensaje = "";
		prontInfo.clear();
		if (Boolean.TRUE.equals(resultado)) {
			mensaje = "Operacion realizada con exito";
		} else {
			mensaje = "ERROR. No se ha podido completar la operacion";
		}

		AlarmaList.detenerAnimacion();
		AlarmaList.mostrarMensajePront(mensaje, resultado, prontInfo);
	}

	/**
	 * Realiza la limpieza de datos en la interfaz gráfica.
	 */
	private void limpiezaDeDatos() {
		enviarReferencias();
		prontInfo.clear();
		prontInfo.setText(null);
		prontInfo.setOpacity(0);
		tablaBBDD.getItems().clear();
		tablaBBDD.refresh();
		imagenCarta.setImage(null);
		imagenCarta.setOpacity(0);

		modificarEstadoTabla(349, 0.6);
	}

	private void limpiarComboBox() {
		// Iterar sobre todos los ComboBox para realizar la limpieza
		for (ComboBox<String> comboBox : AccionReferencias.getListaComboboxes()) {
			// Limpiar el campo
			comboBox.setValue("");
			comboBox.getEditor().setText("");
		}

	}

	/**
	 * Maneja la acción del usuario en relación a los cómics, como agregar,
	 * modificar, eliminar o puntuar un cómic.
	 *
	 * @param event El evento de acción que desencadenó la llamada a esta función.
	 */
	@FXML
	void accionCarta(ActionEvent event) {
		Object fuente = event.getSource();
		tablaBBDD.getItems().clear();

		// Pasar la lista de ComboBoxes a VentanaAccionController
		AccionReferencias.setListaComboboxes(AccionReferencias.getListaComboboxes());

		if (fuente instanceof Button botonPresionado) {
			if (botonPresionado == botonIntroducir) {
				AccionFuncionesComunes.setTipoAccion("aniadir");
			} else if (botonPresionado == botonModificar) {
				AccionFuncionesComunes.setTipoAccion("modificar");
			}
		} else if (fuente instanceof MenuItem menuItemPresionado) {
			if (menuItemPresionado == menuCartaAniadir) {
				AccionFuncionesComunes.setTipoAccion("aniadir");
			} else if (menuItemPresionado == menuCartaModificar) {
				AccionFuncionesComunes.setTipoAccion("modificar");
			}
		}
		modificarEstadoTabla(259, 0.6);
		imagenCarta.setVisible(false);
		imagenCarta.setImage(null);
		prontInfo.setOpacity(0);
		nav.verAccionCarta();
	}

	/////////////////////////////
	//// FUNCIONES PARA SALIR////
	/////////////////////////////

	public Scene miStageVentana() {
		Node rootNode = menuNavegacion;
		while (rootNode.getParent() != null) {
			rootNode = rootNode.getParent();
		}

		if (rootNode instanceof Parent parent) {
			Scene scene = parent.getScene();
			ConectManager.activeScenes.add(scene);
			return scene;
		} else {
			return null;
		}
	}

	/**
	 * Vuelve al menu inicial de conexion de la base de datos.
	 *
	 * @param event
	 */
	@FXML
	void volverMenu(ActionEvent event) {

		if (FuncionesManejoFront.getStageVentanas().contains(estadoStage())) {
			FuncionesManejoFront.getStageVentanas().remove(estadoStage());
		}

		List<Stage> stageVentanas = FuncionesManejoFront.getStageVentanas();

		// Assuming `stages` is a collection of stages you want to check against
		for (Stage stage : stageVentanas) {
			stage.close(); // Close the stage if it's not the current state
		}

		ConectManager.close();
		nav.cerrarCargaCartas();
		nav.verAccesoBBDD();
		estadoStage().close();

	}

	/**
	 * Maneja la acción de salida del programa.
	 *
	 * @param event el evento que desencadena la acción
	 */
	@FXML
	public void salirPrograma(ActionEvent event) {
		// Lógica para manejar la acción de "Salir"
		nav.cerrarCargaCartas();
		if (nav.salirPrograma(event)) {
			estadoStage().close();
		}
	}

	public void cerradoPorOperaciones() {
		List<Stage> stageVentanas = FuncionesManejoFront.getStageVentanas();

		for (Stage stage : stageVentanas) {

			if (!stage.getTitle().equalsIgnoreCase("Menu principal")) {
				stage.close();
			}
		}

		if (FuncionesManejoFront.getStageVentanas().contains(estadoStage())) {
			FuncionesManejoFront.getStageVentanas().remove(estadoStage());
		}
	}

	public Stage estadoStage() {

		return (Stage) botonLimpiar.getScene().getWindow();
	}

	/**
	 * Al cerrar la ventana, carga la ventana del menu principal
	 *
	 */
	public void closeWindows() {
		nav.cerrarCargaCartas();
		Platform.exit();
	}

	public void stop() {

		cerradoPorOperaciones();
		alarmaList.detenerThreadChecker();
		nav.cerrarMenuOpciones();
		nav.cerrarCargaCartas();
		nav.cerrarVentanaAccion();
		Utilidades.cerrarOpcionesAvanzadas();

		Platform.exit();
	}
}
