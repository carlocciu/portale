package portale.boundaries;

import javafx.collections.ObservableList;
import portale.entities.PianoDiStudi;

import java.sql.*;

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

    public ObservableList<PianoDiStudi> getPianodiStudi(String pMatricola) {

        return null;
    }

}
