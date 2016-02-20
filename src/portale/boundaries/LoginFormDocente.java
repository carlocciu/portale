package portale.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.controls.AutenticazioneDocenteCtrl;

public class LoginFormDocente {

    @FXML private TextField matricolaTF;

    @FXML private PasswordField passPF;

    @FXML private Button loginButton;

    AutenticazioneDocenteCtrl ctr = new AutenticazioneDocenteCtrl();

    @FXML public void clickLogin() throws Exception{

        if (!(matricolaTF.getText().isEmpty()) && !(passPF.getText().isEmpty())) {
            ctr.setMatricolaDocente(matricolaTF.getText());
            ctr.setPasswordDocente(passPF.getText());
            ctr.isPresent((Stage) loginButton.getScene().getWindow());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Username e/o password mancanti");
            alert.showAndWait();
        }


    }

}
