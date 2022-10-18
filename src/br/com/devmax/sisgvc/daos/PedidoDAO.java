/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.AuditoriaPedidoBean;
import br.com.devmax.sisgvc.modelo.CidadeBean;
import br.com.devmax.sisgvc.modelo.ClienteBean;
import br.com.devmax.sisgvc.modelo.ConsolidadoDePedidosBean;
import br.com.devmax.sisgvc.modelo.DetalhadoDePedidosBean;
import br.com.devmax.sisgvc.modelo.EquipeBean;
import br.com.devmax.sisgvc.modelo.FuncionarioBean;
import br.com.devmax.sisgvc.modelo.PedidoBean;
import br.com.devmax.sisgvc.modelo.StatusVendaBean;
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
public class PedidoDAO extends GenericDAO {

      
    public int inserir(PedidoBean venda) {
        String sql = "INSERT INTO pedidos(cd_equipe, cd_cliente, dt_entrega, dt_vencimento, cd_status, cd_vendedor) VALUES (?, ?, ?, ?, ?, ?) returning id";
        return executeProcedureInt(sql, venda.getEquipe().getId(), venda.getCliente().getId(), venda.getDataEntrega(), venda.getDataVencimento(), venda.getStatus().getId(), venda.getVendedor().getId());
    }

    public boolean alterar(PedidoBean p) {
        String sql = "UPDATE pedidos SET cd_equipe=?, cd_cliente=?, dt_entrega=?, dt_vencimento=?, cd_status=?, cd_vendedor=?, vl_recebido=?, vl_desconto=?, vlp01 = ?, vlp02 = ?, vlp03 = ?, vlp04 = ?, vlp05 = ?, vlp06 = ?, vlp07 = ?, vlp08 = ?, vlp09 = ?, vlp10 = ?, vlp11 = ?, vlp12 = ?, vlp13 = ?, vlp14 = ?, vlp15 = ?, dtp01 = ?, dtp02 = ?, dtp03 = ?, dtp04 = ?, dtp05 = ?, dtp06 = ?, dtp07 = ?, dtp08 = ?, dtp09 = ?, dtp10 = ?, dtp11 = ?, dtp12 = ?, dtp13 = ?, dtp14 = ?, dtp15 = ?, cd_mtperda = ? WHERE id=?";
        return executeCommand(sql, p.getEquipe().getId(), p.getCliente().getId(), p.getDataEntrega(), p.getDataVencimento(), p.getStatus().getId(), p.getVendedor().getId(), p.getValorRecebido(), p.getDesconto(), p.getVlParcela01(), p.getVlParcela02(), p.getVlParcela03(), p.getVlParcela04(), p.getVlParcela05(), p.getVlParcela06(), p.getVlParcela07(), p.getVlParcela08(), p.getVlParcela09(), p.getVlParcela10(), p.getVlParcela11(), p.getVlParcela12(), p.getVlParcela13(), p.getVlParcela14(), p.getVlParcela15(),p.getDtParcela01(), p.getDtParcela02(), p.getDtParcela03(), p.getDtParcela04(), p.getDtParcela05(), p.getDtParcela06(), p.getDtParcela07(), p.getDtParcela08(), p.getDtParcela09(), p.getDtParcela10(), p.getDtParcela11(), p.getDtParcela12(), p.getDtParcela13(), p.getDtParcela14(), p.getDtParcela15(), p.getMotivoPerda().getId(), p.getId());
       
    }

    public void excluir(PedidoBean venda) {
        String sql = "SELECT remove_pedido(?)";
        executeProcedure(sql, venda.getId());
    }

    public void atualizarComissao(PedidoBean venda) {
        String sql = "UPDATE pedidos SET vl_desconto = ? WHERE id = ?";
        executeCommand(sql, venda.getDesconto(), venda.getId());
    }

    public void atualizaValorPedido(PedidoBean venda) {
        String sql = "UPDATE pedidos SET vl_pedido = ?, vl_brinde = ? WHERE id = ?";
        executeCommand(sql, venda.getValorPedido(), venda.getValorBrinde(), venda.getId());
    }

    public void atualizaValorRecebido(PedidoBean venda) {
        String sql = "UPDATE pedidos SET vl_recebido = ? WHERE id = ?";
        executeCommand(sql, venda.getValorRecebido(), venda.getId());
    }

    public void atualizaStatusVenda(PedidoBean venda) {
        String sql = "UPDATE pedidos SET cd_status = ? WHERE id = ?";
        executeCommand(sql, venda.getStatus().getId(), venda.getId());
    }
    private ResultSet rs = null;
    private PreparedStatement pstm = null;

    public List<PedidoBean> listarIntervaloDatas(Date dtInicial, Date dtFinal) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE dt_entrega BETWEEN ? AND ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setDate(1, dtInicial);
            pstm.setDate(2, dtFinal);
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
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
        return pedidos;
    }

    public List<PedidoBean> listarPorEquipe(String e) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE no_equipe = ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, e);
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
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
        return pedidos;
    }

    public List<PedidoBean> listarPorCliente(String c) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE no_cliente = ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, c);
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
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
        return pedidos;
    }

    public List<PedidoBean> listarPorStatus(String s) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE st_venda = ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, s);
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
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
        return pedidos;
    }

    public List<PedidoBean> localizarPedido(Integer codigo) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE id = ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
            } else {
                JOptionPane.showMessageDialog(null, "Pedido não cadastrado.");
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
        return pedidos;
    }

    public PedidoBean procurarPedido(Integer codigo) {
        String sql = "SELECT * FROM vw_pedidos WHERE id = ? ORDER BY id ASC";
        PedidoBean p = new PedidoBean();
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
            } else {
                p = null;
                JOptionPane.showMessageDialog(null, "Este Pedido de venda não existe");
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
        return p;
    }

    public List<PedidoBean> listar(PedidoBean pedidoBean, boolean cid, boolean cli, Date dtInical, Date dtFinal, boolean eq, boolean st, List<StatusVendaBean> sta) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "select from vw_pedidos order by no_cliente asc, no_cidade asc, id asc";

        List<StatusVendaBean> sl = new ArrayList<StatusVendaBean>();
        sl.add(new StatusVendaBean(1, "CONSIGNADO"));
        sl.add(new StatusVendaBean(2, "PENDENTE"));
        sl.add(new StatusVendaBean(3, "CONCLUÍDO"));
        sl.add(new StatusVendaBean(4, "DEVOLVIDO"));
        sl.add(new StatusVendaBean(5, "PERDIDO"));

        for (int i = 0; i < sl.size(); i++) {
            for (int j = 0; j < sta.size(); j++) {
                if (sta.get(j).getStatus().equalsIgnoreCase(sl.get(i).getStatus())) {
                    sl.remove(i);
                }
            }
        }

        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {

            if (!cid && !eq && !cli && !st) {
                sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setDate(1, dtInical);
                pstm.setDate(2, dtFinal);
            } else if (cid && !eq && !cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!cid && eq && !cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ?  AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getEquipe().getNome());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!cid && !eq && cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cliente = ?  AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getCliente().getNome());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!cid && !eq && !cli && st) {

                if (sta.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sta.get(0).getStatus());
                }
                if (sta.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda = ? or st_venda = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sta.get(0).getStatus());
                    pstm.setString(4, sta.get(1).getStatus());
                }
                if (sta.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda = ? or st_venda = ? or st_venda = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sta.get(0).getStatus());
                    pstm.setString(4, sta.get(1).getStatus());
                    pstm.setString(5, sta.get(2).getStatus());
                }
                if (sta.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda = ? or st_venda = ? or st_venda = ? or st_venda = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sta.get(0).getStatus());
                    pstm.setString(4, sta.get(1).getStatus());
                    pstm.setString(5, sta.get(2).getStatus());
                    pstm.setString(6, sta.get(3).getStatus());
                }
            } else if (cid && eq && !cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND no_equipe = ? AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                pstm.setString(2, pedidoBean.getEquipe().getNome());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (cid && !eq && cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND no_cliente = ? AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                pstm.setString(2, pedidoBean.getCliente().getNome());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (cid && !eq && !cli && st) {

                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, sl.get(3).getStatus());
                }
            } else if (!cid && eq && cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND no_cliente = ? AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getEquipe().getNome());
                pstm.setString(2, pedidoBean.getCliente().getNome());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);

            } else if (!cid && eq && !cli && st) {

                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND dt_vencimento between ? and ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getEquipe().getNome());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getEquipe().getNome());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getEquipe().getNome());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getEquipe().getNome());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, sl.get(3).getStatus());
                }
            } else if (!cid && !eq && cli && st) {
                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, pedidoBean.getCliente().getNome());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, sl.get(2).getStatus());
                    pstm.setString(6, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, sl.get(2).getStatus());
                    pstm.setString(6, sl.get(3).getStatus());
                    pstm.setString(7, pedidoBean.getCliente().getNome());
                }
            } else if (cid && eq && cli && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND no_equipe = ? AND no_cliente = ? AND dt_vencimento between ? and ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                pstm.setString(2, pedidoBean.getEquipe().getNome());
                pstm.setString(3, pedidoBean.getCliente().getNome());
                pstm.setDate(4, dtInical);
                pstm.setDate(5, dtFinal);
            } else if (cid && !eq && cli && st) {
                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, pedidoBean.getCliente().getNome());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(7, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(8, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, sl.get(3).getStatus());
                    pstm.setString(9, pedidoBean.getCliente().getNome());
                }
            } else if (cid && eq && !cli && st) {
                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? AND no_equipe = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, pedidoBean.getEquipe().getNome());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and no_equipe = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, pedidoBean.getEquipe().getNome());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, pedidoBean.getEquipe().getNome());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, sl.get(3).getStatus());
                    pstm.setString(8, pedidoBean.getEquipe().getNome());
                }
            } else if (!cid && eq && cli && st) {
                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? AND no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, pedidoBean.getEquipe().getNome());
                    pstm.setString(5, pedidoBean.getCliente().getNome());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, pedidoBean.getEquipe().getNome());
                    pstm.setString(6, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, sl.get(2).getStatus());
                    pstm.setString(6, pedidoBean.getEquipe().getNome());
                    pstm.setString(7, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setDate(1, dtInical);
                    pstm.setDate(2, dtFinal);
                    pstm.setString(3, sl.get(0).getStatus());
                    pstm.setString(4, sl.get(1).getStatus());
                    pstm.setString(5, sl.get(2).getStatus());
                    pstm.setString(6, sl.get(3).getStatus());
                    pstm.setString(7, pedidoBean.getEquipe().getNome());
                    pstm.setString(8, pedidoBean.getCliente().getNome());
                }
            } else if (cid && eq && cli && st) {
                if (sl.size() == 1) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? AND no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, pedidoBean.getEquipe().getNome());
                    pstm.setString(6, pedidoBean.getCliente().getNome());
                }

                if (sl.size() == 2) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, pedidoBean.getEquipe().getNome());
                    pstm.setString(7, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 3) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, pedidoBean.getEquipe().getNome());
                    pstm.setString(8, pedidoBean.getCliente().getNome());
                }
                if (sl.size() == 4) {
                    sql = "SELECT * FROM vw_pedidos where no_cidade = ? AND dt_vencimento between ? and ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and st_venda <> ? and no_equipe = ? and no_cliente = ? ORDER BY no_cliente ASC, no_cidade, id ASC";
                    pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                    pstm.setString(1, pedidoBean.getCliente().getCidade().getCidade());
                    pstm.setDate(2, dtInical);
                    pstm.setDate(3, dtFinal);
                    pstm.setString(4, sl.get(0).getStatus());
                    pstm.setString(5, sl.get(1).getStatus());
                    pstm.setString(6, sl.get(2).getStatus());
                    pstm.setString(7, sl.get(3).getStatus());
                    pstm.setString(8, pedidoBean.getEquipe().getNome());
                    pstm.setString(9, pedidoBean.getCliente().getNome());
                }
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos por cliente.\n"
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
        return pedidos;
    }

    public List<ConsolidadoDePedidosBean> listarConsolidado(String cidadeP, String statusP) {
        List<ConsolidadoDePedidosBean> lista = new ArrayList<ConsolidadoDePedidosBean>();
        boolean c = cidadeP.equals("Todas");
        boolean s = statusP.equals("Todos");
        String sql;
        try {
            sql = "select * from vw_consolidado order by cidade, cliente";
            if (c && s) {
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            }

            if (c && !s) {
                if (statusP.equals("Consignado")) {
                    sql = "select * from vw_consolidado where consignados > 0 order by cidade, cliente";
                }

                if (statusP.equals("Pendente")) {
                    sql = "select * from vw_consolidado where pendentes > 0 order by cidade, cliente";
                }

                if (statusP.equals("Devolvido")) {
                    sql = "select * from vw_consolidado where devolvidos > 0 order by cidade, cliente";
                }

                if (statusP.equals("Concluído")) {
                    sql = "select * from vw_consolidado where concluidos > 0 order by cidade, cliente";
                }

                if (statusP.equals("Perdido")) {
                    sql = "select * from vw_consolidado where perdidos > 0 order by cidade, cliente";
                }

                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            }

            if (!c && s) {
                sql = "select * from vw_consolidado where cidade = ? order by cidade, cliente";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidadeP);
            }

            if (!c && !s) {
                if (statusP.equals("Consignado")) {
                    sql = "select * from vw_consolidado where consignados > 0 and cidade = ? order by cidade, cliente";
                }

                if (statusP.equals("Pendente")) {
                    sql = "select * from vw_consolidado where pendentes > 0 and cidade = ? order by cidade, cliente";
                }

                if (statusP.equals("Devolvido")) {
                    sql = "select * from vw_consolidado where devolvidos > 0 and cidade = ? order by cidade, cliente";
                }

                if (statusP.equals("Concluído")) {
                    sql = "select * from vw_consolidado where concluidos > 0 and cidade = ? order by cidade, cliente";
                }

                if (statusP.equals("Perdido")) {
                    sql = "select * from vw_consolidado where perdidos > 0 and cidade = ? order by cidade, cliente";
                }

                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidadeP);
            }

            rs = pstm.executeQuery();

            ConsolidadoDePedidosBean consolidado;

            while (rs.next()) {
                consolidado = new ConsolidadoDePedidosBean();
                consolidado.setId(rs.getInt("codigo"));
                consolidado.setNomeCliente(rs.getString("cliente"));
                consolidado.setEndereco(rs.getString("endereco"));
                consolidado.setNumero(rs.getString("numero"));
                consolidado.setBairro(rs.getString("bairro"));
                consolidado.setUf(rs.getString("uf"));
                consolidado.setNomeCidade(rs.getString("cidade"));
                consolidado.setConsignado(rs.getInt("consignados"));
                consolidado.setPendente(rs.getInt("pendentes"));
                consolidado.setDevolvido(rs.getInt("devolvidos"));
                consolidado.setConcluido(rs.getInt("concluidos"));
                consolidado.setPerdido(rs.getInt("perdidos"));
                lista.add(consolidado);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos por cliente.\n"
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
    }

    public List<DetalhadoDePedidosBean> listarDetalhado(String cidadeP, String statusP) {
        List<DetalhadoDePedidosBean> lista = new ArrayList<DetalhadoDePedidosBean>();
        boolean c = cidadeP.equals("Todas");
        boolean s = statusP.equals("Todos");
        String sql;
        try {
            sql = "select * from vw_pedidos where st_venda <> ? and st_venda <> ?  and st_venda <> ? order by no_cidade, no_cliente";
            if (c && s) {
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, "DEVOLVIDO");
                pstm.setString(2, "PERDIDO");
                pstm.setString(3, "CONCLUÍDO");
            }

            if (c && !s) {
                sql = "select * from vw_pedidos where st_venda = ? order by no_cidade, no_cliente";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, statusP.toUpperCase());
            }

            if (!c && s) {
                sql = "select * from vw_pedidos where no_cidade = ? and st_venda <> ? and st_venda <> ?  and st_venda <> ? order by no_cidade, no_cliente";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidadeP);
                pstm.setString(2, "DEVOLVIDO");
                pstm.setString(3, "PERDIDO");
                pstm.setString(4, "CONCLUÍDO");
            }

            if (!c && !s) {
                sql = "select * from vw_pedidos where no_cidade = ? and st_venda = ?order by no_cidade, no_cliente";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidadeP);
                pstm.setString(2, statusP.toUpperCase());
            }

            rs = pstm.executeQuery();

            DetalhadoDePedidosBean detalhado;

            while (rs.next()) {
                detalhado = new DetalhadoDePedidosBean();
                detalhado.setId(rs.getInt("cd_cliente"));
                detalhado.setNomeCliente(rs.getString("no_cliente"));
                detalhado.setEndereco(rs.getString("lged_cliente"));
                detalhado.setNumero(rs.getString("nred_cliente"));
                detalhado.setBairro(rs.getString("bred_cliente"));
                detalhado.setUf(rs.getString("uf"));
                detalhado.setNomeCidade(rs.getString("no_cidade"));
                detalhado.setNumeroPedido(rs.getInt("id"));
                detalhado.setValor(rs.getBigDecimal("vl_pedido"));
                detalhado.setRecebido(rs.getBigDecimal("vl_recebido"));
                detalhado.setDesconto(rs.getBigDecimal("vl_desconto"));
                detalhado.setStatus(rs.getString("st_venda"));
                lista.add(detalhado);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos por cliente.\n"
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
    }

    /**
     * Este método zera o valor dos pedidos devolvidos
     */
    public void correcaoDevolvidoZerado() {
        List<PedidoBean> lista = new ArrayList<PedidoBean>();
        String sql = "select * from vw_devolvidos_pendentes where vl_pedido = 0.00 and cd_status = 4";
        PedidoBean pedido;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pedido = new PedidoBean();
                pedido.setId(rs.getInt("cd_venda"));
                pedido.setValorPedido(rs.getBigDecimal("vl_devolvido"));
                lista.add(pedido);
            }

            pstm.close();
            String sqlup = "update pedidos set vl_pedido = ? where id = ?";
            pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

            for (int i = 0; i < lista.size(); i++) {
                pstm.setBigDecimal(1, lista.get(i).getValorPedido());
                pstm.setInt(2, lista.get(i).getId());
                pstm.addBatch();
                System.out.print(lista.get(i).getId() + "-");
                System.out.println(i + 1);
            }
            System.out.println(pstm.executeBatch().toString());
            ConexaoPostgres.commit();
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos.\n"
                    + "Motivo: "
                    + ex.getMessage());
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
                        + ex.getMessage());
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<PedidoBean> listarPedidos(PedidoBean vendaBean, Date dtInical, Date dtFinal, boolean eq, boolean cl, boolean st) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql;

        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            if (!eq && !cl && !st) {
                sql = "SELECT * FROM vw_pedidos where dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setDate(1, dtInical);
                pstm.setDate(2, dtFinal);
            } else if (eq && !cl && !st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getEquipe().getNome());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!eq && cl && !st) {
                sql = "SELECT * FROM vw_pedidos where no_cliente = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getCliente().getNome());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!eq && !cl && st) {
                sql = "SELECT * FROM vw_pedidos where st_venda = ?  AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getStatus().getStatus());
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (eq && cl && !st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND no_cliente = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getEquipe().getNome());
                pstm.setString(2, vendaBean.getCliente().getNome());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (!eq && cl && st) {
                sql = "SELECT * FROM vw_pedidos where no_cliente = ? AND st_venda = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getCliente().getNome());
                pstm.setString(2, vendaBean.getStatus().getStatus());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (eq && !cl && st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND st_venda = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getEquipe().getNome());
                pstm.setString(2, vendaBean.getStatus().getStatus());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (eq && cl && st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND no_cliente = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getEquipe().getNome());
                pstm.setString(2, vendaBean.getCliente().getNome());
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            } else if (eq && cl && st) {
                sql = "SELECT * FROM vw_pedidos where no_equipe = ? AND no_cliente = ? AND st_venda = ? AND dt_vencimento between ? and ? ORDER BY id ASC";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, vendaBean.getEquipe().getNome());
                pstm.setString(2, vendaBean.getCliente().getNome());
                pstm.setString(3, vendaBean.getStatus().getStatus());
                pstm.setDate(4, dtInical);
                pstm.setDate(5, dtFinal);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar pedidos.");
            JOptionPane.showMessageDialog(null, "Falha ao listar pedidos por cliente.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharPrepareStatement();
            ConexaoPostgres.desconectar();
        }
        return pedidos;
    }

    public List<PedidoBean> localizarCliente(int codigo) {
        List<PedidoBean> pedidos = new ArrayList<PedidoBean>();
        String sql = "SELECT * FROM vw_pedidos WHERE cd_cliente = ? ORDER BY id ASC";
        PedidoBean p;
        EquipeBean equipe;
        ClienteBean cliente;
        CidadeBean cidade;
        StatusVendaBean status;
        FuncionarioBean vendedor;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();         
            
            while (rs.next()) {
                p = new PedidoBean();
                p.setId(rs.getInt("id"));
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("cd_equipe"));
                equipe.setNome(rs.getString("no_equipe"));
                p.setEquipe(equipe);
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
                p.setCliente(cliente);
                p.setDataEntrega(rs.getDate("dt_entrega"));
                p.setDataVencimento(rs.getDate("dt_vencimento"));
                p.setValorBrinde(rs.getBigDecimal("vl_brinde"));
                status = new StatusVendaBean();
                status.setId(rs.getInt("cd_status"));
                status.setStatus(rs.getString("st_venda"));
                p.setStatus(status);
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
                p.setVendedor(vendedor);
                p.setDesconto(rs.getBigDecimal("vl_desconto"));
                p.setValorPedido(rs.getBigDecimal("vl_pedido"));
                p.setValorRecebido(rs.getBigDecimal("vl_recebido"));
                p.setVlParcela01(rs.getBigDecimal("vlp01"));
                p.setVlParcela02(rs.getBigDecimal("vlp02"));
                p.setVlParcela03(rs.getBigDecimal("vlp03"));
                p.setVlParcela04(rs.getBigDecimal("vlp04"));
                p.setVlParcela05(rs.getBigDecimal("vlp05"));
                p.setVlParcela06(rs.getBigDecimal("vlp06"));
                p.setVlParcela07(rs.getBigDecimal("vlp07"));
                p.setVlParcela08(rs.getBigDecimal("vlp08"));
                p.setVlParcela09(rs.getBigDecimal("vlp09"));
                p.setVlParcela10(rs.getBigDecimal("vlp10"));
                p.setVlParcela11(rs.getBigDecimal("vlp11"));
                p.setVlParcela12(rs.getBigDecimal("vlp12"));
                p.setVlParcela13(rs.getBigDecimal("vlp13"));
                p.setVlParcela14(rs.getBigDecimal("vlp14"));
                p.setVlParcela15(rs.getBigDecimal("vlp15"));
                p.setDtParcela01(rs.getDate("dtp01"));
                p.setDtParcela02(rs.getDate("dtp02"));
                p.setDtParcela03(rs.getDate("dtp03"));
                p.setDtParcela04(rs.getDate("dtp04"));
                p.setDtParcela05(rs.getDate("dtp05"));
                p.setDtParcela06(rs.getDate("dtp06"));
                p.setDtParcela07(rs.getDate("dtp07"));
                p.setDtParcela08(rs.getDate("dtp08"));
                p.setDtParcela09(rs.getDate("dtp09"));
                p.setDtParcela10(rs.getDate("dtp10"));
                p.setDtParcela11(rs.getDate("dtp11"));
                p.setDtParcela12(rs.getDate("dtp12"));
                p.setDtParcela13(rs.getDate("dtp13"));
                p.setDtParcela14(rs.getDate("dtp14"));
                p.setDtParcela15(rs.getDate("dtp15"));
                pedidos.add(p);
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
        return pedidos;
    }
    
    public Integer localizarMotivoPerda(int codigo) {
        Integer motivo = 1 ;
        String sql = "SELECT cd_mtperda FROM pedidos WHERE id = ?";
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();         
            
            while (rs.next()) {
                motivo = rs.getInt("cd_mtperda");
            } 

        } catch (SQLException ex) {
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
        return motivo;
    }
    
    public void gravaAutoria(AuditoriaPedidoBean a){
        String sql = "INSERT INTO auditoria_pedidos (cdacao, cdpedido, cdstatus, cdusuario, dtacesso, hora, vlbrinde, vlpedido, vlrecebido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        executeCommand(sql, a.getCodAcao(), a.getCodPedido(), a.getCodStatus(), a.getCodUsuario(), a.getDtAcesso(), a.getHora(), a.getVlBrinde(), a.getVlPedido(), a.getVlRecebido());
    }
}

/*
    public void correcaoConsignadoPendente() {
        List<PedidoBean> lista = new ArrayList<PedidoBean>();
        String sql = "select * from vw_devolvidos_pendentes where devolvidos >0 and cd_status = 1";
        PedidoBean pedido;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pedido = new PedidoBean();
                pedido.setId(rs.getInt("cd_produto"));
                lista.add(pedido);
            }

            pstm.close();
            String sqlup = "update pedidos set cd_status = ? where id = ?";
            pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

            for (int i = 0; i < lista.size(); i++) {
                pstm.setInt(1, 2);
                pstm.setInt(2, lista.get(i).getId());
                pstm.addBatch();
                System.out.print(lista.get(i).getId() + "-");
                System.out.println(i + 1);
            }
            System.out.println(pstm.executeBatch().toString());
            ConexaoPostgres.commit();
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
    }
    
     public void correcaoItensVendidos() {
     List<ItemPedidoBean> lista = new ArrayList<ItemPedidoBean>();
     String sql = "select 	v.id as pedido,	"
     + "i.id as cod_item, "
     + "i.consignado, "
     + "i.vendido "
     + "from pedidos v inner join itens_venda i on v.id = i.cd_produto where cd_status = 3 and consignado > 0";
     ItemPedidoBean item;
     try {
     pstm = ConexaoPostgres.conectar().prepareStatement(sql);
     rs = pstm.executeQuery();
     //System.out.println("listando pedidos...");
     while (rs.next()) {
     item = new ItemPedidoBean();
     item.setId(rs.getLong("cod_item"));
     item.setConsignado(rs.getInt("consignado"));
     item.setVendido(rs.getInt("vendido"));
     lista.add(item);
     }

     pstm.close();
     String sqlup = "update itens_venda set consignado = ?, vendido = ? where id = ?";
     pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

     for (int i = 0; i < lista.size(); i++) {
     pstm.setInt(1, 0);
     pstm.setInt(2, lista.get(i).getConsignado() + lista.get(i).getVendido());
     pstm.setLong(3, lista.get(i).getId());

     pstm.addBatch();
     }
     pstm.executeBatch();
     ConexaoPostgres.commit();
     JOptionPane.showMessageDialog(null, lista.size() + " inconsistência(s) corrigida(s)");
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
     }

     public void transferirConsignadosParaVendidos(List<ItemPedidoBean> itensVenda) {
     try {


     pstm.close();
     String sqlup = "update itens_venda set consignado = ?, vendido = ? where id = ?";
     pstm = ConexaoPostgres.conectar().prepareStatement(sqlup);

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
     }
     */
