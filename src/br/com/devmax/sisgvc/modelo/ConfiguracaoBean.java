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
public class ConfiguracaoBean {

    private Integer id;
    private String licenciada;
    private String cnpj;
    private Date vencimento;
    private Boolean acessoLiberado;
    private Date bloqueio;

    public Boolean getAcessoLiberado() {
        return acessoLiberado;
    }

    public void setAcessoLiberado(Boolean acessoLiberado) {
        this.acessoLiberado = acessoLiberado;
    }

    public Date getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Date bloqueio) {
        this.bloqueio = bloqueio;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenciada() {
        return licenciada;
    }

    public void setLicenciada(String licenciada) {
        this.licenciada = licenciada;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }


}
