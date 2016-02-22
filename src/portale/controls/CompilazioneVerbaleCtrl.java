package portale.controls;

import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import portale.boundaries.CompilazioneVerbaleForm;
import portale.boundaries.DBMSInizializzaVerbaleBnd;
import portale.boundaries.DBMSLoginStudenteBnd;
import portale.boundaries.DBMSVerbalizzaEsameBnd;
import portale.entities.Appello;
import portale.entities.EsameVerbalizzato;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CompilazioneVerbaleCtrl {

    DBMSInizializzaVerbaleBnd mDBMSInizializzaVerbaleBnd = new DBMSInizializzaVerbaleBnd();
    DBMSVerbalizzaEsameBnd mDBMSVerbalizzaEsameBnd = new DBMSVerbalizzaEsameBnd();

    public boolean isValidUserPassword(StudenteClass pStudente, String pPassword) {
        DBMSLoginStudenteBnd dbmsLoginStudenteBnd = new DBMSLoginStudenteBnd();
        return dbmsLoginStudenteBnd.isPresenteUser(pStudente.getMatricolaStudente(), pPassword);
    }

    public void chiudiVerbale (VerbaleComplessivo pVerbale) throws Exception{
        try {
            mDBMSVerbalizzaEsameBnd.chiudiVerbale(pVerbale);
            showPDF(pVerbale);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verbalizzaEsame(StudenteClass pStudente, VerbaleComplessivo pVerbaleComplessivo,
                                CompilazioneVerbaleForm.Esito pEsito, String pVoto, String pDomande) {

        try {
            mDBMSVerbalizzaEsameBnd.insertEsameSostenuto(pStudente, pVerbaleComplessivo, pEsito, pVoto, pDomande);
            mDBMSVerbalizzaEsameBnd.updatePianoDiStudi(pStudente, pVerbaleComplessivo, pEsito, pVoto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<StudenteClass> getStudentiDaEsaminare(Appello pAppello) {

        try {
            ObservableList<StudenteClass> studentiDaEsaminare = FXCollections.observableArrayList();
            ArrayList<StudenteClass> studenti = mDBMSInizializzaVerbaleBnd.getIscrittiAppello(pAppello);

            studentiDaEsaminare.addAll(studenti);

            return studentiDaEsaminare;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showPDF(VerbaleComplessivo pVerbaleComplessivo) throws Exception {
        Document document = new Document();
        ArrayList<EsameVerbalizzato> esamiSostenuti = mDBMSVerbalizzaEsameBnd.getEsamiVerbalizzati(pVerbaleComplessivo);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Verbale" + pVerbaleComplessivo.getIDVerbale() + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(2); // 3 columns.

            List unipa = new List(List.UNORDERED);
            unipa.add("Università degli Studi di Palermo");
            unipa.add("Anno Accademico: " + pVerbaleComplessivo.getAnnoAccademico());
            unipa.add("Verbale degli esami di: " + pVerbaleComplessivo.getAppello().getMateria().getNomeMateria());
            unipa.add("Cod. Materia: " + pVerbaleComplessivo.getAppello().getMateria().getCodiceMateria() + "    CFU: " + pVerbaleComplessivo.getAppello().getMateria().getCFU());

            // We wrap this list in a phrase:
            Phrase infoUnipa = new Phrase();
            infoUnipa.add(unipa);
            // We add this phrase to a cell
            PdfPCell cell1 = new PdfPCell();
            cell1.addElement(infoUnipa);

            // We add the cell to a table:
            table.addCell(cell1);

            List info = new List(List.UNORDERED);
            info.add("Scuola: " + pVerbaleComplessivo.getScuola().getNomeScuola());
            info.add("Corso di Laurea: " + pVerbaleComplessivo.getCDL().getNomeCorso());
            info.add("Sostenuti il: " + pVerbaleComplessivo.getAppello().getDataEsame());
            info.add("(la seduta inizia alle ore: " + pVerbaleComplessivo.getOraApertura() + " )");

            // We wrap this list in a phrase:
            Phrase infoMateria = new Phrase();
            infoMateria.add(info);
            // We add this phrase to a cell
            PdfPCell cell2 = new PdfPCell();
            cell2.addElement(infoMateria);

            // We add the cell to a table:
            table.addCell(cell2);

            for (EsameVerbalizzato s: esamiSostenuti){
                List infoEsame = new List(List.UNORDERED);
                infoEsame.add("Matricola: " + s.getmStudente().getMatricolaStudente() + "   Anno Corso: " + pVerbaleComplessivo.getAppello().getMateria().getAnnoCorso()) ;
                infoEsame.add("Cognome e Nome: " + s.getmStudente().getCognomeStudente() + " " + s.getmStudente().getNomeStudente());

                // We wrap this list in a phrase:
                Phrase phrase = new Phrase();
                phrase.add(infoEsame);
                // We add this phrase to a cell
                PdfPCell phraseCell = new PdfPCell();
                phraseCell.addElement(phrase);

                // We add the cell to a table:
                table.addCell(phraseCell);

                List esame = new List(List.UNORDERED);
                esame.add("Domande: " + s.getDomande());
                if (s.getVoto() != null)
                    switch (s.getVoto()) {
                        case "18":
                            esame.add("Voto: diciotto");
                            break;
                        case "19":
                            esame.add("Voto: diciannove");
                            break;
                        case "20":
                            esame.add("Voto: venti");
                            break;
                        case "21":
                            esame.add("Voto: ventuno");
                            break;
                        case "22":
                            esame.add("Voto: ventidue");
                            break;
                        case "23":
                            esame.add("Voto: ventitre");
                            break;
                        case "24":
                            esame.add("Voto: ventiquattro");
                            break;
                        case "25":
                            esame.add("Voto: venticinque");
                            break;
                        case "26":
                            esame.add("Voto: ventisei");
                            break;
                        case "27":
                            esame.add("Voto: ventisette");
                            break;
                        case "28":
                            esame.add("Voto: ventotto");
                            break;
                        case "29":
                            esame.add("Voto: ventinove");
                            break;
                        case "30":
                            esame.add("Voto: trenta");
                            break;
                        case "30L":
                            esame.add("Voto: trenta e lode");
                            break;
                    }
                else
                    esame.add("Voto: non superato");

                // We wrap this list in a phrase:
                Phrase phrase1 = new Phrase();
                phrase1.add(esame);
                // We add this phrase to a cell
                PdfPCell phraseCell1 = new PdfPCell();
                phraseCell1.addElement(phrase1);

                // We add the cell to a table:
                table.addCell(phraseCell1);

            }

            document.add(table);

            document.close();
            writer.close();

            Desktop d = Desktop.getDesktop();
            d.open(new File("Verbale" + pVerbaleComplessivo.getIDVerbale() + ".pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
