package portale.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import portale.controls.InizializzaVerbaleCtrl;
import portale.entities.*;

import java.io.IOException;

public class InizializzaVerbaleForm{

    @FXML
    private ComboBox<Scuola> scuoleCB;
    private Scuola mSelectedScuola;

    @FXML
    private ComboBox<CorsoDiLaurea> cdlsCB;
    private CorsoDiLaurea mSelectedCorsoDiLaurea;

    @FXML
    private ComboBox<Materia> materieCB;
    private Materia mSelectedMateria;

    @FXML
    private ComboBox<Appello> appelliCB;
    private Appello mSelectedAppello;

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

    private InizializzaVerbaleCtrl mInizializzaVerbaleCtrl = new InizializzaVerbaleCtrl();

    private DocenteClass mDocente;


    public void setDocente(DocenteClass docente) {
        mDocente = docente;
    }

    public DocenteClass getDocente() {
        return mDocente;
    }

    public void clickInizializza() {

    }


    public void riempiScuoleCB() {
        scuoleCB.getItems().clear();
        scuoleCB.setItems(mInizializzaVerbaleCtrl.getScuole(mDocente));

        scuoleCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Scuola>() {
            @Override
            public void changed(ObservableValue<? extends Scuola> observable, Scuola oldValue, Scuola newValue) {
                mSelectedScuola = newValue;
                riempiCdlsCB(mSelectedScuola);
            }
        });
    }

    public void riempiCdlsCB(Scuola pScuola) {
        cdlsCB.getItems().clear();
        cdlsCB.setItems(mInizializzaVerbaleCtrl.getCDLs(pScuola, mDocente));

        cdlsCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CorsoDiLaurea>() {
            @Override
            public void changed(ObservableValue<? extends CorsoDiLaurea> observable, CorsoDiLaurea oldValue, CorsoDiLaurea newValue) {
                mSelectedCorsoDiLaurea = newValue;
                riempiMaterieCB(mSelectedCorsoDiLaurea);
            }
        });
    }

    public void riempiMaterieCB(CorsoDiLaurea pCorsoDiLaurea) {
        materieCB.getItems().clear();
        materieCB.setItems(mInizializzaVerbaleCtrl.getMaterie(mSelectedCorsoDiLaurea, mDocente));

        materieCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Materia>() {
            @Override
            public void changed(ObservableValue<? extends Materia> observable, Materia oldValue, Materia newValue) {
                mSelectedMateria = newValue;
                riempiAppelliCB();
            }
        });
    }

    public void riempiAppelliCB() {
        appelliCB.getItems().clear();
        appelliCB.setItems(mInizializzaVerbaleCtrl.getAppelli(mSelectedMateria, mDocente));

        appelliCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appello>() {
            @Override
            public void changed(ObservableValue<? extends Appello> observable, Appello oldValue, Appello newValue) {
                mSelectedAppello = newValue;
            }
        });
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
            Parent parent = fxmlLoader.load();

            PortaleDocenteFrame portaleDocenteFrame = fxmlLoader.getController();
            portaleDocenteFrame.setCurrDocente(mDocente);

            stage.setTitle("Portale Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickImportaIscritti(ActionEvent actionEvent) {
        if(mSelectedAppello != null){
            mSelectedAppello.setStudentiIscritti(mInizializzaVerbaleCtrl.getIscrittiAppello(mSelectedAppello));
        }
        else{
            //Advice docente that should first select an appello
        }

    }
}
