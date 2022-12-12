/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXML_ChooseOutfitController implements Initializable {

    private int result = 0;
    private String idresult = "";
    private int results = 0;
    private String idresults = "";
    
    @FXML
    private Button prev;
    @FXML
    private Button bot;
    @FXML
    private Button nxt;
    @FXML
    private Button bsearch;
    @FXML
    private Button cancel;
    @FXML
    private TextField search;
    @FXML
    private TableView<OutfitModel> toutfit;
    @FXML
    private Button choose;
    @FXML
    private Button top;
    @FXML
    private ComboBox<String> field;
    
    public int getResult() {
        return (result);
    }

    public String getIdresult() {
        return (idresult);
    }
    
    public int getResults() {
        return (results);
    }

    public String getIdresults() {
        return (idresults);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        field.setItems(FXCollections.observableArrayList(
                "outfit_id", "outfit_name", "outfit_effect"));
        field.getSelectionModel().select(0);
        showdata("outfit_id", "");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<OutfitModel> data = FXML_MainController.dtoutfit.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MainController.dtoutfit.Load();
            search.setText("");
        }
        if (data != null) {
            toutfit.getColumns().clear();
            toutfit.getItems().clear();
            TableColumn col;
            col = new TableColumn("outfit_id");
            col.setCellValueFactory(new PropertyValueFactory<OutfitModel, String>("outfit_id"));
            toutfit.getColumns().addAll(col);
            col = new TableColumn("outfit_name");
            col.setCellValueFactory(new PropertyValueFactory<OutfitModel, String>("outfit_name"));
            toutfit.getColumns().addAll(col);
            col = new TableColumn("outfit_effect");
            col.setCellValueFactory(new PropertyValueFactory<OutfitModel, String>("outfit_effect"));
            toutfit.getColumns().addAll(col);
            toutfit.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            toutfit.getScene().getWindow().hide();
        }
        topc(null);
        search.requestFocus();
    }

    @FXML
    private void topc(ActionEvent event) {
        toutfit.getSelectionModel().selectFirst();
        toutfit.requestFocus();
    }

    @FXML
    private void choosec(ActionEvent event) {
        result = 1;
        results = 1;
        int c = toutfit.getSelectionModel().getSelectedCells().get(0).getRow();
        idresult = String.valueOf(toutfit.getItems().get(c).getOutfit_effect());
        idresults = String.valueOf(toutfit.getItems().get(c).getOutfit_id());
        choose.getScene().getWindow().hide();
    }

    @FXML
    private void cancelc(ActionEvent event) {
        result = 0;
        results = 0;
        cancel.getScene().getWindow().hide();
    }

    @FXML
    private void searchc(ActionEvent event) {
        showdata(field.getSelectionModel().getSelectedItem(), search.getText());
    }

    @FXML
    private void nxtc(ActionEvent event) {
        toutfit.getSelectionModel().selectBelowCell();
        toutfit.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        toutfit.getSelectionModel().selectLast();
        toutfit.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        toutfit.getSelectionModel().selectAboveCell();
        toutfit.requestFocus();
    }
    
}
