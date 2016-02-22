package portale.common;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by carlonuccio on 20/02/16.
 */
public class DisplayVerbale {

    private final SimpleStringProperty anno;
    private final SimpleStringProperty cdl;
    private final SimpleStringProperty materia;
    private final SimpleStringProperty cfu;
    private final SimpleStringProperty data;
    private String idVerbale;

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
