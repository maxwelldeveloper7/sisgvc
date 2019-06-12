/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class CidadeBean {

    private Integer id;
    private String cidade;
    private String uf;

    public String getCidade() {
        return cidade.toUpperCase().trim();
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUf() {
        return uf.toUpperCase().trim();
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
