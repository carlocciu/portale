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

    @FXML
    private Button verbaleButton;

    @FXML
    private Button stampaIscrittiButton;

    @FXML
    private Button logoutButton;

    private DocenteClass currDocente;

    private PortaleDocenteCtrl mPortaleDocenteCtrl = new PortaleDocenteCtrl();

    public void setCurrDocente(DocenteClass currDocente) {
        this.currDocente = currDocente;
    }

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
