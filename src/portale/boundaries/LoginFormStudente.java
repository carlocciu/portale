package portale.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.controls.AutenticazioneDocenteCtrl;
import portale.controls.AutenticazioneStudenteCtrl;

public class LoginFormStudente {

    @FXML private TextField matricolaTF;

    @FXML private PasswordField passPF;

    @FXML private Button loginButton;

    @FXML public void clickLogin() throws Exception{

        AutenticazioneStudenteCtrl login = new AutenticazioneStudenteCtrl(matricolaTF.getText(), passPF.getText(), (Stage) loginButton.getScene().getWindow());

        login.checkLogin();

    }

}
