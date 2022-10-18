/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.PedidoPorVendedorBean;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class PedidoFuncionarioDAO extends GenericDAO {

    
    private ResultSet rs = null;
    private PreparedStatement pstm = null;

    public List<PedidoPorVendedorBean> listarIntervaloDatas(Date dtInicial, Date dtFinal) {
        List<PedidoPorVendedorBean> vendas = new ArrayList<PedidoPorVendedorBean>();
        String sql = "select "
                + "id_vendedor,"
                + "cd_vendedor,"
                + "vendedor,"
                + "sum(consignado) as consignados,"
                + "sum(vl_consignado) as vl_consignado,"
                + "sum(pendente) as pendentes,"
                + "sum(vl_pendente) as vl_pendente,"
                + "sum(concluido) as concluidos,"
                + "sum(vl_concluido) as vl_concluido,"
                + "sum(devolvido) as devolvidos,"
                + "sum(vl_devolvido) as vl_devolvido,"
                + "sum(perdido) as perdidos,"
                + "sum(vl_perdido) as vl_perdido,"
                + "sum(total) as total_pedidos,"
                + "sum(vl_pedido) as vl_pedidos,"
                + "sum(recebido) as recebidos,"
                + "sum(desconto) as descontos "
                + "from "
                + "vw_status_vendas "
                + "where dt_vencimento between ? and ? "
                + "group by id_vendedor, cd_vendedor, vendedor";
        PedidoPorVendedorBean venda;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setDate(1, dtInicial);
            pstm.setDate(2, dtFinal);
            rs = pstm.executeQuery();
            System.out.println("listando dados de vendas vendas...");
            while (rs.next()) {
                venda = new PedidoPorVendedorBean();
                venda.setCodVendedor(rs.getInt("id_vendedor"));
                venda.setVendedor(rs.getString("vendedor"));
                venda.setConsignado(rs.getInt("consignados"));
                venda.setVlConsignado(rs.getBigDecimal("vl_consignado"));
                venda.setPendente(rs.getInt("pendentes"));
                venda.setVlPendente(rs.getBigDecimal("vl_pendente"));
                venda.setConcluido(rs.getInt("concluidos"));
                venda.setVlConcluido(rs.getBigDecimal("vl_concluido"));
                venda.setDevolvido(rs.getInt("devolvidos"));
                venda.setVlDevolvido(rs.getBigDecimal("vl_devolvido"));
                venda.setPerdido(rs.getInt("perdidos"));
                venda.setVlPerdido(rs.getBigDecimal("vl_perdido"));
                venda.setTotal(rs.getInt("total_pedidos"));
                venda.setVlTotal(rs.getBigDecimal("vl_pedidos"));
                venda.setVlDesconto(rs.getBigDecimal("descontos"));
                venda.setVlRecebido(rs.getBigDecimal("recebidos"));
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar vendas.");
            JOptionPane.showMessageDialog(null, "Falha ao listar vendas.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(PedidoFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage());
                Logger.getLogger(PedidoFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vendas;
    }
}
