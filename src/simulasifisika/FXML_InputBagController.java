/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class FXML_InputBagController implements Initializable {

    @FXML
    private Slider bweight;
    @FXML
    private TextField bid;
    @FXML
    private Button save;
    @FXML
    private ComboBox<String> bname;
    @FXML
    private Label lbweight;
    @FXML
    private Button reset;
    @FXML
    private Button close;
    @FXML
    private Button gid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bname.setItems(FXCollections.observableArrayList(
                "Backpack", "Belt bag", "Bindle", "Box clutch", "Bowling bag", "Briefcase", "Bucket bag",
                "Camera bag", "Clutch", "Coin purse", "Cross body", "Doctor", "Drawstring bag", "Duffel",
                "Envelope bag", "Evening bag", "Fanny pack", "Flap bag", "Frame bag", "Handlebar bag",
                "Hobo bag", "Laptop bag", "Messenger", "Micro bag", "Minaudière", "Mini bag", "Pannier bag",
                "Phone pouch", "Pouch", "RuckSack", "Round bag", "Saddle bag", "Satchel", "Shopping bag",
                "Side packs", "Sling bag", "Tote", "Vanity case", "Wallet", "Waist bag", "Weekend bag",
                "Wrislet"));
        bweight.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lbweight.setText(String.valueOf(newVal.intValue() + " kg"));
            }
        });
    }

    @FXML
    private void savec(ActionEvent event) {
        BagModel bm = new BagModel();
        bm.setBag_id(bid.getText());
        bm.setBag_name(bname.getValue());
        bm.setBag_weight((int) Math.round(bweight.getValue()));
        FXML_MainController.dtbag.setBagModel(bm);
        if (editdata) {
            if (FXML_MainController.dtbag.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                bid.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtbag.validation(bm.getBag_id()) <= 0) {
            if (FXML_MainController.dtbag.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Saved Succesfully", ButtonType.OK);
                a.showAndWait();
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Save Data", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data is Already Available", ButtonType.OK);
            a.showAndWait();
            bid.requestFocus();
        }
    }
    boolean editdata = false;
    
    public void execute(BagModel bm) {
        if (!bm.getBag_id().isEmpty()) {
            editdata = true;
            bid.setText(bm.getBag_id());
            bname.setValue(bm.getBag_name());
            bweight.setValue((int)Math.round(bm.getBag_weight()));
            bid.setEditable(false);
            bname.requestFocus();
        }
    }

    @FXML
    private void resetc(ActionEvent event) {
        bid.setText("");
        bname.setValue("");
        bweight.setValue(0);
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void gidc(ActionEvent event) {
        BagModel bm = new BagModel();
        bm.setBag_name(bname.getValue());
        String id = "";
        switch (bm.getBag_name()) {
            case "Backpack":
                id = "01";
                break;
            case "Belt bag":
                id = "02";
                break;
            case "Bindle":
                id = "03";
                break;
            case "Box clutch":
                id = "04";
                break;
            case "Bowling bag":
                id = "05";
                break;
            case "Briefcase":
                id = "06";
                break;
            case "Bucket bag":
                id = "07";
                break;
            case "Camera bag":
                id = "08";
                break;
            case "Clutch":
                id = "09";
                break;
            case "Coin purse":
                id = "10";
                break;
            case "Cross body":
                id = "11";
                break;
            case "Doctor":
                id = "12";
                break;
            case "Drawstring bag":
                id = "13";
                break;
            case "Duffel":
                id = "14";
                break;
            case "Envelope bag":
                id = "15";
                break;
            case "Evening bag":
                id = "16";
                break;
            case "Fanny pack":
                id = "17";
                break;
            case "Flap bag":
                id = "18";
                break;
            case "Frame bag":
                id = "19";
                break;
            case "Handlebar bag":
                id = "20";
                break;
            case "Hobo bag":
                id = "21";
                break;
            case "Laptop bag":
                id = "22";
                break;
            case "Messenger":
                id = "23";
                break;
            case "Micro bag":
                id = "24";
                break;
            case "Minaudière":
                id = "25";
                break;
            case "Mini bag":
                id = "26";
                break;
            case "Pannier bag":
                id = "27";
                break;
            case "Phone pouch":
                id = "28";
                break;
            case "Pouch":
                id = "29";
                break;
            case "RuckSack":
                id = "30";
                break;
            case "Round bag":
                id = "31";
                break;
            case "Saddle bag":
                id = "32";
                break;
            case "Satchel":
                id = "33";
                break;
            case "Shopping bag":
                id = "34";
                break;
            case "Side packs":
                id = "35";
                break;
            case "Sling bag":
                id = "36";
                break;
            case "Tote":
                id = "37";
                break;
            case "Vanity case":
                id = "38";
                break;
            case "Wallet":
                id = "39";
                break;
            case "Waist bag":
                id = "40";
                break;
            case "Weekend bag":
                id = "41";
                break;
            case "Wrislet":
                id = "42";
                break;
        }
        bid.setText(String.valueOf(id));
    }

}
