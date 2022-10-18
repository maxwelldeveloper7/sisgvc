package br.com.devmax.sisgvc;

import br.com.devmax.sisgvc.controle.Controle;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Maxwell
 */
public class Main {

    @SuppressWarnings("CallToThreadDumpStack")
    public static void main (String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
               e.printStackTrace();
               JOptionPane.showMessageDialog(null, "Falha ao setar LookAndFeel."+"/n"+e.getMessage()+"\nA aplicação será encerrada",null,JOptionPane.ERROR);
               System.exit(0);
        }
        Controle.inicializar();
    }
}
