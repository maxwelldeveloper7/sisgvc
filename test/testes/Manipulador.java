/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Maxwell
 */
public class Manipulador {
    
    public static void main (String args[]) throws FileNotFoundException, IOException{
        
        
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./configuracoes.properties");
        props.load(file);        
        String host;
        String caminho;
        
        host = props.getProperty("host");
        caminho = props.getProperty("path.backup");
        
        System.out.println(host+"\n"+caminho);
        
        
    }
    
}
