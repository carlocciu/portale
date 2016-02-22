package portale.Docente.boundaries;

import java.sql.*;

/**
 * Classe Boundary che gestisce la comunicazione con il database per il login
 */
public class DBMSLoginDocenteBnd {

    /**
     * Nome del docente
     */
    private String nome;

    /**
     * Cognome del docente
     */
    private String cognome;

    /**
     * Ritorna il nome del docente
     * @return nome docente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ritorna il cognome del docente
     * @return cognome docente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Verifica le credenziali inserite dal docente nel database
     * @param pMatricola matricola docente
     * @param pPassword password docente
     * @return un booleano, true se i dati sono corretti, altrimenti false
     */
    public boolean isPresentUser(String pMatricola, String pPassword) {
        boolean isValid = false;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM Docente WHERE Matricola = ? and Password = ?");

            ps.setString(1, pMatricola);
            ps.setString(2, pPassword);
            rs = ps.executeQuery();

            if (rs.next()) {
                isValid = true;
                cognome = rs.getString("");
                nome = rs.getString("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            rs = null;
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ps = null;
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            conn = null;
        }
        return isValid;

    }

}
