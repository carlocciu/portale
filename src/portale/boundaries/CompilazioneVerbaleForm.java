package portale.boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import portale.entities.DocenteClass;
import portale.entities.StudenteClass;
import portale.entities.VerbaleComplessivo;

public class CompilazioneVerbaleForm {

    @FXML
    private ComboBox<StudenteClass> studentiCB;

    @FXML
    private TextArea domandeTA;

    @FXML
    private ComboBox esitoCB;

    @FXML
    private Button verbalizzaButton;

    @FXML
    private Button logoutButton;

    @FXML
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
        studentiCB.getItems().clear();
        ObservableList<StudenteClass> studentiIscritti = FXCollections.observableArrayList();
        studentiIscritti.addAll(mVerbaleComplessivo.getmAppello().getStudentiIscritti());
        studentiCB.setItems(studentiIscritti);
    }

    public void clickVerbalizza() {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
