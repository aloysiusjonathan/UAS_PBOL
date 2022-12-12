/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

public class PlayerModel {
    private String Player_id, Player_name, Medical_status, Mood;
    private int Weight, Age, Hunger;
    private float Speed;
    private boolean Atletic;
    String p;
    float pfs, pfins;

    public float getPfins() {
        return pfins;
    }

    public void setPfins(float pfins) {
        this.pfins = pfins;
    }

    public float getPfs() {
        return pfs;
    }

    public void setPfs(float pfs) {
        this.pfs = pfs;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getPlayer_id() {
        return Player_id;
    }

    public void setPlayer_id(String Player_id) {
        this.Player_id = Player_id;
    }

    public String getPlayer_name() {
        return Player_name;
    }

    public void setPlayer_name(String Player_name) {
        this.Player_name = Player_name;
    }

    public String getMedical_status() {
        return Medical_status;
    }

    public void setMedical_status(String Medical_status) {
        this.Medical_status = Medical_status;
    }

    public String getMood() {
        return Mood;
    }

    public void setMood(String Mood) {
        this.Mood = Mood;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getHunger() {
        return Hunger;
    }

    public void setHunger(int Hunger) {
        this.Hunger = Hunger;
    }

    public float getSpeed() {
        return Speed;
    }

    public void setSpeed(float Speed) {
        this.Speed = Speed;
    }

    public boolean isAtletic() {
        return Atletic;
    }

    public void setAtletic(boolean Atletic) {
        this.Atletic = Atletic;
    }
}
