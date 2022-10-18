/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.visao.modelos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxwell
 */
public class TableModel extends DefaultTableModel{

    public TableModel(Object object, String[] string) {
        this.addRow(string);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }



}
