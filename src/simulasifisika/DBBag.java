/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBBag {
    private BagModel dt = new BagModel();

    public BagModel getBagModel() {
        return (dt);
    }

    public void setBagModel(BagModel bm) {
        dt = bm;
    }

    public ObservableList<BagModel> Load() {
        try {
            ObservableList<BagModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select bag_id, bag_name, bag_weight from bag");
            int i = 1;
            while (rs.next()) {
                BagModel bm = new BagModel();
                bm.setBag_id(rs.getString("bag_id"));
                bm.setBag_name(rs.getString("bag_name"));
                bm.setBag_weight(rs.getInt("bag_weight"));

                tableData.add(bm);
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
                    "select count(*) as total from bag where bag_id = '" + code + "'");
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
                    "insert into bag (bag_id, bag_name, bag_weight) values (?,?,?)");
            con.preparedStatement.setString(1, getBagModel().getBag_id());
            con.preparedStatement.setString(2, getBagModel().getBag_name());
            con.preparedStatement.setInt(3, getBagModel().getBag_weight());
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
                    "delete from bag where bag_id  = ? ");
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
                    "update bag set bag_name = ?, bag_weight = ? where  bag_id = ? ; ");
            con.preparedStatement.setString(1, getBagModel().getBag_id());
            con.preparedStatement.setString(2, getBagModel().getBag_name());
            con.preparedStatement.setInt(3, getBagModel().getBag_weight());
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
    public ObservableList<BagModel> LookUp(String fld, String dt) {
        try {
            ObservableList<BagModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select bag_id, bag_name, bag_weight from bag where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                BagModel bm = new BagModel();
                bm.setBag_id(rs.getString("bag_id"));
                bm.setBag_name(rs.getString("bag_name"));
                bm.setBag_weight(rs.getInt("bag_weight"));
                tableData.add(bm);
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
