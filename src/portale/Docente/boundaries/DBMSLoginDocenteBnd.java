package portale.Docente.boundaries;

import java.sql.*;

public class DBMSLoginDocenteBnd {

    private String nome;
    private String cognome;

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

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
