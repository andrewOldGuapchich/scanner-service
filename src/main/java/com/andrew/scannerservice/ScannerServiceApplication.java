package com.andrew.scannerservice;

import com.andrew.scannerservice.connection.PostgreSQLConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class ScannerServiceApplication {

    public static void main(String[] args) {
        /*Connection connection = PostgreSQLConnection.getConnection();
        String query = "select * from box";
        assert connection != null;
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                System.out.println(set.getString(1) + " " + set.getString(2));
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }*/
        SpringApplication.run(ScannerServiceApplication.class, args);
    }
}
