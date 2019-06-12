/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.CidadeBean;
import br.com.devmax.sisgvc.modelo.ClienteBean;
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
public class ClienteDAO extends GenericDAO {

    public boolean inserir(ClienteBean c) {
        String sql = "INSERT INTO clientes(no_cliente, rg_cliente, cpf_cliente, dt_nascimento, lged_cliente, nred_cliente, bred_cliente, cd_cidade, cep_cliente, tl_cliente, cl_cliente, corc_cliente, nrf_cliente, nrl_cliente, pr_cliente, ape_cliente)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return executeCommand(sql, c.getNome(), c.getRg(), c.getCpf(), c.getDataNascimento(), c.getLogradouro(), c.getNumero(), c.getBairro(), c.getCidade().getId(), c.getCep(), c.getTelefone(), c.getCelular(), c.getCorCasa(), c.getNumeroCasaFrente(), c.getNumeroCasaLado(), c.getPontoReferencia(), c.getApelido());
    }

    public boolean alterar(ClienteBean c) {
        String sql = "UPDATE clientes SET no_cliente=?, rg_cliente=?, cpf_cliente=?, dt_nascimento=?, lged_cliente=?, nred_cliente=?, bred_cliente=?, cd_cidade=?, cep_cliente=?, tl_cliente=?, cl_cliente=?, corc_cliente=?, nrf_cliente=?, nrl_cliente=?, pr_cliente=?, ape_cliente=? WHERE id=?";
        return executeCommand(sql, c.getNome(), c.getRg(), c.getCpf(), c.getDataNascimento(), c.getLogradouro(), c.getNumero(), c.getBairro(), c.getCidade().getId(), c.getCep(), c.getTelefone(), c.getCelular(), c.getCorCasa(), c.getNumeroCasaFrente(), c.getNumeroCasaLado(), c.getPontoReferencia(), c.getApelido(), c.getId());
    }

    public void excluir(ClienteBean cliente) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        executeCommand(sql, cliente.getId());
    }
    private ResultSet rs = null;
    private PreparedStatement pstm = null;

    public List<ClienteBean> listar() {
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        String sql = "SELECT * FROM VW_CLIENTES";
        ClienteBean cliente;
        CidadeBean cidade;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("nascimento"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cidade = new CidadeBean();
                cidade.setCidade(rs.getString("cidade"));
                cidade.setUf(rs.getString("uf"));
                cliente.setCidade(cidade);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCorCasa(rs.getString("corc_cliente"));
                cliente.setNumeroCasaFrente(rs.getString("nrf_cliente"));
                cliente.setNumeroCasaLado(rs.getString("nrl_cliente"));
                cliente.setPontoReferencia(rs.getString("pr_cliente"));
                cliente.setApelido(rs.getString("ape_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar clientes.");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
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
                JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                System.exit(0);
            }
        }
        return clientes;
    }

    public List<ClienteBean> listar(String str) {
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        String sql = "SELECT * FROM VW_CLIENTES where nome like ? order by nome";
        ClienteBean cliente;
        CidadeBean cidade;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, str+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("nascimento"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cidade = new CidadeBean();
                cidade.setCidade(rs.getString("cidade"));
                cidade.setUf(rs.getString("uf"));
                cliente.setCidade(cidade);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));                
                cliente.setCorCasa(rs.getString("corc_cliente"));
                cliente.setNumeroCasaFrente(rs.getString("nrf_cliente"));
                cliente.setNumeroCasaLado(rs.getString("nrl_cliente"));
                cliente.setPontoReferencia(rs.getString("pr_cliente"));
                cliente.setApelido(rs.getString("ape_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar clientes.");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
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
                JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                 System.exit(0);
            }
        }
        return clientes;
    }
    
    
    public List<ClienteBean> listarPorCidade(String cid) {
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        String sql = "SELECT * FROM VW_CLIENTES where cidade like ? order by nome";
        ClienteBean cliente;
        CidadeBean cidade;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, cid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("nascimento"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cidade = new CidadeBean();
                cidade.setCidade(rs.getString("cidade"));
                cidade.setUf(rs.getString("uf"));
                cliente.setCidade(cidade);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));                
                cliente.setCorCasa(rs.getString("corc_cliente"));
                cliente.setNumeroCasaFrente(rs.getString("nrf_cliente"));
                cliente.setNumeroCasaLado(rs.getString("nrl_cliente"));
                cliente.setPontoReferencia(rs.getString("pr_cliente"));
                cliente.setApelido(rs.getString("ape_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar clientes.");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
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
                JOptionPane.showMessageDialog(null, "Falha ao listar clientes.\n"
                        + "Motivo: "
                        + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
                 System.exit(0);
            }
        }
        return clientes;
    }
    /*
    public List<ResumoPedidoPorClienteBean> listarResumoPedidoPorCliente() {
        List<ResumoPedidoPorClienteBean> lista = new ArrayList<ResumoPedidoPorClienteBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE dt_entrega BETWEEN ? AND ? ORDER BY id ASC";
        ConsolidadoDePedidosBean pedido;
        
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setDate(1, dtInicial);
            pstm.setDate(2, dtFinal);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pedido = new ConsolidadoDePedidosBean();
                pedido.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                pedido.setEquipe(equipe);
                cliente = new ClienteBean();
                cliente.setId(rs.getInt("cd_cliente"));
                cliente.setNome(rs.getString("no_cliente"));
                cliente.setRg(rs.getString("rg_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setDataNascimento(rs.getDate("dt_nascimento"));
                cliente.setLogradouro(rs.getString("lged_cliente"));
                cliente.setNumero(rs.getString("nred_cliente"));
                cliente.setBairro(rs.getString("bred_cliente"));
                cidade = new CidadeBean();
                cidade.setUf(rs.getString("uf"));
                cidade.setCidade(rs.getString("no_cidade"));
                cliente.setCidade(cidade);
                cliente.setCep(rs.getString("cep_cliente"));
                cliente.setTelefone(rs.getString("tl_cliente"));
                cliente.setCelular(rs.getString("cl_cliente"));
                pedido.setCliente(cliente);
                pedido.setDataEntrega(rs.getDate("dt_entrega"));
                pedido.setDataVencimento(rs.getDate("dt_vencimento"));
                pedido.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                pedido.setPdcdnv(rs.getBoolean("pdcdnv"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                pedido.setStatus(status);
                vendedor = new FuncionarioBean();
                vendedor.setId(rs.getInt("cd_vendedor"));
                vendedor.setNome(rs.getString("no_funcionario"));
                vendedor.setRg(rs.getString("rg_funcionario"));
                vendedor.setCpf(rs.getString("cpf_funcionario"));
                vendedor.setEndereco(rs.getString("ed_funcionario"));
                vendedor.setUf(rs.getString("uf_funcionario"));
                vendedor.setCidade(rs.getString("cid_funcionario"));
                vendedor.setDataAdmissao(rs.getDate("dt_admissao"));
                vendedor.setDataDemissao(rs.getDate("dt_demissao"));
                vendedor.setCep(rs.getString("cep_funcionario"));
                vendedor.setTelefone(rs.getString("tl_funcionario"));
                vendedor.setContaCorrente(rs.getString("nr_cc"));
                vendedor.setNumeroAgencia(rs.getString("nr_agencia"));
                vendedor.setNomeBanco(rs.getString("no_banco"));
                pedido.setVendedor(vendedor);
                pedido.setDesconto(rs.getBigDecimal("vl_desconto"));
                pedido.setValorPedido(rs.getBigDecimal("vl_pedido"));
                pedido.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                lista.add(pedido);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }*/
}
