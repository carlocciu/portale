package portale.controls;

import javafx.collections.ObservableList;
import portale.boundaries.DBMSInizializzaVerbaleBnd;

import java.sql.SQLException;

public class InizializzaVerbaleCtrl {

    private DBMSInizializzaVerbaleBnd mDBMSInizializzaVerbaleBnd;

    public InizializzaVerbaleCtrl() throws SQLException {
        mDBMSInizializzaVerbaleBnd = new DBMSInizializzaVerbaleBnd();
    }

    public ObservableList<String> getScuole() {

        try {
            return mDBMSInizializzaVerbaleBnd.getScuole();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
