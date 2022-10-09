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
import javafx.scene.control.ToggleButton;

public class FXML_InputPlayerController implements Initializable {

    @FXML
    private Label lhunger;
    private Label loxygen;
    @FXML
    private Label lage;
    @FXML
    private Label lweight;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    @FXML
    private Slider hunger;
    @FXML
    private ComboBox<String> mood;
    @FXML
    private ComboBox<String> mstat;
    @FXML
    private Slider age;
    @FXML
    private Slider weight;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private Button close;
    @FXML
    private TextField speed;
    @FXML
    private Button gspeed;
    @FXML
    private ToggleButton atlet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        weight.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lweight.setText(String.valueOf(newVal.intValue() + " kg"));
            }
        });

        age.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lage.setText(String.valueOf(newVal.intValue() + " y.o"));
            }
        });

        mstat.setItems(FXCollections.observableArrayList(
                "Low", "Average", "Excellent"));

        mood.setItems(FXCollections.observableArrayList(
                "Bad", "Average", "Good"));

        hunger.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lhunger.setText(String.valueOf(newVal.intValue()));
            }
        });
    }

    @FXML
    private void savec(ActionEvent event) {
        PlayerModel pm = new PlayerModel();
        pm.setPlayer_id(id.getText());
        pm.setPlayer_name(name.getText());
        pm.setWeight((int) Math.round(weight.getValue()));
        pm.setAge((int) Math.round(age.getValue()));
        pm.setSpeed(Float.parseFloat(speed.getText()));
        pm.setMedical_status(mstat.getValue());
        pm.setMood(mood.getValue());
        pm.setHunger((int) Math.round(hunger.getValue()));
        pm.setAtletic(atlet.isSelected());

        FXML_MainController.dtplayer.setPlayerModel(pm);
        if (editdata) {
            if (FXML_MainController.dtplayer.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                id.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtplayer.validation(pm.getPlayer_id()) <= 0) {
            if (FXML_MainController.dtplayer.insert()) {
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
            id.requestFocus();
        }
    }

    boolean editdata = false;

    public void execute(PlayerModel pm) {
        if (!pm.getPlayer_id().isEmpty()) {
            editdata = true;
            id.setText(pm.getPlayer_id());
            name.setText(pm.getPlayer_name());
            weight.setValue((int) Math.round(pm.getWeight()));
            age.setValue((int) Math.round(pm.getAge()));
            speed.setText(String.valueOf(pm.getSpeed()));
            mstat.setValue(pm.getMedical_status());
            mood.setValue(pm.getMood());
            hunger.setValue((int) Math.round(pm.getHunger()));
            atlet.setText(String.valueOf(pm.isAtletic()));
            id.setEditable(false);
            name.requestFocus();
        }
    }

    @FXML
    private void resetc(ActionEvent event) {
        id.setText("");
        name.setText("");
        weight.setValue(0);
        age.setValue(0);
        mstat.setValue("");
        mood.setValue("");
        hunger.setValue(0);
        speed.setText("");
        atlet.setSelected(false);
        id.requestFocus();
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void gspeedc(ActionEvent event) {
        PlayerModel pm = new PlayerModel();
        pm.setWeight((int) Math.round(weight.getValue()));
        pm.setAge((int) Math.round(age.getValue()));
        pm.setMedical_status(mstat.getValue());
        pm.setMood(mood.getValue());
        pm.setHunger((int) Math.round(hunger.getValue()));
        double agespeed = 0, weightspeed = 0, medicalspeed = 0, moodspeed = 0, hungerspeed = 0, playerspeed = 0;
        if (pm.getAge() >= 20 && pm.getAge() < 30) {
            agespeed = 5.05;
        } else if (pm.getAge() >= 30 && pm.getAge() < 40) {
            agespeed = 5.17;
        } else if (pm.getAge() >= 40 && pm.getAge() < 50) {
            agespeed = 5.13;
        } else if (pm.getAge() >= 50 && pm.getAge() < 60) {
            agespeed = 5.02;
        } else if (pm.getAge() >= 60 && pm.getAge() < 70) {
            agespeed = 4.78;
        } else if (pm.getAge() >= 70 && pm.getAge() < 80) {
            agespeed = 4.69;
        }
        if (pm.getWeight() >= 40 && pm.getWeight() < 50) {
            weightspeed = agespeed + (0.0193 * agespeed);
        } else if (pm.getWeight() >= 50 && pm.getWeight() < 60) {
            weightspeed = agespeed;
        } else if (pm.getWeight() >= 60 && pm.getWeight() < 70) {
            weightspeed = agespeed - (0.0193 * agespeed);
        } else if (pm.getWeight() >= 70 && pm.getWeight() < 80) {
            weightspeed = agespeed - (2 * 0.0193 * agespeed);
        } else if (pm.getWeight() >= 80 && pm.getWeight() < 90) {
            weightspeed = agespeed - (3 * 0.0193 * agespeed);
        } else if (pm.getWeight() >= 90 && pm.getWeight() < 100) {
            weightspeed = agespeed - (4 * 0.0193 * agespeed);
        }
        switch (pm.getMedical_status()) {
            case "Low":
                medicalspeed = 0.5 * weightspeed;
                break;
            case "Average":
                medicalspeed = 0.75 * weightspeed;
                break;
            case "Excellent":
                medicalspeed = weightspeed;
                break;
        }
        switch (pm.getMood()) {
            case "Bad":
                moodspeed = 0.9 * medicalspeed;
                break;
            case "Average":
                moodspeed = medicalspeed;
                break;
            case "Good":
                moodspeed = 1.1 * medicalspeed;
                break;
        }
        if (pm.getHunger() < 3) {
            hungerspeed = 0.8 * moodspeed;
        } else if (pm.getHunger() >= 3 && pm.getHunger() < 6) {
            hungerspeed = 0.85 * moodspeed;
        } else if (pm.getHunger() >= 6 && pm.getHunger() < 9) {
            hungerspeed = 0.9 * moodspeed;
        } else if (pm.getHunger() >= 9) {
            hungerspeed = moodspeed;
        }
        if (atlet.isSelected()) {
            playerspeed = 1.25 * hungerspeed;
        } else {
            playerspeed = hungerspeed;
        }
        speed.setText(String.valueOf(playerspeed));
    }

}
