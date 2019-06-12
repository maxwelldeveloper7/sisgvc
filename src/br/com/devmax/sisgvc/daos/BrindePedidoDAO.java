/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.BrindePedidoBean;
import br.com.devmax.sisgvc.modelo.MedidaBean;
import br.com.devmax.sisgvc.modelo.PedidoBean;
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
public class BrindePedidoDAO extends GenericDAO {

    public void inserir(BrindePedidoBean brinde) {
        String sql = "SELECT insere_brinde_pedido(?, ?, ?, ?, ?)";
        executeProcedure(sql, brinde.getProduto().getId(), brinde.getQuantidade(), brinde.getVenda().getId(), brinde.getValor(), brinde.getTipo());
    }

    public boolean alterar(BrindePedidoBean brinde) {
        String sql = "SELECT altera_brinde_pedido(?, ?, ?, ?, ?, ?)";
        return executeProcedure(sql, brinde.getProduto().getId(), brinde.getQuantidade(), brinde.getVenda().getId(), brinde.getValor(),  brinde.getTipo(), brinde.getId());
    }

    public void excluir(BrindePedidoBean brinde) {
        String sql = "SELECT remove_brinde_pedido(?, ?)";
        executeProcedure(sql, brinde.getProduto().getId(), brinde.getId());
    }
    private ResultSet rs = null;

    public List<BrindePedidoBean> listar(PedidoBean pedido) {
        List<BrindePedidoBean> brindes = new ArrayList<BrindePedidoBean>();
        String sql = "SELECT * FROM vw_brindes_pedido WHERE cd_pedido = ?";
        BrindePedidoBean brinde;
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql, pedido.getId());
            while (rs.next()) {
                brinde = new BrindePedidoBean();
                brinde.setId(rs.getInt("id"));
                produto = new ProdutoBean();
                produto.setId(rs.getInt("cd_produto"));
                produto.setProduto(rs.getString("no_produto"));
                medida = new MedidaBean();
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlCompra(rs.getBigDecimal("vl_custo"));
                produto.setVlVenda(rs.getBigDecimal("vl_venda"));
                brinde.setProduto(produto);
                brinde.setQuantidade(rs.getInt("qt_brinde"));
                brinde.setValor(rs.getBigDecimal("vl_brinde"));
                brinde.setVlTotal(rs.getBigDecimal("vl_total"));
                brinde.setTipo(rs.getInt("tp_brinde"));
                brindes.add(brinde);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar Brindes de venda.");
            Logger.getLogger(BrindePedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao listar Brindes de venda.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                System.out.println("Falha ao fechar ResultSet.");
                Logger.getLogger(BrindePedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                System.exit(0);
            }
        }
        return brindes;
    }
}
