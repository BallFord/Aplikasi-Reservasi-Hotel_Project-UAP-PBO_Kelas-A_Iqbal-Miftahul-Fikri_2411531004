package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/hotel_db";
                String user = "root";
                String password = ""; 
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}