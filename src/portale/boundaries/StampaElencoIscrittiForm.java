package portale.boundaries;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import portale.controls.StampaElencoIscrittiCtrl;
import portale.entities.*;

import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;

public class StampaElencoIscrittiForm {

    @FXML private ComboBox<CorsoDiLaurea> cdlsCB;

    @FXML private ComboBox<Scuola> scuoleCB;

    @FXML  private ComboBox<Materia> materieCB;

    @FXML private ComboBox<Appello> appelliCB;

    @FXML  private Button esportaButton;

    @FXML  private Button logoutButton;

    @FXML  private Button homeButton;

    private DocenteClass doc;

    public void setDoc(DocenteClass doc) {
        this.doc = doc;
    }

    public void clickEsporta() {

    }

    public void riempiMaterieCB(DocenteClass pDocente, String pCDL) {

    }

    @FXML public void riempiScuoleCB() {
        StampaElencoIscrittiCtrl ctr = new StampaElencoIscrittiCtrl();
        scuoleCB.setItems(ctr.getScuole(doc.getMatricolaDocente()));

        scuoleCB.setCellFactory(new Callback<ListView<Scuola>, ListCell<Scuola>>() {
            @Override
            public ListCell<Scuola> call(ListView<Scuola> param) {

                return new ListCell<Scuola>(){
                    @Override
                    public void updateItem(Scuola item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty) {
                            setText(item.getNomeScuola());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        scuoleCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Scuola>() {

            @Override
            public void changed(ObservableValue<? extends Scuola> observable,
                                Scuola oldValue, Scuola newValue) {
                riempiCdlsCB(newValue.getIdScuola());

            }
        });

    }


    @FXML public void riempiCdlsCB(String pScuola) {
        StampaElencoIscrittiCtrl ctr = new StampaElencoIscrittiCtrl();
        cdlsCB.setItems(ctr.getCdls(pScuola, doc.getMatricolaDocente()));

        cdlsCB.setCellFactory(new Callback<ListView<CorsoDiLaurea>, ListCell<CorsoDiLaurea>>() {
            @Override
            public ListCell<CorsoDiLaurea> call(ListView<CorsoDiLaurea> param) {

                return new ListCell<CorsoDiLaurea>(){
                    @Override
                    public void updateItem(CorsoDiLaurea item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty) {
                            setText(item.getNomeCorso());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        cdlsCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CorsoDiLaurea>() {

            @Override
            public void changed(ObservableValue<? extends CorsoDiLaurea> observable,
                                CorsoDiLaurea oldValue, CorsoDiLaurea newValue) {
            //    riempiCdlsCB(newValue.getCodiceCorso(), doc.getMatricolaDocente() );

            }
        });
    }

    public void riempiAppelliCB(DocenteClass pDocente, String pCodiceMateria, String pCDL) {

    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
