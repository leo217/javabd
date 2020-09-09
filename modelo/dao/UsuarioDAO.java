/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import usuario.modelo.bean.UsuarioBean;

/**
 *
 * @author Leonardo
 */
public class UsuarioDAO {

    public void insere(UsuarioBean usu){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO usuario(id, login, senha, status, tipo)VALUES(?,?,?,?,?)");
            stmt.setInt(1, usu.getId());
            stmt.setString(2, usu.getLogin());
            stmt.setString(3, usu.getSenha());
            stmt.setString(4, usu.getStatus());
            stmt.setString(5, usu.getTipo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salve com sucesso.");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar:"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public UsuarioBean busca(UsuarioBean usu){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT login, status, tipo FROM usuario WHERE id = ?");
            stmt.setInt(1, usu.getId());
            rs = stmt.executeQuery();
            while(rs.next()){
                usu.setLogin(rs.getString("login"));
                usu.setStatus(rs.getString("status"));
                usu.setTipo(rs.getString("tipo"));
              
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao realizar busca:", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }
        
        return usu;
        
    }
    
    public boolean valida(UsuarioBean usu){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean valida = false;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, usu.getSenha());
            rs = stmt.executeQuery();
            if(rs.next()){
                valida = true;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Usuário e senha não encontrados.");
            throw new RuntimeException("Erro ao recuperar usuário.", ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return valida;
    }
    
    public void atualizar(UsuarioBean usu){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE usuario SET login = ?, senha = ?, status = ?, tipo = ? WHERE id = ?");
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, usu.getSenha());
            stmt.setString(3, usu.getStatus());
            stmt.setString(4, usu.getTipo());
            stmt.setInt(5, usu.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados alterados:");
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar dados", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            
        }
    }
    
    public void deletar(UsuarioBean usu){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, usu.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso.");
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao deletar usuário", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<UsuarioBean> listar(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UsuarioBean> usus = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT id, login, senha, status, tipo FROM usuario");
            rs = stmt.executeQuery();
            while(rs.next()){
                UsuarioBean usu = new UsuarioBean(rs.getInt("id"), rs.getString("login"), rs.getString("senha"), rs.getString("status"), rs.getString("tipo"));
                usus.add(usu);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao retornar os usuários.", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usus;
    }
    

}
