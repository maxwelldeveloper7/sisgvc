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
public class PedidoPorVendedorBean {

    private Integer codVendedor;
    private String vendedor;
    private Integer consignado = 0;
    private BigDecimal vlConsignado;
    private Integer pendente = 0;
    private BigDecimal vlPendente;
    private Integer concluido = 0;
    private BigDecimal vlConcluido;
    private Integer devolvido = 0;
    private BigDecimal vlDevolvido;
    private Integer perdido = 0;
    private BigDecimal vlPerdido;
    private Integer total = 0;
    private BigDecimal vlTotal;
    private BigDecimal vlRecebido;
    private BigDecimal vlDesconto;

    public Integer getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(Integer codVendedor) {
        this.codVendedor = codVendedor;
    }

    public Integer getConcluido() {
        return concluido;
    }

    public void setConcluido(Integer concluido) {
        this.concluido = concluido;
    }

    public Integer getConsignado() {
        return consignado;
    }

    public void setConsignado(Integer consignado) {
        this.consignado = consignado;
    }

    public Integer getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Integer devolvido) {
        this.devolvido = devolvido;
    }

    public Integer getPendente() {
        return pendente;
    }

    public void setPendente(Integer pendente) {
        this.pendente = pendente;
    }

    public Integer getPerdido() {
        return perdido;
    }

    public void setPerdido(Integer perdido) {
        this.perdido = perdido;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public BigDecimal getVlConcluido() {
        if (vlConcluido == null) {
            vlConcluido = new BigDecimal("0.00");
        }
        return vlConcluido;
    }

    public String getVlConcluidoSTR() {
        if (vlConcluido == null) {
            vlConcluido = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlConcluido);
    }

    public void setVlConcluido(BigDecimal vlConcluido) {
        this.vlConcluido = vlConcluido;
    }

    public BigDecimal getVlConsignado() {
        if (vlConsignado == null) {
            vlConsignado = new BigDecimal("0.00");
        }
        return vlConsignado;
    }

    public String getVlConsignadoSTR() {
        if (vlConsignado == null) {
            vlConsignado = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlConsignado);
    }

    public void setVlConsignado(BigDecimal vlConsignado) {
        this.vlConsignado = vlConsignado;
    }

    public BigDecimal getVlDevolvido() {
        if (vlDevolvido == null) {
            vlDevolvido = new BigDecimal("0.00");
        }
        return vlDevolvido;
    }

    public String getVlDevolvidoSTR() {
        if (vlDevolvido == null) {
            vlDevolvido = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlDevolvido);
    }

    public void setVlDevolvido(BigDecimal vlDevolvido) {
        this.vlDevolvido = vlDevolvido;
    }

    public BigDecimal getVlPendente() {
        if (vlPendente == null) {
            vlPendente = new BigDecimal("0.00");
        }
        return vlPendente;
    }

    public String getVlPendenteSTR() {
        if (vlPendente == null) {
            vlPendente = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlPendente);
    }

    public void setVlPendente(BigDecimal vlPendente) {
        this.vlPendente = vlPendente;
    }

    public BigDecimal getVlPerdido() {
        if (vlPerdido == null) {
            vlPerdido = new BigDecimal("0.00");
        }
        return vlPerdido;
    }

    public String getVlPerdidoSTR() {
        if (vlPerdido == null) {
            vlPerdido = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlPerdido);
    }

    public void setVlPerdido(BigDecimal vlPerdido) {
        this.vlPerdido = vlPerdido;
    }

    public BigDecimal getVlTotal() {
        if (vlTotal == null) {
            vlTotal = new BigDecimal("0.00");
        }
        return vlTotal;
    }

    public String getVlTotalSTR() {
        if (vlTotal == null) {
            vlTotal = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlTotal);
    }

    public void setVlTotal(BigDecimal vlTotal) {
        this.vlTotal = vlTotal;
    }

    public BigDecimal getVlDesconto() {
        if(vlDesconto == null){
            vlDesconto = new BigDecimal("0.00");
        }
        return vlDesconto;
    }

    public String getVlDescontoSRT() {
        if(vlDesconto == null){
            vlDesconto = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlDesconto);
    }

    public void setVlDesconto(BigDecimal vlDesconto) {
        this.vlDesconto = vlDesconto;
    }

    public BigDecimal getVlRecebido() {
        if(vlRecebido == null){
            vlRecebido = new BigDecimal("0.00");
        }
        return vlRecebido;
    }

    public String getVlRecebidoSTR() {
        if(vlRecebido == null){
            vlRecebido = new BigDecimal("0.00");
        }
        return Utilidades.formataMontetarioSTR(vlRecebido);
    }

    public void setVlRecebido(BigDecimal vlRecebido) {
        this.vlRecebido = vlRecebido;
    }

}
