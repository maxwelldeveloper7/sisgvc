/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.FornecedorBean;
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
public class FornecedorDAO extends GenericDAO {

    public boolean inserir(FornecedorBean fornecedor) {
        String sql = "INSERT INTO fornecedores(no_fornecedor, cnpj, ie, ed_fornecedor, uf_fornecedor, cid_fornecedor, cep_fornecedor, tl_fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return executeCommand(sql, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getIe(), fornecedor.getEndereco(), fornecedor.getUf(), fornecedor.getCidade(), fornecedor.getCep(), fornecedor.getTelefone());
    }

    public boolean alterar(FornecedorBean fornecedor) {
        String sql = "UPDATE fornecedores SET no_fornecedor=?, cnpj=?, ie=?, ed_fornecedor=?, uf_fornecedor=?, cid_fornecedor=?, cep_fornecedor=?, tl_fornecedor=? WHERE id=?";
        return executeCommand(sql, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getIe(), fornecedor.getEndereco(), fornecedor.getUf(), fornecedor.getCidade(), fornecedor.getCep(), fornecedor.getTelefone(), fornecedor.getId());
    }

    public void excluir(FornecedorBean fornecedor) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        executeCommand(sql, fornecedor.getId());
    }
    private ResultSet rs = null;

    public List<FornecedorBean> listar() {
        List<FornecedorBean> fornecedores = new ArrayList<FornecedorBean>();
        String sql = "SELECT * FROM fornecedores ORDER BY no_fornecedor ASC";
        FornecedorBean fornecedor;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                fornecedor = new FornecedorBean();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("no_fornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setIe(rs.getString("ie"));
                fornecedor.setEndereco(rs.getString("ed_fornecedor"));
                fornecedor.setUf(rs.getString("uf_fornecedor"));
                fornecedor.setCidade(rs.getString("cid_fornecedor"));
                fornecedor.setCep(rs.getString("cep_fornecedor"));
                fornecedor.setTelefone(rs.getString("tl_fornecedor"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar fornecedores.");
            JOptionPane.showMessageDialog(null, "Falha ao listar fornecedores.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fornecedores;
    }
}
