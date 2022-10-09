/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBTerrain {
    private TerrainModel dt = new TerrainModel();

    public TerrainModel getTerrainModel() {
        return (dt);
    }

    public void setTerrainModel(TerrainModel tm) {
        dt = tm;
    }

    public ObservableList<TerrainModel> Load() {
        try {
            ObservableList<TerrainModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select terrain_id, terrain_name, terrain_effect, slope_level, gravity from terrain");
            int i = 1;
            while (rs.next()) {
                TerrainModel tm = new TerrainModel();
                tm.setTerrain_id(rs.getString("terrain_id"));
                tm.setTerrain_name(rs.getString("terrain_name"));
                tm.setTerrain_effect(rs.getFloat("terrain_effect"));
                tm.setSlope_level(rs.getInt("slope_level"));
                tm.setGravity(rs.getInt("gravity"));

                tableData.add(tm);
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
                    "select count(*) as total from terrain where terrain_id = '" + code + "'");
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
                    "insert into terrain (terrain_id, terrain_name, terrain_effect, slope_level, gravity) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getTerrainModel().getTerrain_id());
            con.preparedStatement.setString(2, getTerrainModel().getTerrain_name());
            con.preparedStatement.setFloat(3, getTerrainModel().getTerrain_effect());
            con.preparedStatement.setInt(4, getTerrainModel().getSlope_level());
            con.preparedStatement.setInt(5, getTerrainModel().getGravity());
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
            con.openConnection();;
            con.preparedStatement = con.dbConnection.prepareStatement(
                    "delete from terrain where terrain_id  = ? ");
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
                    "update terrain set terrain_name = ?, terrain_effect = ?, slope_level = ?, gravity = ?  where  terrain_id = ? ; ");
            con.preparedStatement.setString(1, getTerrainModel().getTerrain_id());
            con.preparedStatement.setString(2, getTerrainModel().getTerrain_name());
            con.preparedStatement.setFloat(3, getTerrainModel().getTerrain_effect());
            con.preparedStatement.setInt(4, getTerrainModel().getSlope_level());
            con.preparedStatement.setInt(5, getTerrainModel().getGravity());
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
    public ObservableList<TerrainModel> LookUp(String fld, String dt) {
        try {
            ObservableList<TerrainModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select terrain_id, terrain_name, terrain_effect, slope_level, gravity from terrain where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                TerrainModel tm = new TerrainModel();
                tm.setTerrain_id(rs.getString("terrain_id"));
                tm.setTerrain_name(rs.getString("terrain_name"));
                tm.setTerrain_effect(rs.getFloat("terrain_effect"));
                tm.setSlope_level(rs.getInt("slope_level"));
                tm.setGravity(rs.getInt("gravity"));
                tableData.add(tm);
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
