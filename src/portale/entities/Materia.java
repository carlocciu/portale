package portale.entities;

import java.nio.charset.CoderMalfunctionError;

public class Materia {

    private String nomeMateria;

    private String codiceMateria;

    private String ordinamento;

    private DocenteClass mDocente;

    private int CFU;

    private int annoCorso;

    public Materia(String codiceMateria, String nomeMateria, int CFU) {
        this.codiceMateria = codiceMateria;
        this.nomeMateria = nomeMateria;
        this.CFU = CFU;
    }

    public Materia(String codiceMateria, String nomeMateria, int CFU, int anno) {
        this.codiceMateria = codiceMateria;
        this.nomeMateria = nomeMateria;
        this.CFU = CFU;
        this.annoCorso = anno;
    }

    public Materia(String nomeMateria, String codiceMateria, DocenteClass mDocente, String ordinamento, int CFU, int annoCorso) {
        this.nomeMateria = nomeMateria;
        this.codiceMateria = codiceMateria;
        this.mDocente = mDocente;
        this.ordinamento = ordinamento;
        this.CFU = CFU;
        this.annoCorso = annoCorso;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public String getCodiceMateria() {
        return codiceMateria;
    }

    public void setCodiceMateria(String codiceMateria) {
        this.codiceMateria = codiceMateria;
    }

    public String getOrdinamento() {
        return ordinamento;
    }

    public void setOrdinamento(String ordinamento) {
        this.ordinamento = ordinamento;
    }

    public DocenteClass getmDocente() {
        return mDocente;
    }

    public void setmDocente(DocenteClass mDocente) {
        this.mDocente = mDocente;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int CFU) {
        this.CFU = CFU;
    }

    public int getAnnoCorso() {
        return annoCorso;
    }

    public void setAnnoCorso(int annoCorso) {
        this.annoCorso = annoCorso;
    }

    @Override
    public String toString() {
        return codiceMateria + " - " + nomeMateria + " - " + CFU + " CFU";
    }
}
