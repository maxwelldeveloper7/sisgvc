/*
 * JanelaPrincipal2.java
 *
 * Created on 3 de Setembro de 2006, 16:52
 */

package jcombobox;

import java.util.*;
import javax.swing.table.*;
import jtable.*;

/**
 *
 * @author  fernando
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    /** Creates new form JanelaPrincipal2 */
    public JanelaPrincipal() {
        initComponents();
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        escolheSistemaOperacional = new SistemaOperacionalJComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Java Magazine - Demo de JComboBox");
        jLabel1.setText("Sistema Operacional:");
        getContentPane().add(jLabel1, java.awt.BorderLayout.WEST);

        getContentPane().add(escolheSistemaOperacional, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox escolheSistemaOperacional;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    
}