/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.StatusVendaBean;
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
public class StatusPedidoDAO extends GenericDAO{
/*
    public boolean inserir(MedidaBean medida){
        String sql = "INSERT INTO medidas(no_medida) VALUES (?)";
        return executeCommand(sql, medida.getMedida());
    }

    public boolean alterar(MedidaBean medida){
        String sql = "UPDATE medidas SET no_medida=? WHERE id=?";
        return executeCommand(sql, medida.getMedida(), medida.getId());
    }

    public void excluir(MedidaBean medida){
        String sql = "DELETE FROM medidas WHERE id = ?";
        executeCommand(sql, medida.getId());
    }*/

    private ResultSet rs = null;

    public List<StatusVendaBean> listar(){
        List<StatusVendaBean> medias = new ArrayList<StatusVendaBean>();
        String sql = "SELECT * FROM status_vendas ORDER BY id ASC";
        StatusVendaBean s;
        try {
            rs = executeQuery(sql);
            System.out.println("listando status...");
            while (rs.next()) {
                s = new StatusVendaBean();
                s.setId(rs.getInt("id"));
                s.setStatus(rs.getString("st_venda"));
                medias.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medias;
    }
/*
    public MedidaBean getMedida(Integer id){
        String sql = "SELECT * FROM medidas WHERE id = ?";
        MedidaBean medida = null;
        try {
            rs = executeQuery(sql, id);
            while (rs.next()) {
                medida = new MedidaBean();
                medida.setId(rs.getInt("id"));
                medida.setMedida(rs.getString("no_medida"));
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao buscar medida.");
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
                fecharPrepareStatement();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medida;
    }*/
}
