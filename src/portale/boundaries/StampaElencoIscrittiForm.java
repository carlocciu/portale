package portale.boundaries;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import portale.entities.Appello;
import portale.entities.CorsoDiLaurea;
import portale.entities.DocenteClass;
import portale.entities.Materia;

public class StampaElencoIscrittiForm {

    private ComboBox<CorsoDiLaurea> cdls;

    private ComboBox<String> scuoleCB;

    private ComboBox<Materia> materiaCB;

    private ComboBox<Appello> appelloCB;

    private Button esportaButton;

    private Button logoutButton;

    private Button homeButton;

    public void clickEsporta() {

    }

    public void riempiMaterieCB(DocenteClass pDocente, String pCDL) {

    }

    public void riempiScuoleCB() {

    }

    public void riempiCdlsCB(String pScuola, DocenteClass pDocente) {

    }

    public void riempiAppelliCB(DocenteClass pDocente, String pCodiceMateria, String pCDL) {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
