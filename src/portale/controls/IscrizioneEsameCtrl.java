package portale.controls;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import portale.boundaries.DBMSIscrizioneEsameBnd;

import java.lang.reflect.Array;
import java.util.Optional;

public class IscrizioneEsameCtrl {

    public void insertPrenotazione(String matricola, String appello){

        String[] dataToConfirm = createDialog();

        DBMSIscrizioneEsameBnd verify = new DBMSIscrizioneEsameBnd();

        if(verify.getTheRightStudent(matricola, dataToConfirm[0], dataToConfirm[1])){

            verify.insertIscrizione(matricola, appello);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prenotazione");
            alert.setHeaderText("Prenotazione avvenuta con successo");
            alert.setContentText("Ti sei prenotato all'appello!");
            alert.showAndWait();

        }else{

            if (!dataToConfirm[0].equals("") || !dataToConfirm[1].equals("")){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore nella prenotazione");
                alert.setHeaderText("Prenotazione non riuscita");
                alert.setContentText("Hai inserito i dati sbagliati");
                alert.showAndWait();
            }

        }

    }

    public String[] createDialog(){

        String[] dataToConfirm = new String[2];
        dataToConfirm[0] = dataToConfirm[1] =  "";

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
}
