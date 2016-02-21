package portale.controls;

import portale.boundaries.CompilazioneVerbaleForm;
import portale.boundaries.DBMSLoginStudenteBnd;
import portale.boundaries.DBMSVerbalizzaEsameBnd;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

import java.sql.SQLException;

public class CompilazioneVerbaleCtrl {

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
}
