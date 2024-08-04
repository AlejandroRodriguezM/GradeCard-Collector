package funcionesManagment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.PropertySheet.Item;

import Controladores.ImagenAmpliadaController;
import alarmas.AlarmaList;
import cartaManagement.CartaGradeo;
import dbmanager.CartaManagerDAO;
import dbmanager.DBUtilidades;
import dbmanager.DBUtilidades.TipoBusqueda;
import dbmanager.ListasCartasDAO;
import dbmanager.SelectManager;
import funcionesAuxiliares.Utilidades;
import funcionesAuxiliares.Ventanas;
import funcionesInterfaz.AccionControlUI;
import funcionesInterfaz.FuncionesTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class AccionSeleccionar {

	private static AccionReferencias referenciaVentana = getReferenciaVentana();

	private static AccionControlUI accionRellenoDatos = new AccionControlUI();

	/**
	 * Método para seleccionar y mostrar detalles de un cómic en la interfaz
	 * gráfica. Si la lista de cómics importados no está vacía, utiliza la
	 * información de la lista; de lo contrario, consulta la base de datos para
	 * obtener la información del cómic.
	 * 
	 * @throws SQLException Si se produce un error al acceder a la base de datos.
	 */
	public static void seleccionarCartas(boolean esPrincipal) {

		FuncionesTableView.nombreColumnas();
		Utilidades.comprobacionListaCartas();
		getReferenciaVentana().getImagenCarta().setOpacity(1);
		CartaGradeo newSelection = getReferenciaVentana().getTablaBBDD().getSelectionModel().getSelectedItem();

		Scene scene = getReferenciaVentana().getTablaBBDD().getScene();
		@SuppressWarnings("unchecked")
		final List<Node>[] elementos = new ArrayList[1];
		elementos[0] = new ArrayList<>();

		if (!esPrincipal) {
			elementos[0] = AccionControlUI.modificarInterfazAccion(AccionFuncionesComunes.getTipoAccion());
		}

		if (scene != null) {
			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

				getReferenciaVentana().getImagenCarta().setVisible(true);
				if (!getReferenciaVentana().getTablaBBDD().isHover()) {
					if (esPrincipal) {
						getReferenciaVentana().getTablaBBDD().getSelectionModel().clearSelection();
					} else {

						if (!"aniadir".equals(AccionFuncionesComunes.TIPO_ACCION)) {
							getReferenciaVentana().getTablaBBDD().getSelectionModel().clearSelection();
						}

						if ("modificar".equals(AccionFuncionesComunes.TIPO_ACCION)) {
							AccionControlUI.mostrarOpcion(AccionFuncionesComunes.TIPO_ACCION);
						}
						Utilidades.cambiarVisibilidad(elementos[0], true);

						// Borrar cualquier mensaje de error presente
						AccionFuncionesComunes.borrarErrores();
						AccionControlUI.validarCamposClave(true);
					}
				}
			});

		}

		// Verificar si idRow es nulo antes de intentar acceder a sus métodos
		if (newSelection != null) {
			String idCarta = newSelection.getIdCarta();
			mostrarCarta(idCarta, esPrincipal);
			Utilidades.cambiarVisibilidad(elementos[0], false);
		}

		getReferenciaVentana().getImagenCarta().setOnMouseClicked(event -> {
			CartaGradeo carta = newSelection;
			ImagenAmpliadaController.setCartaCache(carta);
			Ventanas.verVentanaImagen();
			getReferenciaVentana().getImagenCarta().setVisible(false);
			AccionControlUI.limpiarAutorellenos(esPrincipal);
		});

	}

	public static void actualizarRefrenciaClick(AccionReferencias referenciaFXML) {
		Scene scene = getReferenciaVentana().getTablaBBDD().getScene();

		if (scene != null) {
			scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				setReferenciaVentana(referenciaFXML);
			});


		}
	}

	public static void mostrarCarta(String idCarta, boolean esPrincipal) {

		CartaGradeo comicTemp = null;
		AlarmaList.detenerAnimacion();
		String mensaje = "";

		if (!ListasCartasDAO.cartasImportados.isEmpty() && !esPrincipal) {
			comicTemp = ListasCartasDAO.buscarCartaPorID(ListasCartasDAO.cartasImportados, idCarta);

		} else if (CartaManagerDAO.comprobarIdentificadorCarta(idCarta)) {
			comicTemp = CartaManagerDAO.cartaDatos(idCarta);
		}

		if (idCarta == null || idCarta.isEmpty() || comicTemp == null) {
			AccionControlUI.limpiarAutorellenos(esPrincipal);
			return;
		}
		referenciaVentana.getImagenCarta().setOpacity(1);
		if (!esPrincipal) {
			accionRellenoDatos.setAtributosDesdeTabla(comicTemp);
			AccionControlUI.validarCamposClave(false);

			if (AccionFuncionesComunes.TIPO_ACCION.equals("modificar")) {
				AccionControlUI.mostrarOpcion(AccionFuncionesComunes.TIPO_ACCION);
				getReferenciaVentana().getIdCartaTratarTextField().setText(comicTemp.getIdCarta());
			}
		} else {
			Utilidades.cargarImagenAsync(comicTemp.getDireccionImagenCarta(), getReferenciaVentana().getImagenCarta());
		}

		getReferenciaVentana().getProntInfoTextArea().setOpacity(1);

		if (!ListasCartasDAO.cartasImportados.isEmpty() && CartaManagerDAO.comprobarIdentificadorCarta(idCarta)) {
			mensaje = CartaManagerDAO.cartaDatos(idCarta).toString().replace("[", "").replace("]", "");
		} else {
			mensaje = comicTemp.toString().replace("[", "").replace("]", "");
		}
		getReferenciaVentana().getProntInfoTextArea().clear();
		getReferenciaVentana().getProntInfoTextArea().setText(mensaje);

	}

	public static void verBasedeDatos(boolean completo, boolean esAccion, CartaGradeo comic) {

		ListasCartasDAO.reiniciarListaCartas();
		getReferenciaVentana().getTablaBBDD().refresh();
		getReferenciaVentana().getProntInfoTextArea().setOpacity(0);
		getReferenciaVentana().getImagenCarta().setVisible(false);
		getReferenciaVentana().getImagenCarta().setImage(null);
		getReferenciaVentana().getProntInfoTextArea().setText(null);
		getReferenciaVentana().getProntInfoTextArea().clear();

		FuncionesTableView.nombreColumnas();
		FuncionesTableView.actualizarBusquedaRaw();

		if (CartaManagerDAO.countRows() > 0) {
			if (completo) {

				String sentenciaSQL = DBUtilidades.construirSentenciaSQL(TipoBusqueda.COMPLETA);

				List<CartaGradeo> listaCartas = CartaManagerDAO.verLibreria(sentenciaSQL);

				FuncionesTableView.tablaBBDD(listaCartas);

			} else {

				List<CartaGradeo> listaParametro = listaPorParametro(comic, esAccion);

				FuncionesTableView.tablaBBDD(listaParametro);

				if (!esAccion) {
					getReferenciaVentana().getBusquedaGeneralTextField().setText("");
				}

			}
		} else {
			String mensaje = "ERROR. No hay datos en la base de datos";

			AlarmaList.mostrarMensajePront(mensaje, false, getReferenciaVentana().getProntInfoTextArea());
		}
	}

	/**
	 * Funcion que comprueba segun los datos escritos en los textArea, que comic
	 * estas buscando.
	 * 
	 * @throws SQLException
	 */
	public static List<CartaGradeo> listaPorParametro(CartaGradeo datos, boolean esAccion) {
		String busquedaGeneralTextField = "";

		if (!esAccion) {
			busquedaGeneralTextField = getReferenciaVentana().getBusquedaGeneralTextField().getText();
		}

		List<CartaGradeo> listCarta = FXCollections
				.observableArrayList(SelectManager.busquedaParametro(datos, busquedaGeneralTextField));

		if (!listCarta.isEmpty()) {
			getReferenciaVentana().getProntInfoTextArea().setOpacity(1);
			getReferenciaVentana().getProntInfoTextArea().setStyle("-fx-text-fill: black;"); // Reset the text color to
																								// black
			getReferenciaVentana().getProntInfoTextArea()
					.setText("El número de cómics donde aparece la búsqueda es: " + listCarta.size() + "\n \n \n");
		} else if (listCarta.isEmpty() && esAccion) {
			getReferenciaVentana().getProntInfoTextArea().setOpacity(1);
			// Show error message in red when no search fields are specified
			getReferenciaVentana().getProntInfoTextArea().setStyle("-fx-text-fill: red;");
			getReferenciaVentana().getProntInfoTextArea().setText("Error. No existen con dichos parametros.");
		}

		return listCarta;
	}

	public static AccionReferencias getReferenciaVentana() {
		return referenciaVentana;
	}

	public static void setReferenciaVentana(AccionReferencias referenciaVentana) {
		AccionSeleccionar.referenciaVentana = referenciaVentana;
	}

}
