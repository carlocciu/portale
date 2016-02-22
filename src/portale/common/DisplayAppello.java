package portale.common;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

/**
 * Created by riccardomusmeci on 20/02/16.
 */
public class DisplayAppello {

    private final SimpleStringProperty docente;
    private final Timestamp dataEsame;
    private final SimpleStringProperty aula;
    private final SimpleStringProperty idAppello;

    public DisplayAppello(String docente, Timestamp dataEsame, String aula, String idAppello) {

        this.docente = new SimpleStringProperty(docente);
        this.dataEsame = dataEsame;
        this.aula = new SimpleStringProperty(aula);
        this.idAppello = new SimpleStringProperty(idAppello);
    }

    public String getDocente() {
        return docente.get();
    }

    public void setDocente(String docente) {
        this.docente.set(docente);
    }

    public SimpleStringProperty docenteProperty() {
        return docente;
    }

    public Timestamp getDataEsame() {
        return dataEsame;
    }

    public String getAula() {
        return aula.get();
    }

    public void setAula(String aula) {
        this.aula.set(aula);
    }

    public SimpleStringProperty aulaProperty() {
        return aula;
    }

    public String getIdAppello() {
        return idAppello.get();
    }

    public SimpleStringProperty idAppelloProperty() {
        return idAppello;
    }
}
