package portale.Docente.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import portale.Docente.controls.InizializzaVerbaleCtrl;
import portale.common.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Classe Boundary che gestisce la finestra di inizializzazione del verbale
 */
public class InizializzaVerbaleForm {

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
     * Menù per selezionare ora di apertura del verbale
     */
    @FXML
    private ComboBox<LocalTime> oraAperturaCB;
    /**
     * Ora selezionata
     */
    private LocalTime mSelectedOraApertura;

    /**
     * Button per importare gli iscritti
     */
    @FXML
    private Button importaIscrittiButton;
    /**
     * Variabile per gestire l'importazione degli studenti iscritti
     */
    private boolean mStudentiImportati = false;

    /**
     * Button per confermare l'inizializzazione
     */
    @FXML
    private Button inizializzaButton;

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
     * Control che gestisce l'inizializzazione
     */
    private InizializzaVerbaleCtrl mInizializzaVerbaleCtrl = new InizializzaVerbaleCtrl();

    /**
     * Informazioni del docente
     */
    private DocenteClass mDocente;

    /**
     * inizializza la finestra di compilazione, riempiendo il menù delle scuole e quello per l'ora di apertura
     */
    public void init() {
        riempiScuoleCB();
        initOraAperturaCB();
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
     * Conferma l'inizializzazione del verbale e inizia la compilazione
     */
    public void clickInizializza() {
        if (wereStudentsImported() && mSelectedOraApertura != null) {
            VerbaleComplessivo verbaleComplessivo = new VerbaleComplessivo(mSelectedCorsoDiLaurea, mSelectedScuola,
                    "annoAccademico", mSelectedAppello, mSelectedOraApertura);

            verbaleComplessivo.setIDVerbale(insertNewVerbale());
            startCompilazioneVerbale(verbaleComplessivo);
        }

    }


    /**
     * Inserisce le scuole nel combobox
     */
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

    /**
     * Inserisce i corsi di laurea della scuola selezionata nel combobox relativo
     * @param pScuola scuola selezionata
     */
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

    /**
     * Inserisce le materia del cdl selezionato nel combobox relativo
     * @param pCorsoDiLaurea cdl selezionato
     */
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

    /**
     * Inserisce gli appelli della materia selezionata nel relativo combobox
     */
    public void riempiAppelliCB() {
        appelliCB.getItems().clear();
        appelliCB.setItems(mInizializzaVerbaleCtrl.getAppelli(mSelectedMateria, mDocente));

        appelliCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appello>() {
            @Override
            public void changed(ObservableValue<? extends Appello> observable, Appello oldValue, Appello newValue) {
                mSelectedAppello = newValue;
                mSelectedAppello.setMateria(mSelectedMateria);
            }
        });
    }

    /**
     * Logout docente
     */
    public void clickLogout() {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/LoginFormDocente.fxml"));

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
     * Torna al portale docente
     */
    public void clickHome() {

        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/PortaleDocenteFrame.fxml"));
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
     * Importa gli iscritti dell'appello selezionato
     * @param actionEvent
     */
    public void clickImportaIscritti(ActionEvent actionEvent) {
        if (mSelectedAppello != null) {
            mSelectedAppello.setStudentiIscritti(mInizializzaVerbaleCtrl.getIscrittiAppello(mSelectedAppello));
            importaIscrittiButton.setDisable(true);
            importaIscrittiButton.setText("Studenti Importati");
            mStudentiImportati = true;
        } else {
            //Advice docente that should first select an appello
        }
    }

    /**
     * Ritorna il valore di mStudentiImportati
     * @return mStudentiImportati
     */
    private boolean wereStudentsImported() {
        return mStudentiImportati;
    }

    /**
     * Inizia compilazione verbale
     * @param pVerbaleComplessivo informazioni verbale
     */
    private void startCompilazioneVerbale(VerbaleComplessivo pVerbaleComplessivo) {
        try {
            Stage stage = (Stage) inizializzaButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/CompilazioneVerbaleForm.fxml"));
            Parent parent = fxmlLoader.load();

            CompilazioneVerbaleForm compilazioneVerbaleForm = fxmlLoader.getController();
            compilazioneVerbaleForm.init(mDocente, pVerbaleComplessivo);

            stage.setTitle("Compilazione Verbale");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserisce ora di apertura possibili nel relativo combobox
     */
    public void initOraAperturaCB() {
        ObservableList<LocalTime> orariPossibiliAppelli = FXCollections.observableArrayList();
        for (int i = 8; i <= 17; i++) {
            orariPossibiliAppelli.add(LocalTime.of(i, 0));
        }
        oraAperturaCB.getItems().clear();
        oraAperturaCB.setItems(orariPossibiliAppelli);

        oraAperturaCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocalTime>() {
            @Override
            public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
                mSelectedOraApertura = newValue;
            }
        });
    }

    /**
     * Invia i dati per inserire un nuovo verbale nel database
     * @return
     */
    private int insertNewVerbale() {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime appelloLocalDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(),
                mSelectedOraApertura.getHour(), mSelectedOraApertura.getMinute());

        Timestamp appelloDateTimeForDB = Timestamp.valueOf(appelloLocalDateTime);

        return mInizializzaVerbaleCtrl.insertNewVerbale(appelloDateTimeForDB, mSelectedCorsoDiLaurea, mSelectedAppello, mSelectedMateria);
    }
}
