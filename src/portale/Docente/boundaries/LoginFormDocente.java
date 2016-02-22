package portale.Docente.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.Docente.controls.AutenticazioneDocenteCtrl;

/**
 * Classe Boundary che gestisce la finestra di login per il docente
 */
public class LoginFormDocente {

    /**
     * Campo di testo per inserire la matricola
     */
    @FXML
    private TextField matricolaTF;

    /**
     * Campo di testo per inserire la password
     */
    @FXML
    private PasswordField passPF;

    /**
     * Button per confermare il login
     */
    @FXML
    private Button loginButton;

    /**
     * Control che gestisce l'autenticazione del docente
     */
    private AutenticazioneDocenteCtrl mAutenticazioneDocenteCtrl = new AutenticazioneDocenteCtrl();

    /**
     * Invia i dati al control; se i dati sono invalidi, visualizza un messaggio d'errore
     * @throws Exception
     */
    @FXML
    public void clickLogin() throws Exception {

        if (!(matricolaTF.getText().isEmpty()) && !(passPF.getText().isEmpty())) {
            mAutenticazioneDocenteCtrl.setMatricolaDocente(matricolaTF.getText());
            mAutenticazioneDocenteCtrl.setPasswordDocente(passPF.getText());
            mAutenticazioneDocenteCtrl.checkLogin((Stage) loginButton.getScene().getWindow());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Username e/o password mancanti");
            alert.showAndWait();
        }


    }

}
