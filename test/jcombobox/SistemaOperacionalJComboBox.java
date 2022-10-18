/*
 * SistemaOperacionalJComboBox.java
 *
 * Created on 3 de Setembro de 2006, 17:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jcombobox;

import dados.SistemaOperacional;
import java.awt.*;
import javax.swing.*;
import jtable.*;

/**
 *
 * @author fernando
 */
public class SistemaOperacionalJComboBox extends JComboBox {
    
    /** Creates a new instance of SistemaOperacionalJComboBox */
    public SistemaOperacionalJComboBox() {
        setModel(new SistemaOperacionalComboBoxModel());
        SistemaOperacionalListCellRenderer cellRenderer = new SistemaOperacionalListCellRenderer();
        setRenderer(cellRenderer);
        setPrototypeDisplayValue(SistemaOperacional.WINDOWS);
    }
    
}
