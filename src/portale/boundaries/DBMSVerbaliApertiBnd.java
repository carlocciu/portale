package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.*;

import java.sql.*;
import java.util.*;

public class DBMSVerbaliApertiBnd {

    public ObservableList<DisplayVerbale> verbaliAperti(String pMatricolaDocente) {
        ObservableList<DisplayVerbale> data = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT V.Id_Verbale, M.Anno, M.Id_Materia, M.Nome, M.CFU, A.Data, C.Nome FROM Verbale as V, Materia as M, AppelloEsame as A, CorsoDiLaurea as C WHERE V.Ora_Chiusura IS NULL and V.Ref_Cdl = C.Id_CdL and V.Ref_AppelloEsame = A.Id_Appello and V.Ref_Materia = M.Id_Materia and M.Docente = ?");

            ps.setString(1, pMatricolaDocente);

            rs = ps.executeQuery();

            while (rs.next()) {
             //   ArrayList<EsameVerbalizzato> esamiSostenuti = getEsamiSostenuti(rs.getString("V.Id_Verbale"));
              //  ArrayList<StudenteClass> studentiIscritti = getStudentiIScritti(rs.getString("A.Id_Appello"));
                java.util.Date D = rs.getTimestamp("A.Data");
                data.add(new DisplayVerbale(rs.getInt("M.Anno"), rs.getString("C.Nome"), rs.getString("M.Nome"), rs.getInt("M.CFU"), D.toString(), rs.getString("V.Id_Verbale")));
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

        return data;
    }

    public ArrayList<EsameVerbalizzato> getEsamiSostenuti(String idVerbale){
        ArrayList<EsameVerbalizzato> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM EsameVerbalizzato as E, Studente as S WHERE E.Ref_Verbale = ? and E.Ref_Studente = S.Matricola");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new EsameVerbalizzato(rs.getString("E.Voto"), rs.getString("E.Domande"), new StudenteClass(rs.getString("S.Nome"), rs.getString("S.Cognome"), rs.getString("S.Matricola"))));
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
        return data;
    }

    public ArrayList<StudenteClass> getStudentiIScritti(String appello) {
        ArrayList<StudenteClass> data = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT S.Matricola, S.Nome, S.Cognome FROM Prenotazione P, Studente S WHERE S.Matricola = P.Ref_Studente and P.Ref_Appello = ?");


            ps.setString(1, appello);


            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new StudenteClass(rs.getString("S.Nome"), rs.getString("S.Cognome"), rs.getString("S.Matricola")));
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
        return data;
    }

}
