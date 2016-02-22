package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSVerbaliApertiBnd;
import portale.common.DisplayVerbale;

/**
 * Created by carlonuccio on 22/02/16.
 */
public class PortaleDocenteCtrl {

    private DBMSVerbaliApertiBnd mDBMSVerbaliApertiBnd = new DBMSVerbaliApertiBnd();

    public PortaleDocenteCtrl() {
    }

    public ObservableList<DisplayVerbale> verbaliAperti(String pMatricolaDocente){
        return mDBMSVerbaliApertiBnd.verbaliAperti(pMatricolaDocente);
    }

}
