/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public abstract class GenericDAO2 {

    private static final long serialVersionUID = 1L;
    private static PreparedStatement ps = null;
    private static CallableStatement cs = null;

    public ResultSet executeQuery(String query, Object... params) {
        ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoPostgres2.conectar().prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();           
        } catch (SQLException ex) {
            System.out.println("Falha ao executar query.");
            ConexaoPostgres2.desconectar();
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet executeQuery(String query) {
        ps = null;
        ResultSet rs = null;
        try {
            ps = ConexaoPostgres2.conectar().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Falha ao executar query.");
            ConexaoPostgres2.desconectar();
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean executeCommand(String query, Object... params) {
        ps = null;
        try {
            ps = ConexaoPostgres2.conectar().prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Falha ao executar DML (Linguagem de Manipulação de Dados)."+ ex.getErrorCode());
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);

            if(ex.getErrorCode()==0){
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Falha ao executar DML",JOptionPane.WARNING_MESSAGE);
            }
            return false;
        } finally {
            fecharPrepareStatement();
        }

    }

    public boolean executeProcedure(String query, Object... params) {
        cs = null;
        try {
            cs = ConexaoPostgres2.conectar().prepareCall(query);
            for (int i = 0; i < params.length; i++) {
                cs.setObject(i + 1, params[i]);
            }
            cs.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Falha ao executar Procedure.");
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            fecharCallableStatement();
        }

    }

    public void fecharPrepareStatement() {
        try {
            if (ps != null) {
                ps.close();
                ConexaoPostgres2.desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao fechar preparedStatement.");
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fecharCallableStatement() {
        try {
            if (ps != null) {
                ps.close();
                ConexaoPostgres2.desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao fechar preparedStatement.");
            Logger.getLogger(GenericDAO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
