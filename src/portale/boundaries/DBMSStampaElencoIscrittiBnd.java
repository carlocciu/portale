package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBMSStampaElencoIscrittiBnd {

    /* Connection data */
    private final String DBMS_URL = "jdbc:mysql://localhost:3306/PortaleStudenti";
    private final String DBM_USER = "root";
    private final String DBMS_PASS = "apswpa";


    /* Queries */
    private final String QUERY_GET_SCUOLE = "SELECT *\n" +
            "FROM Scuola\n" +
            "WHERE Scuola.Id_Scuola IN(\n" +
            "\tSELECT Dipartimento.Ref_Scuola\n" +
            "    FROM Dipartimento\n" +
            "    WHERE Dipartimento.Id_Dip IN(\n" +
            "\t\tSELECT CorsoDiLaurea.Ref_Dipartimento\n" +
            "        FROM CorsoDiLaurea\n" +
            "        WHERE CorsoDiLaurea.Id_CdL IN(\n" +
            "\t\t\tSELECT Materia.Ref_CdL\n" +
            "            FROM Materia\n" +
            "            WHERE Materia.Id_Materia IN(\n" +
            "\t\t\t\tSELECT Insegnamento.Ref_Materia\n" +
            "                FROM Insegnamento\n" +
            "                WHERE Insegnamento.Ref_Docente = ?))));";

    private final String QUERY_GET_CDLS = "SELECT *\n" +
            "FROM CorsoDiLaurea\n" +
            "WHERE CorsoDiLaurea.Ref_Dipartimento IN(\n" +
            "\tSELECT Dipartimento.Id_Dip\n" +
            "    FROM Dipartimento\n" +
            "    WHERE Dipartimento.Ref_Scuola = ?)\n" +
            "    \n" +
            "    AND CorsoDiLaurea.Id_CdL IN(\n" +
            "\t\tSELECT Materia.Ref_CdL\n" +
            "        FROM Materia\n" +
            "        WHERE Materia.Id_Materia IN(\n" +
            "\t\t\tSELECT Insegnamento.Ref_Materia\n" +
            "            FROM Insegnamento\n" +
            "            WHERE Insegnamento.Ref_Docente = ?));";

    private final String QURY_GET_MATERIE = "SELECT *\n" +
            "FROM Materia\n" +
            "WHERE Materia.Ref_CdL = ?\n" +
            "\tAND Materia.Id_Materia IN(\n" +
            "\t\tSELECT Insegnamento.Ref_Materia\n" +
            "        FROM Insegnamento\n" +
            "        WHERE Insegnamento.Ref_Docente = ?);";

    private final String QUERY_GET_APPELLI = "SELECT * \n" +
            "FROM PortaleStudenti.AppelloEsame\n" +
            "WHERE AppelloEsame.Ref_Materia = ?\n" +
            "\tAND AppelloEsame.Ref_Docente = ?\n" +
            "    AND AppelloEsame.Id_Appello";

    private final String QUERY_GET_ISCRITTI_APPELLO = "SELECT *\n" +
            "FROM Studente\n" +
            "WHERE Studente.Matricola IN(\n" +
            "\tSELECT Prenotazione.Ref_Studente\n" +
            "\tFROM PortaleStudenti.Prenotazione\n" +
            "\tWHERE Prenotazione.Ref_Appello = ?);";

    private final String INSERT_NEW_VERBALE = "INSERT INTO `Verbale`(Ora_Apertura, Ref_CdL, Ref_AppelloEsame, Ref_Materia) \n" +
            "VALUES ('%s', '%s', '%s', '%s');";


    public ObservableList<StudenteClass> getStudentiIScritti(Date pDataAppello, String pMatricolaDocente, String pCodiceMateria) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_CDLS);
        preparedStatement.setString(1, pScuola.getIdScuola());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Result array */
        ObservableList<CorsoDiLaurea> cdls = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()){
            cdls.add(new CorsoDiLaurea(resultSet.getString("Id_Cdl"), resultSet.getString("Nome")));
        }

        return cdls;
    }

    public ObservableList<Materia> getMaterie(CorsoDiLaurea pCorsoDiLaurea, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QURY_GET_MATERIE);
        preparedStatement.setString(1, pCorsoDiLaurea.getCodiceCorso());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Result array */
        ObservableList<Materia> materie = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()){
            materie.add(new Materia(resultSet.getString("Id_Materia"), resultSet.getString("Nome"), resultSet.getString("Ordinamento"),
                    resultSet.getInt("CFU"), resultSet.getInt("Anno")));
        }

        return materie;
    }

    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_APPELLI);
        preparedStatement.setString(1, pMateria.getCodiceMateria());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ObservableList<Appello> appelli = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()){
            appelli.add(new Appello(resultSet.getString("Id_Appello"), resultSet.getDate("Data"), resultSet.getString("Aula")));
        }

        return appelli;
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

    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_ISCRITTI_APPELLO);
        preparedStatement.setString(1, pAppello.getIdAppello());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ArrayList<StudenteClass> studentiIscritti = new ArrayList<>();

        /* Populate array */
        while (resultSet.next()){
            studentiIscritti.add(new StudenteClass(resultSet.getString("Nome"), resultSet.getString("Cognome"),
                    resultSet.getString("Matricola")));
        }

        return studentiIscritti;
    }


}
