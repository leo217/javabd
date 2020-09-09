/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class ConnectionFactory {
    //Drive de conexão ao Mysql
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbusuario";
    private static final String USER = "root";
    private static final String PASS = "foda2013";
    
    public static Connection getConnection(){
        try {

            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (SQLException ex) {
           throw new RuntimeException("Erro na conexão: ", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
            
        }catch(SQLException e){
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try{
            if(stmt != null){
                stmt.close();
            }
            
        }catch(SQLException e){
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try{
            if(rs != null){
                rs.close();
            }
            
        }catch(SQLException e){
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
    
}
