package com.smallchange.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static Connection connection = null;

    private ConnectionFactory() {}

    public static Connection getConnection() throws ClassNotFoundException {
        if(connection == null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("dbConfig");
            String driver = resourceBundle.getString("driver");
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");

            Class.forName(driver);

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
}
