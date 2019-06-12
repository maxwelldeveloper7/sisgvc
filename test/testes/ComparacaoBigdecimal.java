/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testes;

import br.com.devmax.sisgvc.modelo.Utilidades;
import java.math.BigDecimal;

/**
 *
 * @author Maxwell
 */
public class ComparacaoBigdecimal {

    public static void main (String args[]){
        BigDecimal vlPedido = new BigDecimal("100");
        BigDecimal comissao = new BigDecimal("0");
        BigDecimal recebido = new BigDecimal("100");
        BigDecimal totalPedido = new BigDecimal("0");
        totalPedido = totalPedido.add(comissao);
        totalPedido = totalPedido.add(vlPedido);

        System.out.println(vlPedido);
        System.out.println(comissao);
        System.out.println(recebido);
        System.out.println(totalPedido);

        System.out.println(recebido.equals(BigDecimal.ZERO));
        System.out.println(!recebido.equals(BigDecimal.ZERO)&& vlPedido.compareTo(recebido)==1);
        System.out.println(!recebido.equals(BigDecimal.ZERO)&& vlPedido.compareTo(recebido)==0);
        System.out.println(!recebido.equals(BigDecimal.ZERO)&& vlPedido.compareTo(recebido)==-1);
    }
}
