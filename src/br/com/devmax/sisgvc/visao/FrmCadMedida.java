/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmCadPerfil.java
 *
 * Created on 06/07/2011, 10:36:34
 */
package br.com.devmax.sisgvc.visao;

import br.com.devmax.sisgvc.daos.MedidaDAO;
import br.com.devmax.sisgvc.modelo.MedidaBean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxwell
 */
public class FrmCadMedida extends javax.swing.JDialog {

    /** Creates new form FrmCadPerfil */
    public FrmCadMedida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        InicializarTabela();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelBotoes = new javax.swing.JPanel();
        btNovo = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedidas = new javax.swing.JTable();
        lbMedida = new javax.swing.JLabel();
        tfMedida = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGVC - Sistema Gerenciador de Vendas por Consignação");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/file-new-16x16.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });
        painelBotoes.add(btNovo);

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/edit-16x16.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setEnabled(false);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        painelBotoes.add(btEditar);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/edit-delete-16x16.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setEnabled(false);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        painelBotoes.add(btExcluir);

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/file-save-16x16.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        painelBotoes.add(btSalvar);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/Units-2-32.png"))); // NOI18N
        jLabel1.setText("Cadastro de Unidades de Medidas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbMedidas.setModel(tableModel);
        jScrollPane1.setViewportView(tbMedidas);

        lbMedida.setText("Medida:");

        tfMedida.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(lbMedida)
                    .addComponent(tfMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbMedida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelBotoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-450)/2, (screenSize.height-369)/2, 450, 369);
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        novaMedida();
    }//GEN-LAST:event_btNovoActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editarMedida();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluirExcluir();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        reiniciaTela();
    }//GEN-LAST:event_formWindowClosing

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMedida;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tbMedidas;
    private javax.swing.JTextField tfMedida;
    // End of variables declaration//GEN-END:variables
    private static List<MedidaBean> lista = new ArrayList<MedidaBean>();
    private static MedidaBean mediaBean = new MedidaBean();
    //-Código referente a tabela--------------------------------------------------------------------------------
    private DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Medida"});
    private ListSelectionModel lms;
    private int operacao = 0;//se 1: novo, se 2: editar

    private void tbLinhaSelecionada(JTable tb) {
        if (tb.getSelectedRow() != -1) {
            mediaBean.setId(lista.get(tbMedidas.getSelectedRow()).getId());
            tfMedida.setText(lista.get(tbMedidas.getSelectedRow()).getMedida());
            btEditar.setEnabled(true);
            btExcluir.setEnabled(true);
            operacao = 2;
        } else {
            limparCampos();
            editarCampos(false);
            reiniciaBotoes();
        }
    }

    private void InicializarTabela() {

        tbMedidas.getColumnModel().getColumn(0).setPreferredWidth(400);
        tbMedidas.setModel(tableModel);
        tbMedidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lms = tbMedidas.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tbLinhaSelecionada(tbMedidas);
                }
            }
        });
        atualizarDadosDaTabela();
    }

    private void atualizarDadosDaTabela() {
        MedidaDAO dao = new MedidaDAO();
        lista = dao.listar();

        if (tableModel.getRowCount() > 0) {
            limparTabela();
        }

        String[] campos = {null};
        

        for (int i = 0; i < lista.size(); i++) {
            tableModel.addRow(campos);
            tableModel.setValueAt(lista.get(i).getMedida(), i, 0);
        }
    }

    private void limparTabela() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }
    //-Código referente a tabela--------------------------------------------------------------------------------

    private void novaMedida() {
        habilitaBotoesParaEdicao(true);
        limparCampos();
        operacao = 1;
    }

    private void editarMedida() {
        habilitaBotoesParaEdicao(true);
    }

    private void excluirExcluir() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta unidade de medida?", null, JOptionPane.OK_CANCEL_OPTION);
        System.out.println(opcao);
        if (opcao == 0) {
            MedidaDAO dao = new MedidaDAO();
            dao.excluir(mediaBean);
            reiniciaTela();
        }
    }

    private void salvar() {
        //setamos o objeto MedidaBean
        setaMedidaBean();
        //Instanciamos o DAO
        MedidaDAO dao = new MedidaDAO();
        //verifica qual será a operação de peristência a ser realizada
        if (operacao == 1) {
            dao.inserir(mediaBean);
        }
        if (operacao == 2) {
            dao.alterar(mediaBean);
        }
        habilitaBotoesParaEdicao(false);
        reiniciaTela();
    }

    private void habilitaBotoesParaEdicao(boolean edicao) {

        if (edicao) {
            btNovo.setEnabled(false);
            btEditar.setEnabled(false);
            btExcluir.setEnabled(false);
            btSalvar.setEnabled(true);
        } else {
            btNovo.setEnabled(true);
            btEditar.setEnabled(true);
            btExcluir.setEnabled(true);
            btSalvar.setEnabled(false);
        }
        editarCampos(edicao);
    }

    private void limparCampos() {
        tfMedida.setText("");
        tfMedida.requestFocus();
    }

    private void editarCampos(boolean b) {
        tfMedida.setEditable(b);
        tbMedidas.setEnabled(!b);
        tfMedida.requestFocus();
    }

    public void reiniciaTela() {
        tbMedidas.clearSelection();
        atualizarDadosDaTabela();
        limparCampos();
        reiniciaBotoes();
        editarCampos(false);
        operacao = 0;
    }

    private void reiniciaBotoes() {
        btNovo.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
    }

    private void setaMedidaBean() {
        //O medidaBean.setId() já foi setado no método tbLinhaSelecionada para persistir uma alteração
        mediaBean.setMedida(tfMedida.getText());
    }
}
