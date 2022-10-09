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

public class FXML_ChooseWeatherController implements Initializable {

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
    private TableView<WeatherModel> tweather;
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
                "weather_id", "weather_name", "wind_speed", "wind_direction", "weather_effect"));
        field.getSelectionModel().select(0);
        showdata("weather_id", "");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<WeatherModel> data = FXML_MainController.dtweather.LookUp(f, i);
        if (data.isEmpty()) {
            data = FXML_MainController.dtweather.Load();
            search.setText("");
        }
        if (data != null) {
            tweather.getColumns().clear();
            tweather.getItems().clear();
            TableColumn col;
            col = new TableColumn("weather_id");
            col.setCellValueFactory(new PropertyValueFactory<WeatherModel, String>("weather_id"));
            tweather.getColumns().addAll(col);
            col = new TableColumn("weather_name");
            col.setCellValueFactory(new PropertyValueFactory<WeatherModel, String>("weather_name"));
            tweather.getColumns().addAll(col);
            col = new TableColumn("wind_speed");
            col.setCellValueFactory(new PropertyValueFactory<WeatherModel, String>("wind_speed"));
            tweather.getColumns().addAll(col);
            col = new TableColumn("wind_direction");
            col.setCellValueFactory(new PropertyValueFactory<WeatherModel, String>("wind_direction"));
            tweather.getColumns().addAll(col);
            col = new TableColumn("weather_effect");
            col.setCellValueFactory(new PropertyValueFactory<WeatherModel, String>("weather_effect"));
            tweather.getColumns().addAll(col);
            tweather.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            tweather.getScene().getWindow().hide();
        }
        topc(null);
        search.requestFocus();
    }

    @FXML
    private void topc(ActionEvent event) {
        tweather.getSelectionModel().selectFirst();
        tweather.requestFocus();
    }

    @FXML
    private void choosec(ActionEvent event) {
        result = 1;
        int c = tweather.getSelectionModel().getSelectedCells().get(0).getRow();
        idresult = String.valueOf(tweather.getItems().get(c).getWeather_effect());
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
        tweather.getSelectionModel().selectBelowCell();
        tweather.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        tweather.getSelectionModel().selectLast();
        tweather.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tweather.getSelectionModel().selectAboveCell();
        tweather.requestFocus();
    }
    
}
