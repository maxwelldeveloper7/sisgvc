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

import br.com.devmax.sisgvc.daos.CidadeDAO;
import br.com.devmax.sisgvc.daos.EquipeDAO;
import br.com.devmax.sisgvc.daos.RelVendaProdutoDAO;
import br.com.devmax.sisgvc.modelo.CidadeBean;
import br.com.devmax.sisgvc.modelo.EquipeBean;
import br.com.devmax.sisgvc.modelo.RelBrindeProdutoBean;
import br.com.devmax.sisgvc.modelo.RelVendaProdutoBean;
import br.com.devmax.sisgvc.modelo.Utilidades;
import br.com.devmax.sisgvc.modelo.PedidoBean;
import br.com.devmax.sisgvc.visao.modelos.ColunasNumericas;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Maxwell
 */
public class FrmVendasPorProduto extends javax.swing.JDialog {

    /** Creates new form FrmCadPerfil */
    public FrmVendasPorProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarCbEquipe();
        carregarCbCidade();
        inicializarDatas();
        inicializarTabelaItens();
        inicializarTabelaBrindes();
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
        btPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVendas = new javax.swing.JTable();
        chEquipe = new javax.swing.JCheckBox();
        cbEquipe = new javax.swing.JComboBox();
        cbcidade = new javax.swing.JComboBox();
        chCidade = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        lbDataInical1 = new javax.swing.JLabel();
        lbDataFinal1 = new javax.swing.JLabel();
        ftfDataFinalv = new javax.swing.JFormattedTextField();
        ftfDataInicial = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbBrindes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ftfConsigandos = new javax.swing.JFormattedTextField();
        ftfVlConsignados = new javax.swing.JFormattedTextField();
        ftfVendidos = new javax.swing.JFormattedTextField();
        ftfVlVendidos = new javax.swing.JFormattedTextField();
        ftfDevolvidos = new javax.swing.JFormattedTextField();
        ftfVlDevolvidos = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        ftfBrindes = new javax.swing.JFormattedTextField();
        ftfVlBrindes = new javax.swing.JFormattedTextField();
        btRelatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGVC - Sistema Gerenciador de Vendas por Consignação");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/product-sales-report-32.png"))); // NOI18N
        jLabel1.setText("Vendas por Produto");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/search-16x16.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tbVendas.setModel(tableModelItens);
        jScrollPane1.setViewportView(tbVendas);

        chEquipe.setText("Equipe");

        chCidade.setText("Cidade");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbDataInical1.setText("Inicio:");

        lbDataFinal1.setText("Fim:");

        try {
            ftfDataFinalv.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataFinalv.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        try {
            ftfDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataInicial.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel2.setText("Data de Movimento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDataInical1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataFinal1)
                            .addComponent(ftfDataFinalv, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbDataInical1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbDataFinal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfDataFinalv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Vendas:");

        jLabel4.setText("Brindes:");

        tbBrindes.setModel(tableModelbrindes);
        jScrollPane2.setViewportView(tbBrindes);

        jLabel5.setText("Total Consigandos:");

        jLabel6.setText("Total Vendidos:");

        jLabel7.setText("Total Devolvidos:");

        ftfConsigandos.setEditable(false);
        ftfConsigandos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        ftfConsigandos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVlConsignados.setEditable(false);
        ftfVlConsignados.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        ftfVlConsignados.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVendidos.setEditable(false);
        ftfVendidos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.###"))));
        ftfVendidos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVlVendidos.setEditable(false);
        ftfVlVendidos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        ftfVlVendidos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfDevolvidos.setEditable(false);
        ftfDevolvidos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.###"))));
        ftfDevolvidos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVlDevolvidos.setEditable(false);
        ftfVlDevolvidos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        ftfVlDevolvidos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setText("Total Brindes:");

        ftfBrindes.setEditable(false);
        ftfBrindes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        ftfBrindes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        ftfVlBrindes.setEditable(false);
        ftfVlBrindes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        ftfVlBrindes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ftfConsigandos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(ftfVendidos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ftfDevolvidos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ftfVlVendidos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ftfVlDevolvidos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ftfVlConsignados, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(555, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addContainerGap(768, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chEquipe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEquipe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(193, 193, 193)
                .addComponent(btPesquisar)
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfBrindes, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftfVlBrindes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(577, 577, 577))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chEquipe)
                            .addComponent(cbEquipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisar))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chCidade)
                            .addComponent(cbcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ftfVlConsignados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfConsigandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(ftfVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfVlVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ftfVlDevolvidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDevolvidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(ftfVlBrindes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfBrindes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btRelatorio.setText("Gerar Relatório");
        btRelatorio.setEnabled(false);
        btRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 556, Short.MAX_VALUE)
                        .addComponent(btRelatorio)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btRelatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-857)/2, (screenSize.height-713)/2, 857, 713);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        reiniciaTela();
    }//GEN-LAST:event_formWindowClosing

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        atualizarDadosDaTabelaItens();
        atualizarDadosDaTabelaBrindes();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelatorioActionPerformed
        //abrirRelatorio(lista);
    }//GEN-LAST:event_btRelatorioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btRelatorio;
    private javax.swing.JComboBox cbEquipe;
    private javax.swing.JComboBox cbcidade;
    private javax.swing.JCheckBox chCidade;
    private javax.swing.JCheckBox chEquipe;
    private javax.swing.JFormattedTextField ftfBrindes;
    private javax.swing.JFormattedTextField ftfConsigandos;
    private javax.swing.JFormattedTextField ftfDataFinalv;
    private javax.swing.JFormattedTextField ftfDataInicial;
    private javax.swing.JFormattedTextField ftfDevolvidos;
    private javax.swing.JFormattedTextField ftfVendidos;
    private javax.swing.JFormattedTextField ftfVlBrindes;
    private javax.swing.JFormattedTextField ftfVlConsignados;
    private javax.swing.JFormattedTextField ftfVlDevolvidos;
    private javax.swing.JFormattedTextField ftfVlVendidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDataFinal1;
    private javax.swing.JLabel lbDataInical1;
    private javax.swing.JTable tbBrindes;
    private javax.swing.JTable tbVendas;
    // End of variables declaration//GEN-END:variables
    private static List<RelVendaProdutoBean> itensVenda = new ArrayList<RelVendaProdutoBean>();
    private static List<RelBrindeProdutoBean> brindes = new ArrayList<RelBrindeProdutoBean>();
    private static PedidoBean vendaBeanLinha;
    private static List<EquipeBean> equipes = new ArrayList<EquipeBean>();
    private static List<CidadeBean> cidades = new ArrayList<CidadeBean>();
    //-Código referente a tabela de itens de venda--------------------------------------------------------------------------------
    private DefaultTableModel tableModelItens = new DefaultTableModel(null, new String[]{"Produto", "Consignados", "Valor(R$)", "Vendidos", "Valor(R$)", "Devolvidos", "Valor(R$)"});
    private ListSelectionModel lms;

    private void tbLinhaSelecionada(JTable tb) {
        if (tb.getSelectedRow() != -1) {
            vendaBeanLinha = new PedidoBean();
            /*   vendaBeanLinha.setId(lista.get(tb.getSelectedRow()).getId());
            vendaBeanLinha.setEquipe(lista.get(tb.getSelectedRow()).getEquipe());
            vendaBeanLinha.setCliente(lista.get(tb.getSelectedRow()).getCliente());
            vendaBeanLinha.setDataEntrega(lista.get(tb.getSelectedRow()).getDataEntrega());
            vendaBeanLinha.setDataVencimento(lista.get(tb.getSelectedRow()).getDataVencimento());
            vendaBeanLinha.setStatus(lista.get(tb.getSelectedRow()).getStatus());
            vendaBeanLinha.setVendedor(lista.get(tb.getSelectedRow()).getVendedor());
            vendaBeanLinha.setDesconto(lista.get(tb.getSelectedRow()).getDesconto());
            vendaBeanLinha.setValorPedido(lista.get(tb.getSelectedRow()).getValorPedido());
            vendaBeanLinha.setValorRecebido(lista.get(tb.getSelectedRow()).getValorRecebido());*/
        } else {
            vendaBeanLinha = null;
        }
    }

    private void inicializarTabelaItens() {

        ColunasNumericas cnD = new ColunasNumericas();
        ColunasNumericas cnC = new ColunasNumericas();
        cnC.setHorizontalAlignment(SwingConstants.CENTER);
        cnD.setHorizontalAlignment(SwingConstants.RIGHT);
        tbVendas.getColumnModel().getColumn(0).setPreferredWidth(370);
        tbVendas.getColumnModel().getColumn(1).setCellRenderer(cnD);
        tbVendas.getColumnModel().getColumn(2).setCellRenderer(cnD);
        tbVendas.getColumnModel().getColumn(3).setCellRenderer(cnD);
        tbVendas.getColumnModel().getColumn(4).setCellRenderer(cnD);
        tbVendas.getColumnModel().getColumn(5).setCellRenderer(cnD);
        tbVendas.getColumnModel().getColumn(6).setCellRenderer(cnD);
        tbVendas.setModel(tableModelItens);
        tbVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lms = tbVendas.getSelectionModel();
        lms.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    tbLinhaSelecionada(tbVendas);
                }
            }
        });
        atualizarDadosDaTabelaItens();
    }

    public void atualizarDadosDaTabelaItens() {
        RelVendaProdutoDAO dao = new RelVendaProdutoDAO();

        itensVenda = dao.listarItensVenda(cbcidade.getSelectedItem().toString(), cbEquipe.getSelectedItem().toString(), Utilidades.formataDataSQL(ftfDataInicial.getText()), Utilidades.formataDataSQL(ftfDataFinalv.getText()), chEquipe.isSelected(), chCidade.isSelected());

        if (tableModelItens.getRowCount() > 0) {
            limparTabelaItens();
        }

        Integer consignados = 0;
        Integer vendidos = 0;
        Integer devolvidos = 0;
        BigDecimal vlConsigandos = new BigDecimal(0.00);
        BigDecimal vlVendidos = new BigDecimal(0.00);
        BigDecimal vlDevolvidos = new BigDecimal(0.00);

        String[] campos = {null, null, null, null, null, null, null};

        for (int i = 0; i < itensVenda.size(); i++) {
            tableModelItens.addRow(campos);
            tableModelItens.setValueAt(itensVenda.get(i).getProduto(), i, 0);
            if (itensVenda.get(i).getConsignados() < 0) {
                tableModelItens.setValueAt(0, i, 1);
                tableModelItens.setValueAt(Utilidades.formataMontetarioSTR(new BigDecimal("0.00") ), i, 2);
            } else {
                tableModelItens.setValueAt(itensVenda.get(i).getConsignados(), i, 1);
                tableModelItens.setValueAt(itensVenda.get(i).getVlConsignadoSTR(), i, 2);
                consignados = consignados + itensVenda.get(i).getConsignados();
                vlConsigandos = vlConsigandos.add(itensVenda.get(i).getVlConsigando());

            }
            tableModelItens.setValueAt(itensVenda.get(i).getVendidos(), i, 3);
            tableModelItens.setValueAt(itensVenda.get(i).getVlVendidoSTR(), i, 4);
            tableModelItens.setValueAt(itensVenda.get(i).getDevolvidos(), i, 5);
            tableModelItens.setValueAt(itensVenda.get(i).getVlDevolvidoSTR(), i, 6);

            vendidos = vendidos + itensVenda.get(i).getVendidos();
            vlVendidos = vlVendidos.add(itensVenda.get(i).getVlVendido());
            devolvidos = devolvidos + itensVenda.get(i).getDevolvidos();
            vlDevolvidos = vlDevolvidos.add(itensVenda.get(i).getVlDevolvido());
        }

        ftfConsigandos.setText(consignados.toString());
        ftfVendidos.setText(vendidos.toString());
        ftfDevolvidos.setText(devolvidos.toString());
        ftfVlConsignados.setText(Utilidades.formataMontetarioSTR(vlConsigandos));
        ftfVlVendidos.setText(Utilidades.formataMontetarioSTR(vlVendidos));
        ftfVlDevolvidos.setText(Utilidades.formataMontetarioSTR(vlDevolvidos));
    }

    private void limparTabelaItens() {
        while (tableModelItens.getRowCount() > 0) {
            tableModelItens.removeRow(0);
        }
    }
    //-Código referente a tabela de brindes--------------------------------------------------------------------------------
    private DefaultTableModel tableModelbrindes = new DefaultTableModel(null, new String[]{"Produto", "Quantidade", "Valor(R$)"});

    private void inicializarTabelaBrindes() {

        ColunasNumericas cnD = new ColunasNumericas();
        ColunasNumericas cnC = new ColunasNumericas();
        cnC.setHorizontalAlignment(SwingConstants.CENTER);
        cnD.setHorizontalAlignment(SwingConstants.RIGHT);
        tbBrindes.getColumnModel().getColumn(0).setPreferredWidth(370);
        tbBrindes.getColumnModel().getColumn(1).setCellRenderer(cnD);
        tbBrindes.getColumnModel().getColumn(2).setCellRenderer(cnD);
        //tbBrindes.getColumnModel().getColumn(3).setCellRenderer(cnD);
        tbBrindes.setModel(tableModelbrindes);
        tbBrindes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        atualizarDadosDaTabelaBrindes();
    }

    public void atualizarDadosDaTabelaBrindes() {
        RelVendaProdutoDAO dao = new RelVendaProdutoDAO();

        brindes = dao.listarItensBrinde(cbcidade.getSelectedItem().toString(), cbEquipe.getSelectedItem().toString(), Utilidades.formataDataSQL(ftfDataInicial.getText()), Utilidades.formataDataSQL(ftfDataFinalv.getText()), chEquipe.isSelected(), chCidade.isSelected());

        if (tableModelbrindes.getRowCount() > 0) {
            limparTabelaBrindes();
        }

        Integer brinde = 0;
        BigDecimal vlBrindes = new BigDecimal(0.00);

        String[] campos = {null, null, null};


        for (int i = 0; i < brindes.size(); i++) {
            tableModelbrindes.addRow(campos);
            tableModelbrindes.setValueAt(brindes.get(i).getProduto(), i, 0);
            tableModelbrindes.setValueAt(brindes.get(i).getQuantidade(), i, 1);
            tableModelbrindes.setValueAt(brindes.get(i).getValorSTR(), i, 2);
            //tableModelbrindes.setValueAt(brindes.get(i).getTotalSTR(), i, 3);
            brinde = brinde + brindes.get(i).getQuantidade();
            vlBrindes = vlBrindes.add(brindes.get(i).getTotal());
        }

        ftfBrindes.setText(brinde.toString());
        ftfVlBrindes.setText(Utilidades.formataMontetarioSTR(vlBrindes));
    }

    private void limparTabelaBrindes() {
        while (tableModelbrindes.getRowCount() > 0) {
            tableModelbrindes.removeRow(0);
        }
    }

    public void reiniciaTela() {
        carregarCbEquipe();
        atualizarDadosDaTabelaItens();
        atualizarDadosDaTabelaBrindes();
    }

    private void inicializarDatas() {
        Calendar data = Calendar.getInstance();
        data.setTime(new Date());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String novoFormato;
        novoFormato = formatador.format(data.getTime());
        ftfDataFinalv.setText(novoFormato);
        data.add(Calendar.DAY_OF_MONTH, -30);
        novoFormato = formatador.format(data.getTime());
        ftfDataInicial.setText(novoFormato);
    }

    private void carregarCbEquipe() {
        EquipeDAO edao = new EquipeDAO();
        equipes = edao.listar();
        cbEquipe.removeAllItems();
        for (int i = 0; i < equipes.size(); i++) {
            cbEquipe.addItem(equipes.get(i).getNome());
        }
    }

    private void carregarCbCidade() {
        CidadeDAO cidDao = new CidadeDAO();
        cidades = cidDao.listar();
        cbcidade.removeAllItems();
        for (int i = 0; i < cidades.size(); i++) {
            cbcidade.addItem(cidades.get(i).getCidade());
        }
    }

    private void abrirRelatorio(List<PedidoBean> v) {

        try {
            String arquivoJasper = "vendas.jasper";
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(v);
            HashMap map = new HashMap();
            JasperPrint print = JasperFillManager.fillReport(arquivoJasper, map, ds);
            JasperViewer.viewReport(print, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Falha ao gerar relatório!\n" + e.getMessage());
            System.out.print(e.getStackTrace());
        }
    }
}
