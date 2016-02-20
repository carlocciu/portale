package portale.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.boundaries.DBMSLoginStudenteBnd;
import portale.boundaries.PianoDiStudiForm;
import portale.entities.PianoDiStudi;
import portale.entities.StudenteClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AutenticazioneStudenteCtrl {

    private String matricola;
    private String password;
    private Stage thisStage;


    public AutenticazioneStudenteCtrl(String matricola, String password, Stage myStage) {

        this.matricola = matricola;
        this.password = password;
        this.thisStage = myStage;
    }

    public String getMatricola (){

        return this.matricola;
    }

    public String getPassword(){

        return this.password;
    }


    public void checkLogin(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Login");

        if (this.matricola.isEmpty() || this.password.isEmpty()){


            alert.setHeaderText("Autenticazione non riuscita");

            if(this.matricola.isEmpty()){

                alert.setContentText("Il campo matricola e' vuoto");
            } else {

                alert.setContentText("Il campo password e' vuoto");
            }

            alert.showAndWait();

        } else {

            DBMSLoginStudenteBnd loginDBConnection = new DBMSLoginStudenteBnd();

            if(loginDBConnection.isPresenteUser(getMatricola(), getPassword())){

                //quindi se lo studente è presente nel DB allora lo rimando alla pagina del portale
                sendDataToAnotherPage(loginDBConnection);

            }else{

                alert.setContentText("Studente non trovato");
                alert.showAndWait();

            }
            //databaseConnection(alert);

        }

    }

    public void sendDataToAnotherPage(DBMSLoginStudenteBnd loginDBConnection){



        FXMLLoader page = new FXMLLoader(getClass().getResource("../../res/PianoDiStudiForm.fxml"));
        Parent root = null;
        try {

            root = (Parent) page.load();

            // mi richiamo il controller del boundary che gestisce la grafica della pagina PianoDiStudiForm.fxml
            PianoDiStudiForm controller = page.getController();

            /*
            //adesso che ho il controller(ovvero PianoDiStudiForm.java) allora setto la matricola con la quale farò la richiesta al DB
            controller.setMatricola(getMatricola());

            //ottengo i dati del piano di studi da mostrare nella tabella
            ObservableList<DBMSLoginStudenteBnd.PianoDiStudiModified> pianoDiStudi = loginDBConnection.getPianodiStudi(getMatricola());

            //devo riempire le tabelle --> devo usare il controller

            */

            Scene scene = new Scene(root);
            thisStage.setScene(scene);

           // controller.riempiTabella(thisStage);

            thisStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

