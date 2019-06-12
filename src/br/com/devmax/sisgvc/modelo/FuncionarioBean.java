/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

import java.sql.Date;

/**
 * Representa um funcionário da tabela de funcionários
 * @author Maxwell
 */
public class FuncionarioBean {

    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private String endereco;
    private String uf;
    private String cidade;
    private Date dataAdmissao;
    private Date dataDemissao;
    private String cep;
    private String telefone;
    private String contaCorrente;
    private String numeroAgencia;
    private String nomeBanco;

    public FuncionarioBean(Integer id, String nome, String rg, String cpf, String endereco, String uf, String cidade, String dataAdmissao, String dataDemissao, String cep, String telefone, String contaCorrente, String numeroAgencia, String nomeBanco) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.uf = uf;
        this.cidade = cidade;
        this.dataAdmissao = Utilidades.formataDataSQL(dataAdmissao);
        this.dataDemissao = Utilidades.formataDataSQL(dataDemissao);
        this.cep = cep;
        this.telefone = telefone;
        this.contaCorrente = contaCorrente;
        this.numeroAgencia = numeroAgencia;
        this.nomeBanco = nomeBanco;
    }

    public FuncionarioBean() {
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
     * Campo: no_funcionario
     * @return the nome
     */
    public String getNome() {
        return nome.toUpperCase().trim();
    }

    /**
     * Campo: no_funcionario
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Campo: rg_funcionario
     * @return the rg
     */
    public String getRg() {
        return rg.toUpperCase();
    }

    /**
     * Campo: rg_funcionario
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * Campo: cpf_funcionario
     * @return the cpf
     */
    public String getCpf() {
        return Utilidades.getDigitos(cpf);
    }

    /**
     * Campo: cpf_funcionario
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Campo: ed_funcionario
     * @return the endereco
     */
    public String getEndereco() {
        return endereco.toUpperCase().trim();
    }

    /**
     * Campo: ed_funcionario
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Campo: uf_funcionario
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * uf_funcionario
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Campo: cid_funcionario
     * @return the cidade
     */
    public String getCidade() {
        return cidade.toUpperCase().trim();
    }

    /**
     * Campo: cid_funcionario
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Campo: dt_admissao
     * @return the dataAdmissao
     */
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * Campo: dt_admissao
     * @param dataAdmissao the dataAdmissao to set
     */
    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    /**
     * Campo: dt_admissao
     * @return the dataAdmissao
     */
    public String getDataAdmissaoStr() {
        return Utilidades.formataDataSTR(dataAdmissao);
    }

    /**
     * Campo: dt_admissao
     * @param dataAdmissao the dataAdmissao to set
     */
    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = Utilidades.formataDataSQL(dataAdmissao);
    }

    /**
     * Campo: dt_demissao
     * @return the dataDemissao
     */
    public Date getDataDemissao() {
        return dataDemissao;
    }

    /**
     * Campo: dt_demissao
     * @param dataDemissao the dataDemissao to set
     */
    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    /**
     * Campo: dt_demissao
     * @return the dataDemissao
     */
    public String getDataDemissaoStr() {
        return Utilidades.formataDataSTR(dataDemissao);
    }

    /**
     * Campo: dt_demissao
     * @param dataDemissao the dataDemissao to set
     */
    public void setDataDemissao(String dataDemissao) {
        this.dataDemissao = Utilidades.formataDataSQL(dataDemissao);
    }

    /**
     * Campo: cep_funcionario
     * @return the cep
     */
    public String getCep() {
        return Utilidades.getDigitos(cep);
    }

    /**
     * cep_funcionario
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Campo: tl_funcionario
     * @return the telefone
     */
    public String getTelefone() {
        return Utilidades.getDigitos(telefone);
    }

    /**
     * Campo: tl_funcionario
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Campo: nr_cc
     * @return the contaCorrente
     */
    public String getContaCorrente() {
        return contaCorrente;
    }

    /**
     * Campo: nr_cc
     * @param contaCorrente the contaCorrente to set
     */
    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    /**
     * Campo: nr_agencia
     * @return the numeroAgencia
     */
    public String getNumeroAgencia() {
        return Utilidades.getDigitos(numeroAgencia);
    }

    /**
     * Campo: nr_agencia
     * @param numeroAgencia the numeroAgencia to set
     */
    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    /**
     * Campo: no_banco
     * @return the nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco.toUpperCase().trim();
    }

    /**
     * Campo: no_banco
     * @param nomeBanco the nomeBanco to set
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }    
}
