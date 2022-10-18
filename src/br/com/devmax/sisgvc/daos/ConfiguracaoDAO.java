/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.daos;

import br.com.devmax.sisgvc.modelo.ConfiguracaoBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class ConfiguracaoDAO extends GenericDAO{

    public void alterar(ConfiguracaoBean c) {
        String sql = "UPDATE configuracoes SET no_licenciada=?, cnpj_licenciada=?, dt_vencimento=?, ac_liberado=?, dt_bloqueio=? WHERE id=?";
        executeCommand(sql, c.getLicenciada(), c.getCnpj(), c.getVencimento(), c.getAcessoLiberado(), c.getBloqueio(), c.getId() );
    }

    private ResultSet rs = null;

    public ConfiguracaoBean listar() {
        String sql = "SELECT * FROM configuracoes where id = 1";
        ConfiguracaoBean c = new ConfiguracaoBean();
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setLicenciada(rs.getString("no_licenciada"));
                c.setCnpj(rs.getString("cnpj_licenciada"));
                c.setVencimento(rs.getDate("dt_vencimento"));
                c.setAcessoLiberado(rs.getBoolean("ac_liberado"));
                c.setBloqueio(rs.getDate("dt_bloqueio"));
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao verificar vencimenbto.");
            System.exit(0);
            Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
    
    public boolean liberado(){
        String sql = "SELECT * FROM configuracoes where id = 1";
        boolean result = true;
        try {
            rs = executeQuery(sql);
            while (rs.next()) {
                
                result = rs.getBoolean("ac_liberado");
                
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao verificar vencimenbto.");
            System.exit(0);
            Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
