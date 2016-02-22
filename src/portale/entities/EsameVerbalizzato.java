package portale.entities;

import portale.entities.StudenteClass;

public class EsameVerbalizzato {

    private String voto;

    private String domande;

    private StudenteClass mStudente;

    public EsameVerbalizzato(String voto, String domande, StudenteClass mStudente) {
        this.voto = voto;
        this.domande = domande;
        this.mStudente = mStudente;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public String getDomande() {
        return domande;
    }

    public void setDomande(String domande) {
        this.domande = domande;
    }

    public StudenteClass getmStudente() {
        return mStudente;
    }

    public void setmStudente(StudenteClass mStudente) {
        this.mStudente = mStudente;
    }

    @Override
    public String toString() {
        return "Matricola: " + mStudente.getMatricolaStudente() + "\t\t" + "Domande: " + domande + "\n" +
                "Cognome e Nome: " + mStudente.getCognomeStudente() + " " + mStudente.getNomeStudente() +
                "\t\t" + "Voto: " + voto;


    }
}
