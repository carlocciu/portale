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
        PreparedStatement statementMaterieSostenute = null;
        PreparedStatement statementMaterie = null;
        ResultSet resultMaterie = null;
        ResultSet resultMaterieSostenute = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");


            //Ottenfo tutte le materie del piano di studi
            String queryTutteLeMaterie = "select M.Nome, M.Id_Materia, M.Anno, M.CFU from Studente S, PianoDiStudi P, Materia M where S.Matricola=P.Ref_Studente and P.Ref_Materia=M.Id_Materia and S.Matricola=?";

            statementMaterie = connection.prepareStatement(queryTutteLeMaterie);
            statementMaterie.setString(1, pMatricola);
            resultMaterie = statementMaterie.executeQuery();


            //Ottengo tutte le materie sostenute
            String queryTutteLeMaterieSostenute = "SELECT M.Anno, M.Id_Materia, M.Nome, M.CFU, E.DataEsame, E.Voto FROM Materia M, AppelloEsame A, Verbale V, EsameVerbalizzato E, Studente S WHERE M.Id_Materia = A.Ref_Materia AND A.Id_Appello = V.Ref_AppelloEsame AND V.Id_Verbale = E.Ref_Verbale AND E.Ref_Studente = S.Matricola AND S.Matricola = ?";

            statementMaterieSostenute = connection.prepareStatement(queryTutteLeMaterieSostenute);
            statementMaterieSostenute.setString(1, pMatricola);
            resultMaterieSostenute = statementMaterieSostenute.executeQuery();

            ObservableList<DisplayPianoDiStudi> pianoDiStudi = FXCollections.observableArrayList();

            boolean sostenuta = false;

            while(resultMaterie.next()){

                String uno = resultMaterie.getString("Id_Materia");
                String due = "";

                while (resultMaterieSostenute.next()){

                    due = resultMaterieSostenute.getString("Id_Materia");

                    if(uno.equals(due)){

                        pianoDiStudi.add(new DisplayPianoDiStudi(resultMaterieSostenute.getInt("Anno"), resultMaterieSostenute.getString("Id_Materia"), resultMaterieSostenute.getString("Nome"), resultMaterieSostenute.getInt("CFU"), resultMaterieSostenute.getDate("DataEsame"), resultMaterieSostenute.getInt("Voto"), pMatricola));
                        sostenuta = true;
                        System.out.println("Ho una materia data");

                    }
                }

                if(sostenuta == false){

                    pianoDiStudi.add(new DisplayPianoDiStudi(resultMaterie.getInt("Anno"), resultMaterie.getString("Id_Materia"), resultMaterie.getString("Nome"), resultMaterie.getInt("CFU"), null, 0, pMatricola));

                    System.out.println("Ho una materia non data");
                }
            }


            //statement.close();
            connection.close();

            return pianoDiStudi;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }


}
