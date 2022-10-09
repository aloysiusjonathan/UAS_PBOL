/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXML_InputOutfitController implements Initializable {

    @FXML
    private Button reset;
    @FXML
    private Button save;
    @FXML
    private TextField oid;
    @FXML
    private ComboBox<String> oname;
    @FXML
    private TextField oeffect;
    @FXML
    private Button close;
    @FXML
    private Button geffect;
    @FXML
    private Button gid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oname.setItems(FXCollections.observableArrayList(
                "Cotton", "Linen", "Denim", "Drill", "Baby Canvas", "Leather", "Lycra", "Organza", "Polyester",
                "Suede", "Silk", "Velvet", "Wool", "Brocade", "Chiffon", "Knitted", "Twistcone", "Jersey",
                "Rayon", "Viscose", "Cashmere", "Tweed", "Taffeta"));
    }

    @FXML
    private void resetc(ActionEvent event) {
        oid.setText("");
        oname.setValue("");
        oeffect.setText("");
    }

    @FXML
    private void savec(ActionEvent event) {
        OutfitModel om = new OutfitModel();
        om.setOutfit_id(oid.getText());
        om.setOutfit_name(oname.getValue());
        om.setOutfit_effect(Float.parseFloat(oeffect.getText()));
        FXML_MainController.dtoutfit.setOutfitModel(om);
        if (editdata) {
            if (FXML_MainController.dtoutfit.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                oid.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtoutfit.validation(om.getOutfit_id()) <= 0) {
            if (FXML_MainController.dtoutfit.insert()) {
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
            oid.requestFocus();
        }
    }
    boolean editdata = false;
    
    public void execute(OutfitModel om) {
        if (!om.getOutfit_id().isEmpty()) {
            editdata = true;
            oid.setText(om.getOutfit_id());
            oname.setValue(om.getOutfit_name());
            oeffect.setText(String.valueOf(om.getOutfit_effect()));
            oid.setEditable(false);
            oname.requestFocus();
        }
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void geffectc(ActionEvent event) {
        OutfitModel om = new OutfitModel();
        om.setOutfit_name(oname.getValue());
        double outfiteffect = 0, finaleffect;
        switch (om.getOutfit_name()) {
            case "Cotton": outfiteffect = 1; break;
            case "Linen": outfiteffect = 0.98; break;
            case "Denim": outfiteffect = 0.96; break;
            case "Drill": outfiteffect = 0.92; break;
            case "Baby Canvas": outfiteffect = 0.88; break;
            case "Leather": outfiteffect = 0.84; break;
            case "Lycra": outfiteffect = 1.2; break;
            case "Organza": outfiteffect = 0.98; break;
            case "Polyester": outfiteffect = 0.8; break;
            case "Suede": outfiteffect = 0.86; break;
            case "Silk": outfiteffect = 0.9; break;
            case "Velvet": outfiteffect = 0.89; break;
            case "Wool": outfiteffect = 0.98; break;
            case "Brocade": outfiteffect = 0.93; break;
            case "Chiffon": outfiteffect = 0.83; break;
            case "Knitted": outfiteffect = 0.98; break;
            case "Twistcone": outfiteffect = 1.1; break;
            case "Jersey": outfiteffect = 1.3; break;
            case "Rayon": outfiteffect = 1.5; break;
            case "Viscose": outfiteffect = 1; break;
            case "Cashmere": outfiteffect = 0.9; break;
            case "Tweed": outfiteffect = 0.98; break;
            case "Taffeta": outfiteffect = 0.99; break;
        }
        finaleffect = outfiteffect * 100;
        oeffect.setText(String.valueOf(finaleffect));
    }

    @FXML
    private void gidc(ActionEvent event) {
        OutfitModel om = new OutfitModel();
        om.setOutfit_name(oname.getValue());
        String id = "";
        switch (om.getOutfit_name()) {
            case "Cotton": id = "01"; break;
            case "Linen": id = "02"; break;
            case "Denim": id = "03"; break;
            case "Drill": id = "04"; break;
            case "Baby Canvas": id = "05"; break;
            case "Leather": id = "06"; break;
            case "Lycra": id = "07"; break;
            case "Organza": id = "08"; break;
            case "Polyester": id = "09"; break;
            case "Suede": id = "10"; break;
            case "Silk": id = "11"; break;
            case "Velvet": id = "12"; break;
            case "Wool": id = "13"; break;
            case "Brocade": id = "14"; break;
            case "Chiffon": id = "15"; break;
            case "Knitted": id = "16"; break;
            case "Twistcone": id = "17"; break;
            case "Jersey": id = "18"; break;
            case "Rayon": id = "19"; break;
            case "Viscose": id = "20"; break;
            case "Cashmere": id = "21"; break;
            case "Tweed": id = "22"; break;
            case "Taffeta": id = "23"; break;
        }
        oid.setText(String.valueOf(id));
    }

}
