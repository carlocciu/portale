package portale.entities;

public class Materia {

    private String nomeMateria;

    private int codiceMateria;

    private String ordinamento;

    private DocenteClass mDocente;

    private int CFU;

    private int annoCorso;

    public Materia(String nomeMateria, int codiceMateria, DocenteClass mDocente, String ordinamento, int CFU, int annoCorso) {
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

    public int getCodiceMateria() {
        return codiceMateria;
    }

    public void setCodiceMateria(int codiceMateria) {
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
}
