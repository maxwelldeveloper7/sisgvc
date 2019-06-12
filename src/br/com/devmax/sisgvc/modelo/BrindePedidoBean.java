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
public class BrindePedidoBean {

    private Integer id;
    private ProdutoBean produto;
    private Integer quantidade;
    private PedidoBean venda;
    private BigDecimal valor;
    private BigDecimal vlTotal;
    private Integer tipo;

    public BrindePedidoBean() {
    }
    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public ProdutoBean getProduto() {
        return produto;
    }

   
    public void setProduto(ProdutoBean produto) {
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

    
    public void setValor(BigDecimal valorUnitario) {
        this.valor = valorUnitario;
    }

    
    public void setValor(String valorUnitario) {
        this.valor = Utilidades.formataMonetarioSQL(valorUnitario);
    }
   
    public PedidoBean getVenda() {
        return venda;
    }

    
    public void setVenda(PedidoBean venda) {
        this.venda = venda;
    }

    public BigDecimal getVlTotal() {
        vlTotal = new BigDecimal(quantidade);
        vlTotal = vlTotal.multiply(valor);
        return vlTotal;
    }

    public void setVlTotal(BigDecimal vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String getVlTotalSTR() {
        vlTotal = new BigDecimal(quantidade);
        vlTotal = vlTotal.multiply(valor);
        return Utilidades.formataMontetarioSTR(vlTotal);
    }

    public void setVlTotalSTR(String vlTotal) {
        this.vlTotal = Utilidades.formataMonetarioSQL(vlTotal);
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }    
}
