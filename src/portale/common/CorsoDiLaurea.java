package portale.common;

public class CorsoDiLaurea {

    private String nomeCorso;

    private String codiceCorso;

    public CorsoDiLaurea(String codiceCorso, String nomeCorso) {
        this.codiceCorso = codiceCorso;
        this.nomeCorso = nomeCorso;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public String getCodiceCorso() {
        return codiceCorso;
    }

    public void setCodiceCorso(String codiceCorso) {
        this.codiceCorso = codiceCorso;
    }

    @Override
    public String toString() {
        return codiceCorso + " - " + nomeCorso;
    }
}
