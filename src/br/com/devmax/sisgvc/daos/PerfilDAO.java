/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.PerfilBean;
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
public class PerfilDAO extends GenericDAO{

    public void inserir(PerfilBean perfil){
        String sql = "SELECT INSERE_PERFIL(?,?)";
        executeProcedure(sql, perfil.getNoPerfil(), perfil.getDsPerfil());
    }

    public void alterar(PerfilBean perfil){
        String sql = "UPDATE perfis SET no_perfil=?, ds_perfil=? WHERE id = ?";
        executeCommand(sql, perfil.getNoPerfil(), perfil.getDsPerfil(), perfil.getId());
    }

    public void excluir(PerfilBean perfil){
        String sql = "DELETE FROM perfis WHERE id = ?";
        executeCommand(sql, perfil.getId());
    }

    private ResultSet rs = null;

    public List<PerfilBean> listar(){
        List<PerfilBean> perfis = new ArrayList<PerfilBean>();
        String sql = "SELECT * FROM perfis ORDER BY no_perfil ASC";
        PerfilBean perfil;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                perfil = new PerfilBean();
                perfil.setId(rs.getInt("id"));
                perfil.setNoPerfil(rs.getString("no_perfil"));
                perfil.setDsPerfil(rs.getString("ds_perfil"));
                perfis.add(perfil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return perfis;
    }

    public PerfilBean getPerfil(Integer id){
        String sql = "SELECT * FROM perfis WHERE id = ?";
        PerfilBean perfil = null;
        try {
            rs = executeQuery(sql, id);
            while (rs.next()) {
                perfil = new PerfilBean();
                perfil.setId(rs.getInt("id"));
                perfil.setNoPerfil(rs.getString("no_perfil"));
                perfil.setDsPerfil(rs.getString("ds_perfil"));
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao buscar perfil.");
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return perfil;
    }
}
