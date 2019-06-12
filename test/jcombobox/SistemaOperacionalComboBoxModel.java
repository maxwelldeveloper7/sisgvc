/*
 * SistemasOperacionaisListModel.java
 *
 * Created on 3 de Setembro de 2006, 17:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jcombobox;

import dados.SistemaOperacional;
import javax.swing.*;
import jtable.*;

/**
 *
 * @author fernando
 */
public class SistemaOperacionalComboBoxModel extends DefaultComboBoxModel {
    
    /** Creates a new instance of SistemasOperacionaisListModel */
    public SistemaOperacionalComboBoxModel() {
        addElement(SistemaOperacional.LINUX);
        addElement(SistemaOperacional.WINDOWS);
        addElement(SistemaOperacional.MACOSX);
    }
    
}
