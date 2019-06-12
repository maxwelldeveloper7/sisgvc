/*
 * HostTableModel.java
 *
 * Created on 2 de Setembro de 2006, 19:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jtable;

import dados.Host;
import dados.SistemaOperacional;
import java.util.*;
import javax.swing.table.*;

/**
 *
 * @author fernando
 */
public class HostTableModel extends AbstractTableModel {
    
    private List<Host> hosts;
    private String[] colunas = {
        "Nome", "Endereço IP", "S.O.", "RAM"
    };
    
    /** Creates a new instance of HostTableModel */
    public HostTableModel(List<Host> hosts) {
        super();
        setHosts(hosts);
    }
    
    public int getColumnCount() {
        return 4;
    }
    
    public int getRowCount() {
        return hosts.size();
    }
    
    // sem efeito em colunas criadas manualmente (addColumn() ou ColumnModel())
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public Class getColumnClass(int column) {
        //return getValueAt(0, c).getClass();
        switch (column) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return SistemaOperacional.class;
            case 3: return Integer.class;
        }
        return Object.class;
    }
    
    public Object getValueAt(int row, int column) {
        Host host = hosts.get(row);
        switch (column) {
            case 0: return host.getNome();
            case 1: return host.getIp();
            case 2: return host.getSo();
            case 3: return host.getMemoriaRAM();
        }
        return null;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
