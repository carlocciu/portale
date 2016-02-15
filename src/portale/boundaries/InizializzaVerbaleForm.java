package portale.boundaries;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import portale.entities.Appello;
import portale.entities.CorsoDiLaurea;
import portale.entities.DocenteClass;
import portale.entities.Materia;

public class InizializzaVerbaleForm {

    private ComboBox<String> scuoleCB;

    private ComboBox<CorsoDiLaurea> cdlsCB;

    private ComboBox<Materia> materiaCB;

    private ComboBox<Appello> appelloCB;

    private TextField oraAperturaTF;

    private Button importaIscrittiButton;

    private Button inizializzaButton;

    private Button logoutButton;

    private Button homeButton;

    public void clickInizializza() {

    }

    public Appello clickImporta() {
        return null;
    }

    public void riempiScuoleCB() {

    }

    public void riempiCdlsCB(String pScuola, DocenteClass pDocente) {

    }

    public void riempiMaterieCB(DocenteClass pDocente, String pCDL) {

    }

    public void riempiAppelliCB(DocenteClass pDocente, String pCodiceMateria, String pCDL) {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
