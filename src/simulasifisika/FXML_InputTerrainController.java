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

public class FXML_InputTerrainController implements Initializable {

    @FXML
    private Label lslope;
    @FXML
    private ComboBox<String> tname;
    @FXML
    private Button save;
    @FXML
    private Slider slope;
    @FXML
    private Slider gravity;
    @FXML
    private Label lgravity;
    @FXML
    private TextField tid;
    @FXML
    private Button reset;
    @FXML
    private Button close;
    @FXML
    private Button geffect;
    @FXML
    private TextField teffect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tname.setItems(FXCollections.observableArrayList(
                "Asphalt", "Wet Asphalt", "Dirt", "Mud", "Stone", "Wet Stone", "Ceramic", "Wet Ceramic", "Sand", "Compact Snow",
                "Loose Snow", "Gritted Ice", "Ice"));
        slope.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lslope.setText(String.valueOf(newVal.intValue() + "°"));
            }
        });
        gravity.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lgravity.setText(String.valueOf(newVal.intValue() + " m/s²"));
            }
        });
    }

    @FXML
    private void savec(ActionEvent event) {
        TerrainModel tm = new TerrainModel();
        tm.setTerrain_id(tid.getText());
        tm.setTerrain_name(tname.getValue());
        tm.setTerrain_effect(Float.parseFloat(teffect.getText()));
        tm.setSlope_level((int) Math.round(slope.getValue()));
        tm.setGravity((int) Math.round(gravity.getValue()));

        FXML_MainController.dtterrain.setTerrainModel(tm);
        if (editdata) {
            if (FXML_MainController.dtterrain.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                tid.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtterrain.validation(tm.getTerrain_id()) <= 0) {
            if (FXML_MainController.dtterrain.insert()) {
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
            tid.requestFocus();
        }
    }
    boolean editdata = false;
    
    public void execute(TerrainModel tm) {
        if (!tm.getTerrain_id().isEmpty()) {
            editdata = true;
            tid.setText(tm.getTerrain_id());
            tname.setValue(tm.getTerrain_name());
            slope.setValue((int) Math.round(tm.getSlope_level()));
            gravity.setValue((int) Math.round(tm.getGravity()));
            teffect.setText(String.valueOf(tm.getTerrain_effect()));
            tid.setEditable(false);
            tname.requestFocus();
        }
    }

    @FXML
    private void resetc(ActionEvent event) {
        tid.setText("");
        tname.setValue("");
        slope.setValue(0);
        gravity.setValue(0);
        teffect.setText("");
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void geffectc(ActionEvent event) {
        TerrainModel tm = new TerrainModel();
        tm.setTerrain_name(tname.getValue());
        tm.setSlope_level((int) Math.round(slope.getValue()));
        tm.setGravity((int) Math.round(gravity.getValue()));
        double terraineffect = 0, slopeeffect = 0, gravityeffect = 0, finaleffect = 0;
        switch (tm.getTerrain_name()) {
            case "Asphalt":
                terraineffect = 1;
                break;
            case "Wet Asphalt":
                terraineffect = 0.88;
                break;
            case "Dirt":
                terraineffect = 0.9;
                break;
            case "Mud":
                terraineffect = 0.65;
                break;
            case "Stone":
                terraineffect = 0.95;
                break;
            case "Wet Stone":
                terraineffect = 0.82;
                break;
            case "Ceramic":
                terraineffect = 0.92;
                break;
            case "Wet Ceramic":
                terraineffect = 0.79;
                break;
            case "Sand":
                terraineffect = 0.88;
                break;
            case "Compact Snow":
                terraineffect = 0.97;
                break;
            case "Loose Snow":
                terraineffect = 0.76;
                break;
            case "Gritted Ice":
                terraineffect = 0.71;
                break;
            case "Ice":
                terraineffect = 0.62;
                break;
        }
        if (tm.getSlope_level() >= 40 && tm.getSlope_level() <= 45) {
            slopeeffect = 0.25 * terraineffect;
        } else if (tm.getSlope_level() >= 33 && tm.getSlope_level() < 40) {
            slopeeffect = 0.4 * terraineffect;
        } else if (tm.getSlope_level() >= 24 && tm.getSlope_level() < 33) {
            slopeeffect = 0.55 * terraineffect;
        } else if (tm.getSlope_level() >= 13 && tm.getSlope_level() < 24) {
            slopeeffect = 0.7 * terraineffect;
        } else if (tm.getSlope_level() >= 1 && tm.getSlope_level() < 13) {
            slopeeffect = 0.85 * terraineffect;
        } else if (tm.getSlope_level() == 0) {
            slopeeffect = 1 * terraineffect;
        } else if (tm.getSlope_level() >= -12 && tm.getSlope_level() < 0) {
            slopeeffect = 0.85 * terraineffect;
        } else if (tm.getSlope_level() >= -23 && tm.getSlope_level() < -12) {
            slopeeffect = 0.7 * terraineffect;
        } else if (tm.getSlope_level() >= -32 && tm.getSlope_level() < -23) {
            slopeeffect = 0.55 * terraineffect;
        } else if (tm.getSlope_level() >= -39 && tm.getSlope_level() < -32) {
            slopeeffect = 0.4 * terraineffect;
        } else if (tm.getSlope_level() >= -45 && tm.getSlope_level() < -39) {
            slopeeffect = 0.25 * terraineffect;
        }
        switch (tm.getGravity()) {
            case 1:
                gravityeffect = 0.1 * slopeeffect;
                break;
            case 2:
                gravityeffect = 0.2 * slopeeffect;
                break;
            case 3:
                gravityeffect = 0.3 * slopeeffect;
                break;
            case 4:
                gravityeffect = 0.4 * slopeeffect;
                break;
            case 5:
                gravityeffect = 0.5 * slopeeffect;
                break;
            case 6:
                gravityeffect = 0.6 * slopeeffect;
                break;
            case 7:
                gravityeffect = 0.7 * slopeeffect;
                break;
            case 8:
                gravityeffect = 0.8 * slopeeffect;
                break;
            case 9:
                gravityeffect = 0.9 * slopeeffect;
                break;
            case 10:
                gravityeffect = slopeeffect;
                break;
            default:
                break;
        }
        finaleffect = gravityeffect * 100;
        teffect.setText(String.valueOf(finaleffect));
    }
}
