package portale.Docente.controls;

import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSVerbaliApertiBnd;
import portale.common.*;

import java.time.LocalTime;

public class VerbaleCtrl {

    DBMSVerbaliApertiBnd db = new DBMSVerbaliApertiBnd();
    private DocenteClass docente;

    public VerbaleCtrl(DocenteClass docente) {
        this.docente = docente;
    }

    public ObservableList<DisplayVerbale> getVerbaliAperti() {
        return db.verbaliAperti(docente.getMatricolaDocente());
    }

    public Appello getAppello(String idVerbale) {
        return db.getAppello(idVerbale);
    }

    public CorsoDiLaurea getCDL(String idVerbale) {
        return db.getCDL(idVerbale);
    }

    public Scuola getScuola(String idVerbale) {
        return db.getScuola(idVerbale);
    }

    public LocalTime getOraApertura(String idVerbale) {
        return db.getOraApertura(idVerbale);
    }

    public Materia getMateria(String idVerbale) {
        return db.getMateria(idVerbale);
    }

}
