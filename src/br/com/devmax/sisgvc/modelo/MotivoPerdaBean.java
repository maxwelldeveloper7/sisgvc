/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;



/**
 *
 * @author Maxwell
 */
public class MotivoPerdaBean {

    private Integer id = 1;
    private String motivo = "Desconhecido";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
            return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
