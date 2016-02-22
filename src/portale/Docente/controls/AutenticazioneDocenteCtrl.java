package portale.Docente.controls;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.Docente.boundaries.DBMSLoginDocenteBnd;
import portale.Docente.boundaries.PortaleDocenteFrame;
import portale.common.DocenteClass;


public class AutenticazioneDocenteCtrl {

    private String matricolaDocente;
    private String passwordDocente;

    private DBMSLoginDocenteBnd mDBMSLoginDocenteBnd = new DBMSLoginDocenteBnd();

    public AutenticazioneDocenteCtrl() {
        this.matricolaDocente = "";
        this.passwordDocente = "";
    }

    public AutenticazioneDocenteCtrl(String matricolaDocente, String passwordDocente) {
        this.matricolaDocente = matricolaDocente;
        this.passwordDocente = passwordDocente;
    }

    public void setMatricolaDocente(String matricolaDocente) {
        this.matricolaDocente = matricolaDocente;
    }

    public void setPasswordDocente(String passwordDocente) {
        this.passwordDocente = passwordDocente;
    }

    public void checkLogin(Stage s) throws Exception {
        if (mDBMSLoginDocenteBnd.isPresentUser(matricolaDocente, passwordDocente)) {
            DocenteClass doc = new DocenteClass(matricolaDocente, mDBMSLoginDocenteBnd.getNome(), mDBMSLoginDocenteBnd.getCognome());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/PortaleDocenteFrame.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            PortaleDocenteFrame controller = fxmlLoader.getController();
            controller.setCurrDocente(doc);

            s.setTitle("Portale Docente");
            s.setScene(new Scene(root, 600, 600));
            s.setResizable(false);
            s.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRORE");
            alert.setContentText("Username e/o password errati");
            alert.showAndWait();
        }
    }

}
