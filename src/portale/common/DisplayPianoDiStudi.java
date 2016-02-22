package portale.common;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Classe che contiene le informazioni per rappresentare il piano di studi di uno studente
 */
public class DisplayPianoDiStudi {

    /**
     * Anno in cui si svolgono le lezioni della materia
     */
    private final SimpleStringProperty anno;

    /**
     * Codice della materia
     */
    private final SimpleStringProperty codiceMateria;

    /**
     * Nome della materia
     */
    private final SimpleStringProperty nomeMateria;

    /**
     * Numero di CFU per la materia
     */
    private final SimpleStringProperty cfuMateria;

    /**
     * Esito dell'esame
     */
    private final SimpleStringProperty esitoMateria;

    /**
     * Data in cui si è svolto l'esame
     */
    private final SimpleStringProperty dataEsame;

    /**
     * Matricola dello studente
     */
    private final SimpleStringProperty matricola;

    /**
     * Genera un oggetto DisplayPianoDiStudi
     * @param anno anno in cui si svolgono le lezioni della materia
     * @param codiceMateria codice materia
     * @param nomeMateria nome materia
     * @param cfu cfu materia
     * @param data data esame
     * @param voto esito esame
     * @param matricola matricola studente
     */
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

    /**
     * Ritorna l'anno della materia
     * @return anno materia
     */
    public String getAnno() {
        return anno.get();
    }

    /**
     * Setta l'anno della materia
     * @param anno anno materia
     */
    public void setAnno(String anno) {
        this.anno.set(anno);
    }

    /**
     * Ritorna anno
     * @return anno
     */
    public SimpleStringProperty annoProperty() {
        return anno;
    }

    /**
     * Ritorna il codice della materia
     * @return codice materia
     */
    public String getCodiceMateria() {
        return codiceMateria.get();
    }

    /**
     * Setta il codice della materia
     * @param codiceMateria
     */
    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria.set(codiceMateria);
    }

    /**
     * Ritorna codice materia
     * @return codice materia
     */
    public SimpleStringProperty codiceMateriaProperty() {
        return codiceMateria;
    }

    /**
     * Ritorna il nome della materia
     * @return nome materia
     */
    public String getNomeMateria() {
        return nomeMateria.get();
    }

    /**
     * Setta il nome della materia
     * @param nomeMateria nome materia
     */
    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria.set(nomeMateria);
    }

    /**
     * Ritorna nome materia
     * @return nome materia
     */
    public SimpleStringProperty nomeMateriaProperty() {
        return nomeMateria;
    }

    /**
     * Ritorna i cfu della materia
     * @return cfu materia
     */
    public String getCfuMateria() {
        return cfuMateria.get();
    }

    /**
     * Setta i cfu di una materia
     * @param cfuMateria cfu materia
     */
    public void setCfuMateria(String cfuMateria) {
        this.cfuMateria.set(cfuMateria);
    }

    /**
     * Ritorna cfu materia
     * @return cfu materia
     */
    public SimpleStringProperty cfuMateriaProperty() {
        return cfuMateria;
    }

    /**
     * Ritorna l'esito di un esame di una materia
     * @return esito esame
     */
    public String getEsitoMateria() {
        return esitoMateria.get();
    }

    /**
     * Setta l'esito di una materia
     * @param esitoMateria esito materia
     */
    public void setEsitoMateria(String esitoMateria) {
        this.esitoMateria.set(esitoMateria);
    }

    /**
     * Ritorna esito
     * @return esito
     */
    public SimpleStringProperty esitoMateriaProperty() {
        return esitoMateria;
    }

    /**
     * Ritorna la data in cui si è svolto l'esame
     * @return data esame
     */
    public String getDataEsame() {
        return dataEsame.get();
    }

    /**
     * Setta la data di un esame
     * @param dataEsame data esame
     */
    public void setDataEsame(String dataEsame) {
        this.dataEsame.set(dataEsame);
    }

    /**
     * Ritorna data esame
     * @return data esame
     */
    public SimpleStringProperty dataEsameProperty() {
        return dataEsame;
    }

    /**
     * Ritorna la matricola dello studente
     * @return matricola studente
     */
    public String getMatricola() {
        return matricola.get();
    }

    /**
     * Ritorna matricola studente
     * @return matricola studente
     */
    public SimpleStringProperty matricolaProperty() {
        return matricola;
    }

}
