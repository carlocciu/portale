package portale.boundaries;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import portale.controls.VerbaleCtrl;
import portale.entities.DisplayVerbale;
import portale.entities.DocenteClass;
import portale.entities.VerbaleComplessivo;

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

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
