package portale.Studente.controls;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import portale.Studente.boundaries.DBMSIscrizioneEsameBnd;
import portale.common.DisplayAppello;

import java.util.Optional;

/**
 * Classe control che gestisce l'iscrizione ad un esame
 */
public class IscrizioneEsameCtrl {

    /**
     * Boundary che gestisce la comunicazione con il database per l'iscrizione ad un esame
     */
    private DBMSIscrizioneEsameBnd mDBMSIscrizioneEsameBnd = new DBMSIscrizioneEsameBnd();

    /**
     * Inoltra richiesta di prenotazione al database
     * @param matricola matricola studente
     * @param appello appello
     */
    public void insertPrenotazione(String matricola, String appello) {

        String[] dataToConfirm = createDialog();


        if (mDBMSIscrizioneEsameBnd.getTheRightStudent(matricola, dataToConfirm[0], dataToConfirm[1])) {

            if (mDBMSIscrizioneEsameBnd.insertIscrizione(matricola, appello)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prenotazione");
                alert.setHeaderText("Prenotazione avvenuta con successo");
                alert.setContentText("Ti sei prenotato all'appello!");
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore nella prenotazione");
                alert.setHeaderText("Prenotazione non riuscita");
                alert.setContentText("Hai gia' prenotato questo appello");
                alert.showAndWait();
            }


        } else {

            if (!dataToConfirm[0].equals("") || !dataToConfirm[1].equals("")) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore nella prenotazione");
                alert.setHeaderText("Prenotazione non riuscita");
                alert.setContentText("Hai inserito i dati sbagliati");
                alert.showAndWait();
            }

        }

    }

    /**
     * Mostra dialog per confermare prenotazione
     * @return email e recapito inseriti
     */
    public String[] createDialog() {

        String[] dataToConfirm = new String[2];
        dataToConfirm[0] = dataToConfirm[1] = "";

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Prenotazione Dialog");
        dialog.setHeaderText("Inserisci i dati indicati per continuare la prenotazione");


        // Set the button types.
        ButtonType prenotaButtonType = new ButtonType("Prenota", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(prenotaButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField email = new TextField();
        email.setPromptText("Email");
        TextField telefono = new TextField();
        telefono.setPromptText("Telefono");

        grid.add(new Label("Email:"), 0, 0);
        grid.add(email, 1, 0);
        grid.add(new Label("Telefono:"), 0, 1);
        grid.add(telefono, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(prenotaButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);


        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == prenotaButtonType) {

                dataToConfirm[0] = email.getText();
                dataToConfirm[1] = telefono.getText();

                return new Pair<>(email.getText(), telefono.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        return dataToConfirm;
    }

    /**
     * Ritorna la lista degli appelli di una materia
     * @param idMateria id materia
     * @return lista di appelli
     */
    public ObservableList<DisplayAppello> getAppelli(String idMateria) {
        return mDBMSIscrizioneEsameBnd.getAppelli(idMateria);
    }
}
