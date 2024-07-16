package funcionesManagment;

import java.util.List;

import cartaManagement.CartaGradeo;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AccionReferencias {

	private TableColumn<CartaGradeo, String> iDColumna;
	private TableColumn<CartaGradeo, String> nombreColumna;
	private TableColumn<CartaGradeo, String> numeroColumna;
	private TableColumn<CartaGradeo, String> edicionColumna;
	private TableColumn<CartaGradeo, String> coleccionColumna;
	private TableColumn<CartaGradeo, String> certificacionColumna;
	private TableColumn<CartaGradeo, String> empresaColumna;
	private TableColumn<CartaGradeo, String> referenciaColumna;

	public TableView<CartaGradeo> tablaBBDD;

	private VBox rootVBox;
	private VBox vboxContenido;
	private VBox vboxImage;

	private AnchorPane rootAnchorPane;
	private AnchorPane anchoPaneInfo;

	private ImageView backgroundImage;
	private ImageView imagenCarta;
	private ImageView cargaImagen;

	private Button botonClonarCarta;
	private Button botonModificar;
	private Button botonIntroducir;
	private Button botonEliminar;
	private Button botonComprimirPortadas;
	private Button botonReCopiarPortadas;
	private Button botonCancelarSubida;
	private Button botonBusquedaCodigo;
	private Button botonBusquedaAvanzada;
	private Button botonLimpiar;
	private Button botonModificarCarta;
	private Button botonParametroCarta;
	private Button botonVender;
	private Button botonbbdd;
	private Button botonGuardarCarta;
	private Button botonGuardarCambioCarta;
	private Button botonEliminarImportadoCarta;
	private Button botonSubidaPortada;
	private Button botonMostrarParametro;
	private Button botonActualizarDatos;
	private Button botonActualizarPortadas;
	private Button botonActualizarSoftware;
	private Button botonActualizarTodo;

	private Button botonDescargarPdf;
	private Button botonDescargarSQL;
	private Button botonGuardarListaCartas;
	private Button botonEliminarImportadoListaCarta;
	private Button botonActualizarPrecio;

	private Rectangle barraCambioAltura;

	private Label alarmaConexionInternet;
	private Label labelEdicion;
	private Label labelColeccion;
	private Label labelNombre;
	private Label labelIdMod;
	private Label labelPortada;
	private Label labelGradeo;
	private Label labelReferencia;
	private Label prontInfoLabel;
	private Label alarmaConexionSql;
	private Label labelComprobar;
	private Label labelVersion;
	private Label labelEmpresa;
	private Label prontInfoEspecial;
	private Label prontInfoPreviews;
	private Label prontInfoPortadas;
	private Label labelAnio;
	private Label labelCodigo;

	private TextField busquedaCodigoTextField;
	private TextField nombreCartaTextField;
	private TextField codigoCartaTextField;
	private TextField anioCartaTextField;
	private TextField nombreEmpresaTextField;
	private TextField edicionCartaTextField;
	private TextField coleccionCartaTextField;
	private TextField gradeoCartaTextField;
	private TextField idCartaTratarTextField;
	private TextField direccionImagenTextField;
	private TextField urlReferenciaTextField;
	private TextField numeroCartaTextField;

	private TextField codigoCartaTratarTextField;
	private TextField busquedaGeneralTextField;

	private ComboBox<String> nombreCartaCombobox;
	private ComboBox<String> numeroCartaCombobox;
	private ComboBox<String> nombreTiendaCombobox;
	private ComboBox<String> nombreEdicionCombobox;
	private ComboBox<String> nombreColeccionCombobox;
	private ComboBox<String> nombreGradeoCombobox;
	private ComboBox<String> nombreEmpresaCombobox;
	private ComboBox<String> comboPreviewsCombobox;

	private TextArea prontInfoTextArea;

	private MenuItem menuImportarFicheroCodigoBarras;
	private MenuItem menuCartaAniadir;
	private MenuItem menuCartaEliminar;
	private MenuItem menuCartaModificar;
	private MenuItem menuEstadisticaEstadistica;
	private MenuItem menuArchivoCerrar;
	private MenuItem menuArchivoDelete;
	private MenuItem menuArchivoDesconectar;
	private MenuItem menuArchivoExcel;
	private MenuItem menuArchivoImportar;
	private MenuItem menuArchivoSobreMi;
	private MenuItem menuEstadisticaSumaTotal;
	private MenuItem menuArchivoAvanzado;

	private Menu navegacionCerrar;
	private Menu navegacionCarta;
	private Menu navegacionEstadistica;

	private MenuBar menuNavegacion;

	private ProgressIndicator progresoCarga;

	private CheckBox checkFirmas;

	private List<Control> controlAccion;

	private static List<ComboBox<String>> listaComboboxes;
	private static List<TableColumn<CartaGradeo, String>> listaColumnasTabla;
	private static ObservableList<Control> listaTextFields;
	private static ObservableList<Button> listaBotones;
	private static ObservableList<Node> listaElementosFondo;

	private Stage stageVentana;

	/**
	 * @return the iDColumna
	 */
	public TableColumn<CartaGradeo, String> getiDColumna() {
		return iDColumna;
	}

	/**
	 * @return the nombreColumna
	 */
	public TableColumn<CartaGradeo, String> getNombreColumna() {
		return nombreColumna;
	}

	/**
	 * @return the numeroColumna
	 */
	public TableColumn<CartaGradeo, String> getNumeroColumna() {
		return numeroColumna;
	}

	/**
	 * @return the edicionColumna
	 */
	public TableColumn<CartaGradeo, String> getEdicionColumna() {
		return edicionColumna;
	}

	/**
	 * @return the coleccionColumna
	 */
	public TableColumn<CartaGradeo, String> getColeccionColumna() {
		return coleccionColumna;
	}

	/**
	 * @return the certificacionColumna
	 */
	public TableColumn<CartaGradeo, String> getCertificacionColumna() {
		return certificacionColumna;
	}

	/**
	 * @return the empresaColumna
	 */
	public TableColumn<CartaGradeo, String> getEmpresaColumna() {
		return empresaColumna;
	}

	/**
	 * @return the referenciaColumna
	 */
	public TableColumn<CartaGradeo, String> getReferenciaColumna() {
		return referenciaColumna;
	}

	/**
	 * @return the tablaBBDD
	 */
	public TableView<CartaGradeo> getTablaBBDD() {
		return tablaBBDD;
	}

	/**
	 * @return the rootVBox
	 */
	public VBox getRootVBox() {
		return rootVBox;
	}

	/**
	 * @return the vboxContenido
	 */
	public VBox getVboxContenido() {
		return vboxContenido;
	}

	/**
	 * @return the vboxImage
	 */
	public VBox getVboxImage() {
		return vboxImage;
	}

	/**
	 * @return the rootAnchorPane
	 */
	public AnchorPane getRootAnchorPane() {
		return rootAnchorPane;
	}

	/**
	 * @return the anchoPaneInfo
	 */
	public AnchorPane getAnchoPaneInfo() {
		return anchoPaneInfo;
	}

	/**
	 * @return the backgroundImage
	 */
	public ImageView getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @return the imagenCarta
	 */
	public ImageView getImagenCarta() {
		return imagenCarta;
	}

	/**
	 * @return the cargaImagen
	 */
	public ImageView getCargaImagen() {
		return cargaImagen;
	}

	/**
	 * @return the botonClonarCarta
	 */
	public Button getBotonClonarCarta() {
		return botonClonarCarta;
	}

	/**
	 * @return the botonModificar
	 */
	public Button getBotonModificar() {
		return botonModificar;
	}

	/**
	 * @return the botonIntroducir
	 */
	public Button getBotonIntroducir() {
		return botonIntroducir;
	}

	/**
	 * @return the botonEliminar
	 */
	public Button getBotonEliminar() {
		return botonEliminar;
	}

	/**
	 * @return the botonComprimirPortadas
	 */
	public Button getBotonComprimirPortadas() {
		return botonComprimirPortadas;
	}

	/**
	 * @return the botonReCopiarPortadas
	 */
	public Button getBotonReCopiarPortadas() {
		return botonReCopiarPortadas;
	}

	/**
	 * @return the botonCancelarSubida
	 */
	public Button getBotonCancelarSubida() {
		return botonCancelarSubida;
	}

	/**
	 * @return the botonBusquedaCodigo
	 */
	public Button getBotonBusquedaCodigo() {
		return botonBusquedaCodigo;
	}

	/**
	 * @return the botonBusquedaAvanzada
	 */
	public Button getBotonBusquedaAvanzada() {
		return botonBusquedaAvanzada;
	}

	/**
	 * @return the botonLimpiar
	 */
	public Button getBotonLimpiar() {
		return botonLimpiar;
	}

	/**
	 * @return the botonModificarCarta
	 */
	public Button getBotonModificarCarta() {
		return botonModificarCarta;
	}

	/**
	 * @return the botonParametroCarta
	 */
	public Button getBotonParametroCarta() {
		return botonParametroCarta;
	}

	/**
	 * @return the botonVender
	 */
	public Button getBotonVender() {
		return botonVender;
	}

	/**
	 * @return the botonbbdd
	 */
	public Button getBotonbbdd() {
		return botonbbdd;
	}

	/**
	 * @return the botonGuardarCarta
	 */
	public Button getBotonGuardarCarta() {
		return botonGuardarCarta;
	}

	/**
	 * @return the botonGuardarCambioCarta
	 */
	public Button getBotonGuardarCambioCarta() {
		return botonGuardarCambioCarta;
	}

	/**
	 * @return the botonEliminarImportadoCarta
	 */
	public Button getBotonEliminarImportadoCarta() {
		return botonEliminarImportadoCarta;
	}

	/**
	 * @return the botonSubidaPortada
	 */
	public Button getBotonSubidaPortada() {
		return botonSubidaPortada;
	}

	/**
	 * @return the botonMostrarParametro
	 */
	public Button getBotonMostrarParametro() {
		return botonMostrarParametro;
	}

	/**
	 * @return the botonActualizarDatos
	 */
	public Button getBotonActualizarDatos() {
		return botonActualizarDatos;
	}

	/**
	 * @return the botonActualizarPortadas
	 */
	public Button getBotonActualizarPortadas() {
		return botonActualizarPortadas;
	}

	/**
	 * @return the botonActualizarSoftware
	 */
	public Button getBotonActualizarSoftware() {
		return botonActualizarSoftware;
	}

	/**
	 * @return the botonActualizarTodo
	 */
	public Button getBotonActualizarTodo() {
		return botonActualizarTodo;
	}

	/**
	 * @return the botonDescargarPdf
	 */
	public Button getBotonDescargarPdf() {
		return botonDescargarPdf;
	}

	/**
	 * @return the botonDescargarSQL
	 */
	public Button getBotonDescargarSQL() {
		return botonDescargarSQL;
	}

	/**
	 * @return the botonGuardarListaCartas
	 */
	public Button getBotonGuardarListaCartas() {
		return botonGuardarListaCartas;
	}

	/**
	 * @return the botonEliminarImportadoListaCarta
	 */
	public Button getBotonEliminarImportadoListaCarta() {
		return botonEliminarImportadoListaCarta;
	}

	/**
	 * @return the botonActualizarPrecio
	 */
	public Button getBotonActualizarPrecio() {
		return botonActualizarPrecio;
	}

	/**
	 * @return the barraCambioAltura
	 */
	public Rectangle getBarraCambioAltura() {
		return barraCambioAltura;
	}

	/**
	 * @return the alarmaConexionInternet
	 */
	public Label getAlarmaConexionInternet() {
		return alarmaConexionInternet;
	}

	/**
	 * @return the labelEdicion
	 */
	public Label getLabelEdicion() {
		return labelEdicion;
	}

	/**
	 * @return the labelColeccion
	 */
	public Label getLabelColeccion() {
		return labelColeccion;
	}

	/**
	 * @return the labelNombre
	 */
	public Label getLabelNombre() {
		return labelNombre;
	}

	/**
	 * @return the labelIdMod
	 */
	public Label getLabelIdMod() {
		return labelIdMod;
	}

	/**
	 * @return the labelPortada
	 */
	public Label getLabelPortada() {
		return labelPortada;
	}

	/**
	 * @return the labelGradeo
	 */
	public Label getLabelGradeo() {
		return labelGradeo;
	}

	/**
	 * @return the labelReferencia
	 */
	public Label getLabelReferencia() {
		return labelReferencia;
	}

	/**
	 * @return the prontInfoLabel
	 */
	public Label getProntInfoLabel() {
		return prontInfoLabel;
	}

	/**
	 * @return the alarmaConexionSql
	 */
	public Label getAlarmaConexionSql() {
		return alarmaConexionSql;
	}

	/**
	 * @return the labelComprobar
	 */
	public Label getLabelComprobar() {
		return labelComprobar;
	}

	/**
	 * @return the labelVersion
	 */
	public Label getLabelVersion() {
		return labelVersion;
	}

	/**
	 * @return the labelEmpresa
	 */
	public Label getLabelEmpresa() {
		return labelEmpresa;
	}

	/**
	 * @return the prontInfoEspecial
	 */
	public Label getProntInfoEspecial() {
		return prontInfoEspecial;
	}

	/**
	 * @return the prontInfoPreviews
	 */
	public Label getProntInfoPreviews() {
		return prontInfoPreviews;
	}

	/**
	 * @return the prontInfoPortadas
	 */
	public Label getProntInfoPortadas() {
		return prontInfoPortadas;
	}

	/**
	 * @return the labelAnio
	 */
	public Label getLabelAnio() {
		return labelAnio;
	}

	/**
	 * @return the labelCodigo
	 */
	public Label getLabelCodigo() {
		return labelCodigo;
	}

	/**
	 * @return the busquedaCodigoTextField
	 */
	public TextField getBusquedaCodigoTextField() {
		return busquedaCodigoTextField;
	}

	/**
	 * @return the nombreCartaTextField
	 */
	public TextField getNombreCartaTextField() {
		return nombreCartaTextField;
	}

	/**
	 * @return the codigoCartaTextField
	 */
	public TextField getCodigoCartaTextField() {
		return codigoCartaTextField;
	}

	/**
	 * @return the anioCartaTextField
	 */
	public TextField getAnioCartaTextField() {
		return anioCartaTextField;
	}

	/**
	 * @return the nombreEmpresaTextField
	 */
	public TextField getNombreEmpresaTextField() {
		return nombreEmpresaTextField;
	}

	/**
	 * @return the edicionCartaTextField
	 */
	public TextField getEdicionCartaTextField() {
		return edicionCartaTextField;
	}

	/**
	 * @return the coleccionCartaTextField
	 */
	public TextField getColeccionCartaTextField() {
		return coleccionCartaTextField;
	}

	/**
	 * @return the gradeoCartaTextField
	 */
	public TextField getGradeoCartaTextField() {
		return gradeoCartaTextField;
	}

	/**
	 * @return the idCartaTratarTextField
	 */
	public TextField getIdCartaTratarTextField() {
		return idCartaTratarTextField;
	}

	/**
	 * @return the direccionImagenTextField
	 */
	public TextField getDireccionImagenTextField() {
		return direccionImagenTextField;
	}

	/**
	 * @return the urlReferenciaTextField
	 */
	public TextField getUrlReferenciaTextField() {
		return urlReferenciaTextField;
	}

	/**
	 * @return the numeroCartaTextField
	 */
	public TextField getNumeroCartaTextField() {
		return numeroCartaTextField;
	}

	/**
	 * @return the codigoCartaTratarTextField
	 */
	public TextField getCodigoCartaTratarTextField() {
		return codigoCartaTratarTextField;
	}

	/**
	 * @return the busquedaGeneralTextField
	 */
	public TextField getBusquedaGeneralTextField() {
		return busquedaGeneralTextField;
	}

	/**
	 * @return the nombreCartaCombobox
	 */
	public ComboBox<String> getNombreCartaCombobox() {
		return nombreCartaCombobox;
	}

	/**
	 * @return the numeroCartaCombobox
	 */
	public ComboBox<String> getNumeroCartaCombobox() {
		return numeroCartaCombobox;
	}

	/**
	 * @return the nombreTiendaCombobox
	 */
	public ComboBox<String> getNombreTiendaCombobox() {
		return nombreTiendaCombobox;
	}

	/**
	 * @return the nombreEdicionCombobox
	 */
	public ComboBox<String> getNombreEdicionCombobox() {
		return nombreEdicionCombobox;
	}

	/**
	 * @return the nombreColeccionCombobox
	 */
	public ComboBox<String> getNombreColeccionCombobox() {
		return nombreColeccionCombobox;
	}

	/**
	 * @return the nombreGradeoCombobox
	 */
	public ComboBox<String> getNombreGradeoCombobox() {
		return nombreGradeoCombobox;
	}

	/**
	 * @return the nombreEmpresaCombobox
	 */
	public ComboBox<String> getNombreEmpresaCombobox() {
		return nombreEmpresaCombobox;
	}

	/**
	 * @return the comboPreviewsCombobox
	 */
	public ComboBox<String> getComboPreviewsCombobox() {
		return comboPreviewsCombobox;
	}

	/**
	 * @return the prontInfoTextArea
	 */
	public TextArea getProntInfoTextArea() {
		return prontInfoTextArea;
	}

	/**
	 * @return the menuImportarFicheroCodigoBarras
	 */
	public MenuItem getMenuImportarFicheroCodigoBarras() {
		return menuImportarFicheroCodigoBarras;
	}

	/**
	 * @return the menuCartaAniadir
	 */
	public MenuItem getMenuCartaAniadir() {
		return menuCartaAniadir;
	}

	/**
	 * @return the menuCartaEliminar
	 */
	public MenuItem getMenuCartaEliminar() {
		return menuCartaEliminar;
	}

	/**
	 * @return the menuCartaModificar
	 */
	public MenuItem getMenuCartaModificar() {
		return menuCartaModificar;
	}

	/**
	 * @return the menuEstadisticaEstadistica
	 */
	public MenuItem getMenuEstadisticaEstadistica() {
		return menuEstadisticaEstadistica;
	}

	/**
	 * @return the menuArchivoCerrar
	 */
	public MenuItem getMenuArchivoCerrar() {
		return menuArchivoCerrar;
	}

	/**
	 * @return the menuArchivoDelete
	 */
	public MenuItem getMenuArchivoDelete() {
		return menuArchivoDelete;
	}

	/**
	 * @return the menuArchivoDesconectar
	 */
	public MenuItem getMenuArchivoDesconectar() {
		return menuArchivoDesconectar;
	}

	/**
	 * @return the menuArchivoExcel
	 */
	public MenuItem getMenuArchivoExcel() {
		return menuArchivoExcel;
	}

	/**
	 * @return the menuArchivoImportar
	 */
	public MenuItem getMenuArchivoImportar() {
		return menuArchivoImportar;
	}

	/**
	 * @return the menuArchivoSobreMi
	 */
	public MenuItem getMenuArchivoSobreMi() {
		return menuArchivoSobreMi;
	}

	/**
	 * @return the menuEstadisticaSumaTotal
	 */
	public MenuItem getMenuEstadisticaSumaTotal() {
		return menuEstadisticaSumaTotal;
	}

	/**
	 * @return the menuArchivoAvanzado
	 */
	public MenuItem getMenuArchivoAvanzado() {
		return menuArchivoAvanzado;
	}

	/**
	 * @return the navegacionCerrar
	 */
	public Menu getNavegacionCerrar() {
		return navegacionCerrar;
	}

	/**
	 * @return the navegacionCarta
	 */
	public Menu getNavegacionCarta() {
		return navegacionCarta;
	}

	/**
	 * @return the navegacionEstadistica
	 */
	public Menu getNavegacionEstadistica() {
		return navegacionEstadistica;
	}

	/**
	 * @return the menuNavegacion
	 */
	public MenuBar getMenuNavegacion() {
		return menuNavegacion;
	}

	/**
	 * @return the progresoCarga
	 */
	public ProgressIndicator getProgresoCarga() {
		return progresoCarga;
	}

	/**
	 * @return the checkFirmas
	 */
	public CheckBox getCheckFirmas() {
		return checkFirmas;
	}

	/**
	 * @return the controlAccion
	 */
	public List<Control> getControlAccion() {
		return controlAccion;
	}

	/**
	 * @return the listaComboboxes
	 */
	public static List<ComboBox<String>> getListaComboboxes() {
		return listaComboboxes;
	}

	/**
	 * @return the listaColumnasTabla
	 */
	public static List<TableColumn<CartaGradeo, String>> getListaColumnasTabla() {
		return listaColumnasTabla;
	}

	/**
	 * @return the listaTextFields
	 */
	public static ObservableList<Control> getListaTextFields() {
		return listaTextFields;
	}

	/**
	 * @return the listaBotones
	 */
	public static ObservableList<Button> getListaBotones() {
		return listaBotones;
	}

	/**
	 * @return the listaElementosFondo
	 */
	public static ObservableList<Node> getListaElementosFondo() {
		return listaElementosFondo;
	}

	/**
	 * @return the stageVentana
	 */
	public Stage getStageVentana() {
		return stageVentana;
	}

	/**
	 * @param iDColumna the iDColumna to set
	 */
	public void setiDColumna(TableColumn<CartaGradeo, String> iDColumna) {
		this.iDColumna = iDColumna;
	}

	/**
	 * @param nombreColumna the nombreColumna to set
	 */
	public void setNombreColumna(TableColumn<CartaGradeo, String> nombreColumna) {
		this.nombreColumna = nombreColumna;
	}

	/**
	 * @param numeroColumna the numeroColumna to set
	 */
	public void setNumeroColumna(TableColumn<CartaGradeo, String> numeroColumna) {
		this.numeroColumna = numeroColumna;
	}

	/**
	 * @param edicionColumna the edicionColumna to set
	 */
	public void setEdicionColumna(TableColumn<CartaGradeo, String> edicionColumna) {
		this.edicionColumna = edicionColumna;
	}

	/**
	 * @param coleccionColumna the coleccionColumna to set
	 */
	public void setColeccionColumna(TableColumn<CartaGradeo, String> coleccionColumna) {
		this.coleccionColumna = coleccionColumna;
	}

	/**
	 * @param certificacionColumna the certificacionColumna to set
	 */
	public void setCertificacionColumna(TableColumn<CartaGradeo, String> certificacionColumna) {
		this.certificacionColumna = certificacionColumna;
	}

	/**
	 * @param empresaColumna the empresaColumna to set
	 */
	public void setEmpresaColumna(TableColumn<CartaGradeo, String> empresaColumna) {
		this.empresaColumna = empresaColumna;
	}

	/**
	 * @param referenciaColumna the referenciaColumna to set
	 */
	public void setReferenciaColumna(TableColumn<CartaGradeo, String> referenciaColumna) {
		this.referenciaColumna = referenciaColumna;
	}

	/**
	 * @param tablaBBDD the tablaBBDD to set
	 */
	public void setTablaBBDD(TableView<CartaGradeo> tablaBBDD) {
		this.tablaBBDD = tablaBBDD;
	}

	/**
	 * @param rootVBox the rootVBox to set
	 */
	public void setRootVBox(VBox rootVBox) {
		this.rootVBox = rootVBox;
	}

	/**
	 * @param vboxContenido the vboxContenido to set
	 */
	public void setVboxContenido(VBox vboxContenido) {
		this.vboxContenido = vboxContenido;
	}

	/**
	 * @param vboxImage the vboxImage to set
	 */
	public void setVboxImage(VBox vboxImage) {
		this.vboxImage = vboxImage;
	}

	/**
	 * @param rootAnchorPane the rootAnchorPane to set
	 */
	public void setRootAnchorPane(AnchorPane rootAnchorPane) {
		this.rootAnchorPane = rootAnchorPane;
	}

	/**
	 * @param anchoPaneInfo the anchoPaneInfo to set
	 */
	public void setAnchoPaneInfo(AnchorPane anchoPaneInfo) {
		this.anchoPaneInfo = anchoPaneInfo;
	}

	/**
	 * @param backgroundImage the backgroundImage to set
	 */
	public void setBackgroundImage(ImageView backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	/**
	 * @param imagenCarta the imagenCarta to set
	 */
	public void setImagenCarta(ImageView imagenCarta) {
		this.imagenCarta = imagenCarta;
	}

	/**
	 * @param cargaImagen the cargaImagen to set
	 */
	public void setCargaImagen(ImageView cargaImagen) {
		this.cargaImagen = cargaImagen;
	}

	/**
	 * @param botonClonarCarta the botonClonarCarta to set
	 */
	public void setBotonClonarCarta(Button botonClonarCarta) {
		this.botonClonarCarta = botonClonarCarta;
	}

	/**
	 * @param botonModificar the botonModificar to set
	 */
	public void setBotonModificar(Button botonModificar) {
		this.botonModificar = botonModificar;
	}

	/**
	 * @param botonIntroducir the botonIntroducir to set
	 */
	public void setBotonIntroducir(Button botonIntroducir) {
		this.botonIntroducir = botonIntroducir;
	}

	/**
	 * @param botonEliminar the botonEliminar to set
	 */
	public void setBotonEliminar(Button botonEliminar) {
		this.botonEliminar = botonEliminar;
	}

	/**
	 * @param botonComprimirPortadas the botonComprimirPortadas to set
	 */
	public void setBotonComprimirPortadas(Button botonComprimirPortadas) {
		this.botonComprimirPortadas = botonComprimirPortadas;
	}

	/**
	 * @param botonReCopiarPortadas the botonReCopiarPortadas to set
	 */
	public void setBotonReCopiarPortadas(Button botonReCopiarPortadas) {
		this.botonReCopiarPortadas = botonReCopiarPortadas;
	}

	/**
	 * @param botonCancelarSubida the botonCancelarSubida to set
	 */
	public void setBotonCancelarSubida(Button botonCancelarSubida) {
		this.botonCancelarSubida = botonCancelarSubida;
	}

	/**
	 * @param botonBusquedaCodigo the botonBusquedaCodigo to set
	 */
	public void setBotonBusquedaCodigo(Button botonBusquedaCodigo) {
		this.botonBusquedaCodigo = botonBusquedaCodigo;
	}

	/**
	 * @param botonBusquedaAvanzada the botonBusquedaAvanzada to set
	 */
	public void setBotonBusquedaAvanzada(Button botonBusquedaAvanzada) {
		this.botonBusquedaAvanzada = botonBusquedaAvanzada;
	}

	/**
	 * @param botonLimpiar the botonLimpiar to set
	 */
	public void setBotonLimpiar(Button botonLimpiar) {
		this.botonLimpiar = botonLimpiar;
	}

	/**
	 * @param botonModificarCarta the botonModificarCarta to set
	 */
	public void setBotonModificarCarta(Button botonModificarCarta) {
		this.botonModificarCarta = botonModificarCarta;
	}

	/**
	 * @param botonParametroCarta the botonParametroCarta to set
	 */
	public void setBotonParametroCarta(Button botonParametroCarta) {
		this.botonParametroCarta = botonParametroCarta;
	}

	/**
	 * @param botonVender the botonVender to set
	 */
	public void setBotonVender(Button botonVender) {
		this.botonVender = botonVender;
	}

	/**
	 * @param botonbbdd the botonbbdd to set
	 */
	public void setBotonbbdd(Button botonbbdd) {
		this.botonbbdd = botonbbdd;
	}

	/**
	 * @param botonGuardarCarta the botonGuardarCarta to set
	 */
	public void setBotonGuardarCarta(Button botonGuardarCarta) {
		this.botonGuardarCarta = botonGuardarCarta;
	}

	/**
	 * @param botonGuardarCambioCarta the botonGuardarCambioCarta to set
	 */
	public void setBotonGuardarCambioCarta(Button botonGuardarCambioCarta) {
		this.botonGuardarCambioCarta = botonGuardarCambioCarta;
	}

	/**
	 * @param botonEliminarImportadoCarta the botonEliminarImportadoCarta to set
	 */
	public void setBotonEliminarImportadoCarta(Button botonEliminarImportadoCarta) {
		this.botonEliminarImportadoCarta = botonEliminarImportadoCarta;
	}

	/**
	 * @param botonSubidaPortada the botonSubidaPortada to set
	 */
	public void setBotonSubidaPortada(Button botonSubidaPortada) {
		this.botonSubidaPortada = botonSubidaPortada;
	}

	/**
	 * @param botonMostrarParametro the botonMostrarParametro to set
	 */
	public void setBotonMostrarParametro(Button botonMostrarParametro) {
		this.botonMostrarParametro = botonMostrarParametro;
	}

	/**
	 * @param botonActualizarDatos the botonActualizarDatos to set
	 */
	public void setBotonActualizarDatos(Button botonActualizarDatos) {
		this.botonActualizarDatos = botonActualizarDatos;
	}

	/**
	 * @param botonActualizarPortadas the botonActualizarPortadas to set
	 */
	public void setBotonActualizarPortadas(Button botonActualizarPortadas) {
		this.botonActualizarPortadas = botonActualizarPortadas;
	}

	/**
	 * @param botonActualizarSoftware the botonActualizarSoftware to set
	 */
	public void setBotonActualizarSoftware(Button botonActualizarSoftware) {
		this.botonActualizarSoftware = botonActualizarSoftware;
	}

	/**
	 * @param botonActualizarTodo the botonActualizarTodo to set
	 */
	public void setBotonActualizarTodo(Button botonActualizarTodo) {
		this.botonActualizarTodo = botonActualizarTodo;
	}

	/**
	 * @param botonDescargarPdf the botonDescargarPdf to set
	 */
	public void setBotonDescargarPdf(Button botonDescargarPdf) {
		this.botonDescargarPdf = botonDescargarPdf;
	}

	/**
	 * @param botonDescargarSQL the botonDescargarSQL to set
	 */
	public void setBotonDescargarSQL(Button botonDescargarSQL) {
		this.botonDescargarSQL = botonDescargarSQL;
	}

	/**
	 * @param botonGuardarListaCartas the botonGuardarListaCartas to set
	 */
	public void setBotonGuardarListaCartas(Button botonGuardarListaCartas) {
		this.botonGuardarListaCartas = botonGuardarListaCartas;
	}

	/**
	 * @param botonEliminarImportadoListaCarta the botonEliminarImportadoListaCarta
	 *                                         to set
	 */
	public void setBotonEliminarImportadoListaCarta(Button botonEliminarImportadoListaCarta) {
		this.botonEliminarImportadoListaCarta = botonEliminarImportadoListaCarta;
	}

	/**
	 * @param botonActualizarPrecio the botonActualizarPrecio to set
	 */
	public void setBotonActualizarPrecio(Button botonActualizarPrecio) {
		this.botonActualizarPrecio = botonActualizarPrecio;
	}

	/**
	 * @param barraCambioAltura the barraCambioAltura to set
	 */
	public void setBarraCambioAltura(Rectangle barraCambioAltura) {
		this.barraCambioAltura = barraCambioAltura;
	}

	/**
	 * @param alarmaConexionInternet the alarmaConexionInternet to set
	 */
	public void setAlarmaConexionInternet(Label alarmaConexionInternet) {
		this.alarmaConexionInternet = alarmaConexionInternet;
	}

	/**
	 * @param labelEdicion the labelEdicion to set
	 */
	public void setLabelEdicion(Label labelEdicion) {
		this.labelEdicion = labelEdicion;
	}

	/**
	 * @param labelColeccion the labelColeccion to set
	 */
	public void setLabelColeccion(Label labelColeccion) {
		this.labelColeccion = labelColeccion;
	}

	/**
	 * @param labelNombre the labelNombre to set
	 */
	public void setLabelNombre(Label labelNombre) {
		this.labelNombre = labelNombre;
	}

	/**
	 * @param labelIdMod the labelIdMod to set
	 */
	public void setLabelIdMod(Label labelIdMod) {
		this.labelIdMod = labelIdMod;
	}

	/**
	 * @param labelPortada the labelPortada to set
	 */
	public void setLabelPortada(Label labelPortada) {
		this.labelPortada = labelPortada;
	}

	/**
	 * @param labelGradeo the labelGradeo to set
	 */
	public void setLabelGradeo(Label labelGradeo) {
		this.labelGradeo = labelGradeo;
	}

	/**
	 * @param labelReferencia the labelReferencia to set
	 */
	public void setLabelReferencia(Label labelReferencia) {
		this.labelReferencia = labelReferencia;
	}

	/**
	 * @param prontInfoLabel the prontInfoLabel to set
	 */
	public void setProntInfoLabel(Label prontInfoLabel) {
		this.prontInfoLabel = prontInfoLabel;
	}

	/**
	 * @param alarmaConexionSql the alarmaConexionSql to set
	 */
	public void setAlarmaConexionSql(Label alarmaConexionSql) {
		this.alarmaConexionSql = alarmaConexionSql;
	}

	/**
	 * @param labelComprobar the labelComprobar to set
	 */
	public void setLabelComprobar(Label labelComprobar) {
		this.labelComprobar = labelComprobar;
	}

	/**
	 * @param labelVersion the labelVersion to set
	 */
	public void setLabelVersion(Label labelVersion) {
		this.labelVersion = labelVersion;
	}

	/**
	 * @param labelEmpresa the labelEmpresa to set
	 */
	public void setLabelEmpresa(Label labelEmpresa) {
		this.labelEmpresa = labelEmpresa;
	}

	/**
	 * @param prontInfoEspecial the prontInfoEspecial to set
	 */
	public void setProntInfoEspecial(Label prontInfoEspecial) {
		this.prontInfoEspecial = prontInfoEspecial;
	}

	/**
	 * @param prontInfoPreviews the prontInfoPreviews to set
	 */
	public void setProntInfoPreviews(Label prontInfoPreviews) {
		this.prontInfoPreviews = prontInfoPreviews;
	}

	/**
	 * @param prontInfoPortadas the prontInfoPortadas to set
	 */
	public void setProntInfoPortadas(Label prontInfoPortadas) {
		this.prontInfoPortadas = prontInfoPortadas;
	}

	/**
	 * @param labelAnio the labelAnio to set
	 */
	public void setLabelAnio(Label labelAnio) {
		this.labelAnio = labelAnio;
	}

	/**
	 * @param labelCodigo the labelCodigo to set
	 */
	public void setLabelCodigo(Label labelCodigo) {
		this.labelCodigo = labelCodigo;
	}

	/**
	 * @param busquedaCodigoTextField the busquedaCodigoTextField to set
	 */
	public void setBusquedaCodigoTextField(TextField busquedaCodigoTextField) {
		this.busquedaCodigoTextField = busquedaCodigoTextField;
	}

	/**
	 * @param nombreCartaTextField the nombreCartaTextField to set
	 */
	public void setNombreCartaTextField(TextField nombreCartaTextField) {
		this.nombreCartaTextField = nombreCartaTextField;
	}

	/**
	 * @param codigoCartaTextField the codigoCartaTextField to set
	 */
	public void setCodigoCartaTextField(TextField codigoCartaTextField) {
		this.codigoCartaTextField = codigoCartaTextField;
	}

	/**
	 * @param anioCartaTextField the anioCartaTextField to set
	 */
	public void setAnioCartaTextField(TextField anioCartaTextField) {
		this.anioCartaTextField = anioCartaTextField;
	}

	/**
	 * @param nombreEmpresaTextField the nombreEmpresaTextField to set
	 */
	public void setNombreEmpresaTextField(TextField nombreEmpresaTextField) {
		this.nombreEmpresaTextField = nombreEmpresaTextField;
	}

	/**
	 * @param edicionCartaTextField the edicionCartaTextField to set
	 */
	public void setEdicionCartaTextField(TextField edicionCartaTextField) {
		this.edicionCartaTextField = edicionCartaTextField;
	}

	/**
	 * @param coleccionCartaTextField the coleccionCartaTextField to set
	 */
	public void setColeccionCartaTextField(TextField coleccionCartaTextField) {
		this.coleccionCartaTextField = coleccionCartaTextField;
	}

	/**
	 * @param gradeoCartaTextField the gradeoCartaTextField to set
	 */
	public void setGradeoCartaTextField(TextField gradeoCartaTextField) {
		this.gradeoCartaTextField = gradeoCartaTextField;
	}

	/**
	 * @param idCartaTratarTextField the idCartaTratarTextField to set
	 */
	public void setIdCartaTratarTextField(TextField idCartaTratarTextField) {
		this.idCartaTratarTextField = idCartaTratarTextField;
	}

	/**
	 * @param direccionImagenTextField the direccionImagenTextField to set
	 */
	public void setDireccionImagenTextField(TextField direccionImagenTextField) {
		this.direccionImagenTextField = direccionImagenTextField;
	}

	/**
	 * @param urlReferenciaTextField the urlReferenciaTextField to set
	 */
	public void setUrlReferenciaTextField(TextField urlReferenciaTextField) {
		this.urlReferenciaTextField = urlReferenciaTextField;
	}

	/**
	 * @param numeroCartaTextField the numeroCartaTextField to set
	 */
	public void setNumeroCartaTextField(TextField numeroCartaTextField) {
		this.numeroCartaTextField = numeroCartaTextField;
	}

	/**
	 * @param codigoCartaTratarTextField the codigoCartaTratarTextField to set
	 */
	public void setCodigoCartaTratarTextField(TextField codigoCartaTratarTextField) {
		this.codigoCartaTratarTextField = codigoCartaTratarTextField;
	}

	/**
	 * @param busquedaGeneralTextField the busquedaGeneralTextField to set
	 */
	public void setBusquedaGeneralTextField(TextField busquedaGeneralTextField) {
		this.busquedaGeneralTextField = busquedaGeneralTextField;
	}

	/**
	 * @param nombreCartaCombobox the nombreCartaCombobox to set
	 */
	public void setNombreCartaCombobox(ComboBox<String> nombreCartaCombobox) {
		this.nombreCartaCombobox = nombreCartaCombobox;
	}

	/**
	 * @param numeroCartaCombobox the numeroCartaCombobox to set
	 */
	public void setNumeroCartaCombobox(ComboBox<String> numeroCartaCombobox) {
		this.numeroCartaCombobox = numeroCartaCombobox;
	}

	/**
	 * @param nombreTiendaCombobox the nombreTiendaCombobox to set
	 */
	public void setNombreTiendaCombobox(ComboBox<String> nombreTiendaCombobox) {
		this.nombreTiendaCombobox = nombreTiendaCombobox;
	}

	/**
	 * @param nombreEdicionCombobox the nombreEdicionCombobox to set
	 */
	public void setNombreEdicionCombobox(ComboBox<String> nombreEdicionCombobox) {
		this.nombreEdicionCombobox = nombreEdicionCombobox;
	}

	/**
	 * @param nombreColeccionCombobox the nombreColeccionCombobox to set
	 */
	public void setNombreColeccionCombobox(ComboBox<String> nombreColeccionCombobox) {
		this.nombreColeccionCombobox = nombreColeccionCombobox;
	}

	/**
	 * @param nombreGradeoCombobox the nombreGradeoCombobox to set
	 */
	public void setNombreGradeoCombobox(ComboBox<String> nombreGradeoCombobox) {
		this.nombreGradeoCombobox = nombreGradeoCombobox;
	}

	/**
	 * @param nombreEmpresaCombobox the nombreEmpresaCombobox to set
	 */
	public void setNombreEmpresaCombobox(ComboBox<String> nombreEmpresaCombobox) {
		this.nombreEmpresaCombobox = nombreEmpresaCombobox;
	}

	/**
	 * @param comboPreviewsCombobox the comboPreviewsCombobox to set
	 */
	public void setComboPreviewsCombobox(ComboBox<String> comboPreviewsCombobox) {
		this.comboPreviewsCombobox = comboPreviewsCombobox;
	}

	/**
	 * @param prontInfoTextArea the prontInfoTextArea to set
	 */
	public void setProntInfoTextArea(TextArea prontInfoTextArea) {
		this.prontInfoTextArea = prontInfoTextArea;
	}

	/**
	 * @param menuImportarFicheroCodigoBarras the menuImportarFicheroCodigoBarras to
	 *                                        set
	 */
	public void setMenuImportarFicheroCodigoBarras(MenuItem menuImportarFicheroCodigoBarras) {
		this.menuImportarFicheroCodigoBarras = menuImportarFicheroCodigoBarras;
	}

	/**
	 * @param menuCartaAniadir the menuCartaAniadir to set
	 */
	public void setMenuCartaAniadir(MenuItem menuCartaAniadir) {
		this.menuCartaAniadir = menuCartaAniadir;
	}

	/**
	 * @param menuCartaEliminar the menuCartaEliminar to set
	 */
	public void setMenuCartaEliminar(MenuItem menuCartaEliminar) {
		this.menuCartaEliminar = menuCartaEliminar;
	}

	/**
	 * @param menuCartaModificar the menuCartaModificar to set
	 */
	public void setMenuCartaModificar(MenuItem menuCartaModificar) {
		this.menuCartaModificar = menuCartaModificar;
	}

	/**
	 * @param menuEstadisticaEstadistica the menuEstadisticaEstadistica to set
	 */
	public void setMenuEstadisticaEstadistica(MenuItem menuEstadisticaEstadistica) {
		this.menuEstadisticaEstadistica = menuEstadisticaEstadistica;
	}

	/**
	 * @param menuArchivoCerrar the menuArchivoCerrar to set
	 */
	public void setMenuArchivoCerrar(MenuItem menuArchivoCerrar) {
		this.menuArchivoCerrar = menuArchivoCerrar;
	}

	/**
	 * @param menuArchivoDelete the menuArchivoDelete to set
	 */
	public void setMenuArchivoDelete(MenuItem menuArchivoDelete) {
		this.menuArchivoDelete = menuArchivoDelete;
	}

	/**
	 * @param menuArchivoDesconectar the menuArchivoDesconectar to set
	 */
	public void setMenuArchivoDesconectar(MenuItem menuArchivoDesconectar) {
		this.menuArchivoDesconectar = menuArchivoDesconectar;
	}

	/**
	 * @param menuArchivoExcel the menuArchivoExcel to set
	 */
	public void setMenuArchivoExcel(MenuItem menuArchivoExcel) {
		this.menuArchivoExcel = menuArchivoExcel;
	}

	/**
	 * @param menuArchivoImportar the menuArchivoImportar to set
	 */
	public void setMenuArchivoImportar(MenuItem menuArchivoImportar) {
		this.menuArchivoImportar = menuArchivoImportar;
	}

	/**
	 * @param menuArchivoSobreMi the menuArchivoSobreMi to set
	 */
	public void setMenuArchivoSobreMi(MenuItem menuArchivoSobreMi) {
		this.menuArchivoSobreMi = menuArchivoSobreMi;
	}

	/**
	 * @param menuEstadisticaSumaTotal the menuEstadisticaSumaTotal to set
	 */
	public void setMenuEstadisticaSumaTotal(MenuItem menuEstadisticaSumaTotal) {
		this.menuEstadisticaSumaTotal = menuEstadisticaSumaTotal;
	}

	/**
	 * @param menuArchivoAvanzado the menuArchivoAvanzado to set
	 */
	public void setMenuArchivoAvanzado(MenuItem menuArchivoAvanzado) {
		this.menuArchivoAvanzado = menuArchivoAvanzado;
	}

	/**
	 * @param navegacionCerrar the navegacionCerrar to set
	 */
	public void setNavegacionCerrar(Menu navegacionCerrar) {
		this.navegacionCerrar = navegacionCerrar;
	}

	/**
	 * @param navegacionCarta the navegacionCarta to set
	 */
	public void setNavegacionCarta(Menu navegacionCarta) {
		this.navegacionCarta = navegacionCarta;
	}

	/**
	 * @param navegacionEstadistica the navegacionEstadistica to set
	 */
	public void setNavegacionEstadistica(Menu navegacionEstadistica) {
		this.navegacionEstadistica = navegacionEstadistica;
	}

	/**
	 * @param menuNavegacion the menuNavegacion to set
	 */
	public void setMenuNavegacion(MenuBar menuNavegacion) {
		this.menuNavegacion = menuNavegacion;
	}

	/**
	 * @param progresoCarga the progresoCarga to set
	 */
	public void setProgresoCarga(ProgressIndicator progresoCarga) {
		this.progresoCarga = progresoCarga;
	}

	/**
	 * @param checkFirmas the checkFirmas to set
	 */
	public void setCheckFirmas(CheckBox checkFirmas) {
		this.checkFirmas = checkFirmas;
	}

	/**
	 * @param controlAccion the controlAccion to set
	 */
	public void setControlAccion(List<Control> controlAccion) {
		this.controlAccion = controlAccion;
	}

	/**
	 * @param listaComboboxes the listaComboboxes to set
	 */
	public static void setListaComboboxes(List<ComboBox<String>> listaComboboxes) {
		AccionReferencias.listaComboboxes = listaComboboxes;
	}

	/**
	 * @param listaColumnasTabla the listaColumnasTabla to set
	 */
	public static void setListaColumnasTabla(List<TableColumn<CartaGradeo, String>> listaColumnasTabla) {
		AccionReferencias.listaColumnasTabla = listaColumnasTabla;
	}

	/**
	 * @param listaTextFields the listaTextFields to set
	 */
	public static void setListaTextFields(ObservableList<Control> listaTextFields) {
		AccionReferencias.listaTextFields = listaTextFields;
	}

	/**
	 * @param listaBotones the listaBotones to set
	 */
	public static void setListaBotones(ObservableList<Button> listaBotones) {
		AccionReferencias.listaBotones = listaBotones;
	}

	/**
	 * @param listaElementosFondo the listaElementosFondo to set
	 */
	public static void setListaElementosFondo(ObservableList<Node> listaElementosFondo) {
		AccionReferencias.listaElementosFondo = listaElementosFondo;
	}

	/**
	 * @param stageVentana the stageVentana to set
	 */
	public void setStageVentana(Stage stageVentana) {
		this.stageVentana = stageVentana;
	}

}
