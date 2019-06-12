/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testes;

import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Maxwell
 */
public class Teste {

    public static void main(String[] args) {
        Vector h = new Vector();
        h.add("A");
        h.add("B");
        h.add("C");
        h.add("D");

        String[] valores = new String[] { "UNO", "DUE", "TRE" };
        Vector rows = new Vector();
        for (int row = 0; row < 10; row++) {
            Vector cols = new Vector();
            for (int col = 0; col < h.size(); col++) {
                cols.add(valores[(col + row) % valores.length]);
            }
            rows.add(cols);
        }

        JTable t = new JTable(rows, h);

        JComboBox combo = new JComboBox(valores);
        t.setDefaultEditor(Object.class, new DefaultCellEditor(combo));
        t.setRowHeight(20);

        JFrame f = new JFrame();
        f.getContentPane().add(new JScrollPane(t));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640, 480);
        f.setVisible(true);
    }

}
