package portale.common;

/**
 * Classe che contiene le informazioni relative ad un esame verbalizzato
 */
public class EsameVerbalizzato {

    /**
     * Esito dell'esame
     */
    private String voto;

    /**
     * Domande fatte allo studente
     */
    private String domande;

    /**
     * Informazioni dello studente
     */
    private StudenteClass mStudente;

    /**
     * Genera un oggetto EsameVerbalizzato
     * @param voto esito esame
     * @param domande domande esame
     * @param mStudente informazioni studente
     */
    public EsameVerbalizzato(String voto, String domande, StudenteClass mStudente) {
        this.voto = voto;
        this.domande = domande;
        this.mStudente = mStudente;
    }

    /**
     * Ritorna l'esito dell'esame
     * @return
     */
    public String getVoto() {
        return voto;
    }

    /**
     * Setta l'esito dell'esame
     * @param voto esito dell'esame
     */
    public void setVoto(String voto) {
        this.voto = voto;
    }

    /**
     * Ritorna le domande fatte allo studente
     * @return domande
     */
    public String getDomande() {
        return domande;
    }

    /**
     * Setta le domande fatte allo studente
     * @param domande domande
     */
    public void setDomande(String domande) {
        this.domande = domande;
    }

    /**
     * Ritorna le informazioni dello studente
     * @return informazioni studenet
     */
    public StudenteClass getmStudente() {
        return mStudente;
    }

    /**
     * Setta le informazioni dello studente
     * @param mStudente informazioni studente
     */
    public void setmStudente(StudenteClass mStudente) {
        this.mStudente = mStudente;
    }

    /**
     * Override del metodo toString() per la classe EsameVerbalizzato
     * @return una stringa contenente matricola, nome, cognome dello studente e voto e domande dell'esame
     */
    @Override
    public String toString() {
        return "Matricola: " + mStudente.getMatricolaStudente() + "\t\t" + "Domande: " + domande + "\n" +
                "Cognome e Nome: " + mStudente.getCognomeStudente() + " " + mStudente.getNomeStudente() +
                "\t\t" + "Voto: " + voto;


    }
}
