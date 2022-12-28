/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author yuri-
 */
public class ConnectionFactory {
    
  public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/todoapp";
    public static final String USER="root";
    public static final String PASS ="";
    
    
    


 public static Connection getConnection() { // static n�o precisa instanciar.devolve uma conex�o.
        try {
            Class.forName(DRIVER); // qual o drive que vai fazer a conex�o com o bd. carrega o driver.
            return DriverManager.getConnection(URL, USER, PASS);// realiza a conex�o com os par�metros dados.
        }catch(Exception ex) {
            throw new RuntimeException("Database connection error", ex);            
        }                
    }
  public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
            
        }catch(Exception ex) {
            throw new RuntimeException("Database connection closing error", ex);            
        }                
    }    
   public static void closeConnection(Connection connection,PreparedStatement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            
        }catch(Exception ex) {
            throw new RuntimeException("Database connection closing error", ex);            
        }            
    
}
    public static void closeConnection(Connection connection,PreparedStatement statement,ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        }catch(Exception ex) {
            throw new RuntimeException("Database connection closing error", ex);            
        }            
    
    }
}
    