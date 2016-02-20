package portale.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.controls.InizializzaVerbaleCtrl;
import portale.entities.Appello;
import portale.entities.CorsoDiLaurea;
import portale.entities.DocenteClass;
import portale.entities.Materia;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InizializzaVerbaleForm implements Initializable{

    @FXML
    private ComboBox<String> scuoleCB;

    @FXML
    private ComboBox<CorsoDiLaurea> cdlsCB;

    private ComboBox<Materia> materiaCB;

    private ComboBox<Appello> appelloCB;

    @FXML
    private TextField oraAperturaTF;

    @FXML
    private Button importaIscrittiButton;

    @FXML
    private Button inizializzaButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    private InizializzaVerbaleCtrl mInizializzaVerbaleCtrl;

    public InizializzaVerbaleForm(){
        try {
            mInizializzaVerbaleCtrl = new InizializzaVerbaleCtrl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        riempiScuoleCB();
    }

    public void clickInizializza() {

    }

    public Appello clickImporta() {
        return null;
    }

    public void riempiScuoleCB() {
        scuoleCB.getItems().clear();
        scuoleCB.setItems(mInizializzaVerbaleCtrl.getScuole());
    }

    public void riempiCdlsCB(String pScuola, DocenteClass pDocente) {

    }

    public void riempiMaterieCB(DocenteClass pDocente, String pCDL) {

    }

    public void riempiAppelliCB(DocenteClass pDocente, String pCodiceMateria, String pCDL) {

    }

    public void clickLogout() {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/LoginFormDocente.fxml"));

            Parent parent = (Parent) fxmlLoader.load();

            stage.setTitle("Login Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickHome() {

        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/PortaleDocenteFrame.fxml"));

            Parent parent = (Parent) fxmlLoader.load();

            stage.setTitle("Portale Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickImportaIscritti(ActionEvent actionEvent) {
    }
}
