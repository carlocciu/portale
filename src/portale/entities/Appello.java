package portale.entities;

import java.util.ArrayList;
import java.util.Date;


public class Appello {

    private String idAppello;

    private ArrayList<StudenteClass> studentiIscritti;

    private Date dataEsame;

    private Materia mMateria;

    private DocenteClass mDocente;

    private String aula;

    public Appello(String idAppello, ArrayList<StudenteClass> studentiIscritti, Date dataEsame, Materia mMateria, String aula) {
        this.idAppello = idAppello;
        this.studentiIscritti = studentiIscritti;
        this.dataEsame = dataEsame;
        this.mMateria = mMateria;
        this.aula = aula;
    }

    public Appello (String idAppello, Date d, String aula){
        this.idAppello = idAppello;
        this.dataEsame = d;
        this.aula = aula;
    }

    public String getIdAppello() {
        return idAppello;
    }

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

    public Materia getMateria() {
        return mMateria;
    }

    public void setMateria(Materia pMateria) {
        mMateria = pMateria;
    }

    public DocenteClass getDocente() {
        return mDocente;
    }

    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    @Override
    public String toString() {

        return dataEsame.toLocaleString() + " - " + "Aula: " + aula;
    }

}
