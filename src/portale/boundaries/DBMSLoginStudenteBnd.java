package portale.boundaries;

import javafx.collections.ObservableList;
import portale.entities.PianoDiStudi;

public class DBMSLoginStudenteBnd {

    public boolean isPresenteUser(String pMatricola, String pPassword) {
        return false;
    }

    public ObservableList<PianoDiStudi> getPianodiStudi(String pMatricola) {
        return null;
    }

}
