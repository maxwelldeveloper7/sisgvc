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
public class ItemCompraBean {

    private Long id;
    private CompraBean compra;
    private ProdutoBean produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;

    public ItemCompraBean() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the produto
     */
    public ProdutoBean getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoBean produto) {
        this.produto = produto;
    }

    /**
     * @return the devolvido
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorUnitario
     */
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @return the valorUnitario
     */
    public String getValorUnitarioStr() {
        return Utilidades.formataMontetarioSTR(valorUnitario);
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = Utilidades.formataMonetarioSQL(valorUnitario);
    }

    /**
     * @return the valor Total
     */
    public BigDecimal getValorTotal() {
        BigDecimal result = new BigDecimal(quantidade);
        result = result.multiply(valorUnitario);
        return result;
    }

    /**
     * @return the valor Total
     */
    public String getValorTotalStr() {
        BigDecimal result = new BigDecimal(quantidade);
        result = result.multiply(valorUnitario);
        return Utilidades.formataMontetarioSTR(result);
    }

    /**
     * @return the compra
     */
    public CompraBean getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(CompraBean compra) {
        this.compra = compra;
    }
}
