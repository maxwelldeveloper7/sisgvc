/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Maxwell
 */
public class RelVendaProdutoBean {
    private String produto;
    private Integer consignados;
    private BigDecimal vlConsigando;
    private Integer vendidos;
    private BigDecimal vlVendido;
    private Integer devolvidos;
    private BigDecimal vlDevolvido;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getConsignados() {
        return consignados;
    }

    public void setConsignados(Integer consignados) {
        this.consignados = consignados;
    }

    public BigDecimal getVlConsigando() {
        return vlConsigando;
    }

    public String getVlConsignadoSTR() {
        return Utilidades.formataMontetarioSTR(vlConsigando);
    }

    public void setVlConsigando(BigDecimal vlConsigando) {
        this.vlConsigando = vlConsigando;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public BigDecimal getVlVendido() {
        return vlVendido;
    }

    public String getVlVendidoSTR() {
        return Utilidades.formataMontetarioSTR(vlVendido);
    }

    public void setVlVendido(BigDecimal vlVendido) {
        this.vlVendido = vlVendido;
    }

    public Integer getDevolvidos() {
        return devolvidos;
    }

    public void setDevolvidos(Integer devolvidos) {
        this.devolvidos = devolvidos;
    }

    public BigDecimal getVlDevolvido() {
        return vlDevolvido;
    }

    public String getVlDevolvidoSTR() {
        return Utilidades.formataMontetarioSTR(vlDevolvido);
    }

    public void setVlDevolvidos(BigDecimal vlDevolvidos) {
        this.vlDevolvido = vlDevolvidos;
    }    
}
