package portale.entities;

import java.time.LocalTime;
import java.util.ArrayList;

public class VerbaleComplessivo {

    private CorsoDiLaurea CDL;

    private Scuola scuola;

    private String annoAccademico;

    private Appello mAppello;

    private LocalTime oraApertura;

    private ArrayList<EsameVerbalizzato> esamiSostenuti;

    private enum Pagina {FRONTE, RETRO};
    private Pagina mPagina;

    private int esamiInPagina;

    public VerbaleComplessivo(CorsoDiLaurea CDL, Scuola scuola, String annoAccademico, Appello mAppello, LocalTime oraApertura) {
        this.CDL = CDL;
        this.scuola = scuola;
        this.annoAccademico = annoAccademico;
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        esamiSostenuti = new ArrayList<>();
        mPagina = Pagina.FRONTE;
    }

    public VerbaleComplessivo(CorsoDiLaurea CDL, Scuola scuola, String annoAccademico, Appello mAppello, LocalTime oraApertura, ArrayList<EsameVerbalizzato> esamiSostenuti, Pagina pagina, int esamiInPagina) {
        this.CDL = CDL;
        this.scuola = scuola;
        this.annoAccademico = annoAccademico;
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        this.esamiSostenuti = esamiSostenuti;
        this.mPagina = pagina;
        this.esamiInPagina = esamiInPagina;
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

    public Appello getmAppello() {
        return mAppello;
    }

    public void setmAppello(Appello mAppello) {
        this.mAppello = mAppello;
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

    @Override
    public String toString() {
        return scuola + "\n" + CDL + "\n" + "Anno Accademico: " + annoAccademico + "\n" + mAppello.toString() + "\n" + "Ora Apertura: " + oraApertura + esamiSostenuti.toString();
    }
}
