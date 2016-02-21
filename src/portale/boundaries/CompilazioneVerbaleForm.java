package portale.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import portale.controls.CompilazioneVerbaleCtrl;
import portale.entities.DocenteClass;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

import java.io.IOException;
import java.util.Optional;

public class CompilazioneVerbaleForm {


    @FXML
    private ComboBox<StudenteClass> studentiCB;
    private ObservableList<StudenteClass> mStudentiDaEsaminare;
    private StudenteClass mSelectedStudente;

    @FXML
    private TextArea domandeTA;

    @FXML
    private ComboBox<Esito> esitoCB;
    public enum Esito {positivo, negativo};
    private Esito mSelectedEsito;

    public ComboBox<String> mVotiComboBox;
    private String mSelectedVoto;

    @FXML
    private Button verbalizzaButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    private DocenteClass mDocente;

    private VerbaleComplessivo mVerbaleComplessivo;

    private CompilazioneVerbaleCtrl mCompilazioneVerbaleCtrl = new CompilazioneVerbaleCtrl();


    public void init(DocenteClass pDocente, VerbaleComplessivo pVerbaleComplessivo){
        setDocente(pDocente);
        setVerbaleComplessivo(pVerbaleComplessivo);
        initStudentiCB();
        initEsitoComboBox();
    }

    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    public void setVerbaleComplessivo(VerbaleComplessivo pVerbaleComplessivo) {
        mVerbaleComplessivo = pVerbaleComplessivo;
    }

    public void initStudentiCB() {
        /* Get studenti da esaminare from appello */
        mStudentiDaEsaminare = FXCollections.observableArrayList();
        mStudentiDaEsaminare.addAll(mVerbaleComplessivo.getAppello().getStudentiIscritti());

        studentiCB.getItems().clear();
        studentiCB.setItems(mStudentiDaEsaminare);

        studentiCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudenteClass>() {
            @Override
            public void changed(ObservableValue<? extends StudenteClass> observable, StudenteClass oldValue, StudenteClass newValue) {
                mSelectedStudente = newValue;
            }
        });
    }

    public void initEsitoComboBox(){
        esitoCB.getItems().clear();
        ObservableList<Esito> esitiPossibili = FXCollections.observableArrayList();
        esitiPossibili.add(Esito.positivo);
        esitiPossibili.add(Esito.negativo);

        esitoCB.setItems(esitiPossibili);

        esitoCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Esito>() {
            @Override
            public void changed(ObservableValue<? extends Esito> observable, Esito oldValue, Esito newValue) {
                mSelectedEsito = newValue;
                if(mSelectedEsito == Esito.positivo)
                    initVotiComboBox();
                else
                    mVotiComboBox.getItems().clear();
            }
        });
    }

    public void initVotiComboBox(){
        mVotiComboBox.getItems().clear();
        ObservableList<String> votiPossibili = FXCollections.observableArrayList();
        for(int i = 18; i <= 30; i++)
            votiPossibili.add(String.valueOf(i));
        votiPossibili.add("30L");
        mVotiComboBox.setItems(votiPossibili);

        mVotiComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mSelectedVoto = newValue;
            }
        });
    }

    public void clickVerbalizza() {

        if(mSelectedStudente != null && mSelectedEsito != null && mSelectedVoto != null){
            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Firma Studente");
            passwordDialog.setHeaderText("Inserisci password portale studenti");

            Optional<String> password = passwordDialog.showAndWait();

            if(password.isPresent()){
                System.out.println(password.get());
                if(mCompilazioneVerbaleCtrl.isValidUserPassword(mSelectedStudente, password.get())){
                    mCompilazioneVerbaleCtrl.verbalizzaEsame(mSelectedStudente, mVerbaleComplessivo, mSelectedEsito,
                            mSelectedVoto);

                } else {
                    passwordDialog.setHeaderText("Inserisci password portale studenti\nPassword Errata");
                }
            }
        }

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

    private void clearComboBoxes(){
        studentiCB.getItems().clear();
        esitoCB.getItems().clear();
        mVotiComboBox.getItems().clear();
    }

}
