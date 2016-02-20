package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.DisplayAppello;

import java.sql.*;
import java.util.Date;

public class DBMSIscrizioneEsameBnd {

    public void insertIscrizione(String pMatricola, String pCodiceMateria) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "INSERT INTO Prenotazione VALUES (?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, pMatricola);
            statement.setString(2, pCodiceMateria);

            statement.executeUpdate();

            statement.close();
            connection.close();


        }catch(SQLException e){
            e.printStackTrace();

        }

    }

    public ObservableList<DisplayAppello> getAppelli(String idMateria){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "select D.Cognome, D.Nome, A.Data, A.Id_Appello  from Materia M, Docente D, AppelloEsame A where M.Docente=D.Matricola and A.Ref_Materia=M.Id_Materia and M.Id_Materia=?;";

            statement = connection.prepareStatement(query);

            statement.setString(1, idMateria);

            result = statement.executeQuery();

            ObservableList<DisplayAppello> appelli = FXCollections.observableArrayList();

            while(result.next()){

                appelli.add(new DisplayAppello(result.getString("Cognome"), result.getDate("Data"), "sconosciuta", result.getString("Id_Appello")));
            }

            statement.close();
            connection.close();

            return appelli;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public boolean getTheRightStudent(String matricola, String email, String telefono){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "SELECT * FROM Studente WHERE Matricola=? and Email=? and Telefono=? ";
            statement = connection.prepareStatement(query);

            statement.setString(1, matricola);
            statement.setString(2, email);
            statement.setString(3, telefono);

            result = statement.executeQuery();

            boolean isValid = false;

            if (result.next()){

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

}
