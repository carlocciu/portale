package portale.entities;


public class DocenteClass {

    private String nomeDocente;

    private String cognomeDocente;

    private String matricolaDocente;

    public DocenteClass(){

        this.cognomeDocente = "";
        this.nomeDocente = "";
        this.matricolaDocente = "";
    }

    public DocenteClass(String matricolaDocente, String nomeDocente, String cognomeDocente) {
        this.matricolaDocente = matricolaDocente;
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public String getCognomeDocente() {
        return cognomeDocente;
    }

    public void setCognomeDocente(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }

    public String getMatricolaDocente() {
        return matricolaDocente;
    }

    public void setMatricolaDocente(String matricolaDocente) {
        this.matricolaDocente = matricolaDocente;
    }
}
