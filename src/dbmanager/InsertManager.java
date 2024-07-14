package dbmanager;

import java.io.IOException;
import java.sql.SQLException;

import cartaManagement.CartaGradeo;

public class InsertManager {

	public static final String INSERT_SENTENCIA = "INSERT INTO albumbbdd ("
			+ "nomCarta, codCarta, numCarta, anioCarta, coleccionCarta, edicionCarta, empresaCarta, gradeoCarta, "
			+ "urlReferenciaCarta, direccionImagenCarta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * Inserta los datos de un cómic en la base de datos.
	 *
	 * @param comicDatos los datos del cómic a insertar
	 * @throws IOException  si ocurre un error al manejar el archivo de imagen
	 * @throws SQLException si ocurre un error al ejecutar la consulta SQL
	 */
	public static void insertarDatos(CartaGradeo datos, boolean esImportar) {

		DatabaseManagerDAO.subirCarta(datos, esImportar);
	}

}
