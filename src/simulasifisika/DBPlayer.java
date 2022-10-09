/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBPlayer {

    private PlayerModel dt = new PlayerModel();

    public PlayerModel getPlayerModel() {
        return (dt);
    }

    public void setPlayerModel(PlayerModel pm) {
        dt = pm;
    }

    public ObservableList<PlayerModel> Load() {
        try {
            ObservableList<PlayerModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select player_id, player_name, weight, age, speed, medical_status, mood, hunger, atletic from player");
            int i = 1;
            while (rs.next()) {
                PlayerModel pm = new PlayerModel();
                pm.setPlayer_id(rs.getString("player_id"));
                pm.setPlayer_name(rs.getString("player_name"));
                pm.setWeight(rs.getInt("weight"));
                pm.setAge(rs.getInt("age"));
                pm.setSpeed(rs.getFloat("speed"));
                pm.setMedical_status(rs.getString("medical_status"));
                pm.setMood(rs.getString("mood"));
                pm.setHunger(rs.getInt("hunger"));
                pm.setAtletic(rs.getBoolean("atletic"));

                tableData.add(pm);
                i++;
            }
            con.closeConnection();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validation(String code) {
        int val = 0;
        try {
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as total from player where player_id = '" + code + "'");
            while (rs.next()) {
                val = rs.getInt("total");
            }
            con.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean success = false;
        Connection con = new Connection();
        try {
            con.openConnection();
            con.preparedStatement = con.dbConnection.prepareStatement(
                    "insert into player (player_id, player_name, weight, age, speed, medical_status, mood, hunger, atletic) values (?,?,?,?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getPlayerModel().getPlayer_id());
            con.preparedStatement.setString(2, getPlayerModel().getPlayer_name());
            con.preparedStatement.setInt(3, getPlayerModel().getWeight());
            con.preparedStatement.setInt(4, getPlayerModel().getAge());
            con.preparedStatement.setFloat(5, getPlayerModel().getSpeed());
            con.preparedStatement.setString(6, getPlayerModel().getMedical_status());
            con.preparedStatement.setString(7, getPlayerModel().getMood());
            con.preparedStatement.setInt(8, getPlayerModel().getHunger());
            con.preparedStatement.setBoolean(9, getPlayerModel().isAtletic());
            con.preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            con.closeConnection();
            return success;
        }
    }

    public boolean delete(String code) {
        boolean success = false;
        Connection con = new Connection();
        try {
            con.openConnection();
            con.preparedStatement = con.dbConnection.prepareStatement("delete from player where player_id  = ? ");
            con.preparedStatement.setString(1, code);
            con.preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnection();
            return success;
        }
    }

    public boolean update() {
        boolean success = false;
        Connection con = new Connection();
        try {
            con.openConnection();
            con.preparedStatement = con.dbConnection.prepareStatement(
                    "update player set player_name = ?, weight = ?, age = ?, speed = ?, medical_status = ?, mood = ?, hunger = ?, atletic = ? where  player_id = ? ; ");
            con.preparedStatement.setString(1, getPlayerModel().getPlayer_id());
            con.preparedStatement.setString(2, getPlayerModel().getPlayer_name());
            con.preparedStatement.setInt(3, getPlayerModel().getWeight());
            con.preparedStatement.setInt(4, getPlayerModel().getAge());
            con.preparedStatement.setFloat(5, getPlayerModel().getSpeed());
            con.preparedStatement.setString(6, getPlayerModel().getMedical_status());
            con.preparedStatement.setString(7, getPlayerModel().getMood());
            con.preparedStatement.setInt(8, getPlayerModel().getHunger());
            con.preparedStatement.setBoolean(9, getPlayerModel().isAtletic());
            con.preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            con.closeConnection();
            return success;
        }
    }
//lookup

    public ObservableList<PlayerModel> LookUp(String fld, String dt) {
        try {
            ObservableList<PlayerModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select player_id, player_name, weight, age, speed, medical_status, mood, hunger, atletic from player where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                PlayerModel pm = new PlayerModel();
                pm.setPlayer_id(rs.getString("player_id"));
                pm.setPlayer_name(rs.getString("player_name"));
                pm.setWeight(rs.getInt("weight"));
                pm.setAge(rs.getInt("age"));
                pm.setSpeed(rs.getFloat("speed"));
                pm.setMedical_status(rs.getString("medical_status"));
                pm.setMood(rs.getString("mood"));
                pm.setHunger(rs.getInt("hunger"));
                pm.setAtletic(rs.getBoolean("atletic"));
                tableData.add(pm);
                i++;
            }
            con.closeConnection();
            return tableData;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
