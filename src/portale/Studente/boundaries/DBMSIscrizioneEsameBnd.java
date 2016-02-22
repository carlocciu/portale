package portale.Studente.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.common.DisplayAppello;

import java.sql.*;

/**
 * Classe Boundary che gestisce la comunicazione con il database per la prenotazione ad un esame
 */
public class DBMSIscrizioneEsameBnd {

    /**
     * Ritorna un boolean per informarci se l'iscrizione ad un appello
     * è andata a buon fine. Viene utilizzata per evitare che uno studente si iscriva
     * allo stesso appello più volte.
     *
     * @param pMatricola     matricola dello studente che si vuole iscrivere ad un appello
     * @param pCodiceMateria codice della materia dell'appello selezionato
     * @return un boolean che ci dice se l'iscrizio all'appello è andata a buon fine
     */
    public boolean insertIscrizione(String pMatricola, String pCodiceMateria) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "INSERT INTO Prenotazione VALUES (?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, pMatricola);
            statement.setString(2, pCodiceMateria);

            int count = statement.executeUpdate();
            System.out.println(count);

            statement.close();
            connection.close();

            return (count > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Ritorna una ObservableList di oggetti DisplayAppello che contiene
     * l'elenco degli appelli per una data materia passata come parametro
     *
     * @param idMateria identificativo della materia della quale voglio ottenere l'elenco degli appelli
     * @return un elenco di appelli disponibili per la materia passata in input
     * @see DisplayAppello
     */
    public ObservableList<DisplayAppello> getAppelli(String idMateria) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "select D.Cognome, A.Data, A.Id_Appello, A.Aula from AppelloEsame A, Materia M, Insegnamento I, Docente D where  A.Ref_Materia = M.Id_Materia and M.Id_Materia = I.Ref_Materia and I.Ref_Docente = D.Matricola and I.Ref_Materia=?";

            statement = connection.prepareStatement(query);

            statement.setString(1, idMateria);

            result = statement.executeQuery();

            ObservableList<DisplayAppello> appelli = FXCollections.observableArrayList();

            while (result.next()) {

                appelli.add(new DisplayAppello(result.getString("Cognome"), result.getTimestamp("Data"), result.getString("Aula"), result.getString("Id_Appello")));
            }

            statement.close();
            connection.close();

            return appelli;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Ritorna un boolean per informarci se l'iscrizione ad un appello
     * è andata a buon fine. Viene utilizzata per controllare se lo studente
     * ha inserito correttamente email e telefono nella conferma della
     * prenotazione.
     *
     * @param matricola matricola dello studente che si vuole prenotare all'appello
     * @param email     email dello studente che si vuole prenotare all'appello
     * @param telefono  telefono dello studente che si vuole prenotare all'appello
     * @return un boolean che ci dice se l'iscrizio all'appello è andata a buon fine
     */
    public boolean getTheRightStudent(String matricola, String email, String telefono) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            //bada che manca l'aula nel Db --> fixare
            String query = "SELECT * FROM Studente WHERE Matricola=? and Email=? and Telefono=? ";
            statement = connection.prepareStatement(query);

            statement.setString(1, matricola);
            statement.setString(2, email);
            statement.setString(3, telefono);

            result = statement.executeQuery();

            boolean isValid = false;

            if (result.next()) {

                isValid = true;

            } else {
                isValid = false;
            }

            statement.close();
            connection.close();

            return isValid;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
