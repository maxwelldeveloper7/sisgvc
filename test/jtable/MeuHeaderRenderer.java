/*
 * MeuHeaderRenderer.java
 *
 * Created on 3 de Setembro de 2006, 00:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jtable;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.table.*;
import javax.swing.border.*;

/**
 *
 * @author fernando
 */
public class MeuHeaderRenderer extends DefaultTableCellRenderer {
    
    /** Creates a new instance of MeuHeaderRenderer */
    public MeuHeaderRenderer() {
    }
    
    public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        label.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        label.setForeground(Color.LIGHT_GRAY);
        label.setBackground(Color.DARK_GRAY);
        if (column == 3)
            label.setHorizontalAlignment(JLabel.RIGHT);
        else
            label.setHorizontalAlignment(JLabel.CENTER);
        
        return label;
    }
}
