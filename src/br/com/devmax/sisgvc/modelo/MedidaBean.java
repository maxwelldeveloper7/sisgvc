/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 * Representa uma unidade de medida da tabela de medidas
 *
 * @author Maxwell
 */
public class MedidaBean {

    private Integer id;
    private String medida;

    public MedidaBean() {
    }

    public MedidaBean(Integer id, String medida) {
        this.id = id;
        this.medida = medida;
    }

    /**
     *
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
     * Campo: no_medida
     * @return the medida
     */
    public String getMedida() {
        return medida.toUpperCase().trim();
    }

    /**
     * Campo: no_medida
     * @param medida the medida to set
     */
    public void setMedida(String medida) {
        this.medida = medida;
    }

    
}
