package portale.common;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe che contiene le informazioni per rappresentare un verbale aperto
 */
public class DisplayVerbale {

    /**
     * Anno in cui si svolgono le lezioni della materia
     */
    private final SimpleStringProperty anno;
    /**
     * Corso di laurea
     */
    private final SimpleStringProperty cdl;
    /**
     * Nome materia
     */
    private final SimpleStringProperty materia;
    /**
     * CFU
     */
    private final SimpleStringProperty cfu;
    /**
     * Data in cui si svolge l'esame
     */
    private final SimpleStringProperty data;
    /**
     * Id Verbale
     */
    private String idVerbale;

    /**
     * Genera un oggetto DisplayVerbale
     * @param anno anno materia
     * @param cdl corso di laurea
     * @param materia materia
     * @param cfu cfu
     * @param data data esame
     * @param idVerbale id verbale
     */
    public DisplayVerbale(int anno, String cdl, String materia, int cfu, String data, String idVerbale) {
        this.anno = new SimpleStringProperty(String.valueOf(anno));
        this.cdl = new SimpleStringProperty(cdl);
        this.materia = new SimpleStringProperty(materia);
        this.cfu = new SimpleStringProperty(String.valueOf(cfu));
        this.data = new SimpleStringProperty(String.valueOf(data));
        this.idVerbale = idVerbale;
    }


    public String getIdVerbale() {
        return idVerbale;
    }

    public String getAnno() {
        return anno.get();
    }

    public SimpleStringProperty annoProperty() {
        return anno;
    }

    public String getCdl() {
        return cdl.get();
    }

    public SimpleStringProperty cdlProperty() {
        return cdl;
    }

    public String getMateria() {
        return materia.get();
    }

    public SimpleStringProperty materiaProperty() {
        return materia;
    }

    public String getCfu() {
        return cfu.get();
    }

    public SimpleStringProperty cfuProperty() {
        return cfu;
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }
}
