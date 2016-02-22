package portale.common;

/**
 * Created by carlonuccio on 19/02/16.
 */
public class Scuola {
    String idScuola;
    String nomeScuola;

    public Scuola(String idScuola, String nomeScuola) {
        this.idScuola = idScuola;
        this.nomeScuola = nomeScuola;
    }

    public String getIdScuola() {
        return idScuola;
    }

    public String getNomeScuola() {
        return nomeScuola;
    }

    @Override
    public String toString() {
        return nomeScuola;
    }
}
