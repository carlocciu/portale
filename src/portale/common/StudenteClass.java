package portale.common;

public class StudenteClass {

    private String nomeStudente;

    private String cognomeStudente;

    private String matricolaStudente;

    private String indirizzoEmail;

    private String recapito;

    public StudenteClass(String nomeStudente, String cognomeStudente, String matricolaStudente) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.matricolaStudente = matricolaStudente;

    }

    public String getNomeStudente() {
        return nomeStudente;
    }

    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    public String getCognomeStudente() {
        return cognomeStudente;
    }

    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

    public String getRecapito() {
        return recapito;
    }

    public void setRecapito(String recapito) {
        this.recapito = recapito;
    }

    @Override
    public String toString() {
        return matricolaStudente + " - " + nomeStudente + " " + cognomeStudente;
    }
}
