package portale.boundaries;

import javafx.collections.ObservableList;
import portale.entities.Appello;
import portale.entities.CorsoDiLaurea;
import portale.entities.Materia;
import portale.entities.StudenteClass;

import java.util.Date;

public class DBMSInizializzaVerbaleBnd {

    public ObservableList<StudenteClass> getStudentiIScritti(Date pDataAppello, String pMatricolaDocente, String pCodiceMateria) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(String pScuola) {
        return null;
    }

    public ObservableList<Materia> getMaterie(String pCdl, String pDocente) {
        return null;
    }

    public ObservableList<Appello> getAppelli(String pMatricolaDocente, String pCodiceMateria, String pCdl) {
        return null;
    }

    public ObservableList<String> getScuole() {
        return null;
    }

}
