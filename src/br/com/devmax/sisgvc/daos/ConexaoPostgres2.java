package br.com.devmax.sisgvc.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



import javax.swing.JOptionPane;

/**
 * @author Maxwell
 */
public class ConexaoPostgres2 {

    private static Connection con = null;
    //private static ComboPooledDataSource dataSource = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/SAC";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static ConexaoPostgres2 instanciaSingleton = null;

    private ConexaoPostgres2() {
    }

    public static ConexaoPostgres2 getInstancia() {
        if (instanciaSingleton == null) {
            instanciaSingleton = new ConexaoPostgres2();
        }
        return instanciaSingleton;
    }

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("\tBanco conectado!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoPostgres2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Driver não encontrado.\n" + ex.getMessage());
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao estabelecer conexão com o Banco de Dados.\n"
                    + "Motivo: "
                    + ex.getMessage() + "\n" + "A aplicação será encerrada...\n\n");
            System.exit(0);

        }
        return con;
    }

    public static void desconectar() {
        try {
            if (con != null) {
                con.close();
                //System.out.println("\tBanco desconectado!");
            } else {
                //System.out.println("Banco desconectado!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPostgres2.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao desconectar Banco de Dados.\n" + ex.getMessage());
            System.exit(0);
        }
    }

    public static void commit() {
        if (con != null) {
            try {
                con.commit();
                //System.out.println("\tTransação efetivada!");
            } catch (SQLException ex) {
                //System.out.println("\tFalha ao efetivar transação!");
                JOptionPane.showMessageDialog(null, "Falha ao efetivar transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void rollback() {
        if (con != null) {
            try {
                con.rollback();
                //System.out.println("\tTransação desfeita!");
            } catch (SQLException ex) {
                //System.out.println("\tFalha ao desfazer transação.");
                JOptionPane.showMessageDialog(null, "Falha ao desfazer transação.\n" + ex.getMessage());
                Logger.getLogger(ConexaoPostgres2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
