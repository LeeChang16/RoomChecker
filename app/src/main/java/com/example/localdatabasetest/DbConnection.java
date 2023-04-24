package com.example.localdatabasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.100.33:3306/testdatabase";
            String user = "test";
            String password = "test";
            System.out.println("woring");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("woring2");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}