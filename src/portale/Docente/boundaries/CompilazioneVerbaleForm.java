package portale.Docente.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import portale.Docente.controls.CompilazioneVerbaleCtrl;
import portale.common.DocenteClass;
import portale.common.StudenteClass;
import portale.common.VerbaleComplessivo;

import java.io.IOException;
import java.util.Optional;

/**
 * Classe Boundary che gestisce la finestra di compilazione del verbale
 */
public class CompilazioneVerbaleForm {

    /**
     * Menù per selezionare l'esito dell'esame
    */
    public ComboBox<String> mVotiComboBox;
    /**
     * Lista degli studenti iscritti all'appello
     */
    @FXML
    private ComboBox<StudenteClass> studentiCB;
    /**
     * Lista degli studenti iscritti che devono sostenere l'esame
     */
    private ObservableList<StudenteClass> mStudentiDaEsaminare;
    /**
     * Studente selezionato
     */
    private StudenteClass mSelectedStudente;
    /**
     * Domande fatte allo studente
     */
    @FXML
    private TextArea domandeTA;
    /**
     * Menù per selezionare esito positivo o negativo
     */
    @FXML
    private ComboBox<Esito> esitoCB;

    /**
     * Esito selezionato
     */
    private Esito mSelectedEsito;
    /**
     * Voto selezionato
     */
    private String mSelectedVoto;
    /**
     * Button per confermare l'esame sostenuto
     */
    @FXML
    private Button verbalizzaButton;
    /**
     * Button per effettuare il logout
     */
    @FXML
    private Button logoutButton;
    /**
     * Button per tornare alla pagina principale del portale
     */
    @FXML
    private Button homeButton;
    /**
     * Informazioni sul docente
     */
    private DocenteClass mDocente;
    /**
     * Informazioni del verbale
     */
    private VerbaleComplessivo mVerbaleComplessivo;
    /**
     * Control che gestisce la comunicazione con il database
     */
    private CompilazioneVerbaleCtrl mCompilazioneVerbaleCtrl = new CompilazioneVerbaleCtrl();

    /**
     * Mostra un alert per confermare la chiusura del verbale, aggiorna il database e mostra un pdf con la
     * lista degli esami sostenuti
     * @throws Exception
     */
    public void chiudiVerbale() throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Chiudi Verbale");
        alert.setContentText("Sei sicuro di chiudere il verbale?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            mCompilazioneVerbaleCtrl.chiudiVerbale(mVerbaleComplessivo);
            clickHome();
        } else {

        }


    }

    /**
     * Inizializza la pagina di compilazione
     * @param pDocente informazioni docente
     * @param pVerbaleComplessivo informazioni verbale
     */
    public void init(DocenteClass pDocente, VerbaleComplessivo pVerbaleComplessivo) {
        setDocente(pDocente);
        setVerbaleComplessivo(pVerbaleComplessivo);
        initStudentiCB();
        initEsitoComboBox();
    }

    /**
     * Setta le informazioni del docente
     * @param pDocente informazioni docente
     */
    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    /**
     * Setta le informazioni del verbale
     * @param pVerbaleComplessivo informazioni verbale
     */
    public void setVerbaleComplessivo(VerbaleComplessivo pVerbaleComplessivo) {
        mVerbaleComplessivo = pVerbaleComplessivo;
    }

    /**
     * Inizializza il menù degli studenti da esaminare
     */
    public void initStudentiCB() {
        /* Get studenti da esaminare from appello */
        mStudentiDaEsaminare = FXCollections.observableArrayList();
        mStudentiDaEsaminare.addAll(mCompilazioneVerbaleCtrl.getStudentiDaEsaminare(mVerbaleComplessivo.getAppello()));

        studentiCB.getItems().clear();
        studentiCB.setItems(mStudentiDaEsaminare);

        studentiCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudenteClass>() {
            @Override
            public void changed(ObservableValue<? extends StudenteClass> observable, StudenteClass oldValue, StudenteClass newValue) {
                if (newValue != null)
                    mSelectedStudente = newValue;
            }
        });
    }

    /**
     * Inizializza il menù dell'esito (positivo o negativo)
     */
    public void initEsitoComboBox() {
        esitoCB.getItems().clear();
        ObservableList<Esito> esitiPossibili = FXCollections.observableArrayList();
        esitiPossibili.add(Esito.positivo);
        esitiPossibili.add(Esito.negativo);

        esitoCB.setItems(esitiPossibili);

        esitoCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Esito>() {
            @Override
            public void changed(ObservableValue<? extends Esito> observable, Esito oldValue, Esito newValue) {
                if (newValue != null) {
                    mSelectedEsito = newValue;
                    if (mSelectedEsito == Esito.positivo)
                        initVotiComboBox();
                    else
                        mSelectedVoto = "0";
                }
            }
        });
    }

    /**
     * Se l'esito è positivo, mostra la lista dei voti possibili
     */
    public void initVotiComboBox() {
        mVotiComboBox.getItems().clear();
        ObservableList<String> votiPossibili = FXCollections.observableArrayList();
        for (int i = 18; i <= 30; i++)
            votiPossibili.add(String.valueOf(i));
        votiPossibili.add("30L");
        mVotiComboBox.setItems(votiPossibili);

        mVotiComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null)
                    mSelectedVoto = newValue;
            }
        });
    }

    /**
     * Conferma esame sostenuto e chiede allo studente di inserire la firma digitale
     */
    public void clickVerbalizza() {

        if (mSelectedStudente != null && ((mSelectedEsito == Esito.positivo && mSelectedVoto != null) || (mSelectedEsito == Esito.negativo && mSelectedVoto == "0"))) {
            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Firma Studente");
            passwordDialog.setHeaderText("Inserisci password portale studenti");

            Optional<String> password = passwordDialog.showAndWait();

            if (password.isPresent()) {
                //System.out.println(password.get());
                if (mCompilazioneVerbaleCtrl.isValidUserPassword(mSelectedStudente, password.get())) {
                    mCompilazioneVerbaleCtrl.verbalizzaEsame(mSelectedStudente, mVerbaleComplessivo, mSelectedEsito,
                            mSelectedVoto, domandeTA.getText());

                    clearComboBoxes();

                } else {
                    passwordDialog.setHeaderText("Inserisci password portale studenti\nPassword Errata");
                }
            }
        }

    }

    /**
     * Effettua il logout
     */
    public void clickLogout() {

        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/LoginFormDocente.fxml"));

            Parent parent = (Parent) fxmlLoader.load();

            stage.setTitle("Login Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Torna alla pagina principale del portale docente
     */
    public void clickHome() {

        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../res/PortaleDocenteFrame.fxml"));
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

    /**
     * Resetta i campi della pagina
     */
    private void clearComboBoxes() {
        initStudentiCB();
        domandeTA.clear();
        esitoCB.getSelectionModel().clearSelection();
        mVotiComboBox.getSelectionModel().clearSelection();
    }

    public enum Esito {positivo, negativo}

}
