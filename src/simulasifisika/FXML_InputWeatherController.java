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

public class FXML_InputWeatherController implements Initializable {

    @FXML
    private Slider wdir;
    @FXML
    private ComboBox<String> wname;
    @FXML
    private TextField wid;
    @FXML
    private Button save;
    @FXML
    private Slider wspeed;
    @FXML
    private Button reset;
    @FXML
    private Label lwdir;
    @FXML
    private Button close;
    @FXML
    private TextField weffect;
    @FXML
    private Button geffect;
    @FXML
    private Label lwspeed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wname.setItems(FXCollections.observableArrayList(
                "Burning", "Sunny", "Partially Cloud", "Cloudy", "Overcast", "Rainy", "Drizzle", "Snowy", "Stormy"));
        wspeed.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lwspeed.setText(String.valueOf(newVal.intValue() + " km/h"));
            }
        });
        wdir.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lwdir.setText(String.valueOf(newVal.intValue() + ""));
            }
        });
    }

    @FXML
    private void savec(ActionEvent event) {
        WeatherModel wm = new WeatherModel();
        wm.setWeather_id(wid.getText());
        wm.setWeather_name(wname.getValue());
        wm.setWeather_effect(Float.parseFloat(weffect.getText()));
        wm.setWind_speed((int) Math.round(wspeed.getValue()));
        wm.setWind_direction((int) Math.round(wdir.getValue()));

        FXML_MainController.dtweather.setWeatherModel(wm);
        if (editdata) {
            if (FXML_MainController.dtweather.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Changed Successfully", ButtonType.OK);
                a.showAndWait();
                wid.setEditable(true);
                resetc(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_MainController.dtweather.validation(wm.getWeather_id()) <= 0) {
            if (FXML_MainController.dtweather.insert()) {
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
            wid.requestFocus();
        }
    }
    boolean editdata = false;

    public void execute(WeatherModel wm) {
        if (!wm.getWeather_id().isEmpty()) {
            editdata = true;
            wid.setText(wm.getWeather_id());
            wname.setValue(wm.getWeather_name());
            wspeed.setValue((int) Math.round(wm.getWind_speed()));
            wdir.setValue((int) Math.round(wm.getWind_direction()));
            weffect.setText(String.valueOf(wm.getWeather_effect()));
            wid.setEditable(false);
            wname.requestFocus();
        }
    }
    
    @FXML
    private void resetc(ActionEvent event) {
        wid.setText("");
        wname.setValue("");
        wspeed.setValue(0);
        wdir.setValue(0);
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void geffectc(ActionEvent event) {
        WeatherModel wm = new WeatherModel();
        wm.setWeather_name(wname.getValue());
        wm.setWind_speed((int) Math.round(wspeed.getValue()));
        wm.setWind_direction((int) Math.round(wdir.getValue()));
        double weathereffect = 0, windeffect = 0, finaleffect = 0;
        switch (wm.getWeather_name()) {
            case "Burning":
                weathereffect = 0.75;
                break;
            case "Sunny":
                weathereffect = 1;
                break;
            case "Partially Cloud":
                weathereffect = 1.15;
                break;
            case "Cloudy":
                weathereffect = 1.2;
                break;
            case "Overcast":
                weathereffect = 1.25;
                break;
            case "Rainy":
                weathereffect = 0.9;
                break;
            case "Drizzle":
                weathereffect = 0.97;
                break;
            case "Snowy":
                weathereffect = 0.8;
                break;
            case "Stormy":
                weathereffect = 0.5;
                break;
        }
        if (wm.getWind_speed() == 0 && wm.getWind_direction() >= -10 && wm.getWind_direction() <= 10) {
            windeffect = weathereffect;
        } else if (wm.getWind_speed() >= 1 && wm.getWind_speed() < 11 && wm.getWind_direction() < 0) {
            windeffect = 0.9 * weathereffect;
        } else if (wm.getWind_speed() >= 11 && wm.getWind_speed() < 21 && wm.getWind_direction() < 0) {
            windeffect = 0.8 * weathereffect;
        } else if (wm.getWind_speed() >= 21 && wm.getWind_speed() < 31 && wm.getWind_direction() < 0) {
            windeffect = 0.7 * weathereffect;
        } else if (wm.getWind_speed() >= 31 && wm.getWind_speed() < 41 && wm.getWind_direction() < 0) {
            windeffect = 0.6 * weathereffect;
        } else if (wm.getWind_speed() >= 41 && wm.getWind_speed() < 51 && wm.getWind_direction() < 0) {
            windeffect = 0.5 * weathereffect;
        } else if (wm.getWind_speed() >= 51 && wm.getWind_speed() < 61 && wm.getWind_direction() < 0) {
            windeffect = 0.4 * weathereffect;
        } else if (wm.getWind_speed() >= 61 && wm.getWind_speed() < 71 && wm.getWind_direction() < 0) {
            windeffect = 0.3 * weathereffect;
        } else if (wm.getWind_speed() >= 71 && wm.getWind_speed() < 81 && wm.getWind_direction() < 0) {
            windeffect = 0.2 * weathereffect;
        } else if (wm.getWind_speed() >= 81 && wm.getWind_speed() < 91 && wm.getWind_direction() < 0) {
            windeffect = 0.1 * weathereffect;
        } else if (wm.getWind_speed() >= 91 && wm.getWind_speed() < 101 && wm.getWind_direction() < 0) {
            windeffect = 0.05 * weathereffect;
        } else if (wm.getWind_speed() >= 1 && wm.getWind_speed() < 11 && wm.getWind_direction() > 0) {
            windeffect = 1.05 * weathereffect;
        } else if (wm.getWind_speed() >= 11 && wm.getWind_speed() < 21 && wm.getWind_direction() > 0) {
            windeffect = 1.1 * weathereffect;
        } else if (wm.getWind_speed() >= 21 && wm.getWind_speed() < 31 && wm.getWind_direction() > 0) {
            windeffect = 1.2 * weathereffect;
        } else if (wm.getWind_speed() >= 31 && wm.getWind_speed() < 41 && wm.getWind_direction() > 0) {
            windeffect = 1.3 * weathereffect;
        } else if (wm.getWind_speed() >= 41 && wm.getWind_speed() < 51 && wm.getWind_direction() > 0) {
            windeffect = 1.4 * weathereffect;
        } else if (wm.getWind_speed() >= 51 && wm.getWind_speed() < 61 && wm.getWind_direction() > 0) {
            windeffect = 1.5 * weathereffect;
        } else if (wm.getWind_speed() >= 61 && wm.getWind_speed() < 71 && wm.getWind_direction() > 0) {
            windeffect = 1.6 * weathereffect;
        } else if (wm.getWind_speed() >= 71 && wm.getWind_speed() < 81 && wm.getWind_direction() > 0) {
            windeffect = 1.7 * weathereffect;
        } else if (wm.getWind_speed() >= 81 && wm.getWind_speed() < 91 && wm.getWind_direction() > 0) {
            windeffect = 1.8 * weathereffect;
        } else if (wm.getWind_speed() >= 91 && wm.getWind_speed() < 101 && wm.getWind_direction() > 0) {
            windeffect = 1.9 * weathereffect;
        }
        finaleffect = windeffect * 100;
        weffect.setText(String.valueOf(finaleffect));
    }

}
