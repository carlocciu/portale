package portale.Studente.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.Studente.controls.AutenticazioneStudenteCtrl;

/**
 * Classe boundary che gestisce la finestra di login per lo studente
 */
public class LoginFormStudente {

    /**
     * Campo di testo per la matricola
     */
    @FXML
    private TextField matricolaTF;

    /**
     * Campo di testo per la password
     */
    @FXML
    private PasswordField passPF;

    /**
     * Button per confermare il login
     */
    @FXML
    private Button loginButton;


    /**
     * Control che gestisce il login
     */
    private AutenticazioneStudenteCtrl mAutenticazioneStudenteCtrl = new AutenticazioneStudenteCtrl();


    /**
     * Permette di avviare il procedimento di verifica
     * dei dati inseriti nel momento in cui si clicca sul
     * button.
     */
    @FXML
    public void clickLogin() throws Exception {

        if (!(matricolaTF.getText().isEmpty()) && !(passPF.getText().isEmpty())) {
            mAutenticazioneStudenteCtrl.setMatricola(matricolaTF.getText());
            mAutenticazioneStudenteCtrl.setPassword(passPF.getText());
            mAutenticazioneStudenteCtrl.checkLogin((Stage) loginButton.getScene().getWindow());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Login");
            alert.setHeaderText("Autenticazione non riuscita");

            if (matricolaTF.getText().isEmpty()) {

                alert.setContentText("Il campo matricola e' vuoto");
            } else {

                alert.setContentText("Il campo password e' vuoto");
            }

            alert.showAndWait();
        }


    }

}
