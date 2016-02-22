package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSInizializzaVerbaleBnd;
import portale.common.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Classe control che gestisce l'inizializzazione del verbale
 */
public class InizializzaVerbaleCtrl {

    /**
     * Boundary che gestisce la comunicazione con il database per l'inizializzazione
     */
    private DBMSInizializzaVerbaleBnd mDBMSInizializzaVerbaleBnd = new DBMSInizializzaVerbaleBnd();

    /**
     * Ritorna la lista delle scuole in cui insegna il docente
     * @param mDocente informazioni docente
     * @return lista di scuole
     */
    public ObservableList<Scuola> getScuole(DocenteClass mDocente) {

        try {
            return mDBMSInizializzaVerbaleBnd.getScuole(mDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista dei corsi di laurea di una scuola selezionata in cui insegna il docente
     * @param pScuola informazioni scuola
     * @param pDocente informazioni docente
     * @return lista di corsi di laurea
     */
    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getCDLs(pScuola, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista delle materie insegnate dal docente in un corso di laurea selezionato
     * @param mSelectedCorsoDiLaurea informazioni corso di laurea
     * @param pDocente informazioni docente
     * @return lista di materie
     */
    public ObservableList<Materia> getMaterie(CorsoDiLaurea mSelectedCorsoDiLaurea, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getMaterie(mSelectedCorsoDiLaurea, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista degli appelli di una materia selezionata
     * @param pMateria informazioni materia
     * @param pDocente informazioni docente
     * @return lista di appelli
     */
    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getAppelli(pMateria, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista degli studenti iscritti ad un appello
     * @param pAppello appello
     * @return lista di studenti iscritti
     */
    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) {
        try {
            return mDBMSInizializzaVerbaleBnd.getIscrittiAppello(pAppello);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserisce un nuovo verbale nel database
     * @param pTimestamp ora di apertura
     * @param pSelectedCorsoDiLaurea corso di laurea selezionato
     * @param pSelectedAppello appello selezionato
     * @param pSelectedMateria materia selezionata
     * @return id verbale
     */
    public int insertNewVerbale(Timestamp pTimestamp, CorsoDiLaurea pSelectedCorsoDiLaurea, Appello pSelectedAppello,
                                Materia pSelectedMateria) {
        try {
            return mDBMSInizializzaVerbaleBnd.insertNewVerbale(pTimestamp, pSelectedCorsoDiLaurea, pSelectedAppello, pSelectedMateria);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
