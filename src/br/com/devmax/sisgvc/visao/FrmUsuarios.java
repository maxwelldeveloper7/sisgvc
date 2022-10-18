/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmUsuarios.java
 *
 * Created on 14/06/2011, 15:10:17
 */
package br.com.devmax.sisgvc.visao;

import br.com.devmax.sisgvc.controle.Controle;
import br.com.devmax.sisgvc.modelo.UsuarioBean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxwell
 */
public class FrmUsuarios extends javax.swing.JDialog {

    /** Creates new form FrmUsuarios */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FrmUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        InicializarTabela();
        atualizarDadosDaTabela();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btNovo = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGVC - Sistema Gerenciador de Vendas por Consignação");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/User-Group-32.png"))); // NOI18N
        jLabel1.setText("Cadastro de Usuários");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbUsuarios.setModel(tableModel);
        tbUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbUsuarios);

        btNovo.setText("Novo");
        btNovo.setFocusable(false);
        btNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btEditar.setText("Editar");
        btEditar.setEnabled(false);
        btEditar.setFocusable(false);
        btEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovo)
                    .addComponent(btEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-656)/2, (screenSize.height-424)/2, 656, 424);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        UsuarioBean usuario = null;
        FrmCadUsuario frmCadUsuario = new FrmCadUsuario(null, rootPaneCheckingEnabled, usuario);
        frmCadUsuario.setVisible(true);
        
    }//GEN-LAST:event_btNovoActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        UsuarioBean usuario = new UsuarioBean();
        usuario.setId(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).getId());
        usuario.setNoUsuario(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).getNoUsuario());
        usuario.setLogin(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).getLogin());
        usuario.setSenha(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).getSenha());
        usuario.setPerfil(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).getPerfil());
        usuario.setAtivo(Controle.getUsuarios().get(tbUsuarios.getSelectedRow()).isAtivo());
        FrmCadUsuario frmCadUsuario = new FrmCadUsuario(null, rootPaneCheckingEnabled, usuario);
        frmCadUsuario.setVisible(true);
    }//GEN-LAST:event_btEditarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUsuarios;
    // End of variables declaration//GEN-END:variables
    
    
    //-Código referente a tabela--------------------------------------------------------------------------------
    private DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Nome", "Login", "Perfil"});
    private ListSelectionModel lms;

    private void tbLinhaSelecionada(JTable tb) {
        if (tb.getSelectedRow() != -1) {
            btEditar.setEnabled(true);            
        } else {
            btEditar.setEnabled(false);           
        }
    }

    private void InicializarTabela() {

        tbUsuarios.getColumnModel().getColumn(0).setPreferredWidth(400);
        tbUsuarios.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbUsuarios.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbUsuarios.setModel(tableModel);
        tbUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lms = tbUsuarios.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tbLinhaSelecionada(tbUsuarios);
                }
            }
        });
    }

    public void atualizarDadosDaTabela() {
        List<UsuarioBean> lista = new ArrayList<UsuarioBean>();
        lista = Controle.getUsuarios();

        if (tableModel.getRowCount() > 0) {
            limparTabela();
        }

        String[] campos = {null, null, null};

        for (int i = 0; i < lista.size(); i++) {
            tableModel.addRow(campos);
            tableModel.setValueAt(lista.get(i).getNoUsuario(), i, 0);
            tableModel.setValueAt(lista.get(i).getLogin(), i, 1);
            tableModel.setValueAt(lista.get(i).getPerfil().getNoPerfil(), i, 2);            
        }

    }

    private void limparTabela() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    //-Código referente a tabela--------------------------------------------------------------------------------
}
