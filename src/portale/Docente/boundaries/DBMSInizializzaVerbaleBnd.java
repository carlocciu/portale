package portale.Docente.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.common.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe Boundary che gestisce la comunicazione del database per inizializzare il verbale
 */
public class DBMSInizializzaVerbaleBnd {

    /* Connection data */
    private final String DBMS_URL = "jdbc:mysql://localhost:3306/PortaleStudenti";
    /* Connection data */
    private final String DBM_USER = "root";
    /* Connection data */
    private final String DBMS_PASS = "apswpa";


    /**
     * Query get scuole
     */
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


    /**
     * Query get corsi di laurea
     */
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

    /**
     * Query get materie
     */
    private final String QURY_GET_MATERIE = "SELECT *\n" +
            "FROM Materia\n" +
            "WHERE Materia.Ref_CdL = ?\n" +
            "\tAND Materia.Id_Materia IN(\n" +
            "\t\tSELECT Insegnamento.Ref_Materia\n" +
            "        FROM Insegnamento\n" +
            "        WHERE Insegnamento.Ref_Docente = ?);";

    /**
     * Query get appelli
     */
    private final String QUERY_GET_APPELLI = "SELECT * \n" +
            "FROM PortaleStudenti.AppelloEsame\n" +
            "WHERE AppelloEsame.Ref_Materia = ?\n" +
            "\tAND AppelloEsame.Ref_Docente = ?\n" +
            "    AND AppelloEsame.Id_Appello NOT IN(\n" +
            "\t\tSELECT Verbale.Ref_AppelloEsame\n" +
            "        FROM Verbale);";

    /**
     * Query get iscritti ad un appello
     */
    private final String QUERY_GET_ISCRITTI_APPELLO = "SELECT *\n" +
            "FROM Studente\n" +
            "WHERE Studente.Matricola IN(\n" +
            "\tSELECT Prenotazione.Ref_Studente\n" +
            "\tFROM PortaleStudenti.Prenotazione\n" +
            "    WHERE Prenotazione.Ref_Appello = ?)\n" +
            "    \n" +
            "    AND Studente.Matricola NOT IN(\n" +
            "\t\tSELECT EsameVerbalizzato.Ref_Studente\n" +
            "        FROM EsameVerbalizzato\n" +
            "        WHERE EsameVerbalizzato.Ref_Verbale IN(\n" +
            "\t\t\tSELECT Verbale.Id_Verbale\n" +
            "            FROM Verbale\n" +
            "            WHERE Verbale.Ref_AppelloEsame = ?));";

    /**
     * Query insert nuovo verbale
     */
    private final String INSERT_NEW_VERBALE = "INSERT INTO `Verbale`(Ora_Apertura, Ref_CdL, Ref_AppelloEsame, Ref_Materia) \n" +
            "            VALUES ('%s', '%s', '%s', '%s');";

    /**
     * Ritorna la lista dei corsi di laurea di una scuola selezionata in cui insegna il docente
     * @param pScuola informazioni scuola
     * @param pDocente informazioni docente
     * @return lista di corsi di laurea
     * @throws SQLException
     */
    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_CDLS);
        preparedStatement.setString(1, pScuola.getIdScuola());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Result array */
        ObservableList<CorsoDiLaurea> cdls = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()) {
            cdls.add(new CorsoDiLaurea(resultSet.getString("Id_Cdl"), resultSet.getString("Nome")));
        }

        return cdls;
    }

    /**
     * Ritorna la lista delle materie insegnate dal docente in un corso di laurea selezionato
     * @param pCorsoDiLaurea informazioni corso di laurea
     * @param pDocente informazioni docente
     * @return lista di materie
     * @throws SQLException
     */
    public ObservableList<Materia> getMaterie(CorsoDiLaurea pCorsoDiLaurea, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QURY_GET_MATERIE);
        preparedStatement.setString(1, pCorsoDiLaurea.getCodiceCorso());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Result array */
        ObservableList<Materia> materie = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()) {
            materie.add(new Materia(resultSet.getString("Id_Materia"), resultSet.getString("Nome"), resultSet.getString("Ordinamento"),
                    resultSet.getInt("CFU"), resultSet.getInt("Anno")));
        }

        return materie;
    }

    /**
     * Ritorna la lista degli appelli di una materia selezionata
     * @param pMateria informazioni materia
     * @param pDocente informazioni docente
     * @return lista di appelli
     * @throws SQLException
     */
    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_APPELLI);
        preparedStatement.setString(1, pMateria.getCodiceMateria());
        preparedStatement.setString(2, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ObservableList<Appello> appelli = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()) {
            appelli.add(new Appello(resultSet.getString("Id_Appello"), resultSet.getDate("Data"), resultSet.getString("Aula")));
        }

        return appelli;
    }

    /**
     * Ritorna la lista delle scuole in cui il docente insegna
     * @param pDocente informazioni docente
     * @return lista scuole
     * @throws SQLException
     */
    public ObservableList<Scuola> getScuole(DocenteClass pDocente) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_SCUOLE);
        preparedStatement.setString(1, pDocente.getMatricolaDocente());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ObservableList<Scuola> scuole = FXCollections.observableArrayList();

        /* Populate array */
        while (resultSet.next()) {
            scuole.add(new Scuola(resultSet.getString("Id_Scuola"), resultSet.getString("Nome")));
        }

        return scuole;
    }

    /**
     * Ritorna la lista degli studenti iscritti ad un appello
     * @param pAppello appello
     * @return lista di studenti iscritti
     * @throws SQLException
     */
    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        PreparedStatement preparedStatement = dBConnection.prepareStatement(QUERY_GET_ISCRITTI_APPELLO);
        preparedStatement.setString(1, pAppello.getIdAppello());
        preparedStatement.setString(2, pAppello.getIdAppello());

        ResultSet resultSet = preparedStatement.executeQuery();

        /* Results array */
        ArrayList<StudenteClass> studentiIscritti = new ArrayList<>();

        /* Populate array */
        while (resultSet.next()) {
            studentiIscritti.add(new StudenteClass(resultSet.getString("Nome"), resultSet.getString("Cognome"),
                    resultSet.getString("Matricola")));
        }

        return studentiIscritti;
    }

    /**
     * Inserisce un nuovo verbale nel database
     * @param pTimestamp ora di apertura
     * @param pSelectedCorsoDiLaurea corso di laurea selezionato
     * @param pSelectedAppello appello selezionato
     * @param pSelectedMateria materia selezionata
     * @return id verbale
     * @throws SQLException
     */
    public int insertNewVerbale(Timestamp pTimestamp, CorsoDiLaurea pSelectedCorsoDiLaurea, Appello pSelectedAppello,
                                Materia pSelectedMateria) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        String insertNewVerbale = String.format(INSERT_NEW_VERBALE, pTimestamp.toString(), pSelectedCorsoDiLaurea.getCodiceCorso(),
                pSelectedAppello.getIdAppello(), pSelectedMateria.getCodiceMateria());

        PreparedStatement statement = dBConnection.prepareStatement(insertNewVerbale, Statement.RETURN_GENERATED_KEYS);

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();

        int idVerbale = -1;

        if (resultSet.next()) {
            idVerbale = resultSet.getInt(1);
        }

        if (!statement.isClosed())
            statement.close();

        if (!dBConnection.isClosed()) {
            dBConnection.close();
        }

        return idVerbale;
    }
}
