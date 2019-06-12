/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.controle;

import br.com.devmax.sisgvc.daos.ConfiguracaoDAO;
import br.com.devmax.sisgvc.daos.PerfilDAO;
import br.com.devmax.sisgvc.daos.PrivilegioDAO;
import br.com.devmax.sisgvc.daos.UsuarioDAO;
import br.com.devmax.sisgvc.modelo.BrindePedidoBean;
import br.com.devmax.sisgvc.modelo.ConfiguracaoBean;
import br.com.devmax.sisgvc.modelo.ItemPedidoBean;
import br.com.devmax.sisgvc.modelo.PerfilBean;
import br.com.devmax.sisgvc.modelo.PrivilegioBean;
import br.com.devmax.sisgvc.modelo.RotinaBean;
import br.com.devmax.sisgvc.modelo.SaidaBean;
import br.com.devmax.sisgvc.modelo.UsuarioBean;
import br.com.devmax.sisgvc.visao.FrmAcesso;
import br.com.devmax.sisgvc.visao.FrmCadAdmin;
import br.com.devmax.sisgvc.visao.FrmCadBrinde;
import br.com.devmax.sisgvc.visao.FrmCadCidades;
import br.com.devmax.sisgvc.visao.FrmCadCliente;
import br.com.devmax.sisgvc.visao.FrmCadEquipe;
import br.com.devmax.sisgvc.visao.FrmCadFornecedor;
import br.com.devmax.sisgvc.visao.FrmCadFuncionario;
import br.com.devmax.sisgvc.visao.FrmCadMedida;
import br.com.devmax.sisgvc.visao.FrmCadPerfil;
import br.com.devmax.sisgvc.visao.FrmCadProduto;
import br.com.devmax.sisgvc.visao.FrmCompras;
import br.com.devmax.sisgvc.visao.FrmPedidos;
import br.com.devmax.sisgvc.visao.FrmPerfis;
import br.com.devmax.sisgvc.visao.FrmPrincipal;
import br.com.devmax.sisgvc.visao.FrmUsuarios;
import br.com.devmax.sisgvc.visao.FrmVendasPorCliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class Controle {

    public static Controle instanciaSingleton = null;
    public static FrmPrincipal frmPrincipal = null;
    private static FrmCadAdmin frmCadAdmin = null;
    private static FrmAcesso frmAcesso = null;
    public static FrmUsuarios frmUsuarios = null;
    public static FrmPerfis frmPerfis = null;
    private static FrmCadPerfil frmCadPerfil = null;
    private static FrmCadEquipe frmCadEquipe = null;
    public static FrmCadCliente frmCadCliente = null;
    private static FrmCadFornecedor frmCadFornecedor = null;
    private static FrmCadFuncionario frmCadFuncionario = null;
    private static FrmCadMedida frmCadMedidas = null;
    private static FrmCadProduto frmCadProduto = null;
    private static FrmCadBrinde frmCadBrinde = null;
    public static FrmPedidos frmPedidos = null;
    public static FrmCompras frmCompras = null;
    public static FrmVendasPorCliente frmVendasPorCliente = null;
    private static FrmCadCidades frmCadCidades = null;
    //as listas de usuários, privilégios e rotinas são carregas na inicialização do sistema
    private static List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
    private static List<PrivilegioBean> privilegios = new ArrayList<PrivilegioBean>();
    private static List<RotinaBean> rotinas = new ArrayList<RotinaBean>();
    private static List<PerfilBean> perfis = new ArrayList<PerfilBean>();
    private static List<ItemPedidoBean> ItensList = new ArrayList<ItemPedidoBean>();
    private static List<BrindePedidoBean> brindesList = new ArrayList<BrindePedidoBean>();
    private static SaidaBean saidaBean = new SaidaBean();
    public static UsuarioBean usuario = null;

    public static Boolean checarVencimento() {
        ConfiguracaoBean configuracao = new ConfiguracaoBean();
        ConfiguracaoDAO dao = new ConfiguracaoDAO();

        configuracao = dao.listar();

        Calendar dataAtual = Calendar.getInstance(); //instanciamos data atual

        Calendar dataLicenca = Calendar.getInstance(); //instanciamos data da licença
        dataLicenca.setTime(configuracao.getVencimento());
        Calendar dataBloqueio = Calendar.getInstance();
        dataBloqueio.setTime(configuracao.getBloqueio());

        java.util.Date dataV = new Date();
        dataV.setTime(dataLicenca.getTimeInMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (configuracao.getAcessoLiberado()) {
            if (dataAtual.after(dataLicenca)) {
                 JOptionPane.showMessageDialog(null, "A licença de uso do sistema"
                 + " expirou em " + df.format(dataV) + "." + "\n\n"
                 + "Adquira uma nova licença pelo suporte técnico:   "
                 + "  \n\n"
                 + "Telefone: 33 99109 4470 - 3621 1126\n"
                 + "E-mail: maxwellchaves@hotmail.com\n",
                 "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);

                if (dataAtual.after(dataBloqueio)) {
                    configuracao.setAcessoLiberado(false);
                    dao.alterar(configuracao);
                    return false;
                }
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Devido a não renovação da "
                    + "licença de uso do sistema expirada em "
                    + df.format(dataV) + ", com \nexceção dos Relatórios, todas as"
                    + " funcionalidades do sistema foram desativadas." + "\n\n"
                    + "Adquira uma nova licença pelo suporte técnico:   "
                    + "  \n\n"
                    + "Telefone: 33 99109 4470 - 3621 1126\n"
                    + "E-mail: maxwellchaves@hotmail.com\n",
                    "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private Controle() {
    }

    public static Controle getInstance() {
        if (instanciaSingleton == null) {
            instanciaSingleton = new Controle();


        }
        return instanciaSingleton;


    }
    
    public static void setUsuario(UsuarioBean u){
        usuario = null;
        usuario = new UsuarioBean(u.getId(), u.getPerfil(), u.getNoUsuario(), u.getLogin(), u.getSenha(), u.isAtivo());
        System.out.println(u.getNoUsuario());
    }
    
    
    public static void inicializar() {

        System.gc();

        if (!listarUsuarios()) {
            if (frmCadAdmin == null) {
                frmCadAdmin = new FrmCadAdmin(frmPrincipal, true);
                frmCadAdmin.setVisible(true);


            } else {
                frmCadAdmin.setVisible(true);


            }
        } else {
            abreFrmAcesso();


        }
        listarPerfis();
    }

    public static void abreFrmPrincipal() {
        if (frmPrincipal == null) {
            frmPrincipal = new FrmPrincipal();
            frmPrincipal.setVisible(true);


        } else {
            frmPrincipal.setVisible(true);
        }
    }

    public static void abreFrmAcesso() {
        if (frmAcesso == null) {
            frmAcesso = new FrmAcesso(frmPrincipal, true);
            frmAcesso.setVisible(true);
        } else {
            frmAcesso.setVisible(true);
        }
    }

    public static void abreFrmUsuarios() {
        if (frmUsuarios == null) {
            frmUsuarios = new FrmUsuarios(frmPrincipal, true);
            frmUsuarios.setVisible(true);
        } else {
            frmUsuarios.setVisible(true);
        }
    }

    public static void abreFrmPerfis() {
        if (frmPerfis == null) {
            frmPerfis = new FrmPerfis(frmPrincipal, true);
            frmPerfis.setVisible(true);
        } else {
            frmPerfis.setVisible(true);
        }
    }

    public static void abreFrmCadPerfil() {
        if (frmCadPerfil == null) {
            frmCadPerfil = new FrmCadPerfil(null, true);
            frmCadPerfil.setVisible(true);
        } else {
            frmCadPerfil.setVisible(true);
        }
    }

    public static void abreFrmCadCliente() {
        if (frmCadCliente == null) {
            frmCadCliente = new FrmCadCliente(null, true);
            frmCadCliente.setVisible(true);
        } else {
            frmCadCliente.setVisible(true);
        }
    }

    public static void abreFrmCadEquipe() {
        if (frmCadEquipe == null) {
            frmCadEquipe = new FrmCadEquipe(frmPrincipal, true);
            frmCadEquipe.setVisible(true);
        } else {
            frmCadEquipe.setVisible(true);
        }
    }

    public static void abreFrmCadFornecedor() {
        if (frmCadFornecedor == null) {
            frmCadFornecedor = new FrmCadFornecedor(frmPrincipal, true);
            frmCadFornecedor.setVisible(true);
        } else {
            frmCadFornecedor.setVisible(true);
        }
    }

    public static void abreFrmCadFuncionario() {
        if (frmCadFuncionario == null) {
            frmCadFuncionario = new FrmCadFuncionario(frmPrincipal, true);
            frmCadFuncionario.setVisible(true);
        } else {
            frmCadFuncionario.setVisible(true);
        }
    }

    public static void abreFrmCadMedidas() {
        if (frmCadMedidas == null) {
            frmCadMedidas = new FrmCadMedida(frmPrincipal, true);
            frmCadMedidas.setVisible(true);
        } else {
            frmCadMedidas.setVisible(true);
        }
    }

    public static void abreFrmCadProdutos() {
        if (frmCadProduto == null) {
            frmCadProduto = new FrmCadProduto(null, true);
            frmCadProduto.setVisible(true);
        } else {
            frmCadProduto.InicializarTabela();
            frmCadProduto.setVisible(true);
        }
    }

    public static void abreFrmPedidos() {
        if (frmPedidos == null) {
            frmPedidos = new FrmPedidos(frmPrincipal, true);
            frmPedidos.setVisible(true);
        } else {
            frmPedidos.setVisible(true);
        }
    }

    public static void abreFrmCompras() {
        if (frmCompras == null) {
            frmCompras = new FrmCompras(frmPrincipal, true);
            frmCompras.setVisible(true);
        } else {
            frmCompras.setVisible(true);
        }
    }

    public static void abreFrmVendasPorCliente() {
        if (frmVendasPorCliente == null) {
            frmVendasPorCliente = new FrmVendasPorCliente(frmPrincipal, false);
            frmVendasPorCliente.setVisible(true);
        } else {
            frmVendasPorCliente.setVisible(true);
        }
    }

    public static void abreFrmCadCidades() {
        if (frmCadCidades == null) {
            frmCadCidades = new FrmCadCidades(frmPrincipal, true);
            frmCadCidades.setVisible(true);
        } else {
            frmCadCidades.setVisible(true);
        }
    }

    public static void abreFrmCadBrinde() {
        if (frmCadBrinde == null) {
            frmCadBrinde = new FrmCadBrinde(null, true);
            frmCadBrinde.setVisible(true);
        } else {
            frmCadBrinde.setVisible(true);
        }
    }

    /**
     * Este método lista os usuários cadastrados no sistema, caso haja usuários
     * cadastrados retorna true, caso a lista esteja vazia retorna false.
     *
     * @return boolean
     */
    public static boolean listarUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarios.clear();
        usuarios = dao.listar();
        if (usuarios.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void listarPrivilegios() {
        PrivilegioDAO dao = new PrivilegioDAO();
        privilegios.clear();
        privilegios = dao.listar();
    }

    public static void listarPerfis() {
        PerfilDAO dao = new PerfilDAO();
        perfis.clear();
        perfis = dao.listar();
    }

    public static List<RotinaBean> getRotinas() {
        return rotinas;
    }

    public static List<UsuarioBean> getUsuarios() {
        return usuarios;
    }

    public static List<PrivilegioBean> getPrivilegios() {
        return privilegios;
    }

    public static List<PerfilBean> getPerfis() {
        return perfis;
    }  
}
