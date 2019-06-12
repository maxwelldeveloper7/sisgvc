/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class ConsolidadoDePedidosBean {
    
    
    private Integer id;
    private String nomeCliente;
    private String endereco;
    private String numero;
    private String bairro;
    private String nomeCidade;
    private String uf;
    private Integer consignado = 0;
    private Integer pendente = 0;
    private Integer devolvido = 0;
    private Integer concluido = 0;
    private Integer perdido = 0;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getConsignado() {
        return consignado;
    }

    public void setConsignado(Integer consignado) {
        this.consignado = consignado;
    }

    public Integer getPendente() {
        return pendente;
    }

    public void setPendente(Integer pendente) {
        this.pendente = pendente;
    }

    public Integer getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(Integer devolvido) {
        this.devolvido = devolvido;
    }

    public Integer getConcluido() {
        return concluido;
    }

    public void setConcluido(Integer concluido) {
        this.concluido = concluido;
    }

    public Integer getPerdido() {
        return perdido;
    }

    public void setPerdido(Integer perdido) {
        this.perdido = perdido;
    }    
}
