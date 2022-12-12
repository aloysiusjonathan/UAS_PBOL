/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

public class TerrainModel {
    private String terrain_id, terrain_name;
    private int slope_level, gravity;
    private float terrain_effect;
    String t;
    float te;

    public float getTe() {
        return te;
    }

    public void setTe(float te) {
        this.te = te;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getTerrain_id() {
        return terrain_id;
    }

    public void setTerrain_id(String terrain_id) {
        this.terrain_id = terrain_id;
    }

    public String getTerrain_name() {
        return terrain_name;
    }

    public void setTerrain_name(String terrain_name) {
        this.terrain_name = terrain_name;
    }

    public int getSlope_level() {
        return slope_level;
    }

    public void setSlope_level(int slope_level) {
        this.slope_level = slope_level;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public float getTerrain_effect() {
        return terrain_effect;
    }

    public void setTerrain_effect(float terrain_effect) {
        this.terrain_effect = terrain_effect;
    }
}
