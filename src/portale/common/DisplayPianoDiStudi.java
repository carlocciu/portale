package portale.common;

import javafx.beans.property.SimpleStringProperty;

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

    private final SimpleStringProperty matricola;


    public DisplayPianoDiStudi(int anno, String codiceMateria, String nomeMateria, int cfu, Date data, int voto, String matricola) {

        this.anno = new SimpleStringProperty(String.valueOf(anno));
        this.codiceMateria = new SimpleStringProperty(codiceMateria);
        this.nomeMateria = new SimpleStringProperty(nomeMateria);
        this.cfuMateria = new SimpleStringProperty(String.valueOf(cfu));

        if (voto == 0) {
            this.esitoMateria = new SimpleStringProperty(" - ");
        } else {
            this.esitoMateria = new SimpleStringProperty(String.valueOf(voto));
        }

        if (data == null) {
            this.dataEsame = new SimpleStringProperty(" - ");
        } else {
            this.dataEsame = new SimpleStringProperty(String.valueOf(data));
        }

        this.matricola = new SimpleStringProperty(matricola);

    }

    public String getAnno() {
        return anno.get();
    }

    public void setAnno(String anno) {
        this.anno.set(anno);
    }

    public SimpleStringProperty annoProperty() {
        return anno;
    }

    public String getCodiceMateria() {
        return codiceMateria.get();
    }

    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria.set(codiceMateria);
    }

    public SimpleStringProperty codiceMateriaProperty() {
        return codiceMateria;
    }

    public String getNomeMateria() {
        return nomeMateria.get();
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria.set(nomeMateria);
    }

    public SimpleStringProperty nomeMateriaProperty() {
        return nomeMateria;
    }

    public String getCfuMateria() {
        return cfuMateria.get();
    }

    public void setCfuMateria(String cfuMateria) {
        this.cfuMateria.set(cfuMateria);
    }

    public SimpleStringProperty cfuMateriaProperty() {
        return cfuMateria;
    }

    public String getEsitoMateria() {
        return esitoMateria.get();
    }

    public void setEsitoMateria(String esitoMateria) {
        this.esitoMateria.set(esitoMateria);
    }

    public SimpleStringProperty esitoMateriaProperty() {
        return esitoMateria;
    }

    public String getDataEsame() {
        return dataEsame.get();
    }

    public void setDataEsame(String dataEsame) {
        this.dataEsame.set(dataEsame);
    }

    public SimpleStringProperty dataEsameProperty() {
        return dataEsame;
    }

    public String getMatricola() {
        return matricola.get();
    }

    public SimpleStringProperty matricolaProperty() {
        return matricola;
    }

}
