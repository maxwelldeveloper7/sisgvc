/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.PerfilBean;
import br.com.devmax.sisgvc.modelo.PrivilegioBean;
import br.com.devmax.sisgvc.modelo.RotinaBean;
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
public class PrivilegioDAO extends GenericDAO{

    public void inserir(PrivilegioBean privilegio){
        String sql = "INSERT INTO privilegios(cd_rotina, cd_perfil, acessar)VALUES (?,?,?)";
        executeCommand(sql, privilegio.getRotina().getId(), privilegio.getPerfil().getId(), privilegio.getAcessar());
    }

    public void alterar(PrivilegioBean privilegio){
        String sql = "UPDATE privilegios SET cd_rotina=?, cd_perfil=?, acessar=? WHERE id = ?";
        executeCommand(sql, privilegio.getRotina().getId(), privilegio.getPerfil().getId(), privilegio.getAcessar(), privilegio.getId());
    }

    public void excluir(PrivilegioBean privilegio){
        String sql = "DELETE FROM privilegios WHERE id = ?";
        executeCommand(sql, privilegio.getId());
    }

    private ResultSet rs = null;

    public List<PrivilegioBean> listar(){
        List<PrivilegioBean> privilegios = new ArrayList<PrivilegioBean>();
        String sql = "SELECT * FROM vw_privilegios_by_perfil";
        PrivilegioBean privilegio;
        RotinaBean rotina;
        PerfilBean perfil;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                privilegio = new PrivilegioBean();
                rotina = new RotinaBean();
                perfil = new PerfilBean();
                privilegio.setId(rs.getInt("id"));
                rotina.setId(rs.getInt("cd_rotina"));
                rotina.setNomeRotina(rs.getString("no_rotina"));
                privilegio.setRotina(rotina);
                perfil.setId(rs.getInt("cd_perfil"));
                perfil.setNoPerfil(rs.getString("no_perfil"));
                perfil.setDsPerfil(rs.getString("ds_perfil"));
                privilegio.setPerfil(perfil);
                privilegio.setAcessar(rs.getBoolean("acessar"));
                privilegio.setAdicionar(rs.getBoolean("adicionar"));
                privilegio.setEditar(rs.getBoolean("editar"));
                privilegio.setExcluir(rs.getBoolean("excluir"));
                privilegios.add(privilegio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrivilegioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(PrivilegioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return privilegios;
    }

    public List<PrivilegioBean> listarPorPerfil(Integer codigoPerfil){
        List<PrivilegioBean> privilegios = new ArrayList<PrivilegioBean>();
        String sql = "SELECT * FROM vw_privilegios_by_perfil WHERE cd_perfil = ?";
        PrivilegioBean privilegio;
        RotinaBean rotina;
        PerfilBean perfil;
        try {
            rs = executeQuery(sql, codigoPerfil);
            while (rs.next()) {
                privilegio = new PrivilegioBean();
                rotina = new RotinaBean();
                perfil = new PerfilBean();
                privilegio.setId(rs.getInt("id"));
                rotina.setId(rs.getInt("cd_rotina"));
                rotina.setNomeRotina(rs.getString("no_rotina"));
                privilegio.setRotina(rotina);
                perfil.setId(rs.getInt("cd_perfil"));
                perfil.setNoPerfil(rs.getString("no_perfil"));
                perfil.setDsPerfil(rs.getString("ds_perfil"));
                privilegio.setPerfil(perfil);
                privilegio.setAcessar(rs.getBoolean("acessar"));
                privilegio.setAdicionar(rs.getBoolean("adicionar"));
                privilegio.setEditar(rs.getBoolean("editar"));
                privilegio.setExcluir(rs.getBoolean("excluir"));
                privilegios.add(privilegio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrivilegioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(PrivilegioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return privilegios;
    }

    public PrivilegioBean getPrivilegio(Integer id){
        String sql = "SELECT * FROM vw_privilegios_by_perfil WHERE id = ?";
        PrivilegioBean privilegio = null;
        RotinaBean rotina;
        PerfilBean perfil;
        try {
            rs = executeQuery(sql, id);

            while (rs.next()) {
                privilegio = new PrivilegioBean();
                rotina = new RotinaBean();
                perfil = new PerfilBean();
                privilegio.setId(rs.getInt("id"));
                rotina.setId(rs.getInt("cd_rotina"));
                rotina.setNomeRotina(rs.getString("no_rotina"));
                privilegio.setRotina(rotina);
                perfil.setId(rs.getInt("cd_perfil"));
                perfil.setNoPerfil(rs.getString("no_perfil"));
                perfil.setDsPerfil(rs.getString("ds_perfil"));
                privilegio.setPerfil(perfil);
                privilegio.setAcessar(rs.getBoolean("acessar"));
                privilegio.setAdicionar(rs.getBoolean("adicionar"));
                privilegio.setEditar(rs.getBoolean("editar"));
                privilegio.setExcluir(rs.getBoolean("excluir"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return privilegio;
    }
}
