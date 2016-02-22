package portale.Docente.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import portale.Docente.controls.PortaleDocenteCtrl;
import portale.common.DocenteClass;

import java.io.IOException;

public class PortaleDocenteFrame extends Stage {

    /**
     * Button per iniziare le operazioni per i verbali
     */
    @FXML
    private Button verbaleButton;

    /**
     * Button per visualizzare la finestra per stampare gli iscritti
     */
    @FXML
    private Button stampaIscrittiButton;

    /**
     * Button logout
     */
    @FXML
    private Button logoutButton;

    /**
     * Informazioni del docente
     */
    private DocenteClass currDocente;

    /**
     * Control che gestisce la comunicazione con il database
     */
    private PortaleDocenteCtrl mPortaleDocenteCtrl = new PortaleDocenteCtrl();

    /**
     * Setta le informazioni del docente
     * @param currDocente informazioni docente
     */
    public void setCurrDocente(DocenteClass currDocente) {
        this.currDocente = currDocente;
    }

    /**
     * Inoltra il docente nella pagina di gestione dei verbali
     * @throws Exception
     */
    @FXML
    public void clickVerbale() throws Exception {

        Stage primaryStage = (Stage) verbaleButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/VerbaleFrame.fxml"));
        Parent root = fxmlLoader.load();

        VerbaleFrame controller = fxmlLoader.getController();
        controller.setDocente(currDocente);

        controller.riempiVerbaliAperti(mPortaleDocenteCtrl.verbaliAperti(currDocente.getMatricolaDocente()));

        primaryStage.setTitle("Verbali");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Inoltra il docente nella pagina per la stampa degli iscritti ad un appello
     * @throws Exception
     */
    @FXML
    public void clickStampaIscritti() throws Exception {

        try {
            Stage stage = (Stage) stampaIscrittiButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/StampaElencoIscrittiForm.fxml"));
            Parent parent = fxmlLoader.load();

            /* Pass Docente to StampaElencoIscrittiForm */
            StampaElencoIscrittiForm elencoIscrittiForm = fxmlLoader.getController();
            elencoIscrittiForm.setDocente(currDocente);
            elencoIscrittiForm.init();

            stage.setTitle("Stampa Elenco Iscritti");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logout
     */
    public void clickLogout() {

        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/LoginFormDocente.fxml"));

            Parent parent = fxmlLoader.load();

            stage.setTitle("Login Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
