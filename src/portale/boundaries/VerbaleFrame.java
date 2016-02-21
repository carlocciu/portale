package portale.boundaries;

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
import portale.controls.VerbaleCtrl;
import portale.entities.DisplayVerbale;
import portale.entities.DocenteClass;

import java.io.IOException;

public class VerbaleFrame {

    @FXML private TableView<DisplayVerbale> verbaliApertiTV;

    @FXML private Button nuovoVerbaleButton;

    @FXML private Button logoutButton;

    @FXML private Button homeButton;

    private DocenteClass docente;

    VerbaleCtrl ctr = new VerbaleCtrl(docente);

    public void setDocente(DocenteClass docente) {
        this.docente = docente;
    }

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

        TableColumn actionCol = new TableColumn( "Azioni" );
        actionCol.setCellValueFactory( new PropertyValueFactory<>( "Operazioni" ) );

        Callback<TableColumn<DisplayVerbale, String>, TableCell<DisplayVerbale, String>> cellFactory = //
                new Callback<TableColumn<DisplayVerbale, String>, TableCell<DisplayVerbale, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<DisplayVerbale, String> param )
                    {
                        final TableCell<DisplayVerbale, String> cell = new TableCell<DisplayVerbale, String>()
                        {

                            final Button btn = new Button( "Continua" );

                            @Override
                            public void updateItem( String item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                if ( empty )
                                {
                                    setGraphic( null );
                                    setText( null );
                                }
                                else
                                {
                                    btn.setOnAction( ( ActionEvent event ) ->
                                    {
                                        DisplayVerbale person = getTableView().getItems().get( getIndex() );
                                        System.out.println( person.getIdVerbale() );
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory( cellFactory );

        verbaliApertiTV.setItems(data);
        verbaliApertiTV.getColumns().addAll(annoCol, cdlCol, materiaCol, cfuCol, dataCol, actionCol);

    }

    public void clickSelectVerbale(int pSelectedVerbale) {

    }

    public void clickNuovoVerbale() {
        try {
            Stage stage = (Stage) nuovoVerbaleButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/InizializzaVerbaleForm.fxml"));
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
