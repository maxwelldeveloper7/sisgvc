/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.devmax.sisgvc.daos.ConfiguracaoDAO;
import br.com.devmax.sisgvc.modelo.ConfiguracaoBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class TesteVencimento {

    public static void main(String args[]) {


        ConfiguracaoBean configuracao = new ConfiguracaoBean();
        ConfiguracaoDAO dao = new ConfiguracaoDAO();

        configuracao = dao.listar();

        System.out.println(configuracao.getId());
        System.out.println(configuracao.getLicenciada());
        System.out.println(configuracao.getCnpj());
        System.out.println(configuracao.getVencimento());
        System.out.println(configuracao.getAcessoLiberado());
        System.out.println(configuracao.getBloqueio());

        Calendar dataAtual = Calendar.getInstance(); //instanciamos data atual
        //dataAtual.set(2012, 2, 22);
        Calendar dataLicenca = Calendar.getInstance(); //instanciamos data da licença
        dataLicenca.setTime(configuracao.getVencimento());
        Calendar dataBloqueio = Calendar.getInstance();
        dataBloqueio.setTime(configuracao.getBloqueio());

        java.util.Date dataV = new Date();
        dataV.setTime(dataLicenca.getTimeInMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");


        if (configuracao.getAcessoLiberado()) {
            if (dataAtual.before(dataLicenca)) {
            } else {
                JOptionPane.showMessageDialog(null, "A licença de uso do sistema"
                        + " expirou em " + df.format(dataV) + "." + "\n\n"
                        + "Adquira uma nova licença pelo suporte técnico:   "
                        + "  \n\n"
                        + "Telefone: 33 9104 5946 - 3621 1126\n"
                        + "E-mail: maxwellchaves@hotmail.com\n",
                        "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }


            if (dataAtual.after(dataBloqueio)) {

                configuracao.setAcessoLiberado(false);
                dao.alterar(configuracao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Devido a não renovação da "
                    + "licença de uso do sistema expirada em "
                    + df.format(dataV) + ", com \nexceção dos Relatórios, todas as"
                    + " funcionalidades do sistema foram desativadas." + "\n\n"
                    + "Adquira uma nova licença pelo suporte técnico:   "
                    + "  \n\n"
                    + "Telefone: 33 9104 5946 - 3621 1126\n"
                    + "E-mail: maxwellchaves@hotmail.com\n",
                    "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
