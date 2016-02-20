package portale.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import portale.boundaries.DBMSLoginStudenteBnd;
import portale.boundaries.DBMSVerbaliApertiBnd;
import portale.boundaries.PianoDiStudiForm;
import portale.boundaries.VerbaleFrame;
import portale.entities.DisplayPianoDiStudi;
import portale.entities.PianoDiStudi;
import portale.entities.StudenteClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AutenticazioneStudenteCtrl {

    private String matricola;
    private String password;
    //private Stage thisStage;


    public AutenticazioneStudenteCtrl(String matricola, String password) {

        this.matricola = matricola;
        this.password = password;

    }

    public String getMatricola (){

        return this.matricola;
    }

    public String getPassword(){

        return this.password;
    }


    public boolean checkLogin(){

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
            return false;

        } else {

            DBMSLoginStudenteBnd loginDBConnection = new DBMSLoginStudenteBnd();

            if(loginDBConnection.isPresenteUser(getMatricola(), getPassword())){

                return true;

            }else{

                alert.setContentText("Studente non trovato");
                alert.showAndWait();
                return false;

            }
            //databaseConnection(alert);

        }

    }

}

