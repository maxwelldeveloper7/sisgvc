/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.CidadeBean;
import java.sql.PreparedStatement;
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
public class CidadeDAO extends GenericDAO{

    private PreparedStatement pstm = null;

    public boolean inserir(CidadeBean c){
        String sql = "INSERT INTO cidades (no_cidade, uf) VALUES (?,?)";
        return executeCommand(sql, c.getCidade(), c.getUf());
    }

    public boolean alterar(CidadeBean c){
        String sql = "UPDATE cidades SET no_cidade=?, uf=? WHERE id=?";
        return executeCommand(sql, c.getCidade(), c.getUf(), c.getId());
    }

    public void excluir(CidadeBean c){
        String sql = "DELETE FROM cidades WHERE id = ?";
        executeCommand(sql, c.getId());
    }

    private ResultSet rs = null;

    public List<CidadeBean> listar(){
        List<CidadeBean> cidades = new ArrayList<CidadeBean>();
        String sql = "SELECT * FROM cidades ORDER BY no_cidade ASC";
        CidadeBean c;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                c = new CidadeBean();
                c.setId(rs.getInt("id"));
                c.setCidade(rs.getString("no_cidade"));
                c.setUf(rs.getString("uf"));
                cidades.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar cidades.");
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cidades;
    }

    public List<CidadeBean> listar(String str){
        List<CidadeBean> cidades = new ArrayList<CidadeBean>();
        String sql = "SELECT * FROM cidades where no_cidade like ? ORDER BY no_cidade ASC";
        CidadeBean c;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, str+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                c = new CidadeBean();
                c.setId(rs.getInt("id"));
                c.setCidade(rs.getString("no_cidade"));
                c.setUf(rs.getString("uf"));
                cidades.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar cidades.");
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cidades;
    }
}
