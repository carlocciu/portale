package portale.common;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

/**
 * Classe che contiene le informazioni per rappresentare un appello in una TableView
 */
public class DisplayAppello {

    /**
     * Cognome del docente
     */
    private final SimpleStringProperty docente;

    /**
     * Data dell'esame
     */
    private final Timestamp dataEsame;

    /**
     * Aula in cui si svolge l'esame
     */
    private final SimpleStringProperty aula;

    /**
     * Id appello
     */
    private final SimpleStringProperty idAppello;

    /**
     * Genera un oggetto DisplayAppello
     * @param docente cognome docente
     * @param dataEsame data dell'esame
     * @param aula aula dell'esame
     * @param idAppello id appello
     */
    public DisplayAppello(String docente, Timestamp dataEsame, String aula, String idAppello) {

        this.docente = new SimpleStringProperty(docente);
        this.dataEsame = dataEsame;
        this.aula = new SimpleStringProperty(aula);
        this.idAppello = new SimpleStringProperty(idAppello);
    }

    /**
     * Ritorna il cognome del docente
     * @return cognome docente
     */
    public String getDocente() {
        return docente.get();
    }

    /**
     * Setta il cognome del docente
     * @param docente docente dell'appello
     */
    public void setDocente(String docente) {
        this.docente.set(docente);
    }

    /**
     * Ritorna il cognome del docente
     * @return cognome docente
     */
    public SimpleStringProperty docenteProperty() {
        return docente;
    }

    /**
     * Ritorna la data dell'esame
     * @return data esame
     */
    public Timestamp getDataEsame() {
        return dataEsame;
    }

    /**
     * Ritorna l'aula in cui si svolge l'esame
     * @return aula esame
     */
    public String getAula() {
        return aula.get();
    }

    /**
     * Setta l'aula dell'esame
     * @param aula aula esame
     */
    public void setAula(String aula) {
        this.aula.set(aula);
    }

    /**
     * Ritorna aula
     * @return aula
     */
    public SimpleStringProperty aulaProperty() {
        return aula;
    }

    /**
     * Ritorna id appello
     * @return id appello
     */
    public String getIdAppello() {
        return idAppello.get();
    }

    /**
     * Ritorna id appello
     * @return id appello
     */
    public SimpleStringProperty idAppelloProperty() {
        return idAppello;
    }
}
