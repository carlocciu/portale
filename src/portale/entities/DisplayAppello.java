package portale.entities;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by riccardomusmeci on 20/02/16.
 */
public class DisplayAppello {

    private final SimpleStringProperty docente;
    private final Date dataEsame;
    private final SimpleStringProperty aula;
    private final SimpleStringProperty idAppello;

    public DisplayAppello(String docente, Date dataEsame, String aula, String idAppello) {

        this.docente = new SimpleStringProperty(docente);
        this.dataEsame = dataEsame;
        this.aula = new SimpleStringProperty(aula);
        this.idAppello = new SimpleStringProperty(idAppello);
    }

    public String getDocente() {
        return docente.get();
    }

    public SimpleStringProperty docenteProperty() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente.set(docente);
    }

    public Date getDataEsame() {
        return dataEsame;
    }

    public String getAula() {
        return aula.get();
    }

    public SimpleStringProperty aulaProperty() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula.set(aula);
    }

    public String getIdAppello() {
        return idAppello.get();
    }

    public SimpleStringProperty idAppelloProperty() {
        return idAppello;
    }
}
