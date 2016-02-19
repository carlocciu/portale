package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.*;

import java.sql.*;
import java.util.Date;

public class DBMSStampaElencoIscrittiBnd {

    public ObservableList<StudenteClass> getStudentiIScritti(Date pData, String pCodiceMateria, String pDocente) {
        return null;
    }

    public ObservableList<CorsoDiLaurea> getCDLs(String pScuola, String docente) {
        ObservableList<CorsoDiLaurea> data = FXCollections.observableArrayList();;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM CorsoDiLaurea as CL, Scuola as S, " +
                    "Dipartimento as D, PianoDiStudi as P, " +
                    "Materia as M, Insegnamento as I" +
                    " WHERE I.Ref_Docente = ? and I.Ref_Materia = M.Id_Materia" +
                    " and I.Ref_Docente = M.Docente and P.Ref_Materia = M.Id_Materia" +
                    " and P.Ref_CorsoDiLaurea = CL.Id_Cdl and CL.Ref_Dipartimento = D.Id_Dip" +
                    " and D.Ref_Scuola = S.Id_Scuola and S.Id_Scuola = ?");

            ps.setString(1, docente);
            ps.setString(2, pScuola);
            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new CorsoDiLaurea(rs.getString("CL.Id_Cdl"), rs.getString("CL.Nome")));
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

    public ObservableList<Materia> getMaterie(String pCdl, String pDocente) {
        return null;
    }

    public ObservableList<Appello> getAppelli(String pDocente, String pCodiceMateria, String pCdl) {
        return null;
    }

    public ObservableList<Scuola> getScuole(String matricolaDocente) {
        ObservableList<Scuola> data = FXCollections.observableArrayList();;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM Scuola as S, " +
                    "Dipartimento as D, CorsoDiLaurea as C, PianoDiStudi as P, " +
                    "Materia as M, Insegnamento as I" +
                    " WHERE I.Ref_Docente = ? and I.Ref_Materia = M.Id_Materia" +
                    " and I.Ref_Docente = M.Docente and P.Ref_Materia = M.Id_Materia" +
                    " and P.Ref_CorsoDiLaurea = C.Id_Cdl and C.Ref_Dipartimento = D.Id_Dip" +
                    " and D.Ref_Scuola = S.Id_Scuola");

            ps.setString(1, matricolaDocente);

            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new Scuola(rs.getString("S.Id_Scuola"), rs.getString("S.Nome")));
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
