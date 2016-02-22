package portale.Studente.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.Studente.controls.AutenticazioneStudenteCtrl;

public class LoginFormStudente {

    @FXML
    private TextField matricolaTF;

    @FXML
    private PasswordField passPF;

    @FXML
    private Button loginButton;

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
