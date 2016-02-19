package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBMSStampaElencoIscrittiBnd {

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

    public ObservableList<CorsoDiLaurea> getCDLs(String pScuola, String docente) {
        ObservableList<CorsoDiLaurea> data = FXCollections.observableArrayList();;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT CL.Id_Cdl, CL.Nome FROM CorsoDiLaurea as CL, Scuola as S, Dipartimento as D, PianoDiStudi as P, Materia as M, Insegnamento as I WHERE I.Ref_Docente = ? and I.Ref_Materia = M.Id_Materia and I.Ref_Docente = M.Docente and P.Ref_Materia = M.Id_Materia and P.Ref_CorsoDiLaurea = CL.Id_Cdl and CL.Ref_Dipartimento = D.Id_Dip and D.Ref_Scuola = S.Id_Scuola and S.Id_Scuola = ?");

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

    public ObservableList<Materia> getMaterie(String idScuola, String Cdl, String pDocente) {
        ObservableList<Materia> data = FXCollections.observableArrayList();;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT M.Id_Materia, M.Nome, M.CFU FROM CorsoDiLaurea as CL, Scuola as S, Dipartimento as D, PianoDiStudi as P, Materia as M, Insegnamento as I WHERE I.Ref_Docente = ? and I.Ref_Materia = M.Id_Materia and I.Ref_Docente = M.Docente and P.Ref_Materia = M.Id_Materia and P.Ref_CorsoDiLaurea = CL.Id_Cdl and CL.Ref_Dipartimento = D.Id_Dip and D.Ref_Scuola = S.Id_Scuola and S.Id_Scuola = ? and CL.Id_Cdl = ?");


            ps.setString(1, pDocente);
            ps.setString(2, idScuola);
            ps.setString(3, Cdl);

            rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new Materia(rs.getString("M.Id_Materia"), rs.getString("M.Nome"), rs.getInt("M.CFU")));
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


    public ObservableList<Appello> getAppelli(String idScuola, String pCdl, String pCodiceMateria, String matricola) {
        ObservableList<Appello> data = FXCollections.observableArrayList();;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT AE.Id_Appello, AE.Data, AE.Aula FROM AppelloEsame as AE, CorsoDiLaurea as CL, Scuola as S, Dipartimento as D, PianoDiStudi as P, Materia as M, Insegnamento as I WHERE I.Ref_Docente = ? and I.Ref_Materia = M.Id_Materia and I.Ref_Docente = M.Docente and P.Ref_Materia = M.Id_Materia and P.Ref_CorsoDiLaurea = CL.Id_Cdl and CL.Ref_Dipartimento = D.Id_Dip and D.Ref_Scuola = S.Id_Scuola and S.Id_Scuola = ? and CL.Id_Cdl = ? and AE.Ref_Materia = M.Id_Materia and M.Id_Materia = ?");


            ps.setString(1, matricola);
            ps.setString(2, idScuola);
            ps.setString(3, pCdl);
            ps.setString(4, pCodiceMateria);

            rs = ps.executeQuery();

            while (rs.next()) {
                Date D = rs.getTimestamp("AE.Data");
                data.add(new Appello(rs.getString("AE.Id_Appello"), D, rs.getString("AE.Aula")));
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
