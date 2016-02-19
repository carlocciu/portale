package portale.controls;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.stage.Stage;
import portale.boundaries.DBMSLoginDocenteBnd;
import portale.entities.DocenteClass;


public class AutenticazioneDocenteCtrl {

    private String matricolaDocente;
    private String passwordDocente;

    public AutenticazioneDocenteCtrl(String matricolaDocente, String passwordDocente) {
        this.matricolaDocente = matricolaDocente;
        this.passwordDocente = passwordDocente;
    }

    public void isPresent(Stage s) throws Exception{
        DBMSLoginDocenteBnd db = new DBMSLoginDocenteBnd();
        if (db.isPresentUser(matricolaDocente, passwordDocente)){
            DocenteClass doc = new DocenteClass(matricolaDocente, db.getNome(), db.getCognome());
            Parent root = FXMLLoader.load(getClass().getResource("../../res/PortaleDocenteFrame.fxml"));
            s.setTitle("Portale Docente");
            s.setScene(new Scene(root, 600, 600));
            s.setResizable(false);
            s.show();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(doc.getNomeDocente() + doc.getCognomeDocente());
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Username e/o password errati");
            alert.showAndWait();
        }
    }

}
