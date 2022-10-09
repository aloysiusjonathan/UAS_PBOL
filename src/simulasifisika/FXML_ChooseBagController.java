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

public class FXML_ChooseBagController implements Initializable {

    private int result = 0;
    private String idresult = "";
    
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
    private TableView<BagModel> tbag;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        field.setItems(FXCollections.observableArrayList(
                "bag_id", "bag_name", "bag_weight"));
        field.getSelectionModel().select(0);
        showdata("bag_id", "");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<BagModel> data = FXML_MainController.dtbag.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MainController.dtbag.Load();
            search.setText("");
        }
        if (data != null) {
            tbag.getColumns().clear();
            tbag.getItems().clear();
            TableColumn col;
            col = new TableColumn("bag_id");
            col.setCellValueFactory(new PropertyValueFactory<BagModel, String>("bag_id"));
            tbag.getColumns().addAll(col);
            col = new TableColumn("bag_name");
            col.setCellValueFactory(new PropertyValueFactory<BagModel, String>("bag_name"));
            tbag.getColumns().addAll(col);
            col = new TableColumn("bag_effect");
            col.setCellValueFactory(new PropertyValueFactory<BagModel, String>("bag_weight"));
            tbag.getColumns().addAll(col);
            tbag.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            tbag.getScene().getWindow().hide();
        }
        topc(null);
        search.requestFocus();
    }

    @FXML
    private void topc(ActionEvent event) {
        tbag.getSelectionModel().selectFirst();
        tbag.requestFocus();
    }

    @FXML
    private void choosec(ActionEvent event) {
        result = 1;
        int c = tbag.getSelectionModel().getSelectedCells().get(0).getRow();
        idresult = String.valueOf(tbag.getItems().get(c).getBag_weight());
        choose.getScene().getWindow().hide();
    }

    @FXML
    private void cancelc(ActionEvent event) {
        result = 0;
        cancel.getScene().getWindow().hide();
    }

    @FXML
    private void searchc(ActionEvent event) {
        showdata(field.getSelectionModel().getSelectedItem(), search.getText());
    }

    @FXML
    private void nxtc(ActionEvent event) {
        tbag.getSelectionModel().selectBelowCell();
        tbag.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        tbag.getSelectionModel().selectLast();
        tbag.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tbag.getSelectionModel().selectAboveCell();
        tbag.requestFocus();
    }
    
}
