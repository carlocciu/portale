package portale.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    /**
     * Permette di avviare il procedimento di verifica
     * dei dati inseriti nel momento in cui si clicca sul
     * button.
     */
    @FXML public void clickLogin() throws Exception{

        AutenticazioneStudenteCtrl login = new AutenticazioneStudenteCtrl(matricolaTF.getText(), passPF.getText());

        if(login.checkLogin()){

            //lo studente si è loggato con successo
            //devo mandarlo alla pagina del piano di studi e devo caricare il piano di studi con le materie

            Stage primaryStage = (Stage) loginButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/PianoDiStudiForm.fxml"));

            Parent root = (Parent)fxmlLoader.load();

            DBMSLoginStudenteBnd db = new DBMSLoginStudenteBnd();

            PianoDiStudiForm controller = fxmlLoader.getController();


            controller.fillTheTable(db.getPianodiStudi(matricolaTF.getText()));

            primaryStage.setTitle("Piano Di Studi");
            primaryStage.setScene(new Scene(root, 700, 700));
            primaryStage.setResizable(false);
            primaryStage.show();
        }

    }

}
