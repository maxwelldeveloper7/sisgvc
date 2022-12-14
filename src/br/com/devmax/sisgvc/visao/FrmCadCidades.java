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

import br.com.devmax.sisgvc.controle.Controle;
import br.com.devmax.sisgvc.daos.CidadeDAO;
import br.com.devmax.sisgvc.modelo.CidadeBean;
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
public class FrmCadCidades extends javax.swing.JDialog {

    /** Creates new form FrmCadPerfil */
    public FrmCadCidades(java.awt.Frame parent, boolean modal) {
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
        tbCidades = new javax.swing.JTable();
        lbMedida = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/city32.png"))); // NOI18N
        jLabel1.setText("Cadastro de Cidades");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbCidades.setModel(tableModel);
        jScrollPane1.setViewportView(tbCidades);

        lbMedida.setText("Cidade:");

        tfCidade.setEditable(false);

        jLabel2.setText("UF:");

        cbUf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cbUf.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMedida)
                            .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMedida)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(painelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-449)/2, (screenSize.height-384)/2, 449, 384);
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
    private javax.swing.JComboBox cbUf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMedida;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JTable tbCidades;
    private javax.swing.JTextField tfCidade;
    // End of variables declaration//GEN-END:variables
    private static List<CidadeBean> lista = new ArrayList<CidadeBean>();
    private static CidadeBean cidadeBean = new CidadeBean();
    //-Código referente a tabela--------------------------------------------------------------------------------
    private DefaultTableModel tableModel = new DefaultTableModel(null, new String[]{"Cidade", "UF"});
    private ListSelectionModel lms;
    private int operacao = 0;//se 1: novo, se 2: editar

    private void tbLinhaSelecionada(JTable tb) {
        if (tb.getSelectedRow() != -1) {
            cidadeBean.setId(lista.get(tbCidades.getSelectedRow()).getId());
            tfCidade.setText(lista.get(tbCidades.getSelectedRow()).getCidade());
            cbUf.setSelectedItem(lista.get(tbCidades.getSelectedRow()).getUf());
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

        tbCidades.getColumnModel().getColumn(0).setPreferredWidth(400);
        tbCidades.setModel(tableModel);
        tbCidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lms = tbCidades.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tbLinhaSelecionada(tbCidades);
                }
            }
        });
        atualizarDadosDaTabela();
    }

    private void atualizarDadosDaTabela() {
        CidadeDAO dao = new CidadeDAO();
        lista = dao.listar();

        if (tableModel.getRowCount() > 0) {
            limparTabela();
        }

        String[] campos = {null,null};
        

        for (int i = 0; i < lista.size(); i++) {
            tableModel.addRow(campos);
            tableModel.setValueAt(lista.get(i).getCidade(), i, 0);
            tableModel.setValueAt(lista.get(i).getUf(), i, 1);
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
            CidadeDAO dao = new CidadeDAO();
            dao.excluir(cidadeBean);
            reiniciaTela();
        }
    }

    private void salvar() {
        //setamos o objeto MedidaBean
        setaCidadeBean();
        //Instanciamos o DAO
        CidadeDAO dao = new CidadeDAO();
        //verifica qual será a operação de peristência a ser realizada
        if (operacao == 1) {
            dao.inserir(cidadeBean);
        }
        if (operacao == 2) {
            dao.alterar(cidadeBean);
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
        tfCidade.setText("");
        cbUf.setSelectedIndex(0);
        tfCidade.requestFocus();
    }

    private void editarCampos(boolean b) {
        tfCidade.setEditable(b);
        cbUf.setEnabled(b);
        tbCidades.setEnabled(!b);
        tfCidade.requestFocus();
    }

    public void reiniciaTela() {
        tbCidades.clearSelection();
        atualizarDadosDaTabela();
        limparCampos();
        reiniciaBotoes();
        editarCampos(false);
        operacao = 0;
        if(Controle.frmCadCliente!=null){
            Controle.frmCadCliente.atualizaCbCidades();
        }
    }

    private void reiniciaBotoes() {
        btNovo.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        btSalvar.setEnabled(false);
    }

    private void setaCidadeBean() {
        cidadeBean.setCidade(tfCidade.getText());
        cidadeBean.setUf(cbUf.getSelectedItem().toString());
    }
}
