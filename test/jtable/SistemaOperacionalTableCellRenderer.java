/*
 * HostCellRenderer.java
 *
 * Created on 2 de Setembro de 2006, 20:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jtable;

import dados.SistemaOperacional;
import java.util.*;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import recursos.SistemaOperacionalUIResources;

/**
 * 
 * @author fernando
 */
public class SistemaOperacionalTableCellRenderer extends DefaultTableCellRenderer {
    
    private SistemaOperacionalUIResources recursos = new SistemaOperacionalUIResources();
    
    /** Creates a new instance of HostCellRenderer */
    public SistemaOperacionalTableCellRenderer() {
        super();
    }
    
    public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (! (value instanceof SistemaOperacional))
            throw new IllegalArgumentException("Este cell renderer só aceita valores do tipo SistemaOperacional.");
        
        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        label.setText(null);
        label.setToolTipText(recursos.getNome((SistemaOperacional)value));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(recursos.getIcone((SistemaOperacional)value));
        return label;
    }

    public int getIconHeight() {
        return recursos.getIconHeight();
    }
    
    public int getIconWidth() {
        return recursos.getIconWidth();
    }
    
}
