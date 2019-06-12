/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.visao.modelos;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Maxwell
 */
public class ColunaCheckBox implements TableCellRenderer{

    final JCheckBox check = new JCheckBox();
    public ColunaCheckBox() {
        super();
    }


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        check.setSelected(((Boolean)value).booleanValue()) ;
        
        return check;
    }
}
