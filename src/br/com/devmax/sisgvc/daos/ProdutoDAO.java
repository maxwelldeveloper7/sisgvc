/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

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
public class ProdutoDAO extends GenericDAO {

    public boolean inserir(ProdutoBean produto) {
        String sql = "INSERT INTO produtos(no_produto, cd_medida, vl_compra, vl_unitario, estoque, tp_produto)VALUES (?, ?, ?, ?, ?, ?)";
        return executeCommand(sql, produto.getProduto(), produto.getMedida().getId(), produto.getVlCompra(), produto.getVlVenda(), produto.getEstoque(), produto.getTipo());
    }

    public boolean alterar(ProdutoBean produto) {
        String sql = "UPDATE produtos SET no_produto=?, cd_medida=?, vl_compra=?, vl_unitario=?, estoque=? WHERE id=?";
        return executeCommand(sql, produto.getProduto(), produto.getMedida().getId(), produto.getVlCompra(), produto.getVlVenda(), produto.getEstoque(), produto.getId());
    }

    public void excluir(ProdutoBean produto) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        executeCommand(sql, produto.getId());
    }
    private ResultSet rs = null;

    public List<ProdutoBean> listar() {
        List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
        String sql = "SELECT * FROM vw_produtos";
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                produto = new ProdutoBean();
                medida = new MedidaBean();
                produto.setId(rs.getInt("id"));
                produto.setProduto(rs.getString("no_produto"));
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlCompra(rs.getBigDecimal("vl_compra"));
                produto.setVlVenda(rs.getBigDecimal("vl_venda"));
                produto.setEstoque(rs.getInt("estoque"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao listar produtos.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                System.exit(0);
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }

    public List<ProdutoBean> listarItensDeVenda() {
        List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
        String sql = "SELECT * FROM vw_produtos where tp_produto = 1";
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                produto = new ProdutoBean();
                medida = new MedidaBean();
                produto.setId(rs.getInt("id"));
                produto.setProduto(rs.getString("no_produto"));
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlCompra(rs.getBigDecimal("vl_compra"));
                produto.setVlVenda(rs.getBigDecimal("vl_venda"));
                produto.setEstoque(rs.getInt("estoque"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar produtos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar produtos.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }

    public List<ProdutoBean> listarBrindes() {
        List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
        String sql = "SELECT * FROM vw_produtos where tp_produto = 2";
        ProdutoBean produto;
        MedidaBean medida;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                produto = new ProdutoBean();
                medida = new MedidaBean();
                produto.setId(rs.getInt("id"));
                produto.setProduto(rs.getString("no_produto"));
                medida.setId(rs.getInt("cd_medida"));
                medida.setMedida(rs.getString("no_medida"));
                produto.setMedida(medida);
                produto.setVlCompra(rs.getBigDecimal("vl_compra"));
                produto.setVlVenda(rs.getBigDecimal("vl_venda"));
                produto.setEstoque(rs.getInt("estoque"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao listar produtos.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage());
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }
}
