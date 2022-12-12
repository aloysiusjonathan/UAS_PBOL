/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXML_CalculatorController implements Initializable {

    private BagModel bm = new BagModel();
    private OutfitModel om = new OutfitModel();
    private PlayerModel pm = new PlayerModel();
    private ShoeModel sm = new ShoeModel();
    private TerrainModel tm = new TerrainModel();
    private WeatherModel wm = new WeatherModel();
    
    @FXML
    private Button gspeed;
    @FXML
    private TextField speed;
    @FXML
    private Button close;
    @FXML
    private TextField pid;
    @FXML
    private Button reset;
    @FXML
    private Button gpid;
    @FXML
    private Button gtid;
    @FXML
    private TextField tid;
    @FXML
    private TextField oid;
    @FXML
    private Button goid;
    @FXML
    private Button gwid;
    @FXML
    private TextField wid;
    @FXML
    private TextField bid;
    @FXML
    private Button gbid;
    @FXML
    private Button gsid;
    @FXML
    private TextField sid;
    @FXML
    private Button report;
    @FXML
    private TextField pin;
    @FXML
    private TextField bosi;
    @FXML
    private TextField ti;
    @FXML
    private TextField wi;
    @FXML
    private TextField wep;
    @FXML
    private TextField pfs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void gspeedc(ActionEvent event) {
        float tspeed = 0, wspeed = 0, ospeed = 0, sspeed = 0, bspeed = 0, finalspeed;
        tspeed = Float.parseFloat(pid.getText()) * Float.parseFloat(tid.getText()) / 100;
        wspeed = tspeed * Float.parseFloat(wid.getText()) / 100;
        ospeed = wspeed * Float.parseFloat(oid.getText()) / 100;
        sspeed = ospeed * Float.parseFloat(sid.getText()) / 100;
        bspeed = (float) (sspeed * (0.001 * Float.parseFloat(bid.getText())));
        finalspeed = Float.parseFloat(pid.getText()) - bspeed;
        speed.setText(String.valueOf(finalspeed));
        pm.setPfins(finalspeed);
    }

    @FXML
    private void closec(ActionEvent event) {
        close.getScene().getWindow().hide();
    }

    @FXML
    private void resetc(ActionEvent event) {
        pid.setText("");
        tid.setText("");
        wid.setText("");
        oid.setText("");
        sid.setText("");
        bid.setText("");
        speed.setText("");
    }


    @FXML
    private void gpidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChoosePlayer.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChoosePlayerController isidt = (FXML_ChoosePlayerController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                pid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1 && isidt.getResultss() == 1) {
                pin.setText(isidt.getIdresults() + "/" + isidt.getIdresultss());
            }
            pm.setP(String.valueOf(pin.getText()));
            pm.setPfs(Float.parseFloat(pid.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gtidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChooseTerrain.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChooseTerrainController isidt = (FXML_ChooseTerrainController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                tid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1) {
                wep.setText(isidt.getIdresults());
            }
            tm.setT(String.valueOf(wep.getText()));
            tm.setTe(Float.parseFloat(tid.getText()) / 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChooseOutfit.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChooseOutfitController isidt = (FXML_ChooseOutfitController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                oid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1) {
                wi.setText(isidt.getIdresults());
            }
            om.setO(String.valueOf(wi.getText()));
            om.setOe(Float.parseFloat(oid.getText()) / 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gwidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChooseWeather.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChooseWeatherController isidt = (FXML_ChooseWeatherController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                wid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1) {
                pfs.setText(isidt.getIdresults());
            }
            wm.setW(String.valueOf(pfs.getText()));
            wm.setWe(Float.parseFloat(wid.getText()) / 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gbidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChooseBag.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChooseBagController isidt = (FXML_ChooseBagController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                bid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1) {
                bosi.setText(isidt.getIdresults());
            }
            bm.setB(String.valueOf(bosi.getText()));
            bm.setBe((float) (0.001 * Float.parseFloat(bid.getText())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gsidc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ChooseShoe.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ChooseShoeController isidt = (FXML_ChooseShoeController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getResult() == 1) {
                sid.setText(isidt.getIdresult());
            }
            if (isidt.getResults() == 1) {
                ti.setText(isidt.getIdresults());
            }
            sm.setS(String.valueOf(ti.getText()));
            sm.setSe(Float.parseFloat(sid.getText()) / 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reportc(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_Report.fxml"));
            Parent root = (Parent) loader.load();
            FXML_ReportController dtb = (FXML_ReportController) loader.getController();
            dtb.getdata(bm, om, sm);
            FXML_ReportController dtp = (FXML_ReportController) loader.getController();
            dtp.getdata(pm);
            FXML_ReportController dtt = (FXML_ReportController) loader.getController();
            dtt.getdata(tm, wm);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
