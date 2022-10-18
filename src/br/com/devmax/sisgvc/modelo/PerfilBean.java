/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class PerfilBean {

    private Integer id;
    private String no_perfil;
    private String ds_perfil;

    public PerfilBean() {
    }

    public PerfilBean(Integer id, String no_perfil, String ds_perfil) {
        this.id = id;
        this.no_perfil = no_perfil.toUpperCase();
        this.ds_perfil = ds_perfil;
    }

    /**
     * id tabela de perfis
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id da tabela de perfis
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Nome do perfil da tabela de perfis
     * @return the no_perfil
     */
    public String getNoPerfil() {
        return no_perfil;
    }

    /**
     * Nome do perfil da tabela de perfis
     * @param no_perfil the no_perfil to set
     */
    public void setNoPerfil(String no_perfil) {
        this.no_perfil = no_perfil.toUpperCase();
    }

    /**
     * Descrição do perfil da tabela de perfis
     * @return the ds_perfil
     */
    public String getDsPerfil() {
        return ds_perfil;
    }

    /**
     * Descrição do perfil da tabela de perfis
     * @param ds_perfil the ds_perfil to set
     */
    public void setDsPerfil(String ds_perfil) {
        this.ds_perfil = ds_perfil;
    }

    

}
