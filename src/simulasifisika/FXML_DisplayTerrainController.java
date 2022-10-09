/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXML_DisplayTerrainController implements Initializable {

    @FXML
    private MenuItem top;
    @FXML
    private MenuItem prev;
    @FXML
    private MenuItem nxt;
    @FXML
    private MenuItem bot;
    @FXML
    private MenuItem add;
    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem del;
    @FXML
    private TableView<TerrainModel> tterrain;
    @FXML
    private Button close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata() {
        ObservableList<TerrainModel> data = FXML_MainController.dtterrain.Load();
        if (data != null) {
            tterrain.getColumns().clear();
            tterrain.getItems().clear();
            TableColumn col = new TableColumn("terrain_id");
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
    }

    @FXML
    private void topc(ActionEvent event) {
        tterrain.getSelectionModel().selectFirst();
        tterrain.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tterrain.getSelectionModel().selectAboveCell();
        tterrain.requestFocus();
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
    private void addc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputTerrain.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        topc(event);
    }

    @FXML
    private void editc(ActionEvent event) {
        TerrainModel tm = new TerrainModel();
        tm = tterrain.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputTerrain.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputTerrainController isidt = (FXML_InputTerrainController) loader.getController();
            isidt.execute(tm);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        topc(event);
    }

    @FXML
    private void delc(ActionEvent event) {
        TerrainModel tm = new TerrainModel();
        tm = tterrain.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this row?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MainController.dtterrain.delete(tm.getTerrain_id())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data Deleted Successfully", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Failed to Delete Data", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            topc(event);
        }
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }
    
}
