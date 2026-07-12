package com.bootcamp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    /**
     * Establishes a connection to the database using parameters from the ConfigReader.
     * The calling test method is responsible for closing this connection using try-with-resources.
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        String url = ConfigReader.getProperty("db.url");
        String user = ConfigReader.getProperty("db.username");
        String password = ConfigReader.getProperty("db.password");

        try {
            // Explicitly load the MySQL Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to the database!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
