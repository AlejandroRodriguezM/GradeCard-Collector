
package Controladores;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;

import alarmas.AlarmaList;
import cartaManagement.CartaGradeo;
import dbmanager.ConectManager;
import dbmanager.DBUtilidades;
import dbmanager.ListasCartasDAO;
import funcionesAuxiliares.Utilidades;
import funcionesAuxiliares.Ventanas;
import funcionesInterfaz.AccionControlUI;
import funcionesInterfaz.FuncionesComboBox;
import funcionesInterfaz.FuncionesManejoFront;
import funcionesInterfaz.FuncionesTableView;
import funcionesManagment.AccionAniadir;
import funcionesManagment.AccionEliminar;
import funcionesManagment.AccionFuncionesComunes;
import funcionesManagment.AccionModificar;
import funcionesManagment.AccionReferencias;
import funcionesManagment.AccionSeleccionar;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import webScrap.WebScrapACE;
import webScrap.WebScrapCGC;
import webScrap.WebScrapCGG;
import webScrap.WebScrapNodeJSInstall;
import webScrap.WebScrapOG;
import webScrap.WebScrapPSA;

/**
 * Clase controladora para la ventana de acciones, que gestiona la interfaz de
 * usuario y las operaciones relacionadas con los cómics.
 */
public class VentanaAccionController implements Initializable {
	// Labels
	@FXML
	private Label alarmaConexionInternet;
	@FXML
	private Label alarmaConexionSql;
	@FXML
	private Label labelId;
	@FXML
	private Label labelNombre;
	@FXML
	private Label labelPortada;
	@FXML
	private Label labelGradeo;
	@FXML
	private Label labelReferencia;
	@FXML
	private Label labelcoleccion;
	@FXML
	private Label labelEdicion;
	@FXML
	private Label labelAnio;
	@FXML
	private Label labelCodigo;
	@FXML
	private Label labelEmpresa;

	// Buttons
	@FXML
	private Button botonBusquedaAvanzada;
	@FXML
	private Button botonBusquedaCodigo;
	@FXML
	private Button botonCancelarSubida;
	@FXML
	private Button botonClonarCarta;
	@FXML
	private Button botonEliminar;
	@FXML
	private Button botonEliminarImportadoCarta;
	@FXML
	private Button botonEliminarImportadoListaCarta;
	@FXML
	private Button botonGuardarCambioCarta;
	@FXML
	private Button botonGuardarCarta;
	@FXML
	private Button botonGuardarListaCartas;
	@FXML
	private Button botonLimpiar;
	@FXML
	private Button botonModificarCarta;
	@FXML
	private Button botonParametroCarta;
	@FXML
	private Button botonSubidaPortada;
	@FXML
	private Button botonbbdd;

	// TextFields
	@FXML
	private TextField busquedaCodigo;
	@FXML
	private TextField textFieldColeccionCarta;
	@FXML
	private TextField textFieldDireccionCarta;
	@FXML
	private TextField textFieldEdicionCarta;
	@FXML
	private TextField textFieldGradeoCarta;
	@FXML
	private TextField textFieldIdCarta;
	@FXML
	private TextField textFieldNombreCarta;
	@FXML
	private TextField textFieldNumeroCarta;
	@FXML
	private TextField textFieldUrlCarta;
	@FXML
	private TextField textFieldEmpresaCarta;
	@FXML
	private TextField textFieldCodigoCarta;
	@FXML
	private TextField textFieldAnioCarta;

	// ImageViews
	@FXML
	private ImageView cargaImagen;
	@FXML
	private ImageView imagencarta;

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
	private TableColumn<CartaGradeo, String> columnaNombre;
	@FXML
	private TableColumn<CartaGradeo, String> columnaNumero;
	@FXML
	private TableColumn<CartaGradeo, String> id;

	// ComboBoxes
	@FXML
	private ComboBox<String> comboBoxTienda;

	// MenuItems
	@FXML
	private MenuItem menuImportarFichero;
	@FXML
	private MenuItem navegacionMostrarEstadistica;

	// Menus
	@FXML
	private Menu navegacionEstadistica;
	@FXML
	private Menu navegacionOpciones;

	// MenuBars
	@FXML
	private MenuBar menuNavegacion;

	// ProgressIndicators
	@FXML
	private ProgressIndicator progresoCarga;

	// TextAreas
	@FXML
	private TextArea prontInfo;

	// VBoxes
	@FXML
	private VBox rootVBox;
	@FXML
	private VBox vboxImage;

	// TableViews
	@FXML
	private TableView<CartaGradeo> tablaBBDD;

	/**
	 * Referencia a la ventana (stage).
	 */
	private Stage stage;

	public CartaGradeo cartaCache;

	/**
	 * Instancia de la clase Ventanas para la navegación.
	 */
	private static Ventanas nav = new Ventanas();

	public static final AlarmaList alarmaList = new AlarmaList();

	public static final AccionReferencias referenciaVentana = new AccionReferencias();

	public static AccionReferencias referenciaVentanaPrincipal = new AccionReferencias();

	private static final Logger logger = Logger.getLogger(Utilidades.class.getName());

	public AccionReferencias guardarReferencia() {

		referenciaVentana.setNombreCartaTextField(textFieldNombreCarta);
		referenciaVentana.setCodigoCartaTextField(textFieldCodigoCarta);
		referenciaVentana.setAnioCartaTextField(textFieldAnioCarta);
		referenciaVentana.setNumeroCartaTextField(textFieldNumeroCarta);
		referenciaVentana.setEdicionCartaTextField(textFieldEdicionCarta);
		referenciaVentana.setColeccionCartaTextField(textFieldColeccionCarta);
		referenciaVentana.setGradeoCartaTextField(textFieldGradeoCarta);
		referenciaVentana.setUrlReferenciaTextField(textFieldUrlCarta);
		referenciaVentana.setDireccionImagenTextField(textFieldDireccionCarta);
		referenciaVentana.setNombreTiendaCombobox(comboBoxTienda);
		referenciaVentana.setNombreEmpresaTextField(textFieldEmpresaCarta);
		referenciaVentana.setIdCartaTratarTextField(textFieldIdCarta);
		referenciaVentana.setBusquedaCodigoTextField(busquedaCodigo);

		referenciaVentana.setBotonClonarCarta(botonClonarCarta);
		referenciaVentana.setBotonCancelarSubida(botonCancelarSubida);
		referenciaVentana.setBotonBusquedaCodigo(botonBusquedaCodigo);
		referenciaVentana.setBotonBusquedaAvanzada(botonBusquedaAvanzada);
		referenciaVentana.setBotonEliminar(botonEliminar);
		referenciaVentana.setBotonLimpiar(botonLimpiar);
		referenciaVentana.setBotonModificarCarta(botonModificarCarta);
		referenciaVentana.setBotonParametroCarta(botonParametroCarta);
		referenciaVentana.setBotonbbdd(botonbbdd);
		referenciaVentana.setBotonGuardarCambioCarta(botonGuardarCambioCarta);

		referenciaVentana.setBotonGuardarCarta(botonGuardarCarta);
		referenciaVentana.setBotonEliminarImportadoCarta(botonEliminarImportadoCarta);

		referenciaVentana.setBotonEliminarImportadoListaCarta(botonEliminarImportadoListaCarta);
		referenciaVentana.setBotonGuardarListaCartas(botonGuardarListaCartas);

		referenciaVentana.setBotonSubidaPortada(botonSubidaPortada);
		referenciaVentana.setBusquedaCodigoTextField(busquedaCodigo);
		referenciaVentana.setStageVentana(estadoStage());
		referenciaVentana.setProgresoCarga(progresoCarga);

		referenciaVentana.setLabelColeccion(labelcoleccion);
		referenciaVentana.setLabelNombre(labelNombre);
		referenciaVentana.setLabelCodigo(labelCodigo);
		referenciaVentana.setLabelEmpresa(labelEmpresa);

		referenciaVentana.setLabelIdMod(labelId);
		referenciaVentana.setLabelAnio(labelAnio);
		referenciaVentana.setLabelEdicion(labelEdicion);
		referenciaVentana.setLabelColeccion(labelcoleccion);
		referenciaVentana.setLabelGradeo(labelGradeo);
		referenciaVentana.setLabelReferencia(labelReferencia);
		referenciaVentana.setLabelPortada(labelPortada);

		referenciaVentana.setAlarmaConexionInternet(alarmaConexionInternet);
		referenciaVentana.setAlarmaConexionSql(alarmaConexionSql);

		referenciaVentana.setTablaBBDD(tablaBBDD);
		referenciaVentana.setImagenCarta(imagencarta);
		referenciaVentana.setCargaImagen(cargaImagen);
		referenciaVentana.setProntInfoTextArea(prontInfo);
		referenciaVentana.setRootVBox(rootVBox);
		referenciaVentana.setMenuImportarFicheroCodigoBarras(menuImportarFichero);
		referenciaVentana.setMenuEstadisticaEstadistica(navegacionMostrarEstadistica);
		referenciaVentana.setMenuNavegacion(menuNavegacion);
		referenciaVentana.setNavegacionCerrar(navegacionOpciones);
		referenciaVentana.setNavegacionEstadistica(navegacionEstadistica);

		AccionReferencias.setListaTextFields(FXCollections
				.observableArrayList(Arrays.asList(textFieldNombreCarta, textFieldEdicionCarta, textFieldColeccionCarta,
						textFieldGradeoCarta, textFieldIdCarta, textFieldDireccionCarta, textFieldUrlCarta)));

		referenciaVentana.setControlAccion(Arrays.asList(textFieldNombreCarta, textFieldNumeroCarta,
				textFieldEdicionCarta, textFieldColeccionCarta, textFieldAnioCarta, textFieldUrlCarta,
				textFieldEmpresaCarta, textFieldGradeoCarta, textFieldCodigoCarta, textFieldDireccionCarta,
				textFieldIdCarta, busquedaCodigo));

		AccionReferencias.setListaColumnasTabla(Arrays.asList(columnaNombre, columnaNumero, columnaCertificacion,
				columnaEmpresa, columnaEdicion, columnaColeccion));

		return referenciaVentana;
	}

	public void enviarReferencias() {
		AccionControlUI.setReferenciaVentana(guardarReferencia());
		AccionFuncionesComunes.setReferenciaVentana(guardarReferencia());
		AccionFuncionesComunes.setReferenciaVentanaPrincipal(referenciaVentanaPrincipal);
		FuncionesTableView.setReferenciaVentana(guardarReferencia());
		FuncionesManejoFront.setReferenciaVentana(guardarReferencia());

		AccionSeleccionar.setReferenciaVentana(guardarReferencia());
		AccionAniadir.setReferenciaVentana(guardarReferencia());
		AccionEliminar.setReferenciaVentana(guardarReferencia());
		AccionModificar.setReferenciaVentana(guardarReferencia());
		Utilidades.setReferenciaVentana(guardarReferencia());
		Ventanas.setReferenciaVentana(guardarReferencia());
		DBUtilidades.setReferenciaVentana(guardarReferencia());
	}

	/**
	 * Inicializa la interfaz de usuario y configura el comportamiento de los
	 * elementos al cargar la vista.
	 *
	 * @param location  La ubicación relativa del archivo FXML.
	 * @param resources Los recursos que pueden ser utilizados por el controlador.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuImportarFichero.setGraphic(Utilidades.createIcon("/Icono/Archivo/importar.png", 16, 16));

		navegacionMostrarEstadistica.setGraphic(Utilidades.createIcon("/Icono/Estadistica/descarga.png", 16, 16));

		alarmaList.setAlarmaConexionSql(alarmaConexionSql);
		alarmaList.setAlarmaConexionInternet(alarmaConexionInternet);
		alarmaList.iniciarThreadChecker();

		Platform.runLater(() -> {

			enviarReferencias();

			AccionControlUI.controlarEventosInterfazAccion();

			AccionControlUI.autocompletarListas();

			rellenarCombosEstaticos();

			AccionControlUI.mostrarOpcion(AccionFuncionesComunes.getTipoAccion());

			FuncionesManejoFront.getStageVentanas().add(estadoStage());

			estadoStage().setOnCloseRequest(event -> stop());
			AccionSeleccionar.actualizarRefrenciaClick(guardarReferencia());
		});

		ListasCartasDAO.cartasImportados.clear();

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

	/**
	 * Rellena los combos estáticos en la interfaz. Esta función llena los
	 * ComboBoxes con opciones estáticas predefinidas.
	 */
	public void rellenarCombosEstaticos() {
		List<ComboBox<String>> listaComboboxes = new ArrayList<>();
		listaComboboxes.add(comboBoxTienda);
		FuncionesComboBox.rellenarComboBoxEstaticos(listaComboboxes);
	}

	public void formatearTextField() {
		FuncionesManejoFront.eliminarEspacioInicialYFinal(textFieldNombreCarta);
		FuncionesManejoFront.eliminarSimbolosEspeciales(textFieldNombreCarta);
		FuncionesManejoFront.restringirSimbolos(textFieldEdicionCarta);
		FuncionesManejoFront.restringirSimbolos(textFieldColeccionCarta);
		FuncionesManejoFront.restringirSimbolos(textFieldGradeoCarta);

		FuncionesManejoFront.reemplazarEspaciosMultiples(textFieldNombreCarta);
		FuncionesManejoFront.reemplazarEspaciosMultiples(textFieldEdicionCarta);
		FuncionesManejoFront.reemplazarEspaciosMultiples(textFieldColeccionCarta);
		FuncionesManejoFront.reemplazarEspaciosMultiples(textFieldGradeoCarta);
		FuncionesManejoFront.reemplazarEspaciosMultiples(textFieldNumeroCarta);

		FuncionesManejoFront.permitirUnSimbolo(textFieldNombreCarta);
		FuncionesManejoFront.permitirUnSimbolo(textFieldEdicionCarta);
		FuncionesManejoFront.permitirUnSimbolo(textFieldColeccionCarta);
		FuncionesManejoFront.permitirUnSimbolo(textFieldGradeoCarta);
		FuncionesManejoFront.permitirUnSimbolo(busquedaCodigo);
		textFieldIdCarta.setTextFormatter(FuncionesComboBox.validadorNenteros());

		if (AccionFuncionesComunes.TIPO_ACCION.equalsIgnoreCase("aniadir")) {
			textFieldIdCarta.setTextFormatter(FuncionesComboBox.desactivarValidadorNenteros());
		}
	}

	/**
	 * Metodo que mostrara los cartas o comic buscados por parametro
	 *
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	void mostrarPorParametro(ActionEvent event) {
		enviarReferencias();

		List<String> controls = new ArrayList<>();

		// Iterar sobre los TextField y ComboBox en referenciaVentana
		for (Control control : AccionReferencias.getListaTextFields()) {
			if (control instanceof TextField) {
				controls.add(((TextField) control).getText());
			} else if (control instanceof ComboBox<?>) {
				Object selectedItem = ((ComboBox<?>) control).getSelectionModel().getSelectedItem();
				controls.add(selectedItem != null ? selectedItem.toString() : "");
			}
		}

		// Añadir valores de los ComboBoxes de getListaComboboxes() a controls
		for (ComboBox<?> comboBox : AccionReferencias.getListaComboboxes()) {
			Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
			controls.add(selectedItem != null ? selectedItem.toString() : "");
		}

		// Crear y procesar la Carta con los controles recogidos
		CartaGradeo comic = AccionControlUI.camposCarta(controls, true);
		AccionSeleccionar.verBasedeDatos(false, true, comic);
	}

	/**
	 * Metodo que muestra toda la base de datos.
	 *
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	void verTodabbdd(ActionEvent event) throws IOException, SQLException {
		enviarReferencias();
		AccionControlUI.limpiarAutorellenos(false);
		AccionSeleccionar.verBasedeDatos(true, true, null);
	}

	/**
	 * Método que maneja el evento de guardar los datos de un cómic.
	 * 
	 * @param event El evento de acción que desencadena la llamada al método.
	 */
	@FXML
	void guardarDatos(ActionEvent event) {
		enviarReferencias();
		rellenarCombosEstaticos();
		nav.cerrarMenuOpciones();
		AccionModificar.actualizarCartaLista();
		imagencarta.setImage(null);
		ImagenAmpliadaController.setCartaCache(null);
		ocultarBotonesCartas();
	}

	/**
	 * Método que maneja el evento de guardar la lista de cómics importados.
	 * 
	 * @param event El evento de acción que desencadena la llamada al método.
	 * @throws IOException        Si ocurre un error de entrada/salida.
	 * @throws SQLException       Si ocurre un error de base de datos.
	 * @throws URISyntaxException
	 */
	@FXML
	void guardarCartaImportados(ActionEvent event) throws IOException, SQLException {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionAniadir.guardarContenidoLista(false, ImagenAmpliadaController.getCartaCache());
		rellenarCombosEstaticos();
		imagencarta.setImage(null);
		ImagenAmpliadaController.setCartaCache(null);
		ocultarBotonesCartas();
	}

	/**
	 * Método que maneja el evento de guardar la lista de cómics importados.
	 * 
	 * @param event El evento de acción que desencadena la llamada al método.
	 * @throws IOException        Si ocurre un error de entrada/salida.
	 * @throws SQLException       Si ocurre un error de base de datos.
	 * @throws URISyntaxException
	 */
	@FXML
	void guardarListaImportados(ActionEvent event) throws IOException, SQLException {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionAniadir.guardarContenidoLista(true, null);
		rellenarCombosEstaticos();
		imagencarta.setImage(null);
		ImagenAmpliadaController.setCartaCache(null);
		ocultarBotonesCartas();
	}

	/**
	 * Llamada a funcion que modifica los datos de 1 comic en la base de datos.
	 *
	 * @param event
	 * @throws Exception
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@FXML
	void modificarDatos(ActionEvent event) throws Exception {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionModificar.modificarCarta();
		rellenarCombosEstaticos();
		imagencarta.setImage(null);
		ImagenAmpliadaController.setCartaCache(null);
	}

	@FXML
	void clonarCartaSeleccionada(ActionEvent event) {

		int num = Ventanas.verVentanaNumero();
		CartaGradeo carta = guardarReferencia().getTablaBBDD().getSelectionModel().getSelectedItem();
		ImagenAmpliadaController.setCartaCache(carta);

		for (int i = 0; i < num; i++) {
			CartaGradeo cartaModificada = AccionFuncionesComunes.copiarCartaClon(carta);
			AccionFuncionesComunes.procesarCartaPorCodigo(cartaModificada, true);
		}
	}

	@FXML
	void eliminarCartaSeleccionado(ActionEvent event) {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionModificar.eliminarCartaLista();
		rellenarCombosEstaticos();
		imagencarta.setImage(null);
		ImagenAmpliadaController.setCartaCache(null);
		ocultarBotonesCartas();
	}

	@FXML
	void eliminarListaCartas(ActionEvent event) {
		enviarReferencias();
		nav.cerrarMenuOpciones();

		if (!ListasCartasDAO.cartasImportados.isEmpty() && nav.alertaBorradoLista()) {
			// Ocultar botones relacionados con cartas
			ocultarBotonesCartas();

			// Eliminar cada carta de la lista
			for (CartaGradeo carta : ListasCartasDAO.cartasImportados) {
				// Eliminar archivo de imagen asociado a la carta
				eliminarArchivoImagen(carta.getDireccionImagenCarta());
			}

			// Limpiar la lista de cartas y la tabla de la interfaz
			ListasCartasDAO.cartasImportados.clear();
			guardarReferencia().getTablaBBDD().getItems().clear();

			// Reiniciar la imagen de la carta y limpiar la ventana
			imagencarta.setImage(null);
			limpiarVentana();
		}

		// Rellenar combos estáticos después de la operación
		rellenarCombosEstaticos();
	}

	// Función para eliminar archivo de imagen
	private void eliminarArchivoImagen(String direccionImagen) {
		if (direccionImagen != null && !direccionImagen.isEmpty()) {
			File archivoImagen = new File(direccionImagen);
			if (archivoImagen.exists()) {
				// Intentar borrar el archivo de la imagen
				if (archivoImagen.delete()) {
					System.out.println("Archivo de imagen eliminado: " + direccionImagen);
				} else {
					System.err.println("No se pudo eliminar el archivo de imagen: " + direccionImagen);
					// Puedes lanzar una excepción aquí si lo prefieres
				}
			}
		}
	}

	// Función para ocultar botones relacionados con cartas
	private void ocultarBotonesCartas() {
		guardarReferencia().getBotonClonarCarta().setVisible(false);
		guardarReferencia().getBotonGuardarCarta().setVisible(false);
		guardarReferencia().getBotonEliminarImportadoCarta().setVisible(false);
	}

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
			AccionSeleccionar.seleccionarCartas(false);
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
			AccionSeleccionar.seleccionarCartas(false);
		}

	}

	/**
	 * Maneja la acción de búsqueda avanzada, verifica las claves API de Marvel y
	 * Carta Vine.
	 *
	 * @param event El evento de acción.
	 */
	@FXML
	void busquedaAvanzada(ActionEvent event) {
		enviarReferencias();

		// Verificar si las claves API están ausentes o vacías
		if (!Utilidades.isInternetAvailable()) {
			nav.alertaException("Revisa las APIS de Marvel y Vine, estan incorrectas o no funcionan");
		} else {
			// Continuar con la lógica cuando ambas claves están presente
			AccionFuncionesComunes.cambiarVisibilidadAvanzada();

		}
	}

	/**
	 * Método asociado al evento de acción que se dispara al seleccionar la opción
	 * "Importar Fichero Código de Barras". Este método aún no tiene implementación.
	 *
	 * @param evento Objeto que representa el evento de acción.
	 */
	@FXML
	void importarFicheroCodigoBarras(ActionEvent evento) {
		enviarReferencias();
		if (Utilidades.isInternetAvailable()) {
			nav.cerrarMenuOpciones();
			AccionControlUI.limpiarAutorellenos(false);
			AccionControlUI.borrarDatosGraficos();
			String frase = "Fichero txt";

			String formato = "*.txt";
			File fichero = Utilidades.tratarFichero(frase, formato, false);

			// Verificar si se obtuvo un objeto FileChooser válido
			if (fichero != null) {
				enviarReferencias();
				rellenarCombosEstaticos();
				if (WebScrapNodeJSInstall.checkNodeJSVersion()) {
					AccionFuncionesComunes.busquedaPorCodigoImportacion(fichero, "");
				}

			}
		}

	}

	/**
	 * Realiza una búsqueda por código y muestra información del cómic
	 * correspondiente en la interfaz gráfica.
	 *
	 * @param event El evento que desencadena la acción.
	 * @throws IOException        Si ocurre un error de entrada/salida.
	 * @throws JSONException      Si ocurre un error al procesar datos JSON.
	 * @throws URISyntaxException Si ocurre un error de sintaxis de URI.
	 */
	@FXML
	public void busquedaPorCodigo(ActionEvent event) throws IOException, URISyntaxException {
		enviarReferencias();
		if (Utilidades.isInternetAvailable()) {
			String valorCodigo = busquedaCodigo.getText();
			String tipoTienda = comboBoxTienda.getValue();
			if (valorCodigo.isEmpty() || tipoTienda.isEmpty()) {
				return;
			}
			nav.cerrarMenuOpciones();
			AccionControlUI.borrarDatosGraficos();

			AccionFuncionesComunes.cargarRuning();

			// Aquí se asigna el CompletableFuture, este es un ejemplo de cómo podría ser
			// asignado:
			CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> {
				// Lógica para obtener los enlaces, esto es solo un ejemplo
				return obtenerEnlaces(valorCodigo);
			});

			// Si el future es null, entonces es necesario inicializarlo apropiadamente
			// antes de usarlo
			if (future != null) {
				future.thenAccept(enlaces -> {

					File fichero;
					try {
						fichero = createTempFile(enlaces);

						if (fichero != null) {
							enviarReferencias();
							rellenarCombosEstaticos();
							if (WebScrapNodeJSInstall.checkNodeJSVersion()) {
								AccionFuncionesComunes.busquedaPorCodigoImportacion(fichero,tipoTienda);
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				future.exceptionally(ex -> {
					ex.printStackTrace();
					return null; // Manejar errores aquí según sea necesario
				});
			}
		}
	}

	// Ejemplo de método para obtener los enlaces
	private List<String> obtenerEnlaces(String valorCodigo) {

		List<String> codigoGradeo = new ArrayList<>();
		codigoGradeo.add(valorCodigo);

		return codigoGradeo;
	}

	public File createTempFile(List<String> data) throws IOException {

		String tempDirectory = System.getProperty("java.io.tmpdir");

		// Create a temporary file in the system temporary directory
		Path tempFilePath = Files.createTempFile(Paths.get(tempDirectory), "tempFile", ".txt");
		logger.log(Level.INFO, "Temporary file created at: " + tempFilePath.toString());

		// Write data to the temporary file
		Files.write(tempFilePath, data, StandardOpenOption.WRITE);

		// Convert the Path to a File and return it
		return tempFilePath.toFile();
	}

	public void deleteFile(Path filePath) throws IOException {
		Files.delete(filePath);
	}

	/**
	 * Limpia los datos de la pantalla al hacer clic en el botón "Limpiar".
	 */
	@FXML
	void limpiarDatos(ActionEvent event) {
		limpiarVentana();
	}

	public void limpiarVentana() {
		enviarReferencias();
		AccionFuncionesComunes.limpiarDatosPantallaAccion();
		rellenarCombosEstaticos();
		ImagenAmpliadaController.setCartaCache(null);
	}

	/**
	 * Funcion que permite la subida de una
	 *
	 * @param event
	 */
	@FXML
	void nuevaPortada(ActionEvent event) {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionFuncionesComunes.subirPortada();
	}

	/**
	 * Funcion que elimina un comic de la base de datos.
	 *
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@FXML
	void eliminarDatos(ActionEvent event) {
		enviarReferencias();
		nav.cerrarMenuOpciones();
		AccionModificar.eliminarCarta();
		rellenarCombosEstaticos();

	}

	/**
	 * Establece la instancia de la ventana (Stage) asociada a este controlador.
	 *
	 * @param stage La instancia de la ventana (Stage) que se asocia con este
	 *              controlador.
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene miStageVentana() {
		Node rootNode = botonLimpiar;
		while (rootNode.getParent() != null) {
			rootNode = rootNode.getParent();
		}

		if (rootNode instanceof Parent) {
			Scene scene = ((Parent) rootNode).getScene();
			ConectManager.activeScenes.add(scene);
			return scene;
		} else {
			// Manejar el caso en el que no se pueda encontrar un nodo raíz adecuado
			return null;
		}
	}

	public Stage estadoStage() {

		return (Stage) botonLimpiar.getScene().getWindow();
	}

	/**
	 * Cierra la ventana asociada a este controlador, si está disponible. Si no se
	 * ha establecido una instancia de ventana (Stage), este método no realiza
	 * ninguna acción.
	 */
	public void closeWindow() {

		stage = estadoStage();
		ImagenAmpliadaController.setCartaCache(null);
		if (stage != null) {

			if (FuncionesManejoFront.getStageVentanas().contains(estadoStage())) {
				FuncionesManejoFront.getStageVentanas().remove(estadoStage());
			}
			nav.cerrarCargaCartas();
			stage.close();
		}
	}

	public void stop() {

		if (FuncionesManejoFront.getStageVentanas().contains(estadoStage())) {
			FuncionesManejoFront.getStageVentanas().remove(estadoStage());
		}

		Utilidades.cerrarCargaCartas();
	}

	public static void setReferenciaVentana(AccionReferencias referenciaVentana) {
		VentanaAccionController.referenciaVentanaPrincipal = referenciaVentana;
	}
}
