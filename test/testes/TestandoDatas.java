/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Maxwell
 */
public class TestandoDatas {

    public static void main(String args[]) {
        Calendar dataInicial = Calendar.getInstance();
        dataInicial.setTime(new Date());
        SimpleDateFormat formadador = new SimpleDateFormat("dd/MM/yyyy");
        String novoFormato = formadador.format(dataInicial.getTime());
        System.out.println(novoFormato);
        dataInicial.add(Calendar.DAY_OF_MONTH, -7);
        novoFormato = formadador.format(dataInicial.getTime());
        System.out.println(novoFormato);
    }
}
