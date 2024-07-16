package funcionesInterfaz;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controladores.VentanaAccionController;
import alarmas.AlarmaList;
import cartaManagement.CartaGradeo;
import dbmanager.CartaManagerDAO;
import dbmanager.ListasCartasDAO;
import funcionesAuxiliares.Utilidades;
import funcionesManagment.AccionAniadir;
import funcionesManagment.AccionEliminar;
import funcionesManagment.AccionFuncionesComunes;
import funcionesManagment.AccionModificar;
import funcionesManagment.AccionReferencias;
import funcionesManagment.AccionSeleccionar;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class AccionControlUI {

	private static AccionReferencias referenciaVentana = getReferenciaVentana();

	private static VentanaAccionController accionController = new VentanaAccionController();

	private static AccionAniadir accionAniadir = new AccionAniadir();

	private static AccionEliminar accionEliminar = new AccionEliminar();

	private static AccionModificar accionModificar = new AccionModificar();

	public static void autoRelleno() {

		referenciaVentana.getIdCartaTratarTextField().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.isEmpty()) {
				if (!rellenarCampos(newValue)) {
					limpiarAutorellenos(false);
					borrarDatosGraficos();
				}
			} else {
				limpiarAutorellenos(false);
				borrarDatosGraficos();
			}
		});
	}

	public static boolean rellenarCampos(String idCarta) {
		CartaGradeo cartaTempTemp = CartaGradeo.obtenerCarta(idCarta);
		if (cartaTempTemp != null) {
			rellenarDatos(cartaTempTemp);
			return true;
		}
		return false;
	}

	public static void mostrarOpcion(String opcion) {
		ocultarCampos();

		List<Node> elementosAMostrarYHabilitar = new ArrayList<>();

		switch (opcion.toLowerCase()) {
		case "eliminar":
			accionEliminar.mostrarElementosEliminar(elementosAMostrarYHabilitar);
			break;
		case "aniadir":
			accionAniadir.mostrarElementosAniadir(elementosAMostrarYHabilitar);
			break;
		case "modificar":
			accionModificar.mostrarElementosModificar(elementosAMostrarYHabilitar);
			break;
		default:
			accionController.closeWindow();
			return;
		}

		mostrarElementos(elementosAMostrarYHabilitar);
	}

	public static List<Node> modificarInterfazAccion(String opcion) {

		List<Node> elementosAMostrarYHabilitar = new ArrayList<>();

		switch (opcion.toLowerCase()) {
		case "modificar":
			elementosAMostrarYHabilitar.add(referenciaVentana.getBotonModificarCarta());
			elementosAMostrarYHabilitar.add(referenciaVentana.getBotonEliminar());
			break;
		case "aniadir":
			elementosAMostrarYHabilitar.add(referenciaVentana.getBotonGuardarCarta());
			elementosAMostrarYHabilitar.add(referenciaVentana.getBotonEliminarImportadoCarta());
			elementosAMostrarYHabilitar.add(referenciaVentana.getBotonClonarCarta());
			break;
		default:
			break;
		}

		return elementosAMostrarYHabilitar;
	}

	private static void mostrarElementos(List<Node> elementosAMostrarYHabilitar) {
		for (Node elemento : elementosAMostrarYHabilitar) {

			if (elemento != null) {
				elemento.setVisible(true);
				elemento.setDisable(false);
			}
		}

		if (!AccionFuncionesComunes.TIPO_ACCION.equals("modificar")) {
			autoRelleno();
		}

		if (!AccionFuncionesComunes.TIPO_ACCION.equals("aniadir")) {

			referenciaVentana.getNavegacionCerrar().setDisable(true);
			referenciaVentana.getNavegacionCerrar().setVisible(false);
		} else {
			referenciaVentana.getIdCartaTratarTextField().setEditable(false);
			referenciaVentana.getIdCartaTratarTextField().setOpacity(0.7);
		}
		if (AccionFuncionesComunes.TIPO_ACCION.equals("eliminar")) {
			referenciaVentana.getLabelIdMod().setLayoutX(5);
			referenciaVentana.getNumeroCartaTextField().setVisible(false);
			referenciaVentana.getColeccionCartaTextField().setVisible(false);
			referenciaVentana.getNombreCartaTextField().setVisible(false);
			referenciaVentana.getEdicionCartaTextField().setVisible(false);
			referenciaVentana.getLabelColeccion().setVisible(false);
			referenciaVentana.getLabelNombre().setVisible(false);

		}

		if (AccionFuncionesComunes.TIPO_ACCION.equals("aniadir")) {
			referenciaVentana.getBotonEliminarImportadoListaCarta().setVisible(false);
			referenciaVentana.getBotonGuardarListaCartas().setVisible(false);

			referenciaVentana.getBotonEliminarImportadoListaCarta().setDisable(true);
			referenciaVentana.getBotonGuardarListaCartas().setDisable(true);
		}
		if (AccionFuncionesComunes.TIPO_ACCION.equals("modificar")) {

			referenciaVentana.getBotonModificarCarta().setVisible(false);
			referenciaVentana.getBotonModificarCarta().setDisable(true);

			referenciaVentana.getBotonEliminar().setVisible(false);
			referenciaVentana.getBotonEliminar().setDisable(true);
		}

	}

	/**
	 * Oculta y deshabilita varios campos y elementos en la interfaz gráfica.
	 */
	public static void ocultarCampos() {

		List<Node> elementosTextfield = Arrays.asList(referenciaVentana.getGradeoCartaTextField(),
				referenciaVentana.getDireccionImagenTextField(), referenciaVentana.getUrlReferenciaTextField(),
				referenciaVentana.getNombreEmpresaTextField(), referenciaVentana.getAnioCartaTextField(),
				referenciaVentana.getCodigoCartaTextField());

		List<Node> elementosLabel = Arrays.asList(referenciaVentana.getLabelGradeo(),
				referenciaVentana.getLabelPortada(), referenciaVentana.getLabelReferencia(),
				referenciaVentana.getLabelEmpresa(), referenciaVentana.getLabelCodigo(),
				referenciaVentana.getLabelAnio());

		List<Node> elementosBoton = Arrays.asList(referenciaVentana.getBotonSubidaPortada(),
				referenciaVentana.getBotonEliminar(), referenciaVentana.getBotonModificarCarta(),
				referenciaVentana.getBotonBusquedaCodigo(), referenciaVentana.getBotonbbdd());

		Utilidades.cambiarVisibilidad(elementosTextfield, true);
		Utilidades.cambiarVisibilidad(elementosLabel, true);
		Utilidades.cambiarVisibilidad(elementosBoton, true);
	}

	/**
	 * Establece los atributos del cómic basándose en el objeto Carta proporcionado.
	 * 
	 * @param cartaTempTemp El objeto Carta que contiene los datos a establecer.
	 */
	public void setAtributosDesdeTabla(CartaGradeo cartaTemp) {

		referenciaVentana.getIdCartaTratarTextField().setText(cartaTemp.getIdCarta());

		referenciaVentana.getNombreCartaTextField().setText(cartaTemp.getNomCarta());

		referenciaVentana.getNumeroCartaTextField().setText(cartaTemp.getNumCarta());

		referenciaVentana.getEdicionCartaTextField().setText(cartaTemp.getEdicionCarta());

		referenciaVentana.getColeccionCartaTextField().setText(cartaTemp.getColeccionCarta());

		referenciaVentana.getAnioCartaTextField().setText(cartaTemp.getAnioCarta());

		referenciaVentana.getUrlReferenciaTextField().setText(cartaTemp.getUrlReferenciaCarta());

		referenciaVentana.getGradeoCartaTextField().setText(cartaTemp.getGradeoCarta());

		referenciaVentana.getNombreEmpresaTextField().setText(cartaTemp.getEmpresaCarta());

		referenciaVentana.getGradeoCartaTextField().setText(cartaTemp.getGradeoCarta());

		referenciaVentana.getCodigoCartaTextField().setText(cartaTemp.getCodCarta());

		Utilidades.cargarImagenAsync(cartaTemp.getDireccionImagenCarta(), referenciaVentana.getImagenCarta());
	}

	private static void rellenarDatos(CartaGradeo cartaTemp) {

		referenciaVentana.getNumeroCartaTextField().setText(cartaTemp.getNumCarta());
		referenciaVentana.getNombreCartaTextField().setText(cartaTemp.getNomCarta());
		referenciaVentana.getNumeroCartaTextField().setText(cartaTemp.getNumCarta());
		referenciaVentana.getEdicionCartaTextField().setText(cartaTemp.getEdicionCarta());
		referenciaVentana.getColeccionCartaTextField().setText(cartaTemp.getColeccionCarta());
		referenciaVentana.getGradeoCartaTextField().setText(cartaTemp.getGradeoCarta());
		referenciaVentana.getDireccionImagenTextField().setText(cartaTemp.getDireccionImagenCarta());
		referenciaVentana.getUrlReferenciaTextField().setText(cartaTemp.getUrlReferenciaCarta());

		referenciaVentana.getProntInfoTextArea().clear();
		referenciaVentana.getProntInfoTextArea().setOpacity(1);

		Image imagenCarta = Utilidades.devolverImagenCarta(cartaTemp.getDireccionImagenCarta());
		referenciaVentana.getImagenCarta().setImage(imagenCarta);
	}

	public static void validarCamposClave(boolean esBorrado) {
		List<TextField> camposUi = Arrays.asList(referenciaVentana.getNombreCartaTextField(),
				referenciaVentana.getEdicionCartaTextField(), referenciaVentana.getColeccionCartaTextField(),
				referenciaVentana.getGradeoCartaTextField());

		for (TextField campoUi : camposUi) {

			if (campoUi != null) {
				String datoCarta = campoUi.getText();

				if (esBorrado) {
					if (datoCarta == null || datoCarta.isEmpty() || datoCarta.equalsIgnoreCase("vacio")) {
						campoUi.setStyle("");
					}
				} else {
					// Verificar si el campo está vacío, es nulo o tiene el valor "Vacio"
					if (datoCarta == null || datoCarta.isEmpty() || datoCarta.equalsIgnoreCase("vacio")) {
						campoUi.setStyle("-fx-background-color: red;");
					} else {
						campoUi.setStyle("");
					}
				}
			}
		}
	}

	public boolean camposCartaSonValidos() {
		List<Control> camposUi = Arrays.asList(referenciaVentana.getNombreCartaTextField(),
				referenciaVentana.getEdicionCartaTextField(), referenciaVentana.getColeccionCartaTextField(),
				referenciaVentana.getGradeoCartaTextField(), referenciaVentana.getNumeroCartaCombobox());

		for (Control campoUi : camposUi) {
			if (campoUi instanceof TextField) {
				String datoCarta = ((TextField) campoUi).getText();

				// Verificar si el campo está vacío, es nulo o tiene el valor "Vacio"
				if (datoCarta == null || datoCarta.isEmpty() || datoCarta.equalsIgnoreCase("vacio")) {
					campoUi.setStyle("-fx-background-color: #FF0000;");
					return false; // Devolver false si al menos un campo no es válido
				} else {
					campoUi.setStyle("");
				}
			}
		}

		return true; // Devolver true si todos los campos son válidos
	}

	public static boolean comprobarListaValidacion(CartaGradeo c) {
		String numCartaStr = c.getNumCarta();

		// Validar campos requeridos y "vacio"
		if (c.getNomCarta() == null || c.getNomCarta().isEmpty() || c.getNomCarta().equalsIgnoreCase("vacio")
				|| numCartaStr == null || numCartaStr.isEmpty() || Integer.parseInt(numCartaStr) <= 0
				|| c.getEmpresaCarta() == null || c.getEmpresaCarta().isEmpty()
				|| c.getEmpresaCarta().equalsIgnoreCase("vacio") || c.getColeccionCarta() == null
				|| c.getColeccionCarta().isEmpty() || c.getColeccionCarta().equalsIgnoreCase("vacio")
				|| c.getGradeoCarta() == null || c.getGradeoCarta().isEmpty()
				|| c.getGradeoCarta().equalsIgnoreCase("vacio") || c.getUrlReferenciaCarta() == null
				|| c.getUrlReferenciaCarta().isEmpty() || c.getUrlReferenciaCarta().equalsIgnoreCase("vacio")
				|| c.getEdicionCarta() == null || c.getEdicionCarta().isEmpty()
				|| c.getEdicionCarta().equalsIgnoreCase("vacio") || c.getAnioCarta() == null
				|| c.getAnioCarta().isEmpty() || c.getAnioCarta().equalsIgnoreCase("vacio")
				|| c.getDireccionImagenCarta() == null || c.getDireccionImagenCarta().isEmpty()
				|| c.getDireccionImagenCarta().equalsIgnoreCase("vacio")) {

			String mensajePront = "Revisa la lista, algunos campos están mal rellenados.";
			AlarmaList.mostrarMensajePront(mensajePront, false, referenciaVentana.getProntInfoTextArea());
			return false;
		}

		return true;
	}

	public static boolean isValidPrecio(String precioStr) {
		// Verificar si el precio es válido (no vacío, no solo un símbolo, no solo el
		// número 0)
		if (precioStr == null || precioStr.isEmpty()) {
			return false;
		}

		// Formatear el precio
		String formattedPrecio = parsePrecio(precioStr);

		System.out.println(formattedPrecio);

		// Verificar si el precio es "€0.0", "€0", "$0.0", "$0" o solo el símbolo de la
		// moneda
		if (formattedPrecio.equalsIgnoreCase("€0.0") || formattedPrecio.equalsIgnoreCase("€0")
				|| formattedPrecio.equalsIgnoreCase("$0.0") || formattedPrecio.equalsIgnoreCase("$0")
				|| formattedPrecio.equals("€") || formattedPrecio.equals("$")) {
			return false;
		}

		// Verificar si el precio consiste solo en un símbolo
		if (formattedPrecio.length() == 1) {
			return false;
		}

		// Verificar si el precio consiste solo en el número 0
		if (formattedPrecio.equals("0.0") || formattedPrecio.equals("0,0")) {
			return false;
		}

		return true;
	}

	public static String parsePrecio(String precioStr) {
		if (precioStr == null || precioStr.isEmpty()) {
			return "€0.0";
		}

		// Crear un formateador decimal que maneje símbolos y formatos específicos
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		DecimalFormat format = new DecimalFormat("#,##0.00", symbols);
		format.setParseBigDecimal(true);

		// Encontrar el símbolo de moneda al inicio de la cadena
		char simbolo = precioStr.charAt(0);

		// Eliminar todos los caracteres que no son dígitos, punto o coma
		String cleanPrecioStr = precioStr.replaceAll("[^0-9.,]", "");

		// Insertar el símbolo de moneda al inicio del número limpio
		cleanPrecioStr = simbolo + cleanPrecioStr;

		// Devolver el valor parseado como double
		return cleanPrecioStr;
	}

	/**
	 * Borra los datos del cómic
	 */
	public static void limpiarAutorellenos(boolean esPrincipal) {

		if (esPrincipal) {
			referenciaVentana.getProntInfoTextArea().clear();
			referenciaVentana.getProntInfoTextArea().setText(null);
			referenciaVentana.getProntInfoTextArea().setOpacity(0);
			referenciaVentana.getTablaBBDD().getItems().clear();
			referenciaVentana.getTablaBBDD().setOpacity(0.6);;
			referenciaVentana.getTablaBBDD().refresh();
			referenciaVentana.getImagenCarta().setImage(null);
			referenciaVentana.getImagenCarta().setOpacity(0);
			return;
		}

		referenciaVentana.getNombreCartaTextField().setText("");
		referenciaVentana.getNumeroCartaTextField().setText("");
		referenciaVentana.getAnioCartaTextField().setText("");
		referenciaVentana.getNombreEmpresaTextField().setText("");
		referenciaVentana.getCodigoCartaTextField().setText("");
		referenciaVentana.getEdicionCartaTextField().setText("");
		referenciaVentana.getColeccionCartaTextField().setText("");
		referenciaVentana.getGradeoCartaTextField().setText("");

		referenciaVentana.getIdCartaTratarTextField().setText("");

		referenciaVentana.getDireccionImagenTextField().setText("");
		referenciaVentana.getUrlReferenciaTextField().setText("");
		referenciaVentana.getNumeroCartaTextField().setText("");

		if ("aniadir".equals(AccionFuncionesComunes.TIPO_ACCION)) {
			referenciaVentana.getIdCartaTratarTextField().setDisable(false);
			referenciaVentana.getIdCartaTratarTextField().setText("");
			referenciaVentana.getIdCartaTratarTextField().setDisable(true);
		}

		if ("modificar".equals(AccionFuncionesComunes.TIPO_ACCION)) {
			referenciaVentana.getBotonModificarCarta().setVisible(false);
			referenciaVentana.getBotonEliminar().setVisible(false);
		}

		referenciaVentana.getProntInfoTextArea().setText(null);
		referenciaVentana.getProntInfoTextArea().setOpacity(0);
		referenciaVentana.getProntInfoTextArea().setStyle("");
		validarCamposClave(true);
	}

	public static void borrarDatosGraficos() {
		referenciaVentana.getProntInfoTextArea().setText(null);
		referenciaVentana.getProntInfoTextArea().setOpacity(0);
		referenciaVentana.getProntInfoTextArea().setStyle("");
	}

	/**
	 * Asigna tooltips a varios elementos en la interfaz gráfica. Estos tooltips
	 * proporcionan información adicional cuando el usuario pasa el ratón sobre los
	 * elementos.
	 */
	public static void establecerTooltips() {
		Platform.runLater(() -> {
			Map<Node, String> tooltipsMap = new HashMap<>();

			tooltipsMap.put(referenciaVentana.getNombreCartaCombobox(), "Nombre de los cómics / libros / mangas");
			tooltipsMap.put(referenciaVentana.getNumeroCartaCombobox(), "Número del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getNombreEdicionCombobox(),
					"Nombre de la variante del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getBotonLimpiar(), "Limpia la pantalla y reinicia todos los valores");
			tooltipsMap.put(referenciaVentana.getBotonbbdd(), "Botón para acceder a la base de datos");
			tooltipsMap.put(referenciaVentana.getBotonSubidaPortada(), "Botón para subir una portada");
			tooltipsMap.put(referenciaVentana.getBotonEliminar(), "Botón para eliminar un cómic");
			tooltipsMap.put(referenciaVentana.getBotonParametroCarta(),
					"Botón para buscar un cómic mediante una lista de parámetros");
			tooltipsMap.put(referenciaVentana.getBotonModificarCarta(), "Botón para modificar un cómic");

			tooltipsMap.put(referenciaVentana.getNombreColeccionCombobox(),
					"Nombre de la firma del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getNombreGradeoCombobox(),
					"Nombre del guionista del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getBotonIntroducir(),
					"Realizar una acción de introducción del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getBotonModificar(),
					"Realizar una acción de modificación del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getBotonEliminar(),
					"Realizar una acción de eliminación del cómic / libro / manga");
			tooltipsMap.put(referenciaVentana.getBotonMostrarParametro(),
					"Buscar por parámetros según los datos rellenados");

			FuncionesTooltips.assignTooltips(tooltipsMap);
		});
	}

	public static void autocompletarListas() {
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getNombreCartaTextField(),
				ListasCartasDAO.listaNombre);
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getEdicionCartaTextField(),
				ListasCartasDAO.listaEdicion);
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getColeccionCartaTextField(),
				ListasCartasDAO.listaColeccion);
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getGradeoCartaTextField(),
				ListasCartasDAO.listaGradeo);
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getNumeroCartaTextField(),
				ListasCartasDAO.listaNumeroCarta);
		FuncionesManejoFront.asignarAutocompletado(referenciaVentana.getNombreEmpresaTextField(),
				ListasCartasDAO.listaEmpresa);
	}

	public static void controlarEventosInterfaz() {

		referenciaVentana.getProntInfoTextArea().textProperty().addListener((observable, oldValue, newValue) -> {
			FuncionesTableView.ajustarAnchoVBox();
		});

		// Desactivar el enfoque en el VBox para evitar que reciba eventos de teclado
		referenciaVentana.getRootVBox().setFocusTraversable(false);

		// Agregar un filtro de eventos para capturar el enfoque en el TableView y
		// desactivar el enfoque en el VBox
		referenciaVentana.getTablaBBDD().addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			referenciaVentana.getRootVBox().setFocusTraversable(false);
			referenciaVentana.getTablaBBDD().requestFocus();
		});

		referenciaVentana.getImagenCarta().imageProperty().addListener((observable, oldImage, newImage) -> {
			if (newImage != null) {
				// Cambiar la apariencia del cursor y la opacidad cuando la imagen se ha cargado
				referenciaVentana.getImagenCarta().setOnMouseEntered(e -> {
					if (referenciaVentana.getImagenCarta() != null) {
						referenciaVentana.getImagenCarta().setOpacity(0.7); // Cambiar la opacidad para indicar que es
						// clickable
						referenciaVentana.getImagenCarta().setCursor(Cursor.HAND);
					}
				});

				// Restaurar el cursor y la opacidad al salir del ImageView
				referenciaVentana.getImagenCarta().setOnMouseExited(e -> {

					if (referenciaVentana.getImagenCarta() != null) {

						referenciaVentana.getImagenCarta().setOpacity(1.0); // Restaurar la opacidad
						referenciaVentana.getImagenCarta().setCursor(Cursor.DEFAULT);
					}

				});
			} else {
				// Restaurar el cursor y la opacidad al salir del ImageView
				referenciaVentana.getImagenCarta().setOnMouseEntered(e -> {
					referenciaVentana.getImagenCarta().setCursor(Cursor.DEFAULT);
				});
			}
		});

	}

	public static void controlarEventosInterfazAccion() {
		controlarEventosInterfaz();

		// Establecemos un evento para detectar cambios en el segundo TextField
		referenciaVentana.getIdCartaTratarTextField().textProperty().addListener((observable, oldValue, newValue) -> {
			// Verificar que newValue no sea null antes de usarlo
			AccionSeleccionar.mostrarCarta(newValue, false);
		});

		List<Node> elementos = Arrays.asList(referenciaVentana.getBotonGuardarCarta(),
				referenciaVentana.getBotonEliminarImportadoCarta(),
				referenciaVentana.getBotonEliminarImportadoListaCarta(), referenciaVentana.getBotonGuardarListaCartas(),
				referenciaVentana.getBotonEliminarImportadoCarta(), referenciaVentana.getBotonGuardarCarta());

		ListasCartasDAO.cartasImportados.addListener((ListChangeListener<CartaGradeo>) change -> {
			while (change.next()) {

				if (!change.wasAdded() && ListasCartasDAO.cartasImportados.isEmpty()) {
					Utilidades.cambiarVisibilidad(elementos, true);
				}
			}
		});
	}

	public static void controlarEventosInterfazPrincipal(AccionReferencias referenciaVentana) {
		controlarEventosInterfaz();

		referenciaVentana.getTablaBBDD().getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {

					if (newSelection != null) {
						// Esto algo hace pero seguramente cambie de idea. Mejor no tocar
//						Carta idRow = referenciaVentana.getTablaBBDD().getSelectionModel().getSelectedItem();
					}
				});

		// Establecer un Listener para el tamaño del AnchorPane
		referenciaVentana.getRootAnchorPane().widthProperty().addListener((observable, oldValue, newValue) -> {

			FuncionesTableView.ajustarAnchoVBox();
			FuncionesTableView.seleccionarRaw();
			FuncionesTableView.modificarColumnas(true);
		});
	}

	public static CartaGradeo camposCarta(List<String> camposCarta, boolean esAccion) {
		CartaGradeo cartaTemp = new CartaGradeo();

		// Asignar los valores a las variables correspondientes
		String nomCarta = camposCarta.get(0);
		String numCarta = camposCarta.get(1);
		String edicionCarta = camposCarta.get(2);
		String coleccionCarta = camposCarta.get(3);
		String gradeoCarta = camposCarta.get(4);

		String empresaCarta = "";
		String codigoCarta = "";
		String urlReferenciaCarta = "";
		String direccionImagenCarta = "";
		String idCartaTratar = "";
		String anioCarta = "";
		if (esAccion) {

			urlReferenciaCarta = camposCarta.get(5);
			empresaCarta = camposCarta.get(6);
			anioCarta = camposCarta.get(7);
			codigoCarta = camposCarta.get(8);
			idCartaTratar = camposCarta.get(9);
			direccionImagenCarta = camposCarta.get(10);
		}

		cartaTemp.setNomCarta(Utilidades.defaultIfNullOrEmpty(nomCarta, ""));
		cartaTemp.setNumCarta(Utilidades.defaultIfNullOrEmpty(numCarta, ""));
		cartaTemp.setEdicionCarta(Utilidades.defaultIfNullOrEmpty(edicionCarta, ""));
		cartaTemp.setColeccionCarta(Utilidades.defaultIfNullOrEmpty(coleccionCarta, ""));
		cartaTemp.setGradeoCarta(Utilidades.defaultIfNullOrEmpty(gradeoCarta, ""));
		cartaTemp.setEmpresaCarta(Utilidades.defaultIfNullOrEmpty(empresaCarta, ""));
		cartaTemp.setAnioCarta(Utilidades.defaultIfNullOrEmpty(anioCarta, ""));
		cartaTemp.setCodCarta(Utilidades.defaultIfNullOrEmpty(codigoCarta, ""));
		cartaTemp.setUrlReferenciaCarta(Utilidades.defaultIfNullOrEmpty(urlReferenciaCarta, ""));
		cartaTemp.setDireccionImagenCarta(Utilidades.defaultIfNullOrEmpty(direccionImagenCarta, ""));
		cartaTemp.setIdCarta(Utilidades.defaultIfNullOrEmpty(idCartaTratar, ""));

		return cartaTemp;
	}

	public static List<String> comprobarYDevolverLista(List<ComboBox<String>> comboBoxes,
			ObservableList<Control> observableList) {
		List<String> valores = new ArrayList<>();
		for (ComboBox<String> comboBox : comboBoxes) {
			valores.add(comboBox.getValue() != null ? comboBox.getValue() : "");
		}
		if (contieneNulo(comboBoxes)) {
			return Arrays.asList(observableList.stream()
					.map(control -> control instanceof TextInputControl ? ((TextInputControl) control).getText() : "")
					.toArray(String[]::new));
		} else {
			return valores;
		}
	}

	private static <T> boolean contieneNulo(List<T> lista) {

		if (lista == null) {
			return false;
		}

		for (T elemento : lista) {
			if (elemento == null) {
				return true;
			}
		}
		return false;
	}

	public static CartaGradeo cartaModificado() {

		String id_cartaTemp = referenciaVentana.getIdCartaTratarTextField().getText();

		CartaGradeo cartaTempTemp = CartaManagerDAO.cartaDatos(id_cartaTemp);

		List<String> controls = new ArrayList<>();

		for (Control control : AccionReferencias.getListaTextFields()) {
			controls.add(((TextField) control).getText()); // Add the Control object itself
		}

		// Añadir valores de los ComboBoxes de getListaComboboxes() a controls
		for (ComboBox<?> comboBox : AccionReferencias.getListaComboboxes()) {
			Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
			controls.add(selectedItem != null ? selectedItem.toString() : "");
		}

		CartaGradeo datos = camposCarta(controls, true);

		int numCarta = Integer.parseInt(datos.getNumCarta());

		CartaGradeo cartaTempModificado = new CartaGradeo();
		cartaTempModificado.setIdCarta(cartaTempTemp.getIdCarta());
		cartaTempModificado
				.setNomCarta(Utilidades.defaultIfNullOrEmpty(datos.getNomCarta(), cartaTempTemp.getNomCarta()));
		cartaTempModificado.setNumCarta(numCarta != 0 ? datos.getNumCarta() : cartaTempTemp.getNumCarta());
		cartaTempModificado.setEdicionCarta(
				Utilidades.defaultIfNullOrEmpty(datos.getEdicionCarta(), cartaTempTemp.getEdicionCarta()));
		cartaTempModificado.setColeccionCarta(
				Utilidades.defaultIfNullOrEmpty(datos.getColeccionCarta(), cartaTempTemp.getColeccionCarta()));
		cartaTempModificado.setGradeoCarta(
				Utilidades.defaultIfNullOrEmpty(datos.getGradeoCarta(), cartaTempTemp.getGradeoCarta()));
		cartaTempModificado.setUrlReferenciaCarta(
				Utilidades.defaultIfNullOrEmpty(datos.getUrlReferenciaCarta(), cartaTempTemp.getUrlReferenciaCarta()));
		cartaTempModificado.setDireccionImagenCarta(Utilidades.defaultIfNullOrEmpty(datos.getDireccionImagenCarta(),
				cartaTempTemp.getDireccionImagenCarta()));
		cartaTempModificado.setEmpresaCarta(
				Utilidades.defaultIfNullOrEmpty(datos.getEmpresaCarta(), cartaTempTemp.getEmpresaCarta()));

		// Si hay otros campos que deben ser ignorados, simplemente no los incluimos
		// A continuación, puedes agregar cualquier lógica adicional que necesites

		return cartaTempModificado;

	}

	public static AccionReferencias getReferenciaVentana() {
		return referenciaVentana;
	}

	public static void setReferenciaVentana(AccionReferencias referenciaVentana) {
		AccionControlUI.referenciaVentana = referenciaVentana;
	}

}