/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 * Representa uma equipe da tabela de equipes
 *
 * @author Maxwell
 */
public class EquipeBean {
    private Integer id;
    private String Nome;

    public EquipeBean() {
    }

    public EquipeBean(Integer id, String Nome) {
        this.id = id;
        this.Nome = Nome;
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
     * Campo: no_equipe
     * @return the Nome
     */
    public String getNome() {
        return Nome.toUpperCase().trim();
    }

    /**
     * Campo: no_equipe
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    
}
