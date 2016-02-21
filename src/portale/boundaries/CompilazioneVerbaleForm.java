package portale.boundaries;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import portale.entities.DocenteClass;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

public class CompilazioneVerbaleForm {

    private ComboBox<StudenteClass> studentiCB;

    private TextArea domandeTA;

    private ComboBox esitoCB;

    private Button verbalizzaButton;

    private Button logoutButton;

    private Button homeButton;

    private DocenteClass mDocente;

    private VerbaleComplessivo mVerbaleComplessivo;


    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    public void setVerbaleComplessivo(VerbaleComplessivo pVerbaleComplessivo) {
        mVerbaleComplessivo = pVerbaleComplessivo;
    }

    public void riempiStudentiCB() {

    }

    public void clickVerbalizza() {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
