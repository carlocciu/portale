package portale.entities;

import java.util.ArrayList;
import java.util.Date;


public class Appello {

    private ArrayList<StudenteClass> studentiIscritti;

    private Date dataEsame;

    private Materia mMateria;

    private DocenteClass mDocente;

    private String aula;

    public Appello(Materia mMateria, DocenteClass mDocente, String aula, ArrayList<StudenteClass> studentiIscritti, Date dataEsame) {
        this.mMateria = mMateria;
        this.mDocente = mDocente;
        this.aula = aula;
        this.studentiIscritti = studentiIscritti;
        this.dataEsame = dataEsame;
    }

    public ArrayList<StudenteClass> getStudentiIscritti() {
        return studentiIscritti;
    }

    public void setStudentiIscritti(ArrayList<StudenteClass> studentiIscritti) {
        this.studentiIscritti = studentiIscritti;
    }

    public Date getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(Date dataEsame) {
        this.dataEsame = dataEsame;
    }

    public Materia getmMateria() {
        return mMateria;
    }

    public void setmMateria(Materia mMateria) {
        this.mMateria = mMateria;
    }

    public DocenteClass getmDocente() {
        return mDocente;
    }

    public void setmDocente(DocenteClass mDocente) {
        this.mDocente = mDocente;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
