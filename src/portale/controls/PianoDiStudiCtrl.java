package portale.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import portale.boundaries.LoginFormStudente;
import portale.boundaries.PianoDiStudiForm;

import java.io.IOException;


public class PianoDiStudiCtrl {

    private Stage stage; //gli serve per prendersi il riferimento alla pagina corrente

    public PianoDiStudiCtrl(Stage thisStage) {

        this.stage = thisStage;

    }

    public void logout() {

        FXMLLoader page = new FXMLLoader(getClass().getResource("../../res/LoginFormStudente.fxml"));

        Parent root = null;

        try {

            root = (Parent) page.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            // controller.riempiTabella(thisStage);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
