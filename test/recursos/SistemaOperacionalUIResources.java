/*
 * SistemaOperacionalUIResources.java
 *
 * Created on 3 de Setembro de 2006, 17:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package recursos;

import dados.SistemaOperacional;
import java.util.*;
import javax.swing.*;

/**
 * Ícones from http://www.entity.cc/ICONS/requests1.php
 * @author fernando
 */
public class SistemaOperacionalUIResources {
    
    protected static final Map<SistemaOperacional,ImageIcon> icones =
            new HashMap<SistemaOperacional,ImageIcon>();
    
    private static final Map<SistemaOperacional,String> nomes =
            new HashMap<SistemaOperacional,String>();
    
    /** Creates a new instance of SistemaOperacionalUIResources */
    public SistemaOperacionalUIResources() {
        if (icones.size() == 0) {
        icones.put (SistemaOperacional.LINUX,
            new ImageIcon(getClass().getResource("/recursos/icones/linux.png")));
        icones.put (SistemaOperacional.WINDOWS,
            new ImageIcon(getClass().getResource("/recursos/icones/windows.png")));
        icones.put (SistemaOperacional.MACOSX,
            new ImageIcon(getClass().getResource("/recursos/icones/macosx.png")));
        }
        if (nomes.size() == 0) {
            nomes.put (SistemaOperacional.LINUX, "Linux");
            nomes.put (SistemaOperacional.WINDOWS, "Windows");
            nomes.put (SistemaOperacional.MACOSX, "MacOS X");
        }
    }
    
    public ImageIcon getIcone(SistemaOperacional value) {
        return icones.get(value);
    }
    
    public int getIconHeight() {
        return icones.get(SistemaOperacional.LINUX).getIconHeight();
    }
    
    public int getIconWidth() {
        return icones.get(SistemaOperacional.LINUX).getIconWidth();
    }

    public String getNome(SistemaOperacional value) {
        return nomes.get(value);
    }
}
