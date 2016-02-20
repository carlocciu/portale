package portale.controls;

import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import portale.boundaries.DBMSStampaElencoIscrittiBnd;
import portale.entities.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class StampaElencoIscrittiCtrl {

    private String matricolaDocente;
    private String idScuola;
    private String cdl;
    private String materia;
    private Appello appello;

    public void setAppello(Appello appello) {
        this.appello = appello;
    }

    DBMSStampaElencoIscrittiBnd db = new DBMSStampaElencoIscrittiBnd();

    public ObservableList<Scuola> getScuole(String matricolaDocente){
        this.matricolaDocente = matricolaDocente;
        return db.getScuole(matricolaDocente);
    }

    public ObservableList<CorsoDiLaurea> getCdls(String idScuola, String matricolaDocente){
        this.idScuola = idScuola;
        return db.getCDLs(idScuola, matricolaDocente);
    }

    public ObservableList<Materia> getMaterie(String idScuola, String cdl, String matricolaDocente){
        this.cdl = cdl;
        return db.getMaterie(idScuola, cdl, matricolaDocente);
    }

    public ObservableList<Appello> getAppelli(String idScuola, String cdl, String materia, String matricolaDocente){
        this.materia = materia;
        return db.getAppelli(idScuola, cdl, materia, matricolaDocente);
    }

    public void createDocument() throws Exception {
        Document document = new Document();
        ArrayList<StudenteClass> listaIscritti = db.getStudentiIScritti(appello.getIdAppello());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Elenco.pdf"));
            document.open();
            document.add(new Paragraph("Lista Iscritti"));

            //Add ordered list
            List orderedList = new List(List.ORDERED);
            for (StudenteClass s: listaIscritti)
                orderedList.add(new ListItem(s.toString()));

            document.add(orderedList);
            document.close();
            writer.close();

            Desktop d = Desktop.getDesktop();
            d.open(new File("Elenco.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
