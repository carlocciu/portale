package portale.common;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe che contiene le informazioni di un appello
 */
public class Appello {

    /**
     * ID Appello
     */
    private String idAppello;

    /**
     * Lista degli studenti iscritti
     */
    private ArrayList<StudenteClass> studentiIscritti;

    /**
     * Data dell'appello
     */
    private Date dataEsame;

    /**
     * Materia relativa all'appello
     */
    private Materia mMateria;

    /**
     * Docente relativo all'appello
     */
    private DocenteClass mDocente;

    /**
     * Aula in cui si svolge l'esame
     */
    private String aula;

    /**
     * Genera un oggetto Appello
     * @param idAppello id appello
     * @param studentiIscritti lista degli studenti iscritti
     * @param dataEsame data in cui si svolge l'esame
     * @param mMateria materia dell'appello
     * @param aula aula in cui si svolge l'esame
     */
    public Appello(String idAppello, ArrayList<StudenteClass> studentiIscritti, Date dataEsame, Materia mMateria, String aula) {
        this.idAppello = idAppello;
        this.studentiIscritti = studentiIscritti;
        this.dataEsame = dataEsame;
        this.mMateria = mMateria;
        this.aula = aula;
    }

    /**
     * Genera un oggetto Appello
     * @param idAppello id appello
     * @param d data in cui si svolge l'esame
     * @param aula aula in cui si svolge l'esame
     */
    public Appello(String idAppello, Date d, String aula) {
        this.idAppello = idAppello;
        this.dataEsame = d;
        this.aula = aula;
    }

    /**
     * Genera un oggetto Appello
     * @param mMateria materia dell'appello
     * @param mDocente docente dell'appello
     * @param aula aula in cui si svolge l'esame
     * @param studentiIscritti lista degli iscritti
     * @param dataEsame data dell'esame
     */
    public Appello(Materia mMateria, DocenteClass mDocente, String aula, ArrayList<StudenteClass> studentiIscritti, Date dataEsame) {
        this.mMateria = mMateria;
        this.mDocente = mDocente;
        this.aula = aula;
        this.studentiIscritti = studentiIscritti;
        this.dataEsame = dataEsame;
    }

    /**
     * Ritorna l'id dell'appello
     * @return id appello
     */
    public String getIdAppello() {
        return idAppello;
    }

    /**
     * Ritorna la lista degli iscritti ad un appello
     * @return lista iscritti
     */
    public ArrayList<StudenteClass> getStudentiIscritti() {
        return studentiIscritti;
    }

    /**
     * Setta la lista degli studenti iscritti in un appello
     * @param studentiIscritti lista degli studenti iscritti
     */
    public void setStudentiIscritti(ArrayList<StudenteClass> studentiIscritti) {
        this.studentiIscritti = studentiIscritti;
    }

    /**
     * Ritorna la data dell'esame
     * @return data esame
     */
    public Date getDataEsame() {
        return dataEsame;
    }

    /**
     * Setta la data dell'esame di un appello
     * @param dataEsame data in cui si svolge l'esame
     */
    public void setDataEsame(Date dataEsame) {
        this.dataEsame = dataEsame;
    }

    /**
     * Ritorna la materia di un appello
     * @return materia appello
     */
    public Materia getMateria() {
        return mMateria;
    }

    /**
     * Setta la materia di un appello
     * @param pMateria materia appello
     */
    public void setMateria(Materia pMateria) {
        mMateria = pMateria;
    }

    /**
     * Ritorna il docente di un appello
     * @return docente appello
     */
    public DocenteClass getDocente() {
        return mDocente;
    }

    /**
     * Setta il docente di un appello
     * @param pDocente docente dell'appello
     */
    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    /**
     * Ritorna l'aula in cui si svolge l'esame
     * @return aula esame
     */
    public String getAula() {
        return aula;
    }

    /**
     * Setta l'aula di un esame
     * @param aula aula in cui si svolge l'esame
     */
    public void setAula(String aula) {
        this.aula = aula;
    }

    /**
     * Override del metodo toString() per la classe Appello
     * @return una stringa contenente data e aula dell'esame
     */
    @Override
    public String toString() {

        return dataEsame.toLocaleString() + " - " + "Aula: " + aula;
    }

}
