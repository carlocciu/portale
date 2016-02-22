package portale.common;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe che contiene le informazioni relative ad un verbale
 */
public class VerbaleComplessivo {

    /**
     * Corso di laurea di appartenenza
     */
    private CorsoDiLaurea CDL;

    /**
     * Scuola di appartenenza
     */
    private Scuola scuola;

    /**
     * Anno accademico
     */
    private String annoAccademico;

    /**
     * Appello a cui si riferisce il verbale
     */
    private Appello mAppello;

    /**
     * Ora apertura del verbale
     */
    private LocalTime oraApertura;

    /**
     * Lista degli esami sostenuti
     */
    private ArrayList<EsameVerbalizzato> esamiSostenuti;
    private Pagina mPagina;

    private int esamiInPagina;

    /**
     * ID del verbale
     */
    private int mIDVerbale;

    /**
     * Genera un oggetto VerbaleComplessivo
     * @param CDL
     * @param scuola
     * @param annoAccademico
     * @param mAppello
     * @param oraApertura
     */
    public VerbaleComplessivo(CorsoDiLaurea CDL, Scuola scuola, String annoAccademico, Appello mAppello, LocalTime oraApertura) {
        this.CDL = CDL;
        this.scuola = scuola;
        setAnno();
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        esamiSostenuti = new ArrayList<>();
        mPagina = Pagina.FRONTE;
    }

    /**
     * Genera un oggetto VerbaleComplessivo
     * @param CDL
     * @param scuola
     * @param annoAccademico
     * @param mAppello
     * @param oraApertura
     * @param esamiSostenuti
     * @param pagina
     * @param esamiInPagina
     */
    public VerbaleComplessivo(CorsoDiLaurea CDL, Scuola scuola, String annoAccademico, Appello mAppello, LocalTime oraApertura, ArrayList<EsameVerbalizzato> esamiSostenuti, Pagina pagina, int esamiInPagina) {
        this.CDL = CDL;
        this.scuola = scuola;
        setAnno();
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        this.esamiSostenuti = esamiSostenuti;
        this.mPagina = pagina;
        this.esamiInPagina = esamiInPagina;
    }

    /**
     * Setta l'anno accademico, calcolando l'anno corrente
     */
    public void setAnno() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        if (month == 10 || month == 11 || month == 12) {
            this.annoAccademico = year + "/" + year + 1;
        } else
            this.annoAccademico = year - 1 + "/" + year;
    }

    /**
     * Ritorna il cdl
     * @return cdl
     */
    public CorsoDiLaurea getCDL() {
        return CDL;
    }

    /**
     * Setta il cdl del verbale
     * @param CDL cdl
     */
    public void setCDL(CorsoDiLaurea CDL) {
        this.CDL = CDL;
    }

    /**
     * Ritorna la scuola di appartenenza
     * @return scuola
     */
    public Scuola getScuola() {
        return scuola;
    }

    /**
     * Setta la scuola di appartenenza
     * @param scuola scuola
     */
    public void setScuola(Scuola scuola) {
        this.scuola = scuola;
    }

    /**
     * Ritorna l'anno accademico
     * @return anno accademico
     */
    public String getAnnoAccademico() {
        return annoAccademico;
    }

    /**
     * Setta l'anno accademico
     * @param annoAccademico anno
     */
    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    /**
     * Ritorna l'appello a cui si riferisce il verbale
     * @return appello
     */
    public Appello getAppello() {
        return mAppello;
    }

    /**
     * Setta l'appello a cui si riferisce il verbale
     * @param pAppello appello
     */
    public void setAppello(Appello pAppello) {
        mAppello = pAppello;
    }

    /**
     * Ritorna la lista degli esami sostenuti del verbale
     * @return lista esami sostenuti
     */
    public ArrayList<EsameVerbalizzato> getEsamiSostenuti() {
        return esamiSostenuti;
    }

    /**
     * Setta la lista degli esami sostenuti del verbale
     * @param esamiSostenuti esami sostenuti
     */
    public void setEsamiSostenuti(ArrayList<EsameVerbalizzato> esamiSostenuti) {
        this.esamiSostenuti = esamiSostenuti;
    }

    /**
     * Ritorna l'ora di apertura del verbale
     * @return ora apertura
     */
    public LocalTime getOraApertura() {
        return oraApertura;
    }

    /**
     * Setta l'ora di apertura del verbale
     * @param oraApertura ora
     */
    public void setOraApertura(LocalTime oraApertura) {
        this.oraApertura = oraApertura;
    }

    public Pagina getPagina() {
        return mPagina;
    }

    public void setPagina(Pagina pagina) {
        mPagina = pagina;
    }

    public int getEsamiInPagina() {
        return esamiInPagina;
    }

    public void setEsamiInPagina(int esamiInPagina) {
        this.esamiInPagina = esamiInPagina;
    }

    /**
     * Ritorna l'id del verbale
     * @return id verbale
     */
    public int getIDVerbale() {
        return mIDVerbale;
    }

    /**
     * Setta l'id del verbale
     * @param pIDVerbale id verbale
     */
    public void setIDVerbale(int pIDVerbale) {
        mIDVerbale = pIDVerbale;
    }

    /**
     * Override del metodo toString() per la classe VerbaleComplessivo
     * @return una stringa contenente tutte le informazioni del verbale
     */
    @Override
    public String toString() {
        return scuola + "\n" + CDL + "\n" + "Anno Accademico: " + annoAccademico + "\n" + mAppello.toString() + "\n" + "Ora Apertura: " + oraApertura + esamiSostenuti.toString();
    }

    private enum Pagina {FRONTE, RETRO}
}
