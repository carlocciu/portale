package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSVerbaliApertiBnd;
import portale.common.*;

import java.time.LocalTime;

/**
 * Classe control che gestisce i verbali
 */
public class VerbaleCtrl {


    /**
     * Boundary che gestisce la comunicazione con il database per recuperare verbali aperti
     */
    DBMSVerbaliApertiBnd db = new DBMSVerbaliApertiBnd();
    /**
     * Informazioni docente
     */
    private DocenteClass docente;

    /**
     * Genera un oggetto VerbaleCtrl con le informazioni del docente
     * @param docente informazioni docente
     */
    public VerbaleCtrl(DocenteClass docente) {
        this.docente = docente;
    }

    /**
     * Ritorna la lista dei verbali aperti per il docente
     * @return lista di verbali aperti
     */
    public ObservableList<DisplayVerbale> getVerbaliAperti() {
        return db.verbaliAperti(docente.getMatricolaDocente());
    }

    /**
     * Ritorna le informazioni dell'appello
     * @param idVerbale id verbale
     * @return informazioni appello
     */
    public Appello getAppello(String idVerbale) {
        return db.getAppello(idVerbale);
    }

    /**
     * Ritorna le informazioni del corso di laurea
     * @param idVerbale id verbale
     * @return informazioni corso di laurea
     */
    public CorsoDiLaurea getCDL(String idVerbale) {
        return db.getCDL(idVerbale);
    }

    /**
     * Ritorna le informazioni della scuola
     * @param idVerbale id verbale
     * @return informazioni scuola
     */
    public Scuola getScuola(String idVerbale) {
        return db.getScuola(idVerbale);
    }

    /**
     * Ritorna l'ora di apertura del verbale
     * @param idVerbale id verbale
     * @return ora di apertura
     */
    public LocalTime getOraApertura(String idVerbale) {
        return db.getOraApertura(idVerbale);
    }

    /**
     * Ritorna le informazioni della materia
     * @param idVerbale id verbale
     * @return informazioni materia
     */
    public Materia getMateria(String idVerbale) {
        return db.getMateria(idVerbale);
    }

}
