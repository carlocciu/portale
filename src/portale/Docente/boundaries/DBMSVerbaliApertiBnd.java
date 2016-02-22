package portale.Docente.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.common.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Classe che gestisce la comunicazione con il database per recuperare verbali aperti
 */
public class DBMSVerbaliApertiBnd {

    /**
     * Ritorna le informazioni della materia relativa al verbale
     * @param idVerbale id verbale
     * @return materia del verbale
     */
    public Materia getMateria(String idVerbale) {
        Materia data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM Materia as M, Verbale as V WHERE V.Ref_Materia = M.Id_Materia and V.Id_Verbale = ?");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data = new Materia(rs.getString("Id_Materia"), rs.getString("Nome"), rs.getString("Ordinamento"),
                        rs.getInt("CFU"), rs.getInt("Anno"));
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


    /**
     * Ritorna l'ora di apertura del verbale
     * @param idVerbale id verbale
     * @return ora di apertura
     */
    public LocalTime getOraApertura(String idVerbale) {
        LocalTime data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM Verbale as V WHERE V.Id_Verbale = ?");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data = rs.getTime("V.Ora_Apertura").toLocalTime();
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

    /**
     * Ritorna le informazioni della scuola del verbale
     * @param idVerbale id verbale
     * @return scuola del verbale
     */
    public Scuola getScuola(String idVerbale) {
        Scuola data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM Scuola as S, Dipartimento as D, CorsoDiLaurea as C, Verbale as V WHERE V.Ref_CdL = C.Id_Cdl and C.Ref_Dipartimento = D.Id_Dip and D.Ref_Scuola = S.Id_Scuola and V.Id_Verbale = ?");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data = new Scuola(rs.getString("S.Id_Scuola"), rs.getString("S.Nome"));
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


    /**
     * Ritorna le informazioni del corso di laurea
     * @param idVerbale id verbale
     * @return corso di laurea del verbale
     */
    public CorsoDiLaurea getCDL(String idVerbale) {
        CorsoDiLaurea data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM CorsoDiLaurea as C, Verbale as V WHERE V.Ref_CdL = C.Id_Cdl and V.Id_Verbale = ?");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data = new CorsoDiLaurea(rs.getString("C.Id_Cdl"), rs.getString("C.Nome"));
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


    /**
     * Ritorna le informazioni dell'appello del verbale
     * @param idVerbale id verbale
     * @return appello del verbale
     */
    public Appello getAppello(String idVerbale) {
        Appello data = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT * FROM AppelloEsame as A, Verbale as V WHERE V.Ref_AppelloEsame = A.Id_Appello and V.Id_Verbale = ?");

            ps.setString(1, idVerbale);

            rs = ps.executeQuery();

            while (rs.next()) {
                data = new Appello(rs.getString("A.Id_Appello"), rs.getDate("A.Data"), rs.getString("A.Aula"));
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

    /**
     * Ritorna la lista dei verbali aperti per il docente
     * @param pMatricolaDocente matricola docente
     * @return lista di verbali aperti
     */
    public ObservableList<DisplayVerbale> verbaliAperti(String pMatricolaDocente) {
        ObservableList<DisplayVerbale> data = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PortaleStudenti", "root", "apswpa");

            ps = conn.prepareStatement("SELECT V.Id_Verbale, M.Anno, M.Id_Materia, M.Nome, M.CFU, A.Data, C.Nome FROM Verbale as V, Materia as M, AppelloEsame as A, CorsoDiLaurea as C WHERE V.Ora_Chiusura IS NULL and V.Ref_Cdl = C.Id_CdL and V.Ref_AppelloEsame = A.Id_Appello and V.Ref_Materia = M.Id_Materia and A.Ref_Docente = ?");

            ps.setString(1, pMatricolaDocente);

            rs = ps.executeQuery();

            while (rs.next()) {

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

    /**
     * Ritorna la lista degli esami sostenuti di quel verbale
     * @param idVerbale id verbale
     * @return lista di esami sostenuti
     */
    public ArrayList<EsameVerbalizzato> getEsamiSostenuti(String idVerbale) {
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

    /**
     * Ritorna la lista degli iscritti ad un appello
     * @param appello appello
     * @return lista degli iscritti
     */
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
