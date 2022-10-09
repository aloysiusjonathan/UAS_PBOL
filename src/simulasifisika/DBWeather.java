/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBWeather {
    private WeatherModel dt = new WeatherModel();

    public WeatherModel getWeatherModel() {
        return (dt);
    }

    public void setWeatherModel(WeatherModel wm) {
        dt = wm;
    }

    public ObservableList<WeatherModel> Load() {
        try {
            ObservableList<WeatherModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select weather_id, weather_name, weather_effect, wind_speed, wind_direction from weather");
            int i = 1;
            while (rs.next()) {
                WeatherModel wm = new WeatherModel();
                wm.setWeather_id(rs.getString("weather_id"));
                wm.setWeather_name(rs.getString("weather_name"));
                wm.setWeather_effect(rs.getFloat("weather_effect"));
                wm.setWind_speed(rs.getInt("wind_speed"));
                wm.setWind_direction(rs.getInt("wind_direction"));

                tableData.add(wm);
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
                    "select count(*) as total from weather where weather_id = '" + code + "'");
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
                    "insert into weather (weather_id, weather_name, weather_effect, wind_speed, wind_direction) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getWeatherModel().getWeather_id());
            con.preparedStatement.setString(2, getWeatherModel().getWeather_name());
            con.preparedStatement.setFloat(3, getWeatherModel().getWeather_effect());
            con.preparedStatement.setInt(4, getWeatherModel().getWind_speed());
            con.preparedStatement.setInt(5, getWeatherModel().getWind_direction());
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
                    "delete from weather where weather_id  = ? ");
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
                    "update weather set weather_name = ?, weather_effect = ?, wind_speed = ?, wind_direction = ?  where  weather_id = ? ; ");
            con.preparedStatement.setString(1, getWeatherModel().getWeather_id());
            con.preparedStatement.setString(2, getWeatherModel().getWeather_name());
            con.preparedStatement.setFloat(3, getWeatherModel().getWeather_effect());
            con.preparedStatement.setInt(4, getWeatherModel().getWind_speed());
            con.preparedStatement.setInt(5, getWeatherModel().getWind_direction());
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
    public ObservableList<WeatherModel> LookUp(String fld, String dt) {
        try {
            ObservableList<WeatherModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnection();
            con.statement = con.dbConnection.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select weather_id, weather_name, weather_effect, wind_speed, wind_direction from weather where " + fld + " like '%" + dt + "%'");
            int i = 1;
            while (rs.next()) {
                WeatherModel wm = new WeatherModel();
                wm.setWeather_id(rs.getString("weather_id"));
                wm.setWeather_name(rs.getString("weather_name"));
                wm.setWeather_effect(rs.getFloat("weather_effect"));
                wm.setWind_speed(rs.getInt("wind_speed"));
                wm.setWind_direction(rs.getInt("wind_direction"));
                tableData.add(wm);
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
