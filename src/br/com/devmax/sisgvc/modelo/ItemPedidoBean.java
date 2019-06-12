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
public class ItemPedidoBean {

    private Long id;
    private ProdutoBean produto;
    private Integer consignado;
    private Integer vendido;
    private Integer devolvido;
    private PedidoBean venda;
    private BigDecimal valorUnitario;
    private BigDecimal valorCusto;
    

    public ItemPedidoBean() {
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
     * @return the consignado
     */
    public Integer getConsignado() {
        return consignado;
    }

    /**
     * @param consignado the consignado to set
     */
    public void setConsignado(Integer consignado) {
        this.consignado = consignado;
    }

    /**
     * @return the vendido
     */
    public Integer getVendido() {
        return vendido;
    }

    /**
     * @param vendido the vendido to set
     */
    public void setVendido(Integer vendido) {
        this.vendido = vendido;
    }

    /**
     * @return the devolvido
     */
    public Integer getDevolvido() {
        return devolvido;
    }

    /**
     * @param devolvido the devolvido to set
     */
    public void setDevolvido(Integer devolvido) {
        this.devolvido = devolvido;
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
     * @return the valor Consignado
     */
    public BigDecimal getValorConsignado() {
        BigDecimal consig = new BigDecimal(consignado);
        return consig.multiply(valorUnitario);
    }

    /**
     * @return the valor Consignado
     */
    public String getValorConsignadoStr() {
        BigDecimal consig = new BigDecimal(consignado);
        return Utilidades.formataMontetarioSTR(consig.multiply(valorUnitario));
    }

    /**
     * @return the valor Vendido
     */
    public BigDecimal getValorVendido() {
        BigDecimal vendid = new BigDecimal(vendido);
        return vendid.multiply(valorUnitario);
    }

    /**
     * @return the valor Vendido
     */
    public String getValorVendidoStr() {
        BigDecimal vendid = new BigDecimal(vendido);
        return Utilidades.formataMontetarioSTR(vendid.multiply(valorUnitario));
    }


    public BigDecimal getValorDevolvido() {
        BigDecimal dev = new BigDecimal(devolvido);
        return dev.multiply(valorUnitario);
    }

    public String getValorDevolvidoStr() {
        BigDecimal dev = new BigDecimal(devolvido);
        return Utilidades.formataMontetarioSTR(dev.multiply(valorUnitario));
    }

    /**
     * @return the valor Total
     */
    public BigDecimal getValorTotal() {
        BigDecimal result = new BigDecimal(0);
        result = result.add(getValorConsignado());
        result = result.add(getValorVendido());
        return result;
    }

    /**
     * @return the valor Total
     */
    public String getValorTotalStr() {
        BigDecimal result = new BigDecimal(0);
        result = result.add(getValorConsignado());
        result = result.add(getValorVendido());
        return Utilidades.formataMontetarioSTR(result);
    }    

    /**
     * @return the venda
     */
    public PedidoBean getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(PedidoBean venda) {
        this.venda = venda;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    
}
