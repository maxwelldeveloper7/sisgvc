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
public class RelBrindeProdutoBean {
    private String produto;
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal total;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getValorSTR() {
        return Utilidades.formataMontetarioSTR(valor);
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getTotalSTR() {
        return Utilidades.formataMontetarioSTR(total);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
