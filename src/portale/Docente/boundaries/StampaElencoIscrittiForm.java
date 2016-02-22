package portale.Docente.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import portale.Docente.controls.StampaElencoIscrittiCtrl;
import portale.common.*;

import java.io.IOException;

/**
 * Classe Boundary che gestisce la finestra per esportare gli iscritti
 */
public class StampaElencoIscrittiForm {

    /**
     * Menù per selezionare una scuola
     */
    @FXML
    private ComboBox<Scuola> scuoleCB;
    /**
     * Scuola selezionata
     */
    private Scuola mSelectedScuola;

    /**
     * Menù per selezionare un corso di laurea
     */
    @FXML
    private ComboBox<CorsoDiLaurea> cdlsCB;
    /**
     * Corso di laurea selezionato
     */
    private CorsoDiLaurea mSelectedCorsoDiLaurea;

    /**
     * Menù per selezionare una materia
     */
    @FXML
    private ComboBox<Materia> materieCB;
    /**
     * Materia selezionata
     */
    private Materia mSelectedMateria;

    /**
     * Menù per selezionare un appello
     */
    @FXML
    private ComboBox<Appello> appelliCB;
    /**
     * Appello selezionato
     */
    private Appello mSelectedAppello;

    /**
     * Button per confermare l'esportazione
     */
    @FXML
    private Button esportaButton;

    /**
     * Button per il logout
     */
    @FXML
    private Button logoutButton;

    /**
     * Button per tornare al portale docente
     */
    @FXML
    private Button homeButton;


    /**
     * Control che gestisce l'esportazione degli iscritti ad un appello
     */
    private StampaElencoIscrittiCtrl mStampaElencoIscrittiCtrl = new StampaElencoIscrittiCtrl();

    /**
     * Informazioni dodcente
     */
    private DocenteClass mDocente;

    /**
     * Inizializza la finestra di esportazione
     */
    public void init() {
        riempiScuoleCB();
    }

    /**
     * Ritorna le informazioni del docente
     * @return informazioni docente
     */
    public DocenteClass getDocente() {
        return mDocente;
    }

    /**
     * Setta le informazioni del docente
     * @param docente info docente
     */
    public void setDocente(DocenteClass docente) {
        mDocente = docente;
    }

    /**
     * Inserisce le scuole in cui insegna il docente nel relativo combobox
     */
    public void riempiScuoleCB() {
        scuoleCB.getItems().clear();
        scuoleCB.setItems(mStampaElencoIscrittiCtrl.getScuole(mDocente));

        scuoleCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Scuola>() {
            @Override
            public void changed(ObservableValue<? extends Scuola> observable, Scuola oldValue, Scuola newValue) {
                mSelectedScuola = newValue;
                riempiCdlsCB(mSelectedScuola);
            }
        });
    }

    /**
     * Inserisce i corsi di laurea della scuola selezionata nel combobox relativo
     * @param pScuola scuola selezionata
     */
    public void riempiCdlsCB(Scuola pScuola) {
        cdlsCB.getItems().clear();
        cdlsCB.setItems(mStampaElencoIscrittiCtrl.getCDLs(pScuola, mDocente));

        cdlsCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CorsoDiLaurea>() {
            @Override
            public void changed(ObservableValue<? extends CorsoDiLaurea> observable, CorsoDiLaurea oldValue, CorsoDiLaurea newValue) {
                mSelectedCorsoDiLaurea = newValue;
                riempiMaterieCB(mSelectedCorsoDiLaurea);
            }
        });
    }

    /**
     * Inserisce le materia del cdl selezionato nel combobox relativo
     * @param pCorsoDiLaurea cdl selezionato
     */
    public void riempiMaterieCB(CorsoDiLaurea pCorsoDiLaurea) {
        materieCB.getItems().clear();
        materieCB.setItems(mStampaElencoIscrittiCtrl.getMaterie(mSelectedCorsoDiLaurea, mDocente));

        materieCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Materia>() {
            @Override
            public void changed(ObservableValue<? extends Materia> observable, Materia oldValue, Materia newValue) {
                mSelectedMateria = newValue;
                riempiAppelliCB();
            }
        });
    }


    /**
     * Inserisce gli appelli della materia selezionata nel relativo combobox
     */
    public void riempiAppelliCB() {
        appelliCB.getItems().clear();
        appelliCB.setItems(mStampaElencoIscrittiCtrl.getAppelli(mSelectedMateria, mDocente));

        appelliCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appello>() {
            @Override
            public void changed(ObservableValue<? extends Appello> observable, Appello oldValue, Appello newValue) {
                mSelectedAppello = newValue;
            }
        });
    }

    /**
     * Logout
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
     * Torna alla pagina principale del portale
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
     * Mostra pdf con gli iscritti all'appello selezionato
     * @throws Exception
     */
    public void clickEsporta() throws Exception {
        try {
            mStampaElencoIscrittiCtrl.createDocument(mSelectedAppello);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
