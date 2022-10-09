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

public class FXML_DisplayShoeController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private TableView<ShoeModel> tshoe;
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
        ObservableList<ShoeModel> data = FXML_MainController.dtshoe.Load();
        if (data != null) {
            tshoe.getColumns().clear();
            tshoe.getItems().clear();
            TableColumn col = new TableColumn("shoe_id");
            col.setCellValueFactory(new PropertyValueFactory<ShoeModel, String>("shoe_id"));
            tshoe.getColumns().addAll(col);
            col = new TableColumn("shoe_name");
            col.setCellValueFactory(new PropertyValueFactory<ShoeModel, String>("shoe_name"));
            tshoe.getColumns().addAll(col);
            col = new TableColumn("shoe_effect");
            col.setCellValueFactory(new PropertyValueFactory<ShoeModel, String>("shoe_effect"));
            tshoe.getColumns().addAll(col);
            tshoe.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Empty", ButtonType.OK);
            a.showAndWait();
            tshoe.getScene().getWindow().hide();
        }
    }

    @FXML
    private void topc(ActionEvent event) {
        tshoe.getSelectionModel().selectFirst();
        tshoe.requestFocus();
    }

    @FXML
    private void prevc(ActionEvent event) {
        tshoe.getSelectionModel().selectAboveCell();
        tshoe.requestFocus();
    }

    @FXML
    private void nxtc(ActionEvent event) {
        tshoe.getSelectionModel().selectBelowCell();
        tshoe.requestFocus();
    }

    @FXML
    private void botc(ActionEvent event) {
        tshoe.getSelectionModel().selectLast();
        tshoe.requestFocus();
    }

    @FXML
    private void addc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputShoe.fxml"));
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
        ShoeModel sm = new ShoeModel();
        sm = tshoe.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputShoe.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputShoeController isidt = (FXML_InputShoeController) loader.getController();
            isidt.execute(sm);
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
        ShoeModel sm = new ShoeModel();
        sm = tshoe.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this row?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXML_MainController.dtshoe.delete(sm.getShoe_id())) {
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
