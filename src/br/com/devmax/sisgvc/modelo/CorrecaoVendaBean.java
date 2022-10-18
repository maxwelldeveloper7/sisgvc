/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

import java.sql.Date;

/**
 *
 * @author Maxwell
 */
public class CorrecaoVendaBean {

    private Integer codVenda;
    private Integer idVenda;
    private Date movimentoHistorico;
    private Date movimentoVenda;

    public Integer getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Date getMovimentoHistorico() {
        return movimentoHistorico;
    }

    public void setMovimentoHistorico(Date movimentoHistorico) {
        this.movimentoHistorico = movimentoHistorico;
    }

    public Date getMovimentoVenda() {
        return movimentoVenda;
    }

    public void setMovimentoVenda(Date movimentoVenda) {
        this.movimentoVenda = movimentoVenda;
    }



}
