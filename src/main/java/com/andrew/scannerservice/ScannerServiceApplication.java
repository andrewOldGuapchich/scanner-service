package com.andrew.scannerservice;

import com.andrew.scannerservice.connection.PostgreSQLConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class ScannerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScannerServiceApplication.class, args);
    }
}
