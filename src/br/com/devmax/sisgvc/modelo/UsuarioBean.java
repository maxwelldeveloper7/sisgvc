/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

/**
 *
 * @author Maxwell
 */
public class UsuarioBean {

    private Integer id;
    private PerfilBean perfil;
    private String no_usuario;
    private String login;
    private String senha;
    private boolean ativo;

    public UsuarioBean() {
    }

    public UsuarioBean(Integer id, PerfilBean perfil, String no_usuario, String login, String senha, Boolean ativo) {
        this.id = id;
        this.perfil = perfil;
        this.no_usuario = no_usuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    

    /**
     * id da tabela de usuarios
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id da tabela de usuarios
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Perfil do usuário
     * @return the perfil
     */
    public PerfilBean getPerfil() {
        return perfil;
    }

    /**
     * Perfil do usuário
     * @param perfil the perfil to set
     */
    public void setPerfil(PerfilBean perfil) {
        this.perfil = perfil;
    }

    /**
     * Nome do usuário
     * @return the no_usuario
     */
    public String getNoUsuario() {
        return no_usuario;
    }

    /**
     * Nome do usuário
     * @param no_usuario the no_usuario to set
     */
    public void setNoUsuario(String no_usuario) {
        this.no_usuario = no_usuario;
    }

    /**
     * Login do usuário
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Logim do usuário
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Senha do usuário
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Senha do usuário
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
}
