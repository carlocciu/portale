package portale.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private ObservableList<StudenteClass> mStudentiDaEsaminare;
    private StudenteClass mSelectedStudente;

    @FXML
    private TextArea domandeTA;

    @FXML
    private ComboBox<String> esitoCB;
    private String mSelectedVoto;

    @FXML
    private Button verbalizzaButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    private DocenteClass mDocente;

    private VerbaleComplessivo mVerbaleComplessivo;


    public void init(DocenteClass pDocente, VerbaleComplessivo pVerbaleComplessivo){
        setDocente(pDocente);
        setVerbaleComplessivo(pVerbaleComplessivo);
        initStudentiCB();
        initEsitoCB();
    }

    public void setDocente(DocenteClass pDocente) {
        mDocente = pDocente;
    }

    public void setVerbaleComplessivo(VerbaleComplessivo pVerbaleComplessivo) {
        mVerbaleComplessivo = pVerbaleComplessivo;
    }

    public void initStudentiCB() {
        /* Get studenti da esaminare from appello */
        mStudentiDaEsaminare = FXCollections.observableArrayList();
        mStudentiDaEsaminare.addAll(mVerbaleComplessivo.getmAppello().getStudentiIscritti());

        studentiCB.getItems().clear();
        studentiCB.setItems(mStudentiDaEsaminare);

        studentiCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudenteClass>() {
            @Override
            public void changed(ObservableValue<? extends StudenteClass> observable, StudenteClass oldValue, StudenteClass newValue) {
                mSelectedStudente = newValue;
            }
        });
    }

    public void initEsitoCB(){
        esitoCB.getItems().clear();
        ObservableList<String> votiPossibili = FXCollections.observableArrayList();
        for(int i = 18; i <= 30; i++)
            votiPossibili.add(String.valueOf(i));
        votiPossibili.add("30L");
        esitoCB.setItems(votiPossibili);

        esitoCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mSelectedVoto = newValue;
            }
        });
    }

    public void clickVerbalizza() {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
