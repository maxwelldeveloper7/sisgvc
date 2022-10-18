/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.CompraBean;
import br.com.devmax.sisgvc.modelo.FornecedorBean;
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
public class CompraDAO extends GenericDAO {

    public int inserir(CompraBean compra) {
        String sql = "INSERT INTO compras(nr_nt_fiscal, cd_fornecedor, dt_emissao, dt_entrega) VALUES (?, ?, ?, ?) returning id";
        return executeProcedureInt(sql, compra.getNtFiscal(), compra.getFornecedor().getId(), compra.getDataEmissao(), compra.getDataEntrega());
    }

    public boolean alterar(CompraBean compra) {
        String sql = "UPDATE compras SET nr_nt_fiscal=?, cd_fornecedor=?, dt_emissao=?, dt_entrega=? WHERE id=?";
        return executeCommand(sql, compra.getNtFiscal(), compra.getFornecedor().getId(), compra.getDataEmissao(), compra.getDataEntrega(), compra.getId());
    }

    public void excluir(CompraBean compra) {
        String sql = "SELECT remove_compra(?)";
        executeProcedure(sql, compra.getId());
    }
    private ResultSet rs = null;
    private PreparedStatement pstm = null;

    public List<CompraBean> listar() {
        List<CompraBean> compras = new ArrayList<CompraBean>();
        String sql = "SELECT * FROM vw_compras  ORDER BY id ASC";
        CompraBean compra;
        FornecedorBean fornecedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            System.out.println("listando compras...");
            while (rs.next()) {
                compra = new CompraBean();
                compra.setId(rs.getInt("id"));
                compra.setNtFiscal(rs.getString("nr_nt_fiscal"));
                fornecedor = new FornecedorBean();
                fornecedor.setId(rs.getInt("cd_fornecedor"));
                fornecedor.setNome(rs.getString("no_fornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setIe(rs.getString("ie"));
                fornecedor.setEndereco(rs.getString("ed_fornecedor"));
                fornecedor.setUf(rs.getString("uf_fornecedor"));
                fornecedor.setCidade(rs.getString("cid_fornecedor"));
                fornecedor.setCep(rs.getString("cep_fornecedor"));
                fornecedor.setTelefone(rs.getString("tl_fornecedor"));
                compra.setFornecedor(fornecedor);
                compra.setDataEmissao(rs.getDate("dt_emissao"));
                compra.setDataEntrega(rs.getDate("dt_entrega"));
                compras.add(compra);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar compras.");
            JOptionPane.showMessageDialog(null, "Falha ao listar compras.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return compras;
    }
}
