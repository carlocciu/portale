package portale.Studente.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.Studente.boundaries.DBMSLoginStudenteBnd;
import portale.Studente.boundaries.PianoDiStudiForm;

public class AutenticazioneStudenteCtrl {

    private String matricola;
    private String password;
    //private Stage thisStage;


    private DBMSLoginStudenteBnd mDBMSLoginStudenteBnd = new DBMSLoginStudenteBnd();

    public AutenticazioneStudenteCtrl() {
    }

    public AutenticazioneStudenteCtrl(String matricola, String password) {

        this.matricola = matricola;
        this.password = password;

    }

    public String getMatricola() {

        return this.matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkLogin(Stage s) throws Exception {

        if (mDBMSLoginStudenteBnd.isPresenteUser(matricola, password)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/PianoDiStudiForm.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            PianoDiStudiForm controller = fxmlLoader.getController();


            controller.fillTheTable(mDBMSLoginStudenteBnd.getPianodiStudi(matricola));

            s.setTitle("Piano Di Studi");
            s.setScene(new Scene(root, 700, 700));
            s.setResizable(false);
            s.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Studente non trovato");
            alert.showAndWait();

        }

    }

}

