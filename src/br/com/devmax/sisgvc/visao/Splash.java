package br.com.devmax.sisgvc.visao;


import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Maxwell
 */
public class Splash extends JWindow {

    AbsoluteLayout absoluto;
    AbsoluteConstraints absimage, absbarra;
    ImageIcon image;
    JLabel jlabel;
    JProgressBar barra;

    public Splash() {
        absoluto = new AbsoluteLayout();
        absimage = new AbsoluteConstraints(0, 0);
        absbarra = new AbsoluteConstraints(0, 326);
        image = new ImageIcon(this.getClass().getResource("eagles.gif"));
        jlabel = new JLabel(image);
        barra = new JProgressBar();
        this.getContentPane().setLayout(absoluto);
        this.getContentPane().add(jlabel, absimage);
        this.getContentPane().add(barra, absbarra);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        new Thread() {

            public void run() {
                int i = 0;
                while (i < 101) {
                    barra.setValue(i);
                    i++;
                    try {
                        sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dispose();
            }
        }.start();        
    }
}
