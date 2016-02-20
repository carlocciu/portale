package portale.entities;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by riccardomusmeci on 20/02/16.
 */
public class DisplayPianoDiStudi {

    private final SimpleStringProperty anno;

    private final SimpleStringProperty codiceMateria;

    private final SimpleStringProperty nomeMateria;

    private final SimpleStringProperty cfuMateria;

    private final SimpleStringProperty esitoMateria;

    private final SimpleStringProperty dataEsame;

    private final SimpleStringProperty prenotaMateria;


    public DisplayPianoDiStudi(int anno, String codiceMateria, String nomeMateria, int cfu, Date data, int voto, String prenotato) {

        this.anno = new SimpleStringProperty(String.valueOf(anno));
        this.codiceMateria = new SimpleStringProperty(codiceMateria);
        this.nomeMateria = new SimpleStringProperty(nomeMateria);
        this.cfuMateria = new SimpleStringProperty(String.valueOf(cfu));
        this.esitoMateria = new SimpleStringProperty(String.valueOf(voto));
        this.dataEsame = new SimpleStringProperty(String.valueOf(data));
        this.prenotaMateria = new SimpleStringProperty(prenotato);

    }

    public String getAnno() {
        return anno.get();
    }

    public SimpleStringProperty annoProperty() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno.set(anno);
    }

    public String getCodiceMateria() {
        return codiceMateria.get();
    }

    public SimpleStringProperty codiceMateriaProperty() {
        return codiceMateria;
    }

    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria.set(codiceMateria);
    }

    public String getNomeMateria() {
        return nomeMateria.get();
    }

    public SimpleStringProperty nomeMateriaProperty() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria.set(nomeMateria);
    }

    public String getCfuMateria() {
        return cfuMateria.get();
    }

    public SimpleStringProperty cfuMateriaProperty() {
        return cfuMateria;
    }

    public void setCfuMateria(String cfuMateria) {
        this.cfuMateria.set(cfuMateria);
    }

    public String getEsitoMateria() {
        return esitoMateria.get();
    }

    public SimpleStringProperty esitoMateriaProperty() {
        return esitoMateria;
    }

    public void setEsitoMateria(String esitoMateria) {
        this.esitoMateria.set(esitoMateria);
    }

    public String getDataEsame() {
        return dataEsame.get();
    }

    public SimpleStringProperty dataEsameProperty() {
        return dataEsame;
    }

    public void setDataEsame(String dataEsame) {
        this.dataEsame.set(dataEsame);
    }

    public String getPrenotaMateria() {
        return prenotaMateria.get();
    }

    public SimpleStringProperty prenotaMateriaProperty() {
        return prenotaMateria;
    }

    public void setPrenotaMateria(String prenotaMateria) {
        this.prenotaMateria.set(prenotaMateria);
    }
}
