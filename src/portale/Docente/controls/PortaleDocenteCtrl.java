package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSVerbaliApertiBnd;
import portale.common.DisplayVerbale;

/**
 * Classe control che gestisce la pagina principale del portale docente
 */
public class PortaleDocenteCtrl {

    /**
     * Boundary che comunica con il database
     */
    private DBMSVerbaliApertiBnd mDBMSVerbaliApertiBnd = new DBMSVerbaliApertiBnd();

    /**
     * Costruisce un oggetto PortaleDocenteCtrl
     */
    public PortaleDocenteCtrl() {
    }

    /**
     * Ritorna la lista dei verbali aperti
     * @param pMatricolaDocente matricola docente
     * @return lista di verbali aperti
     */
    public ObservableList<DisplayVerbale> verbaliAperti(String pMatricolaDocente){
        return mDBMSVerbaliApertiBnd.verbaliAperti(pMatricolaDocente);
    }

}
