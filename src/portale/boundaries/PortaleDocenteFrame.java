package portale.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import portale.entities.DocenteClass;

import java.io.IOException;

public class PortaleDocenteFrame extends Stage{

    @FXML private Button verbaleButton;

    @FXML private Button stampaIscrittiButton;

    @FXML private Button logoutButton;

    private DocenteClass currDocente;

    public void setCurrDocente(DocenteClass currDocente) {
        this.currDocente = currDocente;
    }

    @FXML public void clickVerbale() throws Exception {
        Stage primaryStage = (Stage) verbaleButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/VerbaleFrame.fxml"));
        Parent root = fxmlLoader.load();

        DBMSVerbaliApertiBnd db = new DBMSVerbaliApertiBnd();

        VerbaleFrame controller = fxmlLoader.getController();
        controller.setDocente(currDocente);
        controller.riempiVerbaliAperti(db.verbaliAperti(currDocente.getMatricolaDocente()));

        primaryStage.setTitle("Verbali");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML public void clickStampaIscritti() throws Exception {

        Stage primaryStage = (Stage) stampaIscrittiButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/StampaElencoIscrittiForm.fxml"));
        Parent root = fxmlLoader.load();

        StampaElencoIscrittiForm controller = fxmlLoader.getController();
        controller.setDoc(currDocente);
        controller.riempiScuoleCB();

        primaryStage.setTitle("Elenco Iscritti");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void clickLogout() {

        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/LoginFormDocente.fxml"));

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
