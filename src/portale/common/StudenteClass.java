package portale.common;

/**
 * Classe contenente le informazioni relative ad uno studente
 */
public class StudenteClass {

    /**
     * Nome studente
     */
    private String nomeStudente;

    /**
     * Cognome studente
     */
    private String cognomeStudente;

    /**
     * Matricola studente
     */
    private String matricolaStudente;

    /**
     * Indirizzo e-mail studente
     */
    private String indirizzoEmail;

    /**
     * Recapito telefonico dello studente
     */
    private String recapito;

    /**
     * Genera un oggetto StudenteClass
     * @param nomeStudente nome studente
     * @param cognomeStudente cognome studente
     * @param matricolaStudente matricola studente
     */
    public StudenteClass(String nomeStudente, String cognomeStudente, String matricolaStudente) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.matricolaStudente = matricolaStudente;

    }

    /**
     * Ritorna il nome dello studente
     * @return nome studente
     */
    public String getNomeStudente() {
        return nomeStudente;
    }

    /**
     * Setta il nome dello studente
     * @param nomeStudente nome studente
     */
    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    /**
     * Ritorna il cognome dello studente
     * @return cognome studente
     */
    public String getCognomeStudente() {
        return cognomeStudente;
    }

    /**
     * Setta il cognome dello studente
     * @param cognomeStudente cognome studente
     */
    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    /**
     * Ritorna la matricola dello studente
     * @return matricola studente
     */
    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    /**
     * Setta la matricola dello studente
     * @param matricolaStudente matricola studente
     */
    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    /**
     * Ritorna l'indirizzo e-mail dello studente
     * @return indirizzo e-mail studente
     */
    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    /**
     * Setta l'indirizzo e-mail dello studente
     * @param indirizzoEmail indirizzo e-mail
     */
    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

    /**
     * Ritorna il recapito telefonico dello studente
     * @return recapito
     */
    public String getRecapito() {
        return recapito;
    }

    /**
     * Setta il recapito telefonico dello studente
     * @param recapito recapito
     */
    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    /**
     * Override del metodo toString() per la classe StudenteClass
     * @return una stringa contenente matricola, nome e cognome dello studente
     */
    @Override
    public String toString() {
        return matricolaStudente + " - " + nomeStudente + " " + cognomeStudente;
    }
}
