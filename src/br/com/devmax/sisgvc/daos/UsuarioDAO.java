/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.PerfilBean;
import br.com.devmax.sisgvc.modelo.UsuarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxwell
 */
public class UsuarioDAO extends GenericDAO{

    public boolean inserir(UsuarioBean usuario){
        String sql = "INSERT INTO usuarios(cd_perfil, no_usuario, login, senha)VALUES (?,?,?,?)";
        return executeCommand(sql, usuario.getPerfil().getId(), usuario.getNoUsuario(), usuario.getLogin(), usuario.getSenha());
    }

    public boolean alterar(UsuarioBean usuario){
        String sql = "UPDATE usuarios SET cd_perfil = ?, no_usuario = ?, login = ?, senha = ?, ativo = ? WHERE id = ?";
        return executeCommand(sql, usuario.getPerfil().getId(), usuario.getNoUsuario(), usuario.getLogin(), usuario.getSenha(), usuario.isAtivo(), usuario.getId());
    }

    public void excluir(UsuarioBean usuario){
        String sql = "DELETE FROM usuarios WHERE id = ?";
        executeCommand(sql, usuario.getId());
    }

    private ResultSet rs = null;

    @SuppressWarnings("UnusedAssignment")
    public List<UsuarioBean> listar(){
        List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
        String sql = "SELECT * FROM usuarios ORDER BY no_usuario ASC";
        UsuarioBean usuario;
        PerfilBean perfil;
        PerfilDAO perfilDao;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                usuario = new UsuarioBean();
                perfil = new PerfilBean();
                usuario.setId(rs.getInt("id"));
                perfilDao = new PerfilDAO();
                Integer cd_perfil = rs.getInt("cd_perfil");
                perfil = perfilDao.getPerfil(cd_perfil);
                usuario.setPerfil(perfil);
                usuario.setNoUsuario(rs.getString("no_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar usuários.");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }
}
