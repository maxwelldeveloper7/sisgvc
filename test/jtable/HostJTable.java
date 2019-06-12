/*
 * HostJTable.java
 *
 * Created on 2 de Setembro de 2006, 23:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jtable;

import dados.Host;
import dados.SistemaOperacional;
import java.util.List;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author fernando
 */
public class HostJTable extends JTable {
    
    private SistemaOperacionalTableCellRenderer cellRenderer;
    private MeuHeaderRenderer headerRenderer;
    FontMetrics fm;
    
    /** Creates a new instance of HostJTable */
    public HostJTable() {
        setAutoCreateColumnsFromModel(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
    public void setHosts(List<Host> hosts) {
        setModel(new HostTableModel(hosts));

        cellRenderer = new SistemaOperacionalTableCellRenderer();
        headerRenderer = new MeuHeaderRenderer();
        setDefaultRenderer(SistemaOperacional.class, cellRenderer);
        setRowHeight(cellRenderer.getIconHeight());
        
        fm = getFontMetrics(getFont());

        adicionaColuna(0, larguraEmLetras(30), "Nome", true);
        adicionaColuna(1, larguraEmDigitos(16), "Endereço IP", true);
        adicionaColuna(2, cellRenderer.getIconWidth(), "Sist.Oper.", false);
        adicionaColuna(3, larguraEmDigitos(5), "RAM", false);
    }

    private void recalculaLarguraDasColunas() {
        if (getColumnCount() == 0)
            return;
        TableColumn coluna;
        coluna = getColumnModel().getColumn(0);
        coluna.setWidth(larguraEmLetras(30));
        coluna = getColumnModel().getColumn(1);
        coluna.setWidth(larguraEmDigitos(16));
        coluna = getColumnModel().getColumn(2);
        coluna.setWidth(cellRenderer.getIconWidth());
        coluna = getColumnModel().getColumn(3);
        coluna.setWidth(larguraEmDigitos(5));
    }
    
    private void adicionaColuna(int indice, int largura, String titulo, boolean resizeable) {
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo)
            largura = larguraTitulo;
        TableColumn column = new TableColumn(indice, largura, null, null);
        column.setHeaderValue(titulo);
        column.setResizable(resizeable);
        column.setHeaderRenderer(headerRenderer);
        addColumn(column);
    }

    private int larguraEmLetras(int largura) {
        int letra = fm.stringWidth("M");
        return largura * letra;
    }

    private int larguraEmDigitos(int largura) {
        int letra = fm.stringWidth("0");
        return largura * letra;
    }
    
    public void setFont(Font font) {
        super.setFont(font);
        recalculaLarguraDasColunas();
    }

}
