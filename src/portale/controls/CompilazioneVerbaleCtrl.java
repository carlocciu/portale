package portale.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.boundaries.CompilazioneVerbaleForm;
import portale.boundaries.DBMSInizializzaVerbaleBnd;
import portale.boundaries.DBMSLoginStudenteBnd;
import portale.boundaries.DBMSVerbalizzaEsameBnd;
import portale.entities.Appello;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompilazioneVerbaleCtrl {

    DBMSInizializzaVerbaleBnd mDBMSInizializzaVerbaleBnd = new DBMSInizializzaVerbaleBnd();
    DBMSVerbalizzaEsameBnd mDBMSVerbalizzaEsameBnd = new DBMSVerbalizzaEsameBnd();

    public boolean isValidUserPassword(StudenteClass pStudente, String pPassword) {
        DBMSLoginStudenteBnd dbmsLoginStudenteBnd = new DBMSLoginStudenteBnd();
        return dbmsLoginStudenteBnd.isPresenteUser(pStudente.getMatricolaStudente(), pPassword);
    }

    public void verbalizzaEsame(StudenteClass pStudente, VerbaleComplessivo pVerbaleComplessivo,
                                CompilazioneVerbaleForm.Esito pEsito, String pVoto) {

        try {
            mDBMSVerbalizzaEsameBnd.insertEsameSostenuto(pStudente, pVerbaleComplessivo, pEsito, pVoto);
            mDBMSVerbalizzaEsameBnd.updatePianoDiStudi(pStudente, pVerbaleComplessivo, pEsito, pVoto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<StudenteClass> getStudentiDaEsaminare(Appello pAppello) {

        try {
            ObservableList<StudenteClass> studentiDaEsaminare = FXCollections.observableArrayList();
            ArrayList<StudenteClass> studenti = mDBMSInizializzaVerbaleBnd.getIscrittiAppello(pAppello);

            studentiDaEsaminare.addAll(studenti);

            return studentiDaEsaminare;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
