package portale.common;

/**
 * Classe che contiene le informazioni relative ad una scuola
 */
public class Scuola {
    /**
     * Id scuola
     */
    String idScuola;
    /**
     * nome scuola
     */
    String nomeScuola;

    /**
     * Genera un oggetto Scuola
     * @param idScuola id scuola
     * @param nomeScuola nome scuola
     */
    public Scuola(String idScuola, String nomeScuola) {
        this.idScuola = idScuola;
        this.nomeScuola = nomeScuola;
    }

    /**
     * Ritorna l'id della scuola
     * @return
     */
    public String getIdScuola() {
        return idScuola;
    }

    /**
     * Ritorna il nome della scuola
     * @return
     */
    public String getNomeScuola() {
        return nomeScuola;
    }

    /**
     * Override del metodo toString() per la classe Scuola
     * @return una stringa contenente il nome della scuola
     */
    @Override
    public String toString() {
        return nomeScuola;
    }
}
