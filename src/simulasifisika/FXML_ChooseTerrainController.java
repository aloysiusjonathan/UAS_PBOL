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

public class FXML_ChooseTerrainController implements Initializable {

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
    private TableView<TerrainModel> tterrain;
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
                "terrain_id", "terrain_name", "slope_level", "gravity", "terrain_effect"));
        field.getSelectionModel().select(0);
        showdata("terrain_id", "");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<TerrainModel> data = FXML_MainController.dtterrain.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MainController.dtterrain.Load();
            search.setText("");
        }
        if (data != null) {
            tterrain.getColumns().clear();
            tterrain.getItems().clear();
            TableColumn col;
            col = new TableColumn("terrain_id");
            col.setCellValueFactory(new PropertyValueFactory<TerrainModel, String>("terrain_id"));
            tterrain.getColumns().addAll(col);
            col = new TableColumn("terrain_name");
            col.setCellValueFactory(new PropertyValueFactory<TerrainModel, String>("terrain_name"));
            tterrain.getColumns().addAll(col);
            col = new TableColumn("slope_level");
            col.setCellValueFactory(new PropertyValueFactory<TerrainModel, String>("slope_level"));
            tterrain.getColumns().addAll(col);
            col = new TableColumn("gravity");
            col.setCellValueFactory(new PropertyValueFactory<TerrainModel, String>("gravity"));
            tterrain.getColumns().addAll(col);
            col = new TableColumn("terrain_effect");
            col.setCellValueFactory(new PropertyValueFactory<TerrainModel, String>("terrain_effect"));
            tterrain.getColumns().addAll(col);
            tterrain.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            tterrain.getScene().getWindow().hide();
        }
        topc(null);
        search.requestFocus();
    }

    @FXML
    private void topc(ActionEvent event) {
        tterrain.getSelectionModel().selectFirst();
        tterrain.requestFocus();
    }

    @FXML
    private void choosec(ActionEvent event) {
        result = 1;
        int c = tterrain.getSelectionModel().getSelectedCells().get(0).getRow();
        idresult = String.valueOf(tterrain.getItems().get(c).getTerrain_effect());
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
        tterrain.getSelectionModel().selectBelowCell();
        tterrain.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        tterrain.getSelectionModel().selectLast();
        tterrain.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tterrain.getSelectionModel().selectAboveCell();
        tterrain.requestFocus();
    }
    
}
