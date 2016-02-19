package portale.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.controls.AutenticazioneDocenteCtrl;

public class LoginForm {

    @FXML private TextField matricolaTF;

    @FXML private PasswordField passPF;

    @FXML private Button loginButton;

    @FXML public void clickLogin() throws Exception{
        AutenticazioneDocenteCtrl ctr = new AutenticazioneDocenteCtrl(matricolaTF.getText(), passPF.getText());
        ctr.isPresent((Stage) loginButton.getScene().getWindow());

    }

}
