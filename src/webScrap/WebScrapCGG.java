package webScrap;

import java.io.File;
import java.util.List;

import cartaManagement.CartaGradeo;
import ficherosFunciones.FuncionesFicheros;

public class WebScrapCGG {

	public static CartaGradeo extraerDatosMTG(String codigoCarta) {
		String scriptPath = FuncionesFicheros.rutaDestinoRecursos + File.separator + "scrapCGG.js";
		List<String> data = FuncionesScrapeoComunes.getCartaFromPuppeteer(codigoCarta, scriptPath);

		String nombre = "";
		String codigo = "";
		String numero = "";
		String anio = "";
		String coleccion = "";
		String edicion = "";
		String empresa = "";
		String gradeo = "";
		String imagen = "";
		String referencia = "";

		for (String line : data) {
			if (line.startsWith("Nombre: ")) {
				nombre = line.substring("Nombre: ".length()).trim().replaceAll("\\(.*\\)", "").trim();
			} 
			else if (line.startsWith("Codigo: ")) {
				codigo = line.substring("Codigo: ".length()).trim();
			}
			else if (line.startsWith("Numero: ")) {
				numero = line.substring("Numero: ".length()).trim();
			}
			else if (line.startsWith("Anio: ")) {
				anio = line.substring("Anio: ".length()).trim();
			}
			else if (line.startsWith("Coleccion: ")) {
				coleccion = line.substring("Coleccion: ".length()).trim();
			}
			else if (line.startsWith("Edicion: ")) {
				edicion = line.substring("Normas: ".length()).trim();
			}
			else if (line.startsWith("Empresa: ")) {
				empresa = line.substring("Empresa: ".length()).trim();
			}
			else if (line.startsWith("Gradeo: ")) {
				gradeo = line.substring("Gradeo: ".length()).trim();
			}
			else if (line.startsWith("Imagen: ")) {
				imagen = line.substring("Imagen: ".length()).trim();
			}
			else if (line.startsWith("Referencia: ")) {
				referencia = line.substring("Referencia: ".length()).trim();
			}
		}
		CartaGradeo carta = new CartaGradeo.CartaGradeoBuilder("", nombre).codCarta(codigo).numCarta(numero).anioCarta(anio)
				.coleccionCarta(coleccion).edicionCarta(edicion).empresaCarta(empresa).gradeoCarta(gradeo)
				.urlReferenciaCarta(referencia).direccionImagenCarta(imagen).build();
		carta.sustituirCaracteres(carta);
		return carta;
	}

	public static CartaGradeo devolverCartaBuscada(String urlCarta) {
		return extraerDatosMTG(urlCarta);
	}
}
