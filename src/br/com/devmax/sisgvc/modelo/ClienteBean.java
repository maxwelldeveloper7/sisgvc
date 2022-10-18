/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

import java.sql.Date;

/**
 * Representa um cliente da tabela de clientes
 *
 * @author Maxwell
 */
public class ClienteBean {

    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private Date dataNascimento;
    private String logradouro;
    private String numero;
    private String bairro;
    private CidadeBean cidade;
    private String cep;
    private String telefone;
    private String celular;
    private String corCasa;
    private String numeroCasaFrente;
    private String numeroCasaLado;
    private String pontoReferencia;
    private String apelido;

    public ClienteBean() {
    }

    /**
     * Campo: id
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
     * Campo: no_cliente
     * @return the nome
     */
    public String getNome() {
        return nome.toUpperCase().trim();
    }

    /**
     * Campo: no_cliente
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Campo: rg_cliente
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * Campo: rg_cliente
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg.toUpperCase();
    }

    /**
     * Campo: cpf_cliente
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Campo: cpf_cliente
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = Utilidades.getDigitos(cpf);
    }

    /**
     * Campo: dt_nascimento
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Campo: dt_nascimento
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Campo: dt_nascimento
     * @return the dataNascimento
     */
    public String getDataNascimentoStr() {
        return Utilidades.formataDataSTR(dataNascimento);
    }

    /**
     * Campo: dt_nascimento
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = Utilidades.formataDataSQL(dataNascimento);
    }

    public CidadeBean getCidade() {
        return cidade;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    /**
     * Campo: cep_cliente
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Campo: cep_cliente
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = Utilidades.getDigitos(cep);
    }

    /**
     * Campo: tl_clente
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Campo: tl_cliente
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = Utilidades.getDigitos(telefone);
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = Utilidades.getDigitos(celular);
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCorCasa() {
        return corCasa;
    }

    public void setCorCasa(String corCasa) {
        this.corCasa = corCasa;
    }

    public String getNumeroCasaFrente() {
        return numeroCasaFrente;
    }

    public void setNumeroCasaFrente(String numeroCasaFrente) {
        this.numeroCasaFrente = numeroCasaFrente;
    }

    public String getNumeroCasaLado() {
        return numeroCasaLado;
    }

    public void setNumeroCasaLado(String numeroCasaLado) {
        this.numeroCasaLado = numeroCasaLado;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }  
    
}
