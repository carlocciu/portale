package portale.entities;

public class StudenteClass {

    private String nomeStudente;

    private String cognomeStudente;

    private String matricolaStudente;

    private String indirizzoEmail;

    private String recapito;

    public StudenteClass(String nomeStudente, String cognomeStudente, String indirizzoEmail, String matricolaStudente, String recapito) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.indirizzoEmail = indirizzoEmail;
        this.matricolaStudente = matricolaStudente;
        this.recapito = recapito;
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
}
