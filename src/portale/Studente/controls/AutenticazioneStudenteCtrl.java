package portale.Studente.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.Studente.boundaries.DBMSLoginStudenteBnd;
import portale.Studente.boundaries.PianoDiStudiForm;

/**
 * Classe control che gestisce il login per lo studente
 */
public class AutenticazioneStudenteCtrl {

    /**
     * Matricola studente
     */
    private String matricola;
    /**
     * Password studente
     */
    private String password;
    //private Stage thisStage;

    /**
     * Boundary che gestisce la comunicazione con il database per il login
     */
    private DBMSLoginStudenteBnd mDBMSLoginStudenteBnd = new DBMSLoginStudenteBnd();

    /**
     * Genera un oggetto AutenticazioneStudenteCtrl vuoto
     */
    public AutenticazioneStudenteCtrl() {
    }

    /**
     * Genera un oggetto AutenticazioneStudenteCtrl
     * @param matricola matricola
     * @param password password
     */
    public AutenticazioneStudenteCtrl(String matricola, String password) {

        this.matricola = matricola;
        this.password = password;

    }

    /**
     * Ritorna la matricola dello studente
     * @return matricola
     */
    public String getMatricola() {

        return this.matricola;
    }

    /**
     * Setta la matricola dello studente
     * @param matricola matricola studente
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * Ritorna la password dello studente
     * @return password
     */
    public String getPassword() {

        return this.password;
    }

    /**
     * Setta la password dello studente
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Controlla se i dati inseriti sono corretti; in caso di esito positivo, inoltra l'utente nella pagina principale
     * del portale, altrimenti visualizza un messaggio d'errore
     * @param s stage corrente
     * @throws Exception
     */
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

