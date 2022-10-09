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

public class FXML_ChoosePlayerController implements Initializable {

    private int result = 0;
    private String idresult = "";
    
    @FXML
    private ComboBox<String> field;
    @FXML
    private Button top;
    @FXML
    private Button choose;
    @FXML
    private TableView<PlayerModel> tplayer;
    @FXML
    private TextField search;
    @FXML
    private Button cancel;
    @FXML
    private Button bsearch;
    @FXML
    private Button nxt;
    @FXML
    private Button bot;
    @FXML
    private Button prev;
    
    public int getResult() {
        return (result);
    }

    public String getIdresult() {
        return (idresult);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        field.setItems(FXCollections.observableArrayList(
                "player_id", "player_name", "weight", "age", "speed", "medical_status", "mood", "hunger", "atletic"));
        field.getSelectionModel().select(0);
        showdata("player_id", "");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<PlayerModel> data = FXML_MainController.dtplayer.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MainController.dtplayer.Load();
            search.setText("");
        }
        if (data != null) {
            tplayer.getColumns().clear();
            tplayer.getItems().clear();
            TableColumn col;
            col = new TableColumn("player_id");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("player_id"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("player_name");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("player_name"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("weight");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("weight"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("age");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("age"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("speed");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("speed"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("medical_status");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("medical_status"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("mood");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("mood"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("hunger");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("hunger"));
            tplayer.getColumns().addAll(col);
            col = new TableColumn("atletic");
            col.setCellValueFactory(new PropertyValueFactory<PlayerModel, String>("atletic"));
            tplayer.getColumns().addAll(col);
            tplayer.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            tplayer.getScene().getWindow().hide();
        }
        topc(null);
        search.requestFocus();
    }

    @FXML
    private void topc(ActionEvent event) {
        tplayer.getSelectionModel().selectFirst();
        tplayer.requestFocus();
    }

    @FXML
    private void choosec(ActionEvent event) {
        result = 1;
        int c = tplayer.getSelectionModel().getSelectedCells().get(0).getRow();
        idresult = String.valueOf(tplayer.getItems().get(c).getSpeed());
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
        tplayer.getSelectionModel().selectBelowCell();
        tplayer.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        tplayer.getSelectionModel().selectLast();
        tplayer.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tplayer.getSelectionModel().selectAboveCell();
        tplayer.requestFocus();
    }
    
}
