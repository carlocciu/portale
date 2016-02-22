package portale.boundaries;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import portale.controls.IscrizioneEsameCtrl;
import portale.entities.*;

import java.io.IOException;
import java.util.Date;

public class PianoDiStudiForm {


    @FXML private TableView<DisplayPianoDiStudi> pianoDiStudiTV;

    @FXML private TableView<DisplayAppello> appelliTV;

    @FXML private Button logoutButton;

    /**
     * Permette di effettuare il logout dal sistema quando
     * si clicca sul button "logout"
     */
    @FXML public void clickLogout() {


        Stage stage = (Stage) logoutButton.getScene().getWindow();

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

    /**
     * Riempe la tabella del piano di studi aggiungendo tante righe quanti
     * sono gli elementi che ci sono nella ObservableList di oggetti DisplayPianoDiStudi
     *
     * @param  pianoDiStudi  un elenco di informazioni sulle materie del piano di studi
     * @see         DisplayPianoDiStudi
     */
    public void fillTheTable(ObservableList<DisplayPianoDiStudi> pianoDiStudi){

        pianoDiStudiTV.setMaxWidth(654);
        pianoDiStudiTV.setMaxHeight(500);

        TableColumn annoCol = new TableColumn("Anno");
        annoCol.setMinWidth(50);
        annoCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, String>("anno"));


        TableColumn codiceMateriaCol = new TableColumn("Codice Materia");
        codiceMateriaCol.setMinWidth(150);
        codiceMateriaCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, String>("codiceMateria"));

        TableColumn nomeMateriaCol = new TableColumn("Nome Materia");
        nomeMateriaCol.setMinWidth(150);
        nomeMateriaCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, String>("nomeMateria"));

        TableColumn cfuCol = new TableColumn("CFU");
        cfuCol.setMinWidth(50);
        cfuCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, String>("cfuMateria"));

        TableColumn votoCol = new TableColumn("Voto");
        votoCol.setMinWidth(50);
        votoCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, String>("esitoMateria"));

        TableColumn dataCol = new TableColumn("Data Esame");
        dataCol.setMinWidth(100);
        dataCol.setCellValueFactory(new PropertyValueFactory<DisplayPianoDiStudi, Date>("dataEsame"));

        TableColumn prenotatoCol = new TableColumn("Prenotazione");
        prenotatoCol.setMinWidth(100);
        prenotatoCol.setCellValueFactory(new PropertyValueFactory<>("Operazioni"));

        Callback<TableColumn<DisplayPianoDiStudi, String>, TableCell<DisplayPianoDiStudi, String>> cellFactory = //
                 new Callback<TableColumn<DisplayPianoDiStudi, String>, TableCell<DisplayPianoDiStudi, String>>()
                 {
                     @Override
                     public TableCell call( final TableColumn<DisplayPianoDiStudi, String> param )
                     {
                         final TableCell<DisplayPianoDiStudi, String> cell = new TableCell<DisplayPianoDiStudi, String>()
                         {

                             final Button btn = new Button( "Prenota" );

                             @Override
                             public void updateItem( String item, boolean empty )
                             {
                                 super.updateItem( item, empty );
                                 if ( empty || !(getTableView().getItems().get( getIndex()).getEsitoMateria().equals(" - ")))
                                 {
                                     setGraphic( null );
                                     setText( null );
                                 }
                                 else
                                 {
                                     btn.setOnAction( ( ActionEvent event ) ->
                                     {
                                         DisplayPianoDiStudi materia = getTableView().getItems().get( getIndex() );
                                         appelliTV.getColumns().clear();
                                         prenotaEsame(materia);

                                     } );
                                     setGraphic( btn );
                                     setText( null );
                                 }
                             }
                         };
                         return cell;
                     }
                 };

        prenotatoCol.setCellFactory( cellFactory );
        pianoDiStudiTV.setItems(pianoDiStudi);
        pianoDiStudiTV.getColumns().addAll(annoCol, codiceMateriaCol, nomeMateriaCol, cfuCol, votoCol, dataCol, prenotatoCol);


    }

    /**
     * Permette di prenotarsi alla materia selezionata, materia che passo
     * come parametro con un oggetto DisplayPianoDiStudi
     *
     * @param  materia  un elenco di informazioni sulla materia
     * @see         DisplayPianoDiStudi
     */
    public void prenotaEsame(DisplayPianoDiStudi materia){

            appelliTV.setVisible(true);

            fillAppelli(materia);

    }

    /**
     * Ottiene una ObservableList di oggetti DisplayAppello a
     * partire dalla materia passata in input.
     *
     * @param  materia  un elenco di informazioni sulla materia utili nel display degli appelli
     * @see         DisplayPianoDiStudi
     */
    public void fillAppelli(DisplayPianoDiStudi materia){

        DBMSIscrizioneEsameBnd getAppelli = new DBMSIscrizioneEsameBnd();

        ObservableList<DisplayAppello> appelli = getAppelli.getAppelli(materia.getCodiceMateria());

        addRowsAppelli(appelli, materia.getMatricola());


    }

    /**
     * Riempe la tabella degli appelli aggiungendo tante righe quanti
     * sono gli elementi che ci sono nella ObservableList di oggetti DisplayAppello
     * passati come parametro.
     *
     * @param  appelli  un elenco di informazioni sugli appelli di una materia
     * @param matricola matricola dello studente che si vuole prenotare all'appello
     * @see         DisplayAppello
     */
    public void addRowsAppelli(ObservableList<DisplayAppello> appelli, String matricola){

        appelliTV.setMaxWidth(405);
        appelliTV.setMaxHeight(200);

        TableColumn docenteCol = new TableColumn("Docente");
        docenteCol.setMinWidth(50);
        docenteCol.setCellValueFactory(new PropertyValueFactory<DisplayAppello, String>("docente"));


        TableColumn dataCol = new TableColumn("Data Esame");
        dataCol.setMinWidth(160);
        dataCol.setCellValueFactory(new PropertyValueFactory<DisplayAppello, Date>("dataEsame"));

        TableColumn aulaCol = new TableColumn("Aula");
        aulaCol.setMinWidth(70);
        aulaCol.setCellValueFactory(new PropertyValueFactory<DisplayAppello, String>("aula"));

        TableColumn prenotatoCol = new TableColumn("Prenotazione");
        prenotatoCol.setMinWidth(100);
        prenotatoCol.setCellValueFactory(new PropertyValueFactory<>("Operazioni"));

        Callback<TableColumn<DisplayAppello, String>, TableCell<DisplayAppello, String>> cellFactory = //
                new Callback<TableColumn<DisplayAppello, String>, TableCell<DisplayAppello, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<DisplayAppello, String> param )
                    {
                        final TableCell<DisplayAppello, String> cell = new TableCell<DisplayAppello, String>()
                        {

                            final Button btn = new Button( "Prenota" );

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
                                        DisplayAppello appello = getTableView().getItems().get( getIndex() );
                                        insertPrenotazione(appello.getIdAppello(), matricola);

                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        prenotatoCol.setCellFactory( cellFactory );
        appelliTV.setItems(appelli);
        appelliTV.getColumns().addAll(docenteCol, dataCol, aulaCol, prenotatoCol);
    }

    /**
     * Permette l'inserimento della prenotazione nel DB.
     * Riceve in input l'id dell'appello e la matricola dello
     * studente,
     *
     * @param  appello  l'id dell'appello selezionato
     * @param matricola matricola dello studente che si vuole prenotare all'appello
     */
    public void insertPrenotazione(String appello, String matricola){

        IscrizioneEsameCtrl iscrizione = new IscrizioneEsameCtrl();
        iscrizione.insertPrenotazione(matricola,appello);
        appelliTV.getColumns().clear();
        appelliTV.setVisible(false);
    }
}

