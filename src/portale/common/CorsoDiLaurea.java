package portale.common;

/**
 * Classe che contiene le informazioni di un corso di laurea
 */
public class CorsoDiLaurea {

    /**
     * Nome del corso
     */
    private String nomeCorso;

    /**
     * Codice del corso
     */
    private String codiceCorso;

    /**
     * Genera un oggetto CorsoDiLaurea
     * @param codiceCorso codice del corso
     * @param nomeCorso nome del corso
     */
    public CorsoDiLaurea(String codiceCorso, String nomeCorso) {
        this.codiceCorso = codiceCorso;
        this.nomeCorso = nomeCorso;
    }

    /**
     * Ritorna il nome del corso
     * @return nome corso
     */
    public String getNomeCorso() {
        return nomeCorso;
    }

    /**
     * Setta il nome del corso
     * @param nomeCorso nome del corso
     */
    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    /**
     * Ritorna il codice del corso
     * @return codice corso
     */
    public String getCodiceCorso() {
        return codiceCorso;
    }

    /**
     * Setta il codice del corso
     * @param codiceCorso codice del corso
     */
    public void setCodiceCorso(String codiceCorso) {
        this.codiceCorso = codiceCorso;
    }

    /**
     * Override del metodo toString() per la classe CorsoDiLaurea
     * @return una stringa contenente codice e nome del corso
     */
    @Override
    public String toString() {
        return codiceCorso + " - " + nomeCorso;
    }
}
