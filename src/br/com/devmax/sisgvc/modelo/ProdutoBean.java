/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;


/**
 * Representa um protudo da tabela de produtos
 *
 * @author Maxwell
 */
public class ProdutoBean {

    private Integer id;
    private String produto;
    private MedidaBean medida;
    private BigDecimal vlCompra;
    private BigDecimal vlVenda;
    private Integer estoque;
    private Integer tipo;

    public ProdutoBean() {
    }   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Campo: no_produto
     * @return the produto
     */
    public String getProduto() {
        return produto.toUpperCase();
    }

    /**
     * Campo: no_produto
     * @param produto the produto to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * Campo: cd_medida - MedidaBean.getId()
     * @return the medida
     */
    public MedidaBean getMedida() {
        return medida;
    }

    /**
     * Campo: cd_medida - MedidaBean.setId()
     * @param medida the medida to set
     */
    public void setMedida(MedidaBean medida) {
        this.medida = medida;
    }   

    /**
     * Campo: estoque
     * @return the estoque
     */
    public Integer getEstoque() {
        return estoque;
    }

    /**
     * Campo: estoque
     * @return the estoque
     */
    public String getEstoqueStr() {
        return String.valueOf(estoque);
    }

    /**
     * Campo: estoque
     * @param estoque the estoque to set
     */
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    /**
     * Campo: estoque
     * @param estoque the estoque to set
     */
    public void setEstoque(String estoque) {
        this.estoque = Integer.parseInt(estoque);
    }

    /**
     * @return the vlCompra
     */
    public BigDecimal getVlCompra() {
        return vlCompra;
    }

    /**
     * @param vlCompra the vlCompra to set
     */
    public void setVlCompra(BigDecimal vlCompra) {
        this.vlCompra = vlCompra;
    }

    /**
     * @return the vlCompra
     */
    public String getVlCompraStr() {
        return Utilidades.formataMontetarioSTR(vlCompra);
    }

    /**
     * @param vlCompra the vlCompra to set
     */
    public void setVlCompra(String vlCompra) {
        this.vlCompra = Utilidades.formataMonetarioSQL(vlCompra);
    }

    /**
     * @return the vlVenda
     */
    public BigDecimal getVlVenda() {
        return vlVenda;
    }

    /**
     * @param vlVenda the vlVenda to set
     */
    public void setVlVenda(BigDecimal vlVenda) {
        this.vlVenda = vlVenda;
    }

    /**
     * @return the vlVenda
     */
    public String getVlVendaStr() {
        return Utilidades.formataMontetarioSTR(vlVenda);
    }

    /**
     * @param vlVenda the vlVenda to set
     */
    public void setVlVenda(String vlVenda) {
        this.vlVenda = Utilidades.formataMonetarioSQL(vlVenda);
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
