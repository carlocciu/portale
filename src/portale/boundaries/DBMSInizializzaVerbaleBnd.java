package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.*;

import java.sql.*;
import java.util.Date;

public class DBMSInizializzaVerbaleBnd {

    /* Connection data */
    private final String DBMS_URL = "jdbc:mysql://localhost:3306/PortaleStudenti";
    private final String DBM_USER = "root";
    private final String DBMS_PASS = "apswpa";


    /* Queries */
    private final String QUERY_GET_SCUOLE = "SELECT * \n" +
            "FROM PortaleStudenti.Scuola\n" +
            "WHERE Scuola.Id_Scuola IN(\n" +
            "\tSELECT Dipartimento.Ref_Scuola\n" +
            "    FROM Dipartimento\n" +
            "    WHERE Dipartimento.Id_Dip IN(\n" +
            "\t\tSELECT CorsoDiLaurea.Ref_Dipartimento\n" +
            "\t\tFROM CorsoDiLaurea\n" +
            "\t\tWHERE CorsoDiLaurea.Id_CdL = (\n" +
            "\t\t\tSELECT PianoDiStudi.Ref_CorsoDiLaurea\n" +
            "\t\t\tFROM PianoDiStudi\n" +
            "\t\t\tWHERE PianoDiStudi.Ref_Materia IN (\n" +
            "\t\t\t\tSELECT Insegnamento.Ref_Materia\n" +
            "\t\t\t\tFROM PortaleStudenti.Insegnamento\n" +
            "\t\t\t\tWHERE Insegnamento.Ref_Docente = ?))));";

    private final String QUERY_GET_CDLS = "SELECT *\n" +
            "FROM CorsoDiLaurea\n" +
            "WHERE CorsoDiLaurea.Id_CdL = (\n" +
            "\tSELECT PianoDiStudi.Ref_CorsoDiLaurea\n" +
            "\tFROM PianoDiStudi\n" +
            "\tWHERE PianoDiStudi.Ref_Materia IN (\n" +
            "\t\tSELECT Insegnamento.Ref_Materia\n" +
            "\t\tFROM PortaleStudenti.Insegnamento\n" +
            "\t\tWHERE Insegnamento.Ref_Docente = ?)\n" +
            "\t\t\n" +
            "\t\tAND PianoDiStudi.Ref_CorsoDiLaurea IN (\n" +
            "\t\t\tSELECT CorsoDiLaurea.Id_CdL\n" +
            "\t\t\tFROM CorsoDiLaurea\n" +
            "\t\t\tWHERE Ref_Dipartimento IN(\n" +
            "\t\t\t\tSELECT Id_Dip\n" +
            "\t\t\t\tFROM PortaleStudenti.Dipartimento\n" +
            "\t\t\t\tWHERE Dipartimento.Ref_Scuola = ?)));\n" +
            "\n";


    public ObservableList<StudenteClass> getStudentiIScritti(Date pDataAppello, String pMatricolaDocente, String pCodiceMateria) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_CDLS);
        preparedStatement.setString(1, pDocente.getMatricolaDocente());
        preparedStatement.setString(2, pScuola.getIdScuola());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Result array */
        ObservableList<CorsoDiLaurea> cdls = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()){
            cdls.add(new CorsoDiLaurea(resultSet.getString("Id_Cdl"), resultSet.getString("Nome")));
        }

        return cdls;
    }

    public ObservableList<Materia> getMaterie(String pCdl, String pDocente) {
        return null;
    }

    public ObservableList<Appello> getAppelli(String pMatricolaDocente, String pCodiceMateria, String pCdl) {
        return null;
    }

    public ObservableList<Scuola> getScuole(DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_SCUOLE);
        preparedStatement.setString(1, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ObservableList<Scuola> scuole = FXCollections.observableArrayList();

        /* Populate array */
        while(resultSet.next()){
            scuole.add(new Scuola(resultSet.getString("Id_Scuola"), resultSet.getString("Nome")));
        }

        return scuole;
    }

}
