package org.carrental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/carrental";
    private static final String USER = "root";
    private static final String PASS = "tabish";
    Connection conn;

    BaseDAO(){
        //create connection
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create a database connection.", e);
        }
    }
}
