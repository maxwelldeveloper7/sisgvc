/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class DestinoBean {
    private Long id;
    private SaidaBean saida;
    private CidadeBean cidade;

    public CidadeBean getCidade() {
        return cidade;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaidaBean getSaida() {
        return saida;
    }

    public void setSaida(SaidaBean saida) {
        this.saida = saida;
    }

    
}
