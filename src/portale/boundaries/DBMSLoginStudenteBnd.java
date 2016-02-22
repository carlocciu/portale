package portale.boundaries;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import portale.entities.DisplayPianoDiStudi;
import portale.entities.PianoDiStudi;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBMSLoginStudenteBnd {

    public boolean isPresenteUser(String pMatricola, String pPassword) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean isValid;
        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            String query = "Select * from Studente Where Matricola=? and Password=?";

            statement = connection.prepareStatement(query);

            statement.setString(1, pMatricola);

            statement.setString(2, pPassword);

            result = statement.executeQuery();

            if(result.next()){

                isValid = true;
            }else{

                isValid = false;
            }

            statement.close();
            connection.close();

            return isValid;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public ObservableList<DisplayPianoDiStudi> getPianodiStudi(String pMatricola) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");


            //Ottenfo tutte le materie del piano di studi
            String query = "select M.Nome, M.Id_Materia, M.Anno, M.CFU, P.Ref_Voto, P.DataEsame from Studente S, PianoDiStudi P, Materia M where S.Matricola=P.Ref_Studente and P.Ref_Materia=M.Id_Materia and S.Matricola=?";

            statement = connection.prepareStatement(query);
            statement.setString(1, pMatricola);
            result = statement.executeQuery();

            ObservableList<DisplayPianoDiStudi> pianoDiStudi = FXCollections.observableArrayList();

             while(result.next()){
                 int voto = result.getInt("Ref_Voto");
                 java.util.Date date = result.getDate("DataEsame");
                 if (voto == 0)
                    date = null;
                pianoDiStudi.add(new DisplayPianoDiStudi(result.getInt("Anno"), result.getString("Id_Materia"), result.getString("Nome"), result.getInt("CFU"), date, result.getInt("Ref_Voto"), pMatricola));

            }

            statement.close();
            connection.close();

            return pianoDiStudi;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }


}
