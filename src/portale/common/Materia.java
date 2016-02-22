package portale.common;

/**
 * Classe contenente le informazioni relative ad una materia
 */
public class Materia {
    /**
     * Nome materia
     */
    private String nomeMateria;

    /**
     * Codice materia
     */
    private String codiceMateria;

    /**
     * Ordinamento (vecchio/nuovo)
     */
    private String ordinamento;

    /**
     * Informazioni del docente che sostiene la materia
     */
    private DocenteClass mDocente;

    /**
     * CFU
     */
    private int CFU;

    /**
     * Anno in cui si svolgono le lezioni della materia
     */
    private int annoCorso;

    /**
     * Genera un oggetto Materia
     * @param codiceMateria codice materia
     * @param nomeMateria nome materia
     * @param CFU cfu
     */
    public Materia(String codiceMateria, String nomeMateria, int CFU) {
        this.codiceMateria = codiceMateria;
        this.nomeMateria = nomeMateria;
        this.CFU = CFU;
    }

    /**
     * Genera un oggetto Materia
     * @param codiceMateria codice materia
     * @param nomeMateria nome materia
     * @param CFU cfu
     * @param anno anno del corso
     */
    public Materia(String codiceMateria, String nomeMateria, int CFU, int anno) {
        this.codiceMateria = codiceMateria;
        this.nomeMateria = nomeMateria;
        this.CFU = CFU;
        this.annoCorso = anno;
    }

    /**
     * Genera un oggetto Materia
     * @param codiceMateria codice materia
     * @param nomeMateria nome materia
     * @param CFU cfu
     * @param pOrdinamento ordinamento
     * @param anno anno del corso
     */
    public Materia(String codiceMateria, String nomeMateria, String pOrdinamento, int CFU, int anno) {
        this.codiceMateria = codiceMateria;
        this.nomeMateria = nomeMateria;
        ordinamento = pOrdinamento;
        this.CFU = CFU;
        this.annoCorso = anno;
    }

    /**
     * Genera un oggetto Materia
     * @param codiceMateria codice materia
     * @param nomeMateria nome materia
     * @param mDocente informazioni docente
     * @param CFU cfu
     * @param ordinamento ordinamento
     * @param annoCorso  anno del corso
     */
    public Materia(String nomeMateria, String codiceMateria, DocenteClass mDocente, String ordinamento, int CFU, int annoCorso) {
        this.nomeMateria = nomeMateria;
        this.codiceMateria = codiceMateria;
        this.mDocente = mDocente;
        this.ordinamento = ordinamento;
        this.CFU = CFU;
        this.annoCorso = annoCorso;
    }

    /**
     * Ritorna il nome della materia
     * @return nome materia
     */
    public String getNomeMateria() {
        return nomeMateria;
    }

    /**
     * Setta il nome della materia
     * @param nomeMateria nome materia
     */
    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    /**
     * Ritorna il codice della materia
     * @return codice materia
     */
    public String getCodiceMateria() {
        return codiceMateria;
    }

    /**
     * Setta il codice della materia
     * @param codiceMateria codice materia
     */
    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria = codiceMateria;
    }

    /**
     * Ritorna l'ordinamento della materia
     * @return ordinamento
     */
    public String getOrdinamento() {
        return ordinamento;
    }

    /**
     * Setta l'ordinamento della materia
     * @param ordinamento ordinamento
     */
    public void setOrdinamento(String ordinamento) {
        this.ordinamento = ordinamento;
    }

    /**
     * Ritorna le informazioni del docente che sostiene la materia
     * @return informazioni docente
     */
    public DocenteClass getmDocente() {
        return mDocente;
    }

    /**
     * Setta le informazioni del docente
     * @param mDocente informazioni docente
     */
    public void setmDocente(DocenteClass mDocente) {
        this.mDocente = mDocente;
    }

    /**
     * Ritorna i CFU della materia
     * @return cfu
     */
    public int getCFU() {
        return CFU;
    }

    /**
     * Setta i cfu della materia
     * @param CFU cfu
     */
    public void setCFU(int CFU) {
        this.CFU = CFU;
    }

    /**
     * Ritorna l'anno della materia
     * @return anno corso della materia
     */
    public int getAnnoCorso() {
        return annoCorso;
    }

    /**
     * Setta l'anno della materia
     * @param annoCorso anno materia
     */
    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    /**
     * Override del metodo toString() per la classe Materia
     * @return una stringa contenente codice e nome della materia
     */
    @Override
    public String toString() {
        return codiceMateria + " - " + nomeMateria + " - " + CFU + " CFU";
    }
}
