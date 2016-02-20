package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.DisplayAppello;

import java.sql.*;
import java.util.Date;

public class DBMSIscrizioneEsameBnd {

    public void insertIscrizione(String pMatricola, String pCodiceMateria, Date pDataEsame) {

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

                System.out.println(result.getString("Nome"));
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

}
