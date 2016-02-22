package portale.common;

/**
 * Classe che contiene le informazioni relative ad un docente
 */
public class DocenteClass {

    /**
     * Nome docente
     */
    private String nomeDocente;

    /**
     * Cognome docente
     */
    private String cognomeDocente;

    /**
     * Matricola docente
     */
    private String matricolaDocente;

    /**
     * Genera un oggetto DocenteClass vuoto
     */
    public DocenteClass() {

        this.cognomeDocente = "";
        this.nomeDocente = "";
        this.matricolaDocente = "";
    }

    /**
     * Genera un oggetto DocenteClass
     * @param matricolaDocente matricola docente
     * @param nomeDocente nome docente
     * @param cognomeDocente cognome docente
     */
    public DocenteClass(String matricolaDocente, String nomeDocente, String cognomeDocente) {
        this.matricolaDocente = matricolaDocente;
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
    }

    /**
     * Ritorna il nome del docente
     * @return nome docente
     */
    public String getNomeDocente() {
        return nomeDocente;
    }

    /**
     * Setta il nome del docente
     * @param nomeDocente nome docente
     */
    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    /**
     * Ritorna il cognome del docente
     * @return cognome docente
     */
    public String getCognomeDocente() {
        return cognomeDocente;
    }

    /**
     * Setta il cognome del docente
     * @param cognomeDocente cognome docente
     */
    public void setCognomeDocente(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }

    /**
     * Ritorna la matricola del docente
     * @return matricola docente
     */
    public String getMatricolaDocente() {
        return matricolaDocente;
    }

    /**
     * Setta la matricola del docente
     * @param matricolaDocente matricola docente
     */
    public void setMatricolaDocente(String matricolaDocente) {
        this.matricolaDocente = matricolaDocente;
    }
}
