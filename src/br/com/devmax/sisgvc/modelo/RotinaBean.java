/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class RotinaBean {

    private Integer id;
    
    private String no_rotina;

    public RotinaBean() {
    }

    /**
     * id da tabela de rotinas
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id da tabela de rotinas
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Nome da Rotina
     * @return the no_rotina
     */
    public String getNomeRotina() {
        return no_rotina;
    }

    /**
     * Nome da Rotina
     * @param nomeRotina the no_rotina to set
     */
    public void setNomeRotina(String nomeRotina) {
        this.no_rotina = nomeRotina.toUpperCase();
    }


}
