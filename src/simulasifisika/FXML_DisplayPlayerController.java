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

public class FXML_DisplayPlayerController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private TableView<PlayerModel> tplayer;
    @FXML
    private MenuItem add;
    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem del;
    @FXML
    private MenuItem top;
    @FXML
    private MenuItem prev;
    @FXML
    private MenuItem nxt;
    @FXML
    private MenuItem bot;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata() {
        ObservableList<PlayerModel> data = FXML_MainController.dtplayer.Load();
        if (data != null) {
            tplayer.getColumns().clear();
            tplayer.getItems().clear();
            TableColumn col = new TableColumn("player_id");
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
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void addc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputPlayer.fxml"));
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
        PlayerModel pm = new PlayerModel();
        pm = tplayer.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputPlayer.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputPlayerController isidt = (FXML_InputPlayerController) loader.getController();
            isidt.execute(pm);
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
        PlayerModel pm = new PlayerModel();
        pm = tplayer.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this row?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MainController.dtplayer.delete(pm.getPlayer_id())) {
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
    private void topc(ActionEvent event) {
        tplayer.getSelectionModel().selectFirst();
        tplayer.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tplayer.getSelectionModel().selectAboveCell();
        tplayer.requestFocus();
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
    
}
