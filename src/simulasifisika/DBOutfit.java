/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBOutfit {
    private OutfitModel dt = new OutfitModel();

    public OutfitModel getOutfitModel() {
        return (dt);
    }

    public void setOutfitModel(OutfitModel om) {
        dt = om;
    }

    public ObservableList<OutfitModel> Load() {
        try {
            ObservableList<OutfitModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select outfit_id, outfit_name, outfit_effect from outfit");
            int i = 1;
            while (rs.next()) {
                OutfitModel om = new OutfitModel();
                om.setOutfit_id(rs.getString("outfit_id"));
                om.setOutfit_name(rs.getString("outfit_name"));
                om.setOutfit_effect(rs.getFloat("outfit_effect"));

                tableData.add(om);
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
                    "select count(*) as total from outfit where outfit_id = '" + code + "'");
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
                    "insert into outfit (outfit_id, outfit_name, outfit_effect) values (?,?,?)");
            con.preparedStatement.setString(1, getOutfitModel().getOutfit_id());
            con.preparedStatement.setString(2, getOutfitModel().getOutfit_name());
            con.preparedStatement.setFloat(3, getOutfitModel().getOutfit_effect());
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
            con.preparedStatement = con.dbConnection.prepareStatement(
                    "delete from outfit where outfit_id  = ? ");
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
                    "update outfit set outfit_name = ?, outfit_effect = ? where  outfit_id = ? ; ");
            con.preparedStatement.setString(1, getOutfitModel().getOutfit_id());
            con.preparedStatement.setString(2, getOutfitModel().getOutfit_name());
            con.preparedStatement.setFloat(3, getOutfitModel().getOutfit_effect());
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
    public ObservableList<OutfitModel> LookUp(String fld, String dt) {
        try {
            ObservableList<OutfitModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select outfit_id, outfit_name, outfit_effect from outfit where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                OutfitModel om = new OutfitModel();
                om.setOutfit_id(rs.getString("outfit_id"));
                om.setOutfit_name(rs.getString("outfit_name"));
                om.setOutfit_effect(rs.getFloat("outfit_effect"));
                tableData.add(om);
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
