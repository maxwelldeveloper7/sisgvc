/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

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
public class RotinaDAO extends GenericDAO{

    public void inserir(RotinaBean rotina){
        String sql = "INSERT INTO rotinas(no_rotina)VALUES (?)";
        executeCommand(sql, rotina.getNomeRotina());
    }

    public void alterar(RotinaBean rotina){
        String sql = "UPDATE rotinas SET no_rotina=? WHERE id = ?";
        executeCommand(sql, rotina.getNomeRotina(), rotina.getId());
    }

    public void excluir(RotinaBean rotina){
        String sql = "DELETE FROM rotinas WHERE id = ?";
        executeCommand(sql, rotina.getId());
    }

    private ResultSet rs = null;

    public List<RotinaBean> listar(){
        List<RotinaBean> rotinas = new ArrayList<RotinaBean>();
        String sql = "SELECT * FROM rotinas ORDER BY no_rotina ASC";
        RotinaBean rotina;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                rotina = new RotinaBean();
                rotina.setId(rs.getInt("id"));
                rotina.setNomeRotina(rs.getString("no_rotina"));
                rotinas.add(rotina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rotinas;
    }

    public RotinaBean getRotina(Integer id){
        String sql = "SELECT * FROM rotinas WHERE id = ?";
        RotinaBean rotina = null;
        try {
            rs = executeQuery(sql, id);
            while (rs.next()) {
                rotina = new RotinaBean();
                rotina.setId(rs.getInt("id"));
                rotina.setNomeRotina(rs.getString("no_rotina"));
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
        return rotina;
    }
}
