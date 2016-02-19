package portale.controls;

import javafx.collections.ObservableList;
import portale.boundaries.DBMSStampaElencoIscrittiBnd;
import portale.entities.CorsoDiLaurea;
import portale.entities.Scuola;

public class StampaElencoIscrittiCtrl {

    public ObservableList<Scuola> getScuole(String matricolaDocente){
        DBMSStampaElencoIscrittiBnd db = new DBMSStampaElencoIscrittiBnd();
        return db.getScuole(matricolaDocente);
    }

    public ObservableList<CorsoDiLaurea> getCdls(String idScuola, String matricolaDocente){
        DBMSStampaElencoIscrittiBnd db = new DBMSStampaElencoIscrittiBnd();
        return db.getCDLs(idScuola, matricolaDocente);
    }
}
