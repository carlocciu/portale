package portale.entities;

import portale.entities.*;

import java.util.ArrayList;
import java.util.Date;

public class VerbaleComplessivo {

    private String CDL;

    private String scuola;

    private String annoAccademico;

    private Appello mAppello;

    private Date oraApertura;

    private ArrayList<EsameVerbalizzato> esamiSostenuti;

    private Enum pagina;

    private int esamiInPagina;

    public VerbaleComplessivo(String CDL, String scuola, String annoAccademico, Appello mAppello, Date oraApertura, ArrayList<EsameVerbalizzato> esamiSostenuti) {
        this.CDL = CDL;
        this.scuola = scuola;
        this.annoAccademico = annoAccademico;
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        this.esamiSostenuti = esamiSostenuti;
    }

    public VerbaleComplessivo(String CDL, String scuola, String annoAccademico, Appello mAppello, Date oraApertura, ArrayList<EsameVerbalizzato> esamiSostenuti, Enum pagina, int esamiInPagina) {
        this.CDL = CDL;
        this.scuola = scuola;
        this.annoAccademico = annoAccademico;
        this.mAppello = mAppello;
        this.oraApertura = oraApertura;
        this.esamiSostenuti = esamiSostenuti;
        this.pagina = pagina;
        this.esamiInPagina = esamiInPagina;
    }

    public String getCDL() {
        return CDL;
    }

    public void setCDL(String CDL) {
        this.CDL = CDL;
    }

    public String getScuola() {
        return scuola;
    }

    public void setScuola(String scuola) {
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

    public Date getOraApertura() {
        return oraApertura;
    }

    public void setOraApertura(Date oraApertura) {
        this.oraApertura = oraApertura;
    }

    public Enum getPagina() {
        return pagina;
    }

    public void setPagina(Enum pagina) {
        this.pagina = pagina;
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
