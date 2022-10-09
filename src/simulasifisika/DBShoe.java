/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBShoe {
    private ShoeModel dt = new ShoeModel();

    public ShoeModel getShoeModel() {
        return (dt);
    }

    public void setShoeModel(ShoeModel sm) {
        dt = sm;
    }

    public ObservableList<ShoeModel> Load() {
        try {
            ObservableList<ShoeModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select shoe_id, shoe_name, shoe_effect from shoe");
            int i = 1;
            while (rs.next()) {
                ShoeModel sm = new ShoeModel();
                sm.setShoe_id(rs.getString("shoe_id"));
                sm.setShoe_name(rs.getString("shoe_name"));
                sm.setShoe_effect(rs.getFloat("shoe_effect"));

                tableData.add(sm);
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
                    "select count(*) as total from shoe where shoe_id = '" + code + "'");
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
                    "insert into shoe (shoe_id, shoe_name, shoe_effect) values (?,?,?)");
            con.preparedStatement.setString(1, getShoeModel().getShoe_id());
            con.preparedStatement.setString(2, getShoeModel().getShoe_name());
            con.preparedStatement.setFloat(3, getShoeModel().getShoe_effect());
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
                    "delete from shoe where shoe_id  = ? ");
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
                    "update shoe set shoe_name = ?, shoe_effect = ? where  shoe_id = ? ; ");
            con.preparedStatement.setString(1, getShoeModel().getShoe_id());
            con.preparedStatement.setString(2, getShoeModel().getShoe_name());
            con.preparedStatement.setFloat(3, getShoeModel().getShoe_effect());
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
    public ObservableList<ShoeModel> LookUp(String fld, String dt) {
        try {
            ObservableList<ShoeModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select shoe_id, shoe_name, shoe_effect from shoe where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                ShoeModel sm = new ShoeModel();
                sm.setShoe_id(rs.getString("shoe_id"));
                sm.setShoe_name(rs.getString("shoe_name"));
                sm.setShoe_effect(rs.getFloat("shoe_effect"));
                tableData.add(sm);
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
