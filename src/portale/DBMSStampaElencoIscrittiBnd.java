package portale;

import javafx.collections.ObservableList;

import java.util.Date;

public class DBMSStampaElencoIscrittiBnd {

    public ObservableList<StudenteClass> getStudentiIScritti(Date pData, String pCodiceMateria, String pDocente) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(String pScuola) {
        return null;
    }

    public ObservableList<Materia> getMaterie(String pCdl, String pDocente) {
        return null;
    }

    public ObservableList<Appello> getAppelli(String pDocente, String pCodiceMateria, String pCdl) {
        return null;
    }

    public ObservableList<String> getScuole() {
        return null;
    }

}
