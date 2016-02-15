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

    public VerbaleComplessivo(ArrayList<StudenteClass> pStudentiIscritti, Date pDataEsame, Materia pMateria, DocenteClass pDocente) {

    }

    public String getCDL() {
        return null;
    }

    public void setCDL(String pCDL) {

    }

    public String getScuola() {
        return null;
    }

    public void setScuola(String pScuola) {

    }

    public String getAnnoAccademico() {
        return null;
    }

    public Appello getAppello() {
        return null;
    }

    public void setAppello(Appello pAppello) {

    }

    public Date getOraApertura() {
        return null;
    }

    public void setOraApertura(Date pOra) {

    }

    public ArrayList<EsameVerbalizzato> getEsamiSostenuti() {
        return null;
    }

    public Enum getPagina() {
        return null;
    }

    public void setPagina(Enum pPagina) {

    }

    public int getEsamiInPagina() {
        return 0;
    }

    public void setAnno(String pAnno) {

    }

    public void addEsameVerbalizzato(EsameVerbalizzato pEsame) {

    }

}
