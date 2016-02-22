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

public class StampaElencoIscrittiCtrl {

    private DBMSStampaElencoIscrittiBnd mDBMSStampaIscrittiBnd = new DBMSStampaElencoIscrittiBnd();

    public ObservableList<Scuola> getScuole(DocenteClass mDocente) {

        try {
            return mDBMSStampaIscrittiBnd.getScuole(mDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<CorsoDiLaurea> getCDLs(Scuola pScuola, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getCDLs(pScuola, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Materia> getMaterie(CorsoDiLaurea mSelectedCorsoDiLaurea, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getMaterie(mSelectedCorsoDiLaurea, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Appello> getAppelli(Materia pMateria, DocenteClass pDocente) {
        try {
            return mDBMSStampaIscrittiBnd.getAppelli(pMateria, pDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<StudenteClass> getIscrittiAppello(Appello pAppello) {
        try {
            return mDBMSStampaIscrittiBnd.getIscrittiAppello(pAppello);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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
