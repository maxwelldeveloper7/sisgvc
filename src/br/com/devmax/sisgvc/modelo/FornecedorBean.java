/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 *Representa um fornecedor da tabela de fornecedores
 *
 * @author Maxwell
 */
public class FornecedorBean {
    private Integer id;
    private String nome;
    private String cnpj;
    private String ie;
    private String endereco;
    private String uf;
    private String cidade;
    private String cep;
    private String telefone;

    public FornecedorBean() {
    }

    public FornecedorBean(Integer id, String nome, String cnpj, String ie, String endereco, String uf, String cidade, String cep, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.endereco = endereco;
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
        this.telefone = telefone;
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
     * Campo: no_fornecedor
     * @return the nome
     */
    public String getNome() {
        return nome.toUpperCase().trim();
    }

    /**
     * Campo: no_fornecedor
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Campo: cnpj
     * @return the cnpj
     */
    public String getCnpj() {
        return Utilidades.getDigitos(cnpj);
    }

    /**
     * Campo: cnpj
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Campo: ie
     * @return the ie
     */
    public String getIe() {
        return Utilidades.getDigitos(ie);
    }

    /**
     * Campo: ie
     * @param ie the ie to set
     */
    public void setIe(String ie) {
        this.ie = ie;
    }

    /**
     * Campo: ed_fornecedor
     * @return the endereco
     */
    public String getEndereco() {
        return endereco.toUpperCase().trim();
    }

    /**
     * Campo: ed_fornecedor
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Campo: uf_fornecedor
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * Campo: uf_fornecedor
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Campo: cid_fornecedor
     * @return the cidade
     */
    public String getCidade() {
        return cidade.toUpperCase().trim();
    }

    /**
     * Campo: cid_fornecedor
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Campo: cep_fornecedor
     * @return the cep
     */
    public String getCep() {
        return Utilidades.getDigitos(cep);
    }

    /**
     * Campo: cep_fornecedor
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Campo: tl_fornecodor
     * @return the telefone
     */
    public String getTelefone() {
        return Utilidades.getDigitos(telefone);
    }

    /**
     * Campo: tl_fornecedor
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
}
