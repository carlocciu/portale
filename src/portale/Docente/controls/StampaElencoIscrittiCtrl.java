package portale.Docente.controls;

import com.itextpdf.text.Document;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import portale.Docente.boundaries.DBMSStampaElencoIscrittiBnd;
import portale.common.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe control che gestisce l'esportazione degli studenti iscritti ad un appello
 */
public class StampaElencoIscrittiCtrl {

    /**
     * Boundary che gestisce la comunicazione con il database per stampare gli iscritti ad un appello
     */
    private DBMSStampaElencoIscrittiBnd mDBMSStampaIscrittiBnd = new DBMSStampaElencoIscrittiBnd();

    /**
     * Ritorna la lista delle scuole in cui il docente insegna
     * @param mDocente informazioni docente
     * @return lista scuole
     */
    public ObservableList<Scuola> getScuole(DocenteClass mDocente) {

        try {
            return mDBMSStampaIscrittiBnd.getScuole(mDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista dei corsi di laurea di una scuola selezionata in cui insegna il docente
     * @param pScuola informazioni scuola
     * @param pDocente informazioni docente
     * @return lista di corsi di laurea
     */
    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getCDLs(pScuola, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista delle materie insegnate dal docente in un corso di laurea selezionato
     * @param mSelectedCorsoDiLaurea informazioni corso di laurea
     * @param pDocente informazioni docente
     * @return lista di materie
     */
    public ObservableList<Materia> getMaterie(CorsoDiLaurea mSelectedCorsoDiLaurea, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getMaterie(mSelectedCorsoDiLaurea, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista degli appelli di una materia selezionata
     * @param pMateria informazioni materia
     * @param pDocente informazioni docente
     * @return lista di appelli
     */
    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getAppelli(pMateria, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ritorna la lista degli studenti iscritti ad un appello
     * @param pAppello appello
     * @return lista di studenti iscritti
     */
    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) {
        try {
            return mDBMSStampaIscrittiBnd.getIscrittiAppello(pAppello);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Genera un pdf che contiene la lista di tutti gli studenti iscritti ad un appello
     * @param pAppello informazioni appello
     * @throws Exception
     */
    public void createDocument(Appello pAppello) throws Exception {
        Document document = new Document();
        ArrayList<StudenteClass> listaIscritti = mDBMSStampaIscrittiBnd.getIscrittiAppello(pAppello);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Elenco" + pAppello.getIdAppello() + ".pdf"));
            document.open();
            document.add(new Paragraph("Lista Iscritti"));

            //Add ordered list
            List orderedList = new List(List.ORDERED);
            for (StudenteClass s : listaIscritti)
                orderedList.add(new ListItem(s.toString()));

            document.add(orderedList);
            document.close();
            writer.close();

            Desktop d = Desktop.getDesktop();
            d.open(new File("Elenco" + pAppello.getIdAppello() + ".pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
