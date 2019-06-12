/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.EquipeBean;
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
public class EquipeDAO extends GenericDAO {

    public boolean inserir(EquipeBean equipe) {
        String sql = "INSERT INTO equipes(no_equipe)VALUES (?)";
        return executeCommand(sql, equipe.getNome());
    }

    public boolean alterar(EquipeBean equipe) {
        String sql = "UPDATE equipes SET no_equipe=? WHERE id=?";
        return executeCommand(sql, equipe.getNome(), equipe.getId());
    }

    public void excluir(EquipeBean equipe) {
        String sql = "DELETE FROM equipes WHERE id = ?";
        executeCommand(sql, equipe.getId());
    }
    private ResultSet rs = null;

    public List<EquipeBean> listar() {
        List<EquipeBean> equipes = new ArrayList<EquipeBean>();
        String sql = "SELECT * FROM equipes ORDER BY no_equipe ASC";
        EquipeBean equipe;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("no_equipe"));
                equipes.add(equipe);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar equipes.");
            JOptionPane.showMessageDialog(null, "Falha ao listar equipes.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return equipes;
    }

    PreparedStatement pstm = null;

    public List<EquipeBean> listar(String param) {
        List<EquipeBean> equipes = new ArrayList<EquipeBean>();
        String sql = "SELECT * FROM equipes where no_equipe like ? ORDER BY no_equipe ASC";
        EquipeBean equipe;
        try {
            pstm = ConexaoPostgres.conectar().prepareStatement(sql);
            pstm.setString(1, param + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                equipe = new EquipeBean();
                equipe.setId(rs.getInt("id"));
                equipe.setNome(rs.getString("no_equipe"));
                equipes.add(equipe);
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao listar equipes.");
            JOptionPane.showMessageDialog(null, "Falha ao listar equipes.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);
            Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(EquipeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return equipes;
    }
}
