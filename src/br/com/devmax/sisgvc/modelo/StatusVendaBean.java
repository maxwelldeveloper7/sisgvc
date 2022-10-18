/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 * Representa o status de uma venda da tabela status_venda
 *
 * @author Maxwell
 */
public class StatusVendaBean {

    private Integer id;
    private String status;

    public StatusVendaBean() {
    }

    public StatusVendaBean(Integer id, String status) {
        this.id = id;
        this.status = status;
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
     * Campo: st_venda
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Campo: st_venda
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusVenda(Integer id, String status){
        this.id = id;
        this.status = status;
    }
    
}
