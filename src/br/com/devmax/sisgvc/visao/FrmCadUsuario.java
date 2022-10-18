/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmCadAdmin.java
 *
 * Created on 13/06/2011, 14:54:29
 */
package br.com.devmax.sisgvc.visao;

import br.com.devmax.sisgvc.controle.Controle;
import br.com.devmax.sisgvc.daos.PerfilDAO;
import br.com.devmax.sisgvc.daos.UsuarioDAO;
import br.com.devmax.sisgvc.modelo.PerfilBean;
import br.com.devmax.sisgvc.modelo.UsuarioBean;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class FrmCadUsuario extends javax.swing.JDialog {

    /** Creates new form FrmCadAdmin */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FrmCadUsuario(java.awt.Frame parent, boolean modal, UsuarioBean usuarioBean) {
        super(parent, modal);
        initComponents();
        carregarCbPerfil();
        verificarOperacao(usuarioBean);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tfLogin = new javax.swing.JTextField();
        chbAtivo = new javax.swing.JCheckBox();
        lbSenha = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lbLogin = new javax.swing.JLabel();
        ptfSenha = new javax.swing.JPasswordField();
        lbNome = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox();
        lbPerfil = new javax.swing.JLabel();
        lbmsNome = new javax.swing.JLabel();
        lbmsNome.setVisible(false);
        lbmsLogin = new javax.swing.JLabel();
        lbmsLogin.setVisible(false);
        lbmsSenha = new javax.swing.JLabel();
        lbmsSenha.setVisible(false);
        lbmsPerfil = new javax.swing.JLabel();
        lbmsPerfil.setVisible(false);
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGVC - Sistema Gerenciador de Vendas por Consignação");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfLoginFocusGained(evt);
            }
        });

        chbAtivo.setText("Ativo");

        lbSenha.setText("Senha:");

        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNomeFocusGained(evt);
            }
        });

        lbLogin.setText("Login:");

        ptfSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ptfSenhaFocusGained(evt);
            }
        });

        lbNome.setText("Nome:");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador" }));
        cbPerfil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbPerfilFocusGained(evt);
            }
        });

        lbPerfil.setText("Perfil:");

        lbmsNome.setForeground(new java.awt.Color(255, 0, 0));
        lbmsNome.setText("Informe o nome do usuário.");
        lbmsNome.setFocusable(false);

        lbmsLogin.setForeground(new java.awt.Color(255, 0, 0));
        lbmsLogin.setText("Informe o login do usuário.");
        lbmsLogin.setFocusable(false);

        lbmsSenha.setForeground(new java.awt.Color(255, 0, 0));
        lbmsSenha.setText("Informe a senha do usuário.");
        lbmsSenha.setFocusable(false);

        lbmsPerfil.setForeground(new java.awt.Color(255, 0, 0));
        lbmsPerfil.setText("Selecione um perfil");
        lbmsPerfil.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbmsNome)
                        .addGap(217, 217, 217))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbmsLogin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chbAtivo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ptfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbmsSenha)
                                        .addGap(54, 54, 54)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbPerfil)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbmsPerfil))
                                    .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmsNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmsLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPerfil)
                    .addComponent(lbmsSenha)
                    .addComponent(lbmsPerfil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPerfil))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/devmax/sisgvc/visao/img/User-Administrator-Blue-32.png"))); // NOI18N
        jLabel1.setText("Dados do Usuário");

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btSalvar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-451)/2, (screenSize.height-304)/2, 451, 304);
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if(operacao == 1){
            cadastrarUsuario();
        }
        if(operacao == 2){
            atualizarUsuario();
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusGained
        lbmsNome.setVisible(false);
    }//GEN-LAST:event_tfNomeFocusGained

    private void tfLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLoginFocusGained
        lbmsLogin.setVisible(false);
    }//GEN-LAST:event_tfLoginFocusGained

    private void ptfSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ptfSenhaFocusGained
        lbmsSenha.setVisible(false);
    }//GEN-LAST:event_ptfSenhaFocusGained

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        limparCampos();
    }//GEN-LAST:event_formWindowClosed

    private void cbPerfilFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbPerfilFocusGained
        lbmsPerfil.setVisible(false);
    }//GEN-LAST:event_cbPerfilFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbPerfil;
    private javax.swing.JCheckBox chbAtivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbPerfil;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbmsLogin;
    private javax.swing.JLabel lbmsNome;
    private javax.swing.JLabel lbmsPerfil;
    private javax.swing.JLabel lbmsSenha;
    private javax.swing.JPasswordField ptfSenha;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
    private static List<PerfilBean> perfis = new ArrayList<PerfilBean>();
    private static int operacao;
    private static Integer idUsuario;

    private void cadastrarUsuario() {
        //verifica se os campos estão preenchidos.
        if (checarDados()) {
            //instancia um administrador do tipo usuário e seta os dados para seus atribuitos.
            UsuarioBean usuario = new UsuarioBean();
            usuario.setNoUsuario(tfNome.getText());
            usuario.setLogin(tfLogin.getText());
            usuario.setSenha(ptfSenha.getText());
            //setamos o perfil através do método getPerfilFromCB
            usuario.setPerfil(getPerfilFromCB(cbPerfil.getSelectedItem().toString()));

            UsuarioDAO dao = new UsuarioDAO();

            if (dao.inserir(usuario)) {
                Controle.listarUsuarios();
                Controle.frmUsuarios.atualizarDadosDaTabela();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "O usuário não foi cadastrado.");
            }
        }

    }

    private void atualizarUsuario() {
        //verifica se os campos estão preenchidos.
        if (checarDados()) {
            //instancia um administrador do tipo usuário e seta os dados para seus atribuitos.
            UsuarioBean usuario = new UsuarioBean();
            usuario.setId(idUsuario);
            usuario.setNoUsuario(tfNome.getText());
            usuario.setLogin(tfLogin.getText());
            usuario.setAtivo(chbAtivo.isSelected());
            usuario.setSenha(ptfSenha.getText());
            //setamos o perfil através do método getPerfilFromCB
            usuario.setPerfil(getPerfilFromCB(cbPerfil.getSelectedItem().toString()));

            UsuarioDAO dao = new UsuarioDAO();

            if (dao.alterar(usuario)) {
                Controle.listarUsuarios();
                Controle.frmUsuarios.atualizarDadosDaTabela();
                dispose();                
            } else {
                JOptionPane.showMessageDialog(this, "O usuário não foi cadastrado.");
            }
        }

    }

    private boolean checarDados() {
        boolean result = true;
        if (tfNome.getText().isEmpty()) {
            lbmsNome.setVisible(true);
            result = false;
        }
        if (tfLogin.getText().isEmpty()) {
            lbmsLogin.setVisible(true);
            result = false;
        }
        if (ptfSenha.getText().isEmpty()) {
            lbmsSenha.setVisible(true);
            result = false;
        }
        if (cbPerfil.getSelectedIndex()==0){
            lbmsPerfil.setVisible(true);
            result = false;
        }
        return result;
    }

    public void carregarCbPerfil() {

        PerfilDAO dao = new PerfilDAO();
        perfis = dao.listar();

        cbPerfil.removeAllItems();
        cbPerfil.addItem("");

        for (int i = 0; i < perfis.size(); i++) {
            cbPerfil.addItem(perfis.get(i).getNoPerfil());
        }
    }

    /**
     * Este método recebe uma String de um JComboBox e percorre a lista a procura
     * do elemento referente ao selecionado no combobox para ser setado no objeto bean.
     * @param item
     * @return PerfilBean
     */
    private PerfilBean getPerfilFromCB(String item) {
        PerfilBean perfil = new PerfilBean();
        for (int i = 0; i < perfis.size(); i++) {
            if (perfis.get(i).getNoPerfil().equals(item)) {
                perfil.setId(perfis.get(i).getId());
                perfil.setNoPerfil(perfis.get(i).getNoPerfil());
                perfil.setDsPerfil(perfis.get(i).getDsPerfil());
                break;
            }
        }
        return perfil;
    }

    private void verificarOperacao(UsuarioBean usuario) {
        if (usuario == null) {
            limparCampos();
            operacao = 1;//1 = cadastrar
        } else {
            idUsuario = usuario.getId();
            tfNome.setText(usuario.getNoUsuario());
            tfLogin.setText(usuario.getLogin());
            ptfSenha.setText(usuario.getSenha());
            cbPerfil.setSelectedItem(usuario.getPerfil().getNoPerfil());
            chbAtivo.setSelected(usuario.isAtivo());
            operacao = 2;//2 = atualizar
        }
    }

    private void limparCampos() {
        tfNome.setText("");
        tfLogin.setText("");
        ptfSenha.setText("");
        cbPerfil.setSelectedIndex(0);
        chbAtivo.setSelected(true);
    }
}
