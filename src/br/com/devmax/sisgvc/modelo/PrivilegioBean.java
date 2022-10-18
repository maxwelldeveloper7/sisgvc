/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;


/**
 *
 * @author Maxwell
 */
public class PrivilegioBean{

    private Integer id;    
    private RotinaBean cd_rotina;
    private PerfilBean cd_perfil;    
    private Boolean acessar;    
    private Boolean adicionar;    
    private Boolean editar;    
    private Boolean excluir;

    public PrivilegioBean() {
    }

    public PrivilegioBean(Integer id, RotinaBean rotina, PerfilBean perfil, Boolean acessar, Boolean adicionar, Boolean editar, Boolean excluir) {
        this.id = id;
        this.cd_rotina = rotina;
        this.cd_perfil = perfil;
        this.acessar = acessar;
        this.adicionar = adicionar;
        this.editar = editar;
        this.excluir = excluir;
    }

    /**
     * id da tabela de privilégios
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id da tabela de privilégios
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * código da rotina
     * @return the rotina
     */
    public RotinaBean getRotina() {
        return cd_rotina;
    }

    /**
     * código da rotina
     * @param rotina the rotina to set
     */
    public void setRotina(RotinaBean rotina) {
        this.cd_rotina = rotina;
    }

    /**
     * código do perfil
     * @return the perfil
     */
    public PerfilBean getPerfil() {
        return cd_perfil;
    }

    /**
     * código do perfil
     * @param perfil the perfil to set
     */
    public void setPerfil(PerfilBean perfil) {
        this.cd_perfil = perfil;
    }

    /**
     * acessar da tabela de privilégios
     * @return the acessar
     */
    public Boolean getAcessar() {
        return acessar;
    }

    /**
     * acessar da tabela de privilégios
     * @param acessar the acessar to set
     */
    public void setAcessar(Boolean acessar) {
        this.acessar = acessar;
    }

    /**
     * adicionar da tabela de privilégios
     * @return the adicionar
     */
    public Boolean getAdicionar() {
        return adicionar;
    }

    /**
     * adicionar da tabela de privilégios
     * @param adicionar the adicionar to set
     */
    public void setAdicionar(Boolean adicionar) {
        this.adicionar = adicionar;
    }

    /**
     * editar da tabela de privilégios
     * @return the editar
     */
    public Boolean getEditar() {
        return editar;
    }

    /**
     * editar da tabela de privilégios
     * @param editar the editar to set
     */
    public void setEditar(Boolean editar) {
        this.editar = editar;
    }

    /**
     * excluir da tabela de privilégios
     * @return the excluir
     */
    public Boolean getExcluir() {
        return excluir;
    }

    /**
     * escluir da tabela de privilégios
     * @param excluir the excluir to set
     */
    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }
}
