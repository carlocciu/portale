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

    private Appello appelloSelezionato;
    private DocenteClass doc;

    StampaElencoIscrittiCtrl ctr = new StampaElencoIscrittiCtrl();

    public void setDoc(DocenteClass doc) {
        this.doc = doc;
    }

    @FXML public void riempiScuoleCB() {
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
        cdlsCB.setItems(ctr.getCdls(pScuola, doc.getMatricolaDocente()));

        cdlsCB.setCellFactory(new Callback<ListView<CorsoDiLaurea>, ListCell<CorsoDiLaurea>>() {
            @Override
            public ListCell<CorsoDiLaurea> call(ListView<CorsoDiLaurea> param) {

                return new ListCell<CorsoDiLaurea>(){
                    @Override
                    public void updateItem(CorsoDiLaurea item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty) {
                            setText(item.getCodiceCorso() + " - " + item.getNomeCorso());
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
                riempiMaterieCB(newValue.getCodiceCorso(), pScuola);

            }
        });
    }

    public void riempiMaterieCB(String pCDL, String pScuola) {
        materieCB.setItems(ctr.getMaterie(pScuola, pCDL, doc.getMatricolaDocente()));

        materieCB.setCellFactory(new Callback<ListView<Materia>, ListCell<Materia>>() {
            @Override
            public ListCell<Materia> call(ListView<Materia> param) {

                return new ListCell<Materia>(){
                    @Override
                    public void updateItem(Materia item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty) {
                            setText(item.getCodiceMateria() + " - " + item.getNomeMateria() + " - " + item.getCFU() + " CFU");
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        materieCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Materia>() {

            @Override
            public void changed(ObservableValue<? extends Materia> observable,
                                Materia oldValue, Materia newValue) {
                riempiAppelliCB(newValue.getCodiceMateria(), pCDL, pScuola);

            }
        });
    }

    public void riempiAppelliCB(String pCodiceMateria, String pCDL, String pScuola) {
        appelliCB.setItems(ctr.getAppelli(pScuola, pCDL, pCodiceMateria, doc.getMatricolaDocente()));

        appelliCB.setCellFactory(new Callback<ListView<Appello>, ListCell<Appello>>() {
            @Override
            public ListCell<Appello> call(ListView<Appello> param) {

                return new ListCell<Appello>(){
                    @Override
                    public void updateItem(Appello item, boolean empty){
                        super.updateItem(item, empty);
                        if(!empty) {
                            setText(item.getDataEsame().toString() + " - Aula: " + item.getAula());
                            setGraphic(null);
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        appelliCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Appello>() {

            @Override
            public void changed(ObservableValue<? extends Appello> observable,
                                Appello oldValue, Appello newValue) {
                    appelloSelezionato = newValue;
            }
        });
    }


    @FXML public void clickEsporta() throws Exception {
        ctr.setAppello(appelloSelezionato);
        ctr.createDocument();
    }

    public void clickLogout() {

    }

    public void clickHome() {

    }

}
