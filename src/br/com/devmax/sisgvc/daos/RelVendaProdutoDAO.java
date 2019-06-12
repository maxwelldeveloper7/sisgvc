/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.RelBrindeProdutoBean;
import br.com.devmax.sisgvc.modelo.RelVendaProdutoBean;
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
public class RelVendaProdutoDAO extends GenericDAO {

    private ResultSet rs = null;
    private PreparedStatement pstm = null;

    public List<RelVendaProdutoBean> listarItensVenda(String cidade, String equipe, Date dtInical, Date dtFinal, boolean eq, boolean cid) {
        List<RelVendaProdutoBean> lista = new ArrayList<RelVendaProdutoBean>();
        String sql = "";

        RelVendaProdutoBean venda;

        try {

            if (!cid && !eq) {
                sql = "SELECT "
                        + "produto, "
                        + "sum(consignado) as consignados, "
                        + "sum(vl_consignado) as vl_consignado, "
                        + "sum(vendido) as vendidos, "
                        + "sum(vl_vendido) as vl_vendido, "
                        + "sum(devolvido) as devolvidos, "
                        + "sum(vl_devolvido) as vl_devolvido  "
                        + "FROM vw_movimento_itens "
                        + "where movimento between ? and ? group by produto order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setDate(1, dtInical);
                pstm.setDate(2, dtFinal);
            } else if (cid && !eq) {
                sql = "SELECT "
                        + "produto, "
                        + "sum(consignado) as consignados, "
                        + "sum(vl_consignado) as vl_consignado, "
                        + "sum(vendido) as vendidos, "
                        + "sum(vl_vendido) as vl_vendido, "
                        + "sum(devolvido) as devolvidos, "
                        + "sum(vl_devolvido) as vl_devolvido  "
                        + "FROM vw_movimento_itens "
                        + "where cidade = ? and movimento between ? and ? group by produto order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidade);
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!cid && eq) {
                sql = "SELECT "
                        + "produto, "
                        + "sum(consignado) as consignados, "
                        + "sum(vl_consignado) as vl_consignado, "
                        + "sum(vendido) as vendidos, "
                        + "sum(vl_vendido) as vl_vendido, "
                        + "sum(devolvido) as devolvidos, "
                        + "sum(vl_devolvido) as vl_devolvido  "
                        + "FROM vw_movimento_itens "
                        + "where equipe = ? and movimento between ? and ? group by produto order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, equipe);
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (cid && eq) {
                sql = "SELECT "
                        + "produto, "
                        + "sum(consignado) as consignados, "
                        + "sum(vl_consignado) as vl_consignado, "
                        + "sum(vendido) as vendidos, "
                        + "sum(vl_vendido) as vl_vendido, "
                        + "sum(devolvido) as devolvidos, "
                        + "sum(vl_devolvido) as vl_devolvido  "
                        + "FROM vw_movimento_itens "
                        + "where equipe = ? and cidade = ? and movimento between ? and ? group by produto order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, equipe);
                pstm.setString(2, cidade);
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            }

            rs = pstm.executeQuery();
            while (rs.next()) {
                venda = new RelVendaProdutoBean();
                venda.setProduto(rs.getString("produto"));
                venda.setConsignados(rs.getInt("consignados"));
                venda.setVlConsigando(rs.getBigDecimal("vl_consignado"));
                venda.setVendidos(rs.getInt("vendidos"));
                venda.setVlVendido(rs.getBigDecimal("vl_vendido"));
                venda.setDevolvidos(rs.getInt("devolvidos"));
                venda.setVlDevolvidos(rs.getBigDecimal("vl_devolvido"));
                lista.add(venda);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao listar itens do relatório.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(RelVendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage());
                Logger.getLogger(RelVendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public List<RelBrindeProdutoBean> listarItensBrinde(String cidade, String equipe, Date dtInical, Date dtFinal, boolean eq, boolean cid) {
        List<RelBrindeProdutoBean> lista = new ArrayList<RelBrindeProdutoBean>();
        String sql = "";

        RelBrindeProdutoBean brinde;

        try {

            if (!cid && !eq) {
                sql = "select "
                        + "produto, "
                        + "sum(quantidade) as quantidade, "
                        + "sum(valor) as valor, "
                        + "sum(total) as total from vw_movimento_brindes "
                        + "where movimento between ? and ? group by produto "
                        + "order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setDate(1, dtInical);
                pstm.setDate(2, dtFinal);
            } else if (cid && !eq) {
                sql = "select "
                        + "produto, "
                        + "sum(quantidade) as quantidade, "
                        + "sum(valor) as valor, "
                        + "sum(total) as total from vw_movimento_brindes "
                        + "where cidade = ? and movimento between ? and ? group by produto "
                        + "order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, cidade);
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (!cid && eq) {
                sql = "select "
                        + "produto, "
                        + "sum(quantidade) as quantidade, "
                        + "sum(valor) as valor, "
                        + "sum(total) as total from vw_movimento_brindes "
                        + "where equipe = ? and movimento between ? and ? group by produto "
                        + "order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, equipe);
                pstm.setDate(2, dtInical);
                pstm.setDate(3, dtFinal);
            } else if (cid && eq) {
                sql = "select "
                        + "produto, "
                        + "sum(quantidade) as quantidade, "
                        + "sum(valor) as valor, "
                        + "sum(total) as total from vw_movimento_brindes "
                        + "where equipe = ? and cidade = ? and movimento between ? and ? group by produto "
                        + "order by produto";
                pstm = ConexaoPostgres.conectar().prepareStatement(sql);
                pstm.setString(1, equipe);
                pstm.setString(2, cidade);
                pstm.setDate(3, dtInical);
                pstm.setDate(4, dtFinal);
            }

            rs = pstm.executeQuery();
            while (rs.next()) {
                brinde = new RelBrindeProdutoBean();
                brinde.setProduto(rs.getString("produto"));
                brinde.setQuantidade(rs.getInt("quantidade"));
                brinde.setValor(rs.getBigDecimal("valor"));
                brinde.setTotal(rs.getBigDecimal("total"));
                lista.add(brinde);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao listar itens do relatório.\n"
                    + "Motivo: "
                    + ex.getMessage());
            Logger.getLogger(RelVendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                fecharPrepareStatement();
                ConexaoPostgres.desconectar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao fechar ResultSet.\n"
                        + "Motivo: "
                        + ex.getMessage());
                Logger.getLogger(RelVendaProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}
