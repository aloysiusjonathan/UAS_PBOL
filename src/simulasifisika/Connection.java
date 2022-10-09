/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulasifisika;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Connection {
    public java.sql.Connection dbConnection;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public Connection() {
        this.dbConnection = null;
    }
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/player_db?user=root&password=likmi123");
            } catch (Exception e) {
            e.printStackTrace();
            }
    }
    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
