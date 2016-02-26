package portale.Docente.boundaries;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import portale.Docente.controls.VerbaleCtrl;
import portale.common.*;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Classe Boundary che gestisce la finestra di gestione dei verbali
 */
public class VerbaleFrame {

    /**
     * TableView per visualizzare i verbali aperti
     */
    @FXML
    private TableView<DisplayVerbale> verbaliApertiTV;

    /**
     * Button per creare un nuovo verbale
     */
    @FXML
    private Button nuovoVerbaleButton;

    /**
     * Button per il logout
     */
    @FXML
    private Button logoutButton;

    /**
     * Button per tornare alla pagina principale del portale
     */
    @FXML
    private Button homeButton;

    /**
     * Informazioni docente
     */
    private DocenteClass docente;

    /**
     * Control che gestisce i verbali
     */
    private VerbaleCtrl mVerbaleCtrl = new VerbaleCtrl(docente);

    /**
     * Setta le informazioni del docente
     * @param docente
     */
    public void setDocente(DocenteClass docente) {
        this.docente = docente;
    }

    /**
     * Inserisce la lista dei verbali aperti nella tabella
     * @param data lista di verbali aperti
     */
    public void riempiVerbaliAperti(ObservableList<DisplayVerbale> data) {

        TableColumn annoCol = new TableColumn("Anno");
        annoCol.setMinWidth(50);
        annoCol.setCellValueFactory(
                new PropertyValueFactory<DisplayVerbale, String>("anno"));

        TableColumn cdlCol = new TableColumn("Corso Di Laurea");
        cdlCol.setMinWidth(150);
        cdlCol.setCellValueFactory(
                new PropertyValueFactory<DisplayVerbale, String>("cdl"));

        TableColumn materiaCol = new TableColumn("Materia");
        materiaCol.setMinWidth(200);
        materiaCol.setCellValueFactory(
                new PropertyValueFactory<DisplayVerbale, String>("materia"));

        TableColumn cfuCol = new TableColumn("CFU");
        cfuCol.setMinWidth(50);
        cfuCol.setCellValueFactory(
                new PropertyValueFactory<DisplayVerbale, String>("cfu"));

        TableColumn dataCol = new TableColumn("Data Esame");
        dataCol.setMinWidth(50);
        dataCol.setCellValueFactory(
                new PropertyValueFactory<DisplayVerbale, String>("data"));

        TableColumn actionCol = new TableColumn("Azioni");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("Operazioni"));

        Callback<TableColumn<DisplayVerbale, String>, TableCell<DisplayVerbale, String>> cellFactory = //
                new Callback<TableColumn<DisplayVerbale, String>, TableCell<DisplayVerbale, String>>() {
                    @Override
                    public TableCell call(final TableColumn<DisplayVerbale, String> param) {
                        final TableCell<DisplayVerbale, String> cell = new TableCell<DisplayVerbale, String>() {

                            final Button btn = new Button("Continua");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction((ActionEvent event) ->
                                    {
                                        try {
                                            Stage stage = (Stage) btn.getScene().getWindow();

                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/CompilazioneVerbaleForm.fxml"));
                                            Parent parent = fxmlLoader.load();

                                            CompilazioneVerbaleForm compilazioneVerbaleForm = fxmlLoader.getController();

                                            Appello mSelectedAppello = mVerbaleCtrl.getAppello(getTableView().getItems().get(getIndex()).getIdVerbale());

                                            CorsoDiLaurea mSelectedCorsoDiLaurea = mVerbaleCtrl.getCDL(getTableView().getItems().get(getIndex()).getIdVerbale());

                                            Scuola mSelectedScuola = mVerbaleCtrl.getScuola(getTableView().getItems().get(getIndex()).getIdVerbale());

                                            LocalTime mSelectedOraApertura = mVerbaleCtrl.getOraApertura(getTableView().getItems().get(getIndex()).getIdVerbale());

                                            Materia mSelectedMateria = mVerbaleCtrl.getMateria(getTableView().getItems().get(getIndex()).getIdVerbale());

                                            mSelectedAppello.setMateria(mSelectedMateria);

                                            VerbaleComplessivo pVerbaleComplessivo = new VerbaleComplessivo(mSelectedCorsoDiLaurea, mSelectedScuola,
                                                    "annoAccademico", mSelectedAppello, mSelectedOraApertura);

                                            pVerbaleComplessivo.setIDVerbale(Integer.parseInt(getTableView().getItems().get(getIndex()).getIdVerbale()));

                                            compilazioneVerbaleForm.init(docente, pVerbaleComplessivo);

                                            stage.setTitle("Compilazione Verbale");
                                            stage.setScene(new Scene(parent, 600, 600));
                                            stage.setResizable(false);
                                            stage.show();


                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        DisplayVerbale person = getTableView().getItems().get(getIndex());
                                        System.out.println(person.getIdVerbale());

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);

        verbaliApertiTV.setItems(data);
        verbaliApertiTV.getColumns().addAll(annoCol, cdlCol, materiaCol, cfuCol, dataCol, actionCol);

    }

    /**
     * Inoltra l'utente nella pagina di inizializzazione verbale
     */
    public void clickNuovoVerbale() {
        try {
            Stage stage = (Stage) nuovoVerbaleButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/InizializzaVerbaleForm.fxml"));
            Parent parent = fxmlLoader.load();

            /* Pass Docente to InizializzaVerbaleForm */
            InizializzaVerbaleForm inizializzaVerbaleForm = fxmlLoader.getController();
            inizializzaVerbaleForm.setDocente(docente);
            inizializzaVerbaleForm.init();

            stage.setTitle("Inizializzazione Verbale");
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
     * Torna alla pagina principale del portale
     */
    public void clickHome() {

        try {
            Stage stage = (Stage) homeButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/PortaleDocenteFrame.fxml"));
            Parent parent = fxmlLoader.load();

            PortaleDocenteFrame portaleDocenteFrame = fxmlLoader.getController();
            portaleDocenteFrame.setCurrDocente(docente);

            stage.setTitle("Portale Docente");
            stage.setScene(new Scene(parent, 600, 600));
            stage.setResizable(false);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
