/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.visao.modelos;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Maxwell
 */
public class ColunasNumericas extends DefaultTableCellRenderer {

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        /*if (isSelected) {
            cell.setBackground(Color.blue);
        } else {
            if (row % 2 == 0) {
                cell.setBackground(Color.white);
            } else {
                cell.setBackground(Color.lightGray);
            }
        }*/
        return cell;
    }
}
