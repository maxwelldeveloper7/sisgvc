/*
 * Main.java
 *
 * Created on 2 de Setembro de 2006, 19:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jtable;

import dados.Host;
import dados.SistemaOperacional;
import java.util.*;

/**
 *
 * @author fernando
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Host> hosts = new ArrayList<Host>();
        hosts.add(new Host("servidor", "192.168.0.1", SistemaOperacional.LINUX, 2048));
        hosts.add(new Host("firewall", "192.168.0.2", SistemaOperacional.LINUX, 128));
        hosts.add(new Host("contabilidade", "192.168.0.11", SistemaOperacional.WINDOWS, 256));
        hosts.add(new Host("rh", "192.168.0.12", SistemaOperacional.WINDOWS, 512));
        hosts.add(new Host("diretoria", "192.168.0.20", SistemaOperacional.WINDOWS, 512));
        hosts.add(new Host("marketing", "192.168.0.33", SistemaOperacional.MACOSX, 1024));
        
        JanelaPrincipal frame = new JanelaPrincipal();
        frame.setHosts(hosts);
        //JanelaPrincipal2 frame = new JanelaPrincipal2();
        frame.setVisible(true);
    }
    
}
