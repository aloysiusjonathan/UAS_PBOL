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

public class FXML_InputShoeController implements Initializable {

    @FXML
    private ComboBox<String> sname;
    @FXML
    private TextField sid;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    @FXML
    private TextField seffect;
    @FXML
    private Button close;
    @FXML
    private Button geffect;
    @FXML
    private Button gid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sname.setItems(FXCollections.observableArrayList(
                "Air forces", "Ballet", "Bast", "Blucher", "Boat", "Brogan", "Brogue", "Brothel", "Bucks",
                "Cantabrian albarca", "Chelsea", "Chopine", "Chukka", "Climbing", "Clog", "Court", "Cross country running", "Derby",
                "Desert", "Diabetic", "Dress", "Duckbill", "Driving moccasins", "Earth", "Elevator", "Espadrille", "Fashion", "Galesh",
                "Geta", "Giveh", "High-heeled", "Hiking", "Huarache", "Jazz", "Jelly", "Jika-tabi", "Jutti", "Kitten", "Kolhapuri chappal",
                "Kung fu", "Loafers", "Lotus", "Mary jane", "Mojari", "Moccasin", "Monk", "Mule", "Okobo", "Opanak", "Opinga", "Organ",
                "Orthopaedic", "Over-the-knee", "Oxford", "Pampootie", "Peep-toe", "Peranakan beaded", "Peshawari", "Platform", "Plimsoll",
                "Pointed", "Pointinini", "Riding", "Rocker bottom", "Rope-soled", "Russian", "Saddle", "Sailing", "Sandal", "Silver",
                "Slingback", "Slip-on", "Slipper", "Sneakers", "Snow", "Spectator", "Spool", "Steel-toe", "Stiletto", "T-bar", "Tiger head",
                "Toe", "Tsarouhi", "Turn", "Venetian-style", "Walk-over", "Wedge", "Wellington", "Winklepicker", "Worishofer", "Zori"));
    }

    @FXML
    private void savec(ActionEvent event) {
        ShoeModel sm = new ShoeModel();
        sm.setShoe_id(sid.getText());
        sm.setShoe_name(sname.getValue());
        sm.setShoe_effect(Float.parseFloat(seffect.getText()));
        FXML_MainController.dtshoe.setShoeModel(sm);
        if (editdata) {
            if (FXML_MainController.dtshoe.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                sid.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtshoe.validation(sm.getShoe_id()) <= 0) {
            if (FXML_MainController.dtshoe.insert()) {
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
            sid.requestFocus();
        }
    }
    boolean editdata = false;
    
    public void execute(ShoeModel sm) {
        if (!sm.getShoe_id().isEmpty()) {
            editdata = true;
            sid.setText(sm.getShoe_id());
            sname.setValue(sm.getShoe_name());
            seffect.setText(String.valueOf(sm.getShoe_effect()));
            sid.setEditable(false);
            sname.requestFocus();
        }
    }

    @FXML
    private void resetc(ActionEvent event) {
        sid.setText("");
        sname.setValue("");
        seffect.setText("");
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void geffectc(ActionEvent event) {
        ShoeModel sm = new ShoeModel();
        sm.setShoe_name(sname.getValue());
        double shoeeffect = 0, finaleffect;
        switch (sm.getShoe_name()) {
            case "Air forces":
                shoeeffect = 1;
                break;
            case "Ballet":
                shoeeffect = 0.5;
                break;
            case "Bast":
                shoeeffect = 0.75;
                break;
            case "Blucher":
                shoeeffect = 0.95;
                break;
            case "Boat":
                shoeeffect = 0.85;
                break;
            case "Brogan":
                shoeeffect = 0.8;
                break;
            case "Brogue":
                shoeeffect = 0.85;
                break;
            case "Brothel":
                shoeeffect = 0.8;
                break;
            case "Bucks":
                shoeeffect = 0.85;
                break;
            case "Cantabrian albarca":
                shoeeffect = 0.75;
                break;
            case "Chelsea":
                shoeeffect = 0.8;
                break;
            case "Chopine":
                shoeeffect = 0.6;
                break;
            case "Chukka":
                shoeeffect = 0.8;
                break;
            case "Climbing":
                shoeeffect = 0.9;
                break;
            case "Clog":
                shoeeffect = 0.6;
                break;
            case "Court":
                shoeeffect = 0.7;
                break;
            case "Cross country running":
                shoeeffect = 1;
                break;
            case "Derby":
                shoeeffect = 0.8;
                break;
            case "Desert":
                shoeeffect = 0.8;
                break;
            case "Diabetic":
                shoeeffect = 0.8;
                break;
            case "Dress":
                shoeeffect = 0.7;
                break;
            case "Duckbill":
                shoeeffect = 0.7;
                break;
            case "Driving moccasins":
                shoeeffect = 0.7;
                break;
            case "Earth":
                shoeeffect = 0.7;
                break;
            case "Elevator":
                shoeeffect = 0.8;
                break;
            case "Espadrille":
                shoeeffect = 0.7;
                break;
            case "Fashion":
                shoeeffect = 0.6;
                break;
            case "Galesh":
                shoeeffect = 0.5;
                break;
            case "Geta":
                shoeeffect = 0.6;
                break;
            case "Giveh":
                shoeeffect = 0.7;
                break;
            case "High-heeled":
                shoeeffect = 0.5;
                break;
            case "Hiking":
                shoeeffect = 1.1;
                break;
            case "Huarache":
                shoeeffect = 0.8;
                break;
            case "Jazz":
                shoeeffect = 0.8;
                break;
            case "Jelly":
                shoeeffect = 0.7;
                break;
            case "Jika-tabi":
                shoeeffect = 0.6;
                break;
            case "Jutti":
                shoeeffect = 0.6;
                break;
            case "Kitten":
                shoeeffect = 0.6;
                break;
            case "Kolhapuri chappal":
                shoeeffect = 0.7;
                break;
            case "Kung fu":
                shoeeffect = 0.8;
                break;
            case "Loafers":
                shoeeffect = 0.8;
                break;
            case "Lotus":
                shoeeffect = 0.5;
                break;
            case "Mary jane":
                shoeeffect = 0.8;
                break;
            case "Mojari":
                shoeeffect = 0.7;
                break;
            case "Moccasin":
                shoeeffect = 0.7;
                break;
            case "Monk":
                shoeeffect = 0.8;
                break;
            case "Mule":
                shoeeffect = 0.6;
                break;
            case "Okobo":
                shoeeffect = 0.6;
                break;
            case "Opanak":
                shoeeffect = 0.8;
                break;
            case "Opinga":
                shoeeffect = 0.7;
                break;
            case "Organ":
                shoeeffect = 0.7;
                break;
            case "Orthopaedic":
                shoeeffect = 0.6;
                break;
            case "Over-the-knee":
                shoeeffect = 0.8;
                break;
            case "Oxford":
                shoeeffect = 0.8;
                break;
            case "Pampootie":
                shoeeffect = 0.8;
                break;
            case "Peep-toe":
                shoeeffect = 0.5;
                break;
            case "Peranakan beaded":
                shoeeffect = 0.6;
                break;
            case "Peshawari":
                shoeeffect = 0.7;
                break;
            case "Platform":
                shoeeffect = 0.5;
                break;
            case "Plimsoll":
                shoeeffect = 0.7;
                break;
            case "Pointed":
                shoeeffect = 0.6;
                break;
            case "Pointinini":
                shoeeffect = 0.6;
                break;
            case "Riding":
                shoeeffect = 0.7;
                break;
            case "Rocker bottsm":
                shoeeffect = 0.9;
                break;
            case "Rope-soled":
                shoeeffect = 0.6;
                break;
            case "Russian":
                shoeeffect = 0.7;
                break;
            case "Saddle":
                shoeeffect = 0.8;
                break;
            case "Sailing":
                shoeeffect = 0.8;
                break;
            case "Sandal":
                shoeeffect = 1;
                break;
            case "Silver":
                shoeeffect = 0.7;
                break;
            case "Slingback":
                shoeeffect = 0.5;
                break;
            case "Slip-on":
                shoeeffect = 0.6;
                break;
            case "Slipper":
                shoeeffect = 0.6;
                break;
            case "Sneakers":
                shoeeffect = 1;
                break;
            case "Snow":
                shoeeffect = 0.85;
                break;
            case "Spectator":
                shoeeffect = 0.8;
                break;
            case "Spool":
                shoeeffect = 0.5;
                break;
            case "Steel-toe":
                shoeeffect = 0.85;
                break;
            case "Stiletto":
                shoeeffect = 0.5;
                break;
            case "T-bar":
                shoeeffect = 0.75;
                break;
            case "Tiger head":
                shoeeffect = 0.6;
                break;
            case "Toe":
                shoeeffect = 1.1;
                break;
            case "Tsarouhi":
                shoeeffect = 0.8;
                break;
            case "Turn":
                shoeeffect = 0.6;
                break;
            case "Venetian-style":
                shoeeffect = 0.8;
                break;
            case "Walk-over":
                shoeeffect = 0.5;
                break;
            case "Wedge":
                shoeeffect = 0.55;
                break;
            case "Wellington":
                shoeeffect = 0.8;
                break;
            case "Winklepicker":
                shoeeffect = 0.6;
                break;
            case "Worishofer":
                shoeeffect = 0.7;
                break;
            case "Zori":
                shoeeffect = 0.7;
                break;
        }
        finaleffect = shoeeffect * 100;
        seffect.setText(String.valueOf(finaleffect));
    }

    @FXML
    private void gidc(ActionEvent event) {
        ShoeModel sm = new ShoeModel();
        sm.setShoe_name(sname.getValue());
        String id = "";
        switch (sm.getShoe_name()) {
            case "Air forces":
                id = "01";
                break;
            case "Ballet":
                id = "02";
                break;
            case "Bast":
                id = "03";
                break;
            case "Blucher":
                id = "04";
                break;
            case "Boat":
                id = "05";
                break;
            case "Brogan":
                id = "06";
                break;
            case "Brogue":
                id = "07";
                break;
            case "Brothel":
                id = "08";
                break;
            case "Bucks":
                id = "09";
                break;
            case "Cantabrian albarca":
                id = "10";
                break;
            case "Chelsea":
                id = "11";
                break;
            case "Chopine":
                id = "12";
                break;
            case "Chukka":
                id = "13";
                break;
            case "Climbing":
                id = "14";
                break;
            case "Clog":
                id = "15";
                break;
            case "Court":
                id = "16";
                break;
            case "Cross country running":
                id = "17";
                break;
            case "Derby":
                id = "18";
                break;
            case "Desert":
                id = "19";
                break;
            case "Diabetic":
                id = "20";
                break;
            case "Dress":
                id = "21";
                break;
            case "Duckbill":
                id = "22";
                break;
            case "Driving moccasins":
                id = "23";
                break;
            case "Earth":
                id = "24";
                break;
            case "Elevator":
                id = "25";
                break;
            case "Espadrille":
                id = "26";
                break;
            case "Fashion":
                id = "27";
                break;
            case "Galesh":
                id = "28";
                break;
            case "Geta":
                id = "29";
                break;
            case "Giveh":
                id = "30";
                break;
            case "High-heeled":
                id = "31";
                break;
            case "Hiking":
                id = "32";
                break;
            case "Huarache":
                id = "33";
                break;
            case "Jazz":
                id = "34";
                break;
            case "Jelly":
                id = "35";
                break;
            case "Jika-tabi":
                id = "36";
                break;
            case "Jutti":
                id = "37";
                break;
            case "Kitten":
                id = "38";
                break;
            case "Kolhapuri chappal":
                id = "39";
                break;
            case "Kung fu":
                id = "40";
                break;
            case "Loafers":
                id = "41";
                break;
            case "Lotus":
                id = "42";
                break;
            case "Mary jane":
                id = "43";
                break;
            case "Mojari":
                id = "44";
                break;
            case "Moccasin":
                id = "45";
                break;
            case "Monk":
                id = "46";
                break;
            case "Mule":
                id = "47";
                break;
            case "Okobo":
                id = "48";
                break;
            case "Opanak":
                id = "49";
                break;
            case "Opinga":
                id = "50";
                break;
            case "Organ":
                id = "51";
                break;
            case "Orthopaedic":
                id = "52";
                break;
            case "Over-the-knee":
                id = "53";
                break;
            case "Oxford":
                id = "54";
                break;
            case "Pampootie":
                id = "55";
                break;
            case "Peep-toe":
                id = "56";
                break;
            case "Peranakan beaded":
                id = "57";
                break;
            case "Peshawari":
                id = "58";
                break;
            case "Platform":
                id = "59";
                break;
            case "Plimsoll":
                id = "60";
                break;
            case "Pointed":
                id = "61";
                break;
            case "Pointinini":
                id = "62";
                break;
            case "Riding":
                id = "63";
                break;
            case "Rocker bottsm":
                id = "64";
                break;
            case "Rope-soled":
                id = "65";
                break;
            case "Russian":
                id = "66";
                break;
            case "Saddle":
                id = "67";
                break;
            case "Sailing":
                id = "68";
                break;
            case "Sandal":
                id = "69";
                break;
            case "Silver":
                id = "70";
                break;
            case "Slingback":
                id = "71";
                break;
            case "Slip-on":
                id = "72";
                break;
            case "Slipper":
                id = "73";
                break;
            case "Sneakers":
                id = "74";
                break;
            case "Snow":
                id = "75";
                break;
            case "Spectator":
                id = "76";
                break;
            case "Spool":
                id = "77";
                break;
            case "Steel-toe":
                id = "78";
                break;
            case "Stiletto":
                id = "79";
                break;
            case "T-bar":
                id = "80";
                break;
            case "Tiger head":
                id = "81";
                break;
            case "Toe":
                id = "82";
                break;
            case "Tsarouhi":
                id = "83";
                break;
            case "Turn":
                id = "84";
                break;
            case "Venetian-style":
                id = "85";
                break;
            case "Walk-over":
                id = "86";
                break;
            case "Wedge":
                id = "87";
                break;
            case "Wellington":
                id = "88";
                break;
            case "Winklepicker":
                id = "89";
                break;
            case "Worishofer":
                id = "90";
                break;
            case "Zori":
                id = "91";
                break;
        }
        sid.setText(String.valueOf(id));
    }

}
