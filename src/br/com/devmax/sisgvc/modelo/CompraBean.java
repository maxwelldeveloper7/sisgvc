/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Representa uma venda da tabela de vendas
 *
 * @author Maxwell
 */
public class CompraBean {

    private Integer id;
    private String ntFiscal;
    private FornecedorBean fornecedor;
    private Date dataEmissao;
    private Date dataEntrega;
    private List<ItemCompraBean> itensDeCompra;

    public CompraBean() {
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
     * @return the dataEntrega
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEntrega to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the dataEntrega
     */
    public String getDataEmissaoStr() {
        return Utilidades.formataDataSTR(dataEmissao);
    }

    /**
     * @param dataEmissao the dataEntrega to set
     */
    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = Utilidades.formataDataSQL(dataEmissao);
    }

    /**
     * @return the dataVencimento
     */
    public Date getDataEntrega() {
        return dataEntrega;
    }

    /**
     * @param dataEntrega the dataVencimento to set
     */
    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    /**
     * @return the dataVencimento
     */
    public String getDataEntregaStr() {
        return Utilidades.formataDataSTR(dataEntrega);
    }

    /**
     * @param dataEngrega the dataVencimento to set
     */
    public void setDataEntrega(String dataEngrega) {
        this.dataEntrega = Utilidades.formataDataSQL(dataEngrega);
    }

    /**
     * @return the itensDeVenda
     */
    public List<ItemCompraBean> getItensDeVenda() {
        return itensDeCompra;
    }

    /**
     * @param itensDeCompra the itensDeVenda to set
     */
    public void setItensDeVenda(List<ItemCompraBean> itensDeCompra) {
        this.itensDeCompra = itensDeCompra;
    }

    /**
     * @return the ntFiscal
     */
    public String getNtFiscal() {
        return ntFiscal;
    }

    /**
     * @param ntFiscal the ntFiscal to set
     */
    public void setNtFiscal(String ntFiscal) {
        this.ntFiscal = ntFiscal;
    }

    /**
     * @return the fornecedor
     */
    public FornecedorBean getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(FornecedorBean fornecedor) {
        this.fornecedor = fornecedor;
    }
}
