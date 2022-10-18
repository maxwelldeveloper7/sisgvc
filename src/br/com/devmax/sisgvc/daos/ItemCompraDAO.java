/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.CompraBean;
import br.com.devmax.sisgvc.modelo.ItemCompraBean;
import br.com.devmax.sisgvc.modelo.MedidaBean;
import br.com.devmax.sisgvc.modelo.ProdutoBean;
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
public class ItemCompraDAO extends GenericDAO {

    public void inserir(ItemCompraBean item) {
        String sql = "SELECT insere_item_compra(?, ?, ?, ?)";
        executeProcedure(sql, item.getCompra().getId(), item.getProduto().getId(), item.getQuantidade(), item.getValorUnitario());
    }

    public boolean alterar(ItemCompraBean item) {
        String sql = "SELECT altera_item_compra(?, ?, ?, ?, ?)";
        return executeProcedure(sql, item.getCompra().getId(), item.getProduto().getId(), item.getQuantidade(), item.getValorUnitario(), item.getId());
    }

    public void excluir(ItemCompraBean item) {
        String sql = "SELECT remove_item_compra(?, ?)";
        executeProcedure(sql, item.getProduto().getId(), item.getId());
    }
    private ResultSet rs = null;

    public List<ItemCompraBean> listar(CompraBean compra) {
        List<ItemCompraBean> itens = new ArrayList<ItemCompraBean>();
        String sql = "SELECT * FROM vw_itens_compra WHERE cd_compra = ?";
        ItemCompraBean item;
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql, compra.getId());
            System.out.println("listando Itens de compra...");
            while (rs.next()) {
                item = new ItemCompraBean();
                item.setId(rs.getLong("id"));
                produto = new ProdutoBean();
                produto.setId(rs.getInt("cd_produto"));
                produto.setProduto(rs.getString("no_produto"));
                medida = new MedidaBean();
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlCompra(rs.getBigDecimal("vl_compra"));
                item.setProduto(produto);
                item.setQuantidade(rs.getInt("qt_produto"));
                item.setValorUnitario(rs.getBigDecimal("vl_unitario"));
                itens.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar Itens de compra.");
            JOptionPane.showMessageDialog(null, "Falha ao listar itens de compra.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(ItemCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                System.exit(0);
                Logger.getLogger(ItemCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return itens;
    }
}
