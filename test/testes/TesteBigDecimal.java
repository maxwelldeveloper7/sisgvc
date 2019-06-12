/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.devmax.sisgvc.modelo.Utilidades;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Maxwell
 */
public class TesteBigDecimal {

    public static void main(String args[]) {
        
        Integer consig = 0;
        Integer vendid = 5;
        BigDecimal desconto = new BigDecimal(Utilidades.formataFlutuante("0"));
        BigDecimal valorUnitario = new BigDecimal(Utilidades.formataFlutuante("1,50"));
        BigDecimal consignado = new BigDecimal(consig);
        BigDecimal vendido = new BigDecimal(vendid);
        BigDecimal divisor = new BigDecimal("1");

        BigDecimal Total = new BigDecimal("0");
        Total = Total.add(consignado.multiply(valorUnitario));
        Total = Total.add(vendido.multiply(valorUnitario));
        Total = Total.subtract(desconto);
        Total.divide(divisor, 2, RoundingMode.FLOOR);
                

        System.out.println(Utilidades.formataMontetarioSTR(Total));
    }
}
