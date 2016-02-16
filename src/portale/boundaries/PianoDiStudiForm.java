package portale.boundaries;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import portale.entities.Appello;
import portale.entities.PianoDiStudi;

public class PianoDiStudiForm {

    private TableView<PianoDiStudi> pianoStudiTV;

    private TableView<Appello> appelliTV;

    private TableColumn<PianoDiStudi, String> annoCol;

    private TableColumn<PianoDiStudi, String> materiaCol;

    private TableColumn<PianoDiStudi, Integer> cfuCol;

    private TableColumn<PianoDiStudi, String> esitoCol;

    private TableColumn<PianoDiStudi, String> dataCol;

    private TableColumn<PianoDiStudi, String> btnPrenotaCol;

    private TableColumn<Appello, String> docenteCol;

    private TableColumn<Appello, String> dateAppelliCol;

    private TableColumn<Appello, String> aulaCol;

    private TableColumn<Appello, String> prenotaCol;

    private Button logoutButton;

    public void selectMateria(int pIndexMateria) {

    }

    public void selectAppello(int pIndexAppello) {

    }

    public void clickLogout() {

    }

}
