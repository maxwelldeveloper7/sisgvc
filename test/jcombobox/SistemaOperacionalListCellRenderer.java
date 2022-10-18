/*
 * SistemaOperacionalListCellRenderer.java
 *
 * Created on 3 de Setembro de 2006, 17:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jcombobox;

import dados.SistemaOperacional;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import jtable.*;
import recursos.SistemaOperacionalUIResources;

/**
 *
 * @author fernando
 */
public class SistemaOperacionalListCellRenderer extends DefaultListCellRenderer{

    private static final SistemaOperacionalUIResources recursos =
            new SistemaOperacionalUIResources();
    
    /** Creates a new instance of SistemaOperacionalListCellRenderer */
    public SistemaOperacionalListCellRenderer() {
    }
    
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel)super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);
        label.setIcon(recursos.getIcone((SistemaOperacional)value));
        label.setText(recursos.getNome((SistemaOperacional)value));
        
        return label;
    }
}
