package org.ex.o2oneToOneUni.jdbcTestConnection;


import java.sql.Connection;
import java.sql.DriverManager;


public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useUnicode=true&serverTimezone=UTC&useSSL=false";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            //  Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

