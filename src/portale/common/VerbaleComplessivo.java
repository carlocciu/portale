package portale.common;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class VerbaleComplessivo {

    private CorsoDiLaurea CDL;

    private Scuola scuola;

    private String annoAccademico;

    private Appello mAppello;

    private LocalTime oraApertura;

    private ArrayList<EsameVerbalizzato> esamiSostenuti;
    private Pagina mPagina;

    ;
    private int esamiInPagina;
    private int mIDVerbale;

    public VerbaleComplessivo(CorsoDiLaurea CDL, Scuola scuola, String annoAccademico, Appello mAppello, LocalTime oraApertura) {
        this.CDL = CDL;
        this.scuola = scuola;
        setAnno();
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        esamiSostenuti = new ArrayList<>();
        mPagina = Pagina.FRONTE;
    }

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

    public void setAnno() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        if (month == 10 || month == 11 || month == 12) {
            this.annoAccademico = year + "/" + year + 1;
        } else
            this.annoAccademico = year - 1 + "/" + year;
    }

    public CorsoDiLaurea getCDL() {
        return CDL;
    }

    public void setCDL(CorsoDiLaurea CDL) {
        this.CDL = CDL;
    }

    public Scuola getScuola() {
        return scuola;
    }

    public void setScuola(Scuola scuola) {
        this.scuola = scuola;
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public Appello getAppello() {
        return mAppello;
    }

    public void setAppello(Appello pAppello) {
        mAppello = pAppello;
    }

    public ArrayList<EsameVerbalizzato> getEsamiSostenuti() {
        return esamiSostenuti;
    }

    public void setEsamiSostenuti(ArrayList<EsameVerbalizzato> esamiSostenuti) {
        this.esamiSostenuti = esamiSostenuti;
    }

    public LocalTime getOraApertura() {
        return oraApertura;
    }

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

    public int getIDVerbale() {
        return mIDVerbale;
    }

    public void setIDVerbale(int pIDVerbale) {
        mIDVerbale = pIDVerbale;
    }

    @Override
    public String toString() {
        return scuola + "\n" + CDL + "\n" + "Anno Accademico: " + annoAccademico + "\n" + mAppello.toString() + "\n" + "Ora Apertura: " + oraApertura + esamiSostenuti.toString();
    }

    private enum Pagina {FRONTE, RETRO}
}
