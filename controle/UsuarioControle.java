/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controle;

import java.util.ArrayList;
import java.util.List;
import usuario.modelo.bean.UsuarioBean;
import usuario.modelo.dao.UsuarioDAO;

/**
 *
 * @author user
 */
public class UsuarioControle {

    
    public UsuarioBean valida (UsuarioBean usu) {
  
        return usu;
    }

    public UsuarioBean busca (UsuarioBean usu) {
        UsuarioDAO usuDAO = new UsuarioDAO();
        usu = new UsuarioBean(usu.getId());
        usu = usuDAO.busca(usu);
        return usu;
    }
    
    public void insere (UsuarioBean usu) {
        UsuarioDAO usuDAO = new UsuarioDAO();
        usu = new UsuarioBean(usu.getId(), usu.getLogin(), usu.getSenha(), usu.getStatus(), usu.getTipo());
        usuDAO.insere(usu);    
    }

    public UsuarioBean altera (UsuarioBean usu) {
        UsuarioDAO usuDAO = new UsuarioDAO();
        usu = new UsuarioBean(usu.getId(), usu.getLogin(), usu.getSenha(), usu.getStatus(), usu.getTipo());
        usuDAO.atualizar(usu);
        usuDAO.busca(usu);
        return usu;  
    }

    public void exclui (UsuarioBean usu) {
        UsuarioDAO usuDAO = new UsuarioDAO();
        usu = new UsuarioBean(usu.getId());
        usuDAO.deletar(usu);
        
    }
    
    public UsuarioBean validar (UsuarioBean usu){
        UsuarioDAO usuDAO = new UsuarioDAO();
        usu = new UsuarioBean(usu.getLogin(), usu.getSenha());
        
        if(usuDAO.valida(usu)){
             return usu;
        }else{
            return null;
        }
       
    }
    
    public List<UsuarioBean> listar(){
        UsuarioDAO usuDAO = new UsuarioDAO();
        List<UsuarioBean> usu = new ArrayList<>();
        
        for(UsuarioBean u: usuDAO.listar()){
            u.getId();
            u.getLogin();
            u.getStatus();
            u.getTipo();
            usu.add(u);
        }
        
        return usu;
    }

}
