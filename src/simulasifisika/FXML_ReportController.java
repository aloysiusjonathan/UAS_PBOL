/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package simulasifisika;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author fxbil
 */
public class FXML_ReportController implements Initializable {

    @FXML
    private Label pin;
    @FXML
    private Label bosi;
    @FXML
    private Label wi;
    @FXML
    private Label ti;
    @FXML
    private Label pfs;
    @FXML
    private Label wep;
    @FXML
    private Label eep;
    @FXML
    private Label pfins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getdata(BagModel bm, OutfitModel om, ShoeModel sm){
        String bag = bm.getB();
        String outfit = om.getO();
        String shoe = sm.getS();
        float bags = bm.getBe();
        float outfits = om.getOe();
        float shoes = sm.getSe();
        bosi.setText(String.valueOf(bag + "/" + outfit + "/" + shoe));
        wep.setText(String.valueOf(bags * outfits * shoes));
    }
    public void getdata(PlayerModel pm){
        String player = pm.getP();
        float players = pm.getPfs();
        float playerss = pm.getPfins();
        pin.setText(player);
        pfs.setText(String.valueOf(players) + " km/h");
        pfins.setText(String.valueOf(playerss) + " km/h");
    }
    public void getdata(TerrainModel tm, WeatherModel wm){
        String terrain = tm.getT();
        float terrains = tm.getTe();
        ti.setText(terrain);
        String weather = wm.getW();
        float weathers = wm.getWe();
        wi.setText(weather);
        eep.setText(String.valueOf(terrains * weathers));
    }
}
