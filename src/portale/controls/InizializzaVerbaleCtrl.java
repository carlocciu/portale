package portale.controls;

import javafx.collections.ObservableList;
import portale.boundaries.DBMSInizializzaVerbaleBnd;
import portale.entities.CorsoDiLaurea;
import portale.entities.DocenteClass;
import portale.entities.Scuola;

import java.sql.SQLException;

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

    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente){
        try {
            return mDBMSInizializzaVerbaleBnd.getCDLs(pScuola, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
