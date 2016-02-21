package portale.boundaries;

import portale.entities.Appello;
import portale.entities.Materia;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.StringJoiner;

public class DBMSVerbalizzaEsameBnd {

    /* Connection data */
    private final String DBMS_URL = "jdbc:mysql://localhost:3306/PortaleStudenti";
    private final String DBM_USER = "root";
    private final String DBMS_PASS = "apswpa";


    /* SQL */
    private final String INSERT_ESAME_SOSTENUTO = "INSERT INTO `EsameVerbalizzato`(Ref_Studente, Ref_Verbale, Esito, Voto, DataEsame) \n" +
            "            VALUES (?, ?, ?, ?, ?);\n";

    private final String UPDATE_PIANO_DI_STUDI1 = "UPDATE PianoDiStudi\n" +
            "SET PianoDiStudi.Ref_Voto = ?\n" +
            "WHERE PianoDiStudi.Ref_Materia = ?\n" +
            "\tAND PianoDiStudi.Ref_Studente = ?;";

    private  final String UPDATE_PIANO_DI_STUDI2 = "UPDATE PianoDiStudi\n" +
            "SET PianoDiStudi.DataEsame = ?\n" +
            "WHERE PianoDiStudi.Ref_Materia = ?\n" +
            "\tAND PianoDiStudi.Ref_Studente = ?;    \n" +
            "    \n" +
            "    ";

    private final String CLOSE_VERBALE = "UPDATE Verbale SET Ora_Chiusura = ? WHERE Id_Verbale = ?";

    public void chiudiVerbale(VerbaleComplessivo pVerbaleComplessivo) throws Exception{
        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);


        /* Close Verbale */
        PreparedStatement preparedStatement = dBConnection.prepareStatement(CLOSE_VERBALE);
        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());

        preparedStatement.setDate(1, sqldate);

        preparedStatement.setInt(2, pVerbaleComplessivo.getIDVerbale());

        preparedStatement.executeUpdate();

        if(!preparedStatement.isClosed())
            preparedStatement.close();

        if(!dBConnection.isClosed())
            dBConnection.close();

    }

    public void insertEsameSostenuto(StudenteClass pStudente, VerbaleComplessivo pVerbaleComplessivo,
                                     CompilazioneVerbaleForm.Esito pEsito, String pVoto) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        Appello appello = pVerbaleComplessivo.getAppello();
        Date dataEsame = new Date(appello.getDataEsame().getTime());

        /* Update Esami Sostenuti */
        PreparedStatement preparedStatement = dBConnection.prepareStatement(INSERT_ESAME_SOSTENUTO);
        preparedStatement.setString(1, pStudente.getMatricolaStudente());
        preparedStatement.setInt(2, pVerbaleComplessivo.getIDVerbale());
        preparedStatement.setString(3, pEsito.toString());
        preparedStatement.setString(4, pVoto);
        preparedStatement.setDate(5, dataEsame);

        preparedStatement.executeUpdate();

        if(!preparedStatement.isClosed())
            preparedStatement.close();

        if(!dBConnection.isClosed())
            dBConnection.close();


    }

    public void updatePianoDiStudi(StudenteClass pStudente, VerbaleComplessivo pVerbaleComplessivo,
                                   CompilazioneVerbaleForm.Esito pEsito, String pVoto) throws SQLException {

        Connection dBConnection = DriverManager.getConnection(DBMS_URL, DBM_USER, DBMS_PASS);

        Appello appello = pVerbaleComplessivo.getAppello();
        Date dataEsame = new Date(appello.getDataEsame().getTime());
        Materia materia = appello.getMateria();
        String matricolaStudente = pStudente.getMatricolaStudente();


        /* Update Piano di studi */
        PreparedStatement preparedStatement = dBConnection.prepareStatement(UPDATE_PIANO_DI_STUDI1);
        preparedStatement.setString(1, pVoto);
        preparedStatement.setString(2, materia.getCodiceMateria());
        preparedStatement.setString(3, matricolaStudente);

        preparedStatement.executeUpdate();


        preparedStatement = dBConnection.prepareStatement(UPDATE_PIANO_DI_STUDI2);

        preparedStatement.setDate(1, dataEsame);
        preparedStatement.setString(2, materia.getCodiceMateria());
        preparedStatement.setString(3, matricolaStudente);

        preparedStatement.executeUpdate();



        if(!preparedStatement.isClosed())
            preparedStatement.close();

        if(!dBConnection.isClosed())
            dBConnection.close();
    }
}
