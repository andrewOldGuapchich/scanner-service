package com.andrew.scannerservice.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(
                    "jdbc:postgresql://94.241.139.199:5432/allur",
                    "andrew",
                    "andrew5525613"
            );
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
