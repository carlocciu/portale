package portale.Docente.controls;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.Docente.boundaries.DBMSLoginDocenteBnd;
import portale.Docente.boundaries.PortaleDocenteFrame;
import portale.common.DocenteClass;

/**
 * Classe control che gestisce l'autenticazione del docente
 */
public class AutenticazioneDocenteCtrl {

    /**
     * Matricola del docente
     */
    private String matricolaDocente;
    /**
     * Password del docente
     */
    private String passwordDocente;

    /**
     * Boundary che comunica con il databse
     */
    private DBMSLoginDocenteBnd mDBMSLoginDocenteBnd = new DBMSLoginDocenteBnd();

    /**
     * Genera un oggetto AutenticazioneDocenteCtrl vuoto
     */
    public AutenticazioneDocenteCtrl() {
        this.matricolaDocente = "";
        this.passwordDocente = "";
    }

    /**
     * Genera un oggetto AutenticazioneDocenteCtrl
     * @param matricolaDocente matricola
     * @param passwordDocente password
     */
    public AutenticazioneDocenteCtrl(String matricolaDocente, String passwordDocente) {
        this.matricolaDocente = matricolaDocente;
        this.passwordDocente = passwordDocente;
    }

    /**
     * Setta la matricola del docente
     * @param matricolaDocente matricola docente
     */
    public void setMatricolaDocente(String matricolaDocente) {
        this.matricolaDocente = matricolaDocente;
    }

    /**
     * Setta la password del docente
     * @param passwordDocente password docente
     */
    public void setPasswordDocente(String passwordDocente) {
        this.passwordDocente = passwordDocente;
    }

    /**
     * Controlla se i dati inseriti sono corretti; in caso di esito positivo, inoltra l'utente nella pagina principale
     * del portale, altrimenti visualizza un messaggio d'errore
     * @param s stage corrente
     * @throws Exception
     */
    public void checkLogin(Stage s) throws Exception {
        if (mDBMSLoginDocenteBnd.isPresentUser(matricolaDocente, passwordDocente)) {
            DocenteClass doc = new DocenteClass(matricolaDocente, mDBMSLoginDocenteBnd.getNome(), mDBMSLoginDocenteBnd.getCognome());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/PortaleDocenteFrame.fxml"));
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
