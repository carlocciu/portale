package portale.controls;


import javafx.scene.control.Alert;

import portale.boundaries.DBMSLoginDocenteBnd;


public class AutenticazioneDocenteCtrl {

    private String matricolaDocente;
    private String passwordDocente;
    private DBMSLoginDocenteBnd db;

    public AutenticazioneDocenteCtrl(String matricolaDocente, String passwordDocente) {
        this.matricolaDocente = matricolaDocente;
        this.passwordDocente = passwordDocente;
    }

    public void isPresent() throws Exception{
        if (db.isPresentUser(matricolaDocente, passwordDocente)){

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Username e/o password errati");
            alert.showAndWait();
        }
    }

}
