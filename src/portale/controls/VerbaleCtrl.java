package portale.controls;

import javafx.collections.ObservableList;
import portale.boundaries.DBMSVerbaliApertiBnd;
import portale.entities.*;

import java.time.LocalTime;

public class VerbaleCtrl {

    private DocenteClass docente;

    DBMSVerbaliApertiBnd db = new DBMSVerbaliApertiBnd();

    public VerbaleCtrl(DocenteClass docente) {
        this.docente = docente;
    }

    public ObservableList<DisplayVerbale> getVerbaliAperti(){
        return db.verbaliAperti(docente.getMatricolaDocente());
    }

    public Appello getAppello(String idVerbale){
        return db.getAppello(idVerbale);
    }

    public CorsoDiLaurea getCDL(String idVerbale){
        return db.getCDL(idVerbale);
    }

    public Scuola getScuola(String idVerbale){
        return db.getScuola(idVerbale);
    }

    public LocalTime getOraApertura(String idVerbale){
        return db.getOraApertura(idVerbale);
    }

    public Materia getMateria(String idVerbale){
        return db.getMateria(idVerbale);
    }

}
