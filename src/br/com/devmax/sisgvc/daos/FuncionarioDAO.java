/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.FuncionarioBean;
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
public class FuncionarioDAO extends GenericDAO {

    public boolean inserir(FuncionarioBean funcionario) {
        String sql = "INSERT INTO funcionarios(no_funcionario, rg_funcionario, cpf_funcionario, ed_funcionario, uf_funcionario, cid_funcionario, dt_admissao, dt_demissao, cep_funcionario, tl_funcionario, nr_cc, nr_agencia, no_banco)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeCommand(sql, funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getEndereco(), funcionario.getUf(), funcionario.getCidade(), funcionario.getDataAdmissao(), funcionario.getDataDemissao(), funcionario.getCep(), funcionario.getTelefone(), funcionario.getContaCorrente(), funcionario.getNumeroAgencia(), funcionario.getNomeBanco());
    }

    public boolean alterar(FuncionarioBean funcionario) {
        String sql = "UPDATE funcionarios SET no_funcionario=?, rg_funcionario=?, cpf_funcionario=?, ed_funcionario=?, uf_funcionario=?, cid_funcionario=?, dt_admissao=?, dt_demissao=?, cep_funcionario=?, tl_funcionario=?, nr_cc=?, nr_agencia=?, no_banco=? WHERE id=?";
        return executeCommand(sql, funcionario.getNome(), funcionario.getRg(), funcionario.getCpf(), funcionario.getEndereco(), funcionario.getUf(), funcionario.getCidade(), funcionario.getDataAdmissao(), funcionario.getDataDemissao(), funcionario.getCep(), funcionario.getTelefone(), funcionario.getContaCorrente(), funcionario.getNumeroAgencia(), funcionario.getNomeBanco(), funcionario.getId());
    }

    public void excluir(FuncionarioBean funcionario) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        executeCommand(sql, funcionario.getId());
    }
    private ResultSet rs = null;

    public List<FuncionarioBean> listar() {
        List<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
        String sql = "SELECT * FROM funcionarios ORDER BY no_funcionario ASC";
        FuncionarioBean funcionario;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                funcionario = new FuncionarioBean();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("no_funcionario"));
                funcionario.setRg(rs.getString("rg_funcionario"));
                funcionario.setCpf(rs.getString("cpf_funcionario"));
                funcionario.setEndereco(rs.getString("ed_funcionario"));
                funcionario.setUf(rs.getString("uf_funcionario"));
                funcionario.setCidade(rs.getString("cid_funcionario"));
                funcionario.setDataAdmissao(rs.getDate("dt_admissao"));
                funcionario.setDataDemissao(rs.getDate("dt_demissao"));
                funcionario.setCep(rs.getString("cep_funcionario"));
                funcionario.setTelefone(rs.getString("tl_funcionario"));
                funcionario.setContaCorrente(rs.getString("nr_cc"));
                funcionario.setNumeroAgencia(rs.getString("nr_agencia"));
                funcionario.setNomeBanco(rs.getString("no_banco"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar funcionários.");
            JOptionPane.showMessageDialog(null, "Falha ao listar funcionários.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionarios;
    }
    
    public List<FuncionarioBean> listarAdmitidos() {
        List<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
        String sql = "SELECT * FROM funcionarios WHERE dt_demissao IS NULL ORDER BY no_funcionario ASC";
        FuncionarioBean funcionario;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                funcionario = new FuncionarioBean();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("no_funcionario"));
                funcionario.setRg(rs.getString("rg_funcionario"));
                funcionario.setCpf(rs.getString("cpf_funcionario"));
                funcionario.setEndereco(rs.getString("ed_funcionario"));
                funcionario.setUf(rs.getString("uf_funcionario"));
                funcionario.setCidade(rs.getString("cid_funcionario"));
                funcionario.setDataAdmissao(rs.getDate("dt_admissao"));
                funcionario.setDataDemissao(rs.getDate("dt_demissao"));
                funcionario.setCep(rs.getString("cep_funcionario"));
                funcionario.setTelefone(rs.getString("tl_funcionario"));
                funcionario.setContaCorrente(rs.getString("nr_cc"));
                funcionario.setNumeroAgencia(rs.getString("nr_agencia"));
                funcionario.setNomeBanco(rs.getString("no_banco"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar funcionários.");
            JOptionPane.showMessageDialog(null, "Falha ao listar funcionários.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return funcionarios;
    }
}
