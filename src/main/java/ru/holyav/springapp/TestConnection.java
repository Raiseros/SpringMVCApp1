package ru.holyav.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.holyav.springapp.entity.Student;
import ru.holyav.springapp.mapper.StudentMapper;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestConnection {


    public static void main(String[] args) {


        String jdbcUrl = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&amp&serverTimezone=UTC";
        String user = "root";
        String pass = "ypzpp45xs";
        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);


            System.out.println("Connecting successful!!!");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
