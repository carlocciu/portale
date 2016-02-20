package portale.controls;

import javafx.collections.ObservableList;
import portale.boundaries.DBMSVerbaliApertiBnd;
import portale.entities.DisplayVerbale;
import portale.entities.DocenteClass;

public class VerbaleCtrl {

    private DocenteClass docente;

    DBMSVerbaliApertiBnd db = new DBMSVerbaliApertiBnd();

    public VerbaleCtrl(DocenteClass docente) {
        this.docente = docente;
    }

    public ObservableList<DisplayVerbale> getVerbaliAperti(){
        return db.verbaliAperti(docente.getMatricolaDocente());
    }

}
