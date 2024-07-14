package cartaManagement;

import java.util.Objects;

import dbmanager.CartaManagerDAO;
import dbmanager.ListasCartasDAO;
import funcionesAuxiliares.Utilidades;

/**
 * Esta clase objeto sirve para dar forma al comic que estara dentro de la base
 * de datos.
 *
 * @author Alejandro Rodriguez
 */
public class CartaGradeo implements Cloneable {

    protected String idCarta;
    protected String nomCarta;
    protected String codCarta;
    protected String numCarta;
    protected String anioCarta;
    protected String coleccionCarta;
    protected String edicionCarta;
    protected String empresaCarta;
    protected String gradeoCarta;
    protected String direccionImagenCarta;
    protected String urlReferenciaCarta;

    public static class CartaGradeoBuilder {
        private String idCarta;
        private String nomCarta;
        private String codCarta;
        private String numCarta;
        private String anioCarta;
        private String coleccionCarta;
        private String edicionCarta;
        private String empresaCarta;
        private String gradeoCarta;
        private String direccionImagenCarta;
        private String urlReferenciaCarta;

        public CartaGradeoBuilder(String idCarta, String nomCarta) {
            this.idCarta = idCarta;
            this.nomCarta = nomCarta;
        }

        public CartaGradeoBuilder codCarta(String codCarta) {
            this.codCarta = codCarta;
            return this;
        }

        public CartaGradeoBuilder numCarta(String numCarta) {
            this.numCarta = numCarta;
            return this;
        }

        public CartaGradeoBuilder anioCarta(String anioCarta) {
            this.anioCarta = anioCarta;
            return this;
        }

        public CartaGradeoBuilder coleccionCarta(String coleccionCarta) {
            this.coleccionCarta = coleccionCarta;
            return this;
        }

        public CartaGradeoBuilder edicionCarta(String edicionCarta) {
            this.edicionCarta = edicionCarta;
            return this;
        }

        public CartaGradeoBuilder empresaCarta(String empresaCarta) {
            this.empresaCarta = empresaCarta;
            return this;
        }

        public CartaGradeoBuilder gradeoCarta(String gradeoCarta) {
            this.gradeoCarta = gradeoCarta;
            return this;
        }

        public CartaGradeoBuilder direccionImagenCarta(String direccionImagenCarta) {
            this.direccionImagenCarta = direccionImagenCarta;
            return this;
        }

        public CartaGradeoBuilder urlReferenciaCarta(String urlReferenciaCarta) {
            this.urlReferenciaCarta = urlReferenciaCarta;
            return this;
        }

        public CartaGradeo build() {
            return new CartaGradeo(this);
        }
    }

    private CartaGradeo(CartaGradeoBuilder builder) {
        this.idCarta = builder.idCarta;
        this.nomCarta = builder.nomCarta;
        this.codCarta = builder.codCarta;
        this.numCarta = builder.numCarta;
        this.anioCarta = builder.anioCarta;
        this.coleccionCarta = builder.coleccionCarta;
        this.edicionCarta = builder.edicionCarta;
        this.empresaCarta = builder.empresaCarta;
        this.gradeoCarta = builder.gradeoCarta;
        this.direccionImagenCarta = builder.direccionImagenCarta;
        this.urlReferenciaCarta = builder.urlReferenciaCarta;
    }

    public static CartaGradeo obtenerCarta(String idCarta) {
        boolean existeComic = CartaManagerDAO.comprobarIdentificadorCarta(idCarta);
        if (!existeComic) {
            existeComic = ListasCartasDAO.verificarIDExistente(idCarta);
            if (existeComic) {
                return ListasCartasDAO.devolverCartaLista(idCarta);
            }
        } else {
            return CartaManagerDAO.cartaDatos(idCarta);
        }
        return null;
    }

    public boolean estaVacio() {
        return isNullOrEmpty(this.nomCarta) && this.numCarta.equals("0") && isNullOrEmpty(this.coleccionCarta)
                && isNullOrEmpty(this.gradeoCarta) && isNullOrEmpty(this.urlReferenciaCarta)
                && isNullOrEmpty(this.direccionImagenCarta) && isNullOrEmpty(this.edicionCarta);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static void limpiarCamposCarta(CartaGradeo carta) {
        carta.setNomCarta(limpiarCampo(carta.getNomCarta()));
        carta.setColeccionCarta(limpiarCampo(carta.getColeccionCarta()));
        carta.setGradeoCarta(limpiarCampo(carta.getGradeoCarta()));
        carta.setEdicionCarta(limpiarCampo(carta.getEdicionCarta()));
    }

    public static String limpiarCampo(String campo) {
        if (campo != null) {
            campo = campo.replaceAll("^\\s*[,\\s-]+", ""); // Al principio
            campo = campo.replaceAll("[,\\s-]+\\s*$", ""); // Al final
            campo = campo.replaceAll(",\\s*,", ","); // Comas repetidas
            campo = campo.replaceAll(",\\s*", " - "); // Reemplazar ", " por " - "
            campo = campo.replace("'", " "); // Reemplazar ", " por " - "
        } else {
            return "";
        }
        return campo;
    }

    public static boolean validarCarta(CartaGradeo carta) {
        if (carta.getNomCarta() != null && !carta.getNomCarta().isEmpty()) {
            return true;
        }
        if (!carta.getNumCarta().equals("0")) {
            return true;
        }
        if (carta.getColeccionCarta() != null && !carta.getColeccionCarta().isEmpty()) {
            return true;
        }
        if (carta.getGradeoCarta() != null && !carta.getGradeoCarta().isEmpty()) {
            return true;
        }
        if (carta.getEdicionCarta() != null && !carta.getEdicionCarta().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder contenidoCarta = new StringBuilder();

        Utilidades.appendIfNotEmpty(contenidoCarta, "Nombre", nomCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Código", codCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Número", numCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Año", anioCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Colección", coleccionCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Edición", edicionCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Empresa", empresaCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Gradeo", gradeoCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "URL Referencia", urlReferenciaCarta);
        Utilidades.appendIfNotEmpty(contenidoCarta, "Dirección Imagen", direccionImagenCarta);

        return contenidoCarta.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartaGradeo carta = (CartaGradeo) o;
        return Objects.equals(codCarta, carta.codCarta) && Objects.equals(nomCarta, carta.nomCarta)
                && Objects.equals(numCarta, carta.numCarta) && Objects.equals(anioCarta, carta.anioCarta)
                && Objects.equals(coleccionCarta, carta.coleccionCarta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCarta, nomCarta, numCarta, anioCarta, coleccionCarta);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Getters y setters
    public String getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(String idCarta) {
        this.idCarta = idCarta;
    }

    public String getNomCarta() {
        return nomCarta;
    }

    public void setNomCarta(String nomCarta) {
        this.nomCarta = nomCarta;
    }

    public String getCodCarta() {
        return codCarta;
    }

    public void setCodCarta(String codCarta) {
        this.codCarta = codCarta;
    }

    public String getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(String numCarta) {
        this.numCarta = numCarta;
    }

    public String getAnioCarta() {
        return anioCarta;
    }

    public void setAnioCarta(String anioCarta) {
        this.anioCarta = anioCarta;
    }

    public String getColeccionCarta() {
        return coleccionCarta;
    }

    public void setColeccionCarta(String coleccionCarta) {
        this.coleccionCarta = coleccionCarta;
    }

    public String getEdicionCarta() {
        return edicionCarta;
    }

    public void setEdicionCarta(String edicionCarta) {
        this.edicionCarta = edicionCarta;
    }

    public String getEmpresaCarta() {
        return empresaCarta;
    }

    public void setEmpresaCarta(String empresaCarta) {
        this.empresaCarta = empresaCarta;
    }

    public String getGradeoCarta() {
        return gradeoCarta;
    }

    public void setGradeoCarta(String gradeoCarta) {
        this.gradeoCarta = gradeoCarta;
    }

    public String getDireccionImagenCarta() {
        return direccionImagenCarta;
    }

    public void setDireccionImagenCarta(String direccionImagenCarta) {
        this.direccionImagenCarta = direccionImagenCarta;
    }

    public String getUrlReferenciaCarta() {
        return urlReferenciaCarta;
    }

    public void setUrlReferenciaCarta(String urlReferenciaCarta) {
        this.urlReferenciaCarta = urlReferenciaCarta;
    }
}
