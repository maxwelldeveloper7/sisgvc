/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.ItemPedidoBean;
import br.com.devmax.sisgvc.modelo.MedidaBean;
import br.com.devmax.sisgvc.modelo.ProdutoBean;
import br.com.devmax.sisgvc.modelo.PedidoBean;
import java.sql.CallableStatement;
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
public class ItemPedidoDAO extends GenericDAO {

    public void inserir(ItemPedidoBean item) {
        String sql = "SELECT insere_item_pedido(?, ?, ?, ?, ?, ?, ?)";
        executeProcedure(sql, item.getProduto().getId(), item.getConsignado(), item.getVendido(), item.getDevolvido(), item.getVenda().getId(), item.getValorUnitario(), item.getValorCusto());
    }

    public boolean alterar(ItemPedidoBean item) {
        String sql = "SELECT altera_item_pedido(?, ?, ?, ?, ?, ?, ?, ?)";
        return executeProcedure(sql, item.getProduto().getId(), item.getConsignado(), item.getVendido(), item.getDevolvido(), item.getVenda().getId(), item.getValorUnitario(), item.getValorCusto(), item.getId());
    }

    public void excluir(ItemPedidoBean item) {
        String sql = "SELECT remove_item_pedido(?, ?)";
        executeProcedure(sql, item.getProduto().getId(), item.getId());
    }
    private ResultSet rs = null;
    private CallableStatement cstm = null;

    public void atualizarItensDeVenda(List<ItemPedidoBean> l, PedidoBean p) {
        String sql = "SELECT altera_item_pedido(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            cstm = ConexaoPostgres.conectar().prepareCall(sql);
            for (int i = 0; i < l.size(); i++) {
                cstm.setInt(1, l.get(i).getProduto().getId());
                cstm.setInt(2, l.get(i).getConsignado());
                cstm.setInt(3, l.get(i).getVendido());
                cstm.setInt(4, l.get(i).getDevolvido());
                cstm.setInt(5, p.getId());
                cstm.setBigDecimal(6, l.get(i).getValorUnitario());
                cstm.setBigDecimal(7, l.get(i).getValorCusto());
                cstm.setLong(8, l.get(i).getId());
                cstm.addBatch();
                alterar(l.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoPostgres.desconectar();
        }
    }

    public List<ItemPedidoBean> listar(PedidoBean venda) {
        List<ItemPedidoBean> itens = new ArrayList<ItemPedidoBean>();
        String sql = "SELECT * FROM vw_itens_venda WHERE cd_venda = ?";
        ItemPedidoBean item;
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql, venda.getId());
            while (rs.next()) {
                item = new ItemPedidoBean();
                item.setId(rs.getLong("id"));
                produto = new ProdutoBean();
                produto.setId(rs.getInt("cd_produto"));
                produto.setProduto(rs.getString("no_produto"));
                medida = new MedidaBean();
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlVenda(rs.getBigDecimal("vl_venda"));
                item.setProduto(produto);
                item.setConsignado(rs.getInt("consignado"));
                item.setVendido(rs.getInt("vendido"));
                item.setDevolvido(rs.getInt("devolvido"));
                item.setValorUnitario(rs.getBigDecimal("vl_unitario"));
                item.setValorCusto(rs.getBigDecimal("vl_custo"));
                itens.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar Itens de venda.");
            JOptionPane.showMessageDialog(null, "Falha ao listar itens de venda.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return itens;
    }

    public void transferirConsignadosParaVendidos(List<ItemPedidoBean> itensVenda) {
        try {
            
            String sqlup = "update itens_pedido set consignado = ?, vendido = ? where id = ?";
            PreparedStatement pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

            for (int i = 0; i < itensVenda.size(); i++) {
                pstm.setInt(1, 0);
                pstm.setInt(2, itensVenda.get(i).getConsignado() + itensVenda.get(i).getVendido());
                pstm.setLong(3, itensVenda.get(i).getId());

                pstm.addBatch();
            }
            pstm.executeBatch();
            ConexaoPostgres.commit();
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharPrepareStatement();
            ConexaoPostgres.desconectar();
        }
    }
    
    public void transferirConsignadosParaDevolvidos(List<ItemPedidoBean> itensVenda) {
        try {
            
            String sqlup = "update itens_pedido set consignado = ?, devolvido = ? where id = ?";
            PreparedStatement pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

            for (int i = 0; i < itensVenda.size(); i++) {
                pstm.setInt(1, 0);
                pstm.setInt(2, itensVenda.get(i).getConsignado());
                pstm.setLong(3, itensVenda.get(i).getId());

                pstm.addBatch();
            }
            pstm.executeBatch();
            ConexaoPostgres.commit();
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharPrepareStatement();
            ConexaoPostgres.desconectar();
        }
    }
}
