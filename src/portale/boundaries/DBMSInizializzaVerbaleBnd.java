package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.Appello;
import portale.entities.CorsoDiLaurea;
import portale.entities.Materia;
import portale.entities.StudenteClass;

import java.sql.*;
import java.util.Date;

public class DBMSInizializzaVerbaleBnd {

    /* Connection data */
    private final String DBMS_URL = "jdbc:mysql://localhost:3306/PortaleStudenti";
    private final String DBM_USER = "root";
    private final String DBMS_PASS = "apswpa";


    /* Queries */
    private final String QUERY_GET_SCUOLE = "SELECT Nome FROM PortaleStudenti.Scuola;";


    public ObservableList<StudenteClass> getStudentiIScritti(Date pDataAppello, String pMatricolaDocente, String pCodiceMateria) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(String pScuola) {
        return null;
    }

    public ObservableList<Materia> getMaterie(String pCdl, String pDocente) {
        return null;
    }

    public ObservableList<Appello> getAppelli(String pMatricolaDocente, String pCodiceMateria, String pCdl) {
        return null;
    }

    public ObservableList<String> getScuole() throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_SCUOLE);

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ObservableList<String> scuole = FXCollections.observableArrayList();

        /* Populate array */
        while(resultSet.next()){
            scuole.add(resultSet.getString("Nome"));
        }

        return scuole;
    }

}
