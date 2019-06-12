/*
 * Contato.java
 *
 * Created on 2 de Setembro de 2006, 19:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package dados;

/**
 *
 * @author fernando
 */
public class Host {
    
    private String nome;
    private String ip;
    private SistemaOperacional so;
    private int memoriaRAM;
    
    /** Creates a new instance of Contato */
    public Host() {
    }

    public Host(String nome, String ip, SistemaOperacional so, int memoriaRAM) {
        setNome(nome);
        setIp(ip);
        setSo(so);
        setMemoriaRAM(memoriaRAM);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public SistemaOperacional getSo() {
        return so;
    }

    public void setSo(SistemaOperacional so) {
        this.so = so;
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(int memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }
    
}
