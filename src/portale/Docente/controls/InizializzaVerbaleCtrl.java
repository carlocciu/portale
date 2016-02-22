package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSInizializzaVerbaleBnd;
import portale.common.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class InizializzaVerbaleCtrl {

    private DBMSInizializzaVerbaleBnd mDBMSInizializzaVerbaleBnd = new DBMSInizializzaVerbaleBnd();

    public ObservableList<Scuola> getScuole(DocenteClass mDocente) {

        try {
            return mDBMSInizializzaVerbaleBnd.getScuole(mDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getCDLs(pScuola, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Materia> getMaterie(CorsoDiLaurea mSelectedCorsoDiLaurea, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getMaterie(mSelectedCorsoDiLaurea, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) {
        try {
            return mDBMSInizializzaVerbaleBnd.getAppelli(pMateria, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) {
        try {
            return mDBMSInizializzaVerbaleBnd.getIscrittiAppello(pAppello);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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
