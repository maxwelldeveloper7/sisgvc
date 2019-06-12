/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author maxwell
 */
public class AuditoriaPedidoBean {
    private Long id;
    private Integer codPedido;
    private Integer codUsuario;
    private Date dtAcesso;
    private String hora;
    private Integer codAcao;
    private Integer codStatus;
    private BigDecimal vlPedido;
    private BigDecimal vlRecebido;
    private BigDecimal vlBrinde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Date getDtAcesso() {
        return dtAcesso;
    }

    public void setDtAcesso(Date dtAcesso) {
        this.dtAcesso = dtAcesso;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getCodAcao() {
        return codAcao;
    }

    public void setCodAcao(Integer codAcao) {
        this.codAcao = codAcao;
    }

    public Integer getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(Integer codStatus) {
        this.codStatus = codStatus;
    }

    public BigDecimal getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(BigDecimal vlPedido) {
        this.vlPedido = vlPedido;
    }

    public BigDecimal getVlRecebido() {
        return vlRecebido;
    }

    public void setVlRecebido(BigDecimal vlRecebido) {
        this.vlRecebido = vlRecebido;
    }

    public BigDecimal getVlBrinde() {
        return vlBrinde;
    }

    public void setVlBrinde(BigDecimal vlBrinde) {
        this.vlBrinde = vlBrinde;
    }
}
