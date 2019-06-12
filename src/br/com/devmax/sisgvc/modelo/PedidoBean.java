/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Representa um pedido de venda da tabela de pedidos
 *
 * @author Maxwell
 */
public class PedidoBean {

    private Integer id;
    private EquipeBean equipe;
    private ClienteBean cliente;
    private Date dataEntrega;
    private Date dataVencimento;
    private StatusVendaBean status;
    private FuncionarioBean vendedor;
    private BigDecimal desconto;
    private BigDecimal valorPedido = new BigDecimal("0.00");
    private BigDecimal valorRecebido;
    private BigDecimal valorBrinde;
    private BigDecimal vlParcela01 = new BigDecimal("0.00");
    private BigDecimal vlParcela02 = new BigDecimal("0.00");
    private BigDecimal vlParcela03 = new BigDecimal("0.00");
    private BigDecimal vlParcela04 = new BigDecimal("0.00");
    private BigDecimal vlParcela05 = new BigDecimal("0.00");
    private BigDecimal vlParcela06 = new BigDecimal("0.00");
    private BigDecimal vlParcela07 = new BigDecimal("0.00");
    private BigDecimal vlParcela08 = new BigDecimal("0.00");
    private BigDecimal vlParcela09 = new BigDecimal("0.00");
    private BigDecimal vlParcela10 = new BigDecimal("0.00");
    private BigDecimal vlParcela11 = new BigDecimal("0.00");
    private BigDecimal vlParcela12 = new BigDecimal("0.00");
    private BigDecimal vlParcela13 = new BigDecimal("0.00");
    private BigDecimal vlParcela14 = new BigDecimal("0.00");
    private BigDecimal vlParcela15 = new BigDecimal("0.00");
    private Date dtParcela01;
    private Date dtParcela02;
    private Date dtParcela03;
    private Date dtParcela04;
    private Date dtParcela05;
    private Date dtParcela06;
    private Date dtParcela07;
    private Date dtParcela08;
    private Date dtParcela09;
    private Date dtParcela10;
    private Date dtParcela11;
    private Date dtParcela12;
    private Date dtParcela13;
    private Date dtParcela14;
    private Date dtParcela15;
    private MotivoPerdaBean motivoPerda;

    public MotivoPerdaBean getMotivoPerda() {
        return motivoPerda;
    }

    public void setMotivoPerda(MotivoPerdaBean motivoPerda) {
        this.motivoPerda = motivoPerda;
    }

    public PedidoBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EquipeBean getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeBean equipe) {
        this.equipe = equipe;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDataEntregaStr() {
        return Utilidades.formataDataSTR(dataEntrega);
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = Utilidades.formataDataSQL(dataEntrega);
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataVencimentoStr() {
        return Utilidades.formataDataSTR(dataVencimento);
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = Utilidades.formataDataSQL(dataVencimento);
    }

    public StatusVendaBean getStatus() {
        return status;
    }

    public void setStatus(StatusVendaBean status) {
        this.status = status;
    }

    public FuncionarioBean getVendedor() {
        return vendedor;
    }

    public void setVendedor(FuncionarioBean vendedor) {
        this.vendedor = vendedor;
    }
    

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public String getDescontoSTR() {
        return Utilidades.formataMontetarioSTR(desconto);
    }

    public void setDesconto(String desconto) {
        this.desconto = Utilidades.formataMonetarioSQL(desconto);
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getValorPedidoSTR() {
        return Utilidades.formataMontetarioSTR(valorPedido);
    }

    public void setValorPedido(String valorPedido) {
        this.valorPedido = Utilidades.formataMonetarioSQL(valorPedido);
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getValorRecebidoSTR() {
        return Utilidades.formataMontetarioSTR(valorRecebido);
    }

    public void setValorRecebido(String valorRecebido) {
        this.valorRecebido = Utilidades.formataMonetarioSQL(valorRecebido);
    }    

    public BigDecimal getValorBrinde() {
        return valorBrinde;
    }

    public void setValorBrinde(BigDecimal valorBrinde) {
        this.valorBrinde = valorBrinde;
    }

    public String getValorBrindeSTR() {
        return Utilidades.formataMontetarioSTR(valorBrinde);
    }

    public void setValorBrindeSTR(String valorBrinde) {
        this.valorBrinde = Utilidades.formataMonetarioSQL(valorBrinde);
    }

    public BigDecimal getVlParcela01() {
        return vlParcela01;
    }

    public void setVlParcela01(BigDecimal vl) {
        vlParcela01 = vl;
    }

    public String getVlParcela01Str() {
        return Utilidades.formataMontetarioSTR(vlParcela01);
    }

    public void setVlParcela01(String vl) {
        vlParcela01 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela01() {
        return dtParcela01;
    }

    public void setDtParcela01(Date dt) {
        dtParcela01 = dt;
    }

    public String getDtParcela01Str() {
        return Utilidades.formataDataSTR(dtParcela01);
    }

    public void setDtParcela01(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela01 = null;
        } else {
            dtParcela01 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela02() {
        return vlParcela02;
    }

    public void setVlParcela02(BigDecimal vl) {
        vlParcela02 = vl;
    }

    public String getVlParcela02Str() {
        return Utilidades.formataMontetarioSTR(vlParcela02);
    }

    public void setVlParcela02(String vl) {
        vlParcela02 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela02() {
        return dtParcela02;
    }

    public void setDtParcela02(Date dt) {
        dtParcela02 = dt;
    }

    public String getDtParcela02Str() {
        return Utilidades.formataDataSTR(dtParcela02);
    }

    public void setDtParcela02(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela02 = null;
        } else {
            dtParcela02 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela03() {
        return vlParcela03;
    }

    public void setVlParcela03(BigDecimal vl) {
        vlParcela03 = vl;
    }

    public String getVlParcela03Str() {
        return Utilidades.formataMontetarioSTR(vlParcela03);
    }

    public void setVlParcela03(String vl) {
        vlParcela03 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela03() {
        return dtParcela03;
    }

    public void setDtParcela03(Date dt) {
        dtParcela03 = dt;
    }

    public String getDtParcela03Str() {
        return Utilidades.formataDataSTR(dtParcela03);
    }

    public void setDtParcela03(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela03 = null;
        } else {
            dtParcela03 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela04() {
        return vlParcela04;
    }

    public void setVlParcela04(BigDecimal vl) {
        vlParcela04 = vl;
    }

    public String getVlParcela04Str() {
        return Utilidades.formataMontetarioSTR(vlParcela04);
    }

    public void setVlParcela04(String vl) {
        vlParcela04 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela04() {
        return dtParcela04;
    }

    public void setDtParcela04(Date dt) {
        dtParcela04 = dt;
    }

    public String getDtParcela04Str() {
        return Utilidades.formataDataSTR(dtParcela04);
    }

    public void setDtParcela04(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela04 = null;
        } else {
            dtParcela04 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela05() {
        return vlParcela05;
    }

    public void setVlParcela05(BigDecimal vl) {
        vlParcela05 = vl;
    }

    public String getVlParcela05Str() {
        return Utilidades.formataMontetarioSTR(vlParcela05);
    }

    public void setVlParcela05(String vl) {
        vlParcela05 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela05() {
        return dtParcela05;
    }

    public void setDtParcela05(Date dt) {
        dtParcela05 = dt;
    }

    public String getDtParcela05Str() {
        return Utilidades.formataDataSTR(dtParcela05);
    }

    public void setDtParcela05(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela05 = null;
        } else {
            dtParcela05 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela06() {
        return vlParcela06;
    }

    public void setVlParcela06(BigDecimal vl) {
        vlParcela06 = vl;
    }

    public String getVlParcela06Str() {
        return Utilidades.formataMontetarioSTR(vlParcela06);
    }

    public void setVlParcela06(String vl) {
        vlParcela06 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela06() {
        return dtParcela06;
    }

    public void setDtParcela06(Date dt) {
        dtParcela06 = dt;
    }

    public String getDtParcela06Str() {
        return Utilidades.formataDataSTR(dtParcela06);
    }

    public void setDtParcela06(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela06 = null;
        } else {
            dtParcela06 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela07() {
        return vlParcela07;
    }

    public void setVlParcela07(BigDecimal vl) {
        vlParcela07 = vl;
    }

    public String getVlParcela07Str() {
        return Utilidades.formataMontetarioSTR(vlParcela07);
    }

    public void setVlParcela07(String vl) {
        vlParcela07 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela07() {
        return dtParcela07;
    }

    public void setDtParcela07(Date dt) {
        dtParcela07 = dt;
    }

    public String getDtParcela07Str() {
        return Utilidades.formataDataSTR(dtParcela07);
    }

    public void setDtParcela07(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela07 = null;
        } else {
            dtParcela07 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela08() {
        return vlParcela08;
    }

    public void setVlParcela08(BigDecimal vl) {
        vlParcela08 = vl;
    }

    public String getVlParcela08Str() {
        return Utilidades.formataMontetarioSTR(vlParcela08);
    }

    public void setVlParcela08(String vl) {
        vlParcela08 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela08() {
        return dtParcela08;
    }

    public void setDtParcela08(Date dt) {
        dtParcela08 = dt;
    }

    public String getDtParcela08Str() {
        return Utilidades.formataDataSTR(dtParcela08);
    }

    public void setDtParcela08(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela08 = null;
        } else {
            dtParcela08 = Utilidades.formataDataSQL(dt);
        }
    }

    public BigDecimal getVlParcela09() {
        return vlParcela09;
    }

    public void setVlParcela09(BigDecimal vl) {
        vlParcela09 = vl;
    }

    public String getVlParcela09Str() {
        return Utilidades.formataMontetarioSTR(vlParcela09);
    }

    public void setVlParcela09(String vl) {
        vlParcela09 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela09() {
        return dtParcela09;
    }

    public void setDtParcela09(Date dt) {
        dtParcela09 = dt;
    }

    public String getDtParcela09Str() {
        return Utilidades.formataDataSTR(dtParcela09);
    }

    public void setDtParcela09(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela09 = null;
        } else {
            dtParcela09 = Utilidades.formataDataSQL(dt);
        }
    }
    
    
    
    public BigDecimal getVlParcela10() {
        return vlParcela10;
    }

    public void setVlParcela10(BigDecimal vl) {
        vlParcela10 = vl;
    }

    public String getVlParcela10Str() {
        return Utilidades.formataMontetarioSTR(vlParcela10);
    }

    public void setVlParcela10(String vl) {
        vlParcela10 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela10() {
        return dtParcela10;
    }

    public void setDtParcela10(Date dt) {
        dtParcela10 = dt;
    }

    public String getDtParcela10Str() {
        return Utilidades.formataDataSTR(dtParcela10);
    }

    public void setDtParcela10(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela10 = null;
        } else {
            dtParcela10 = Utilidades.formataDataSQL(dt);
        }
    }
    
    public BigDecimal getVlParcela11() {
        return vlParcela11;
    }

    public void setVlParcela11(BigDecimal vl) {
        vlParcela11 = vl;
    }

    public String getVlParcela11Str() {
        return Utilidades.formataMontetarioSTR(vlParcela11);
    }

    public void setVlParcela11(String vl) {
        vlParcela11 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela11() {
        return dtParcela11;
    }

    public void setDtParcela11(Date dt) {
        dtParcela11 = dt;
    }

    public String getDtParcela11Str() {
        return Utilidades.formataDataSTR(dtParcela11);
    }

    public void setDtParcela11(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela11 = null;
        } else {
            dtParcela11 = Utilidades.formataDataSQL(dt);
        }
    }
    
    public BigDecimal getVlParcela12() {
        return vlParcela12;
    }

    public void setVlParcela12(BigDecimal vl) {
        vlParcela12 = vl;
    }

    public String getVlParcela12Str() {
        return Utilidades.formataMontetarioSTR(vlParcela12);
    }

    public void setVlParcela12(String vl) {
        vlParcela12 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela12() {
        return dtParcela12;
    }

    public void setDtParcela12(Date dt) {
        dtParcela12 = dt;
    }

    public String getDtParcela12Str() {
        return Utilidades.formataDataSTR(dtParcela12);
    }

    public void setDtParcela12(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela12 = null;
        } else {
            dtParcela12 = Utilidades.formataDataSQL(dt);
        }
    }
    
    public BigDecimal getVlParcela13() {
        return vlParcela13;
    }

    public void setVlParcela13(BigDecimal vl) {
        vlParcela13 = vl;
    }

    public String getVlParcela13Str() {
        return Utilidades.formataMontetarioSTR(vlParcela13);
    }

    public void setVlParcela13(String vl) {
        vlParcela13 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela13() {
        return dtParcela13;
    }

    public void setDtParcela13(Date dt) {
        dtParcela13 = dt;
    }

    public String getDtParcela13Str() {
        return Utilidades.formataDataSTR(dtParcela13);
    }

    public void setDtParcela13(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela13 = null;
        } else {
            dtParcela13 = Utilidades.formataDataSQL(dt);
        }
    }
    
    public BigDecimal getVlParcela14() {
        return vlParcela14;
    }

    public void setVlParcela14(BigDecimal vl) {
        vlParcela14 = vl;
    }

    public String getVlParcela14Str() {
        return Utilidades.formataMontetarioSTR(vlParcela14);
    }

    public void setVlParcela14(String vl) {
        vlParcela14 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela14() {
        return dtParcela14;
    }

    public void setDtParcela14(Date dt) {
        dtParcela14 = dt;
    }

    public String getDtParcela14Str() {
        return Utilidades.formataDataSTR(dtParcela14);
    }

    public void setDtParcela14(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela14 = null;
        } else {
            dtParcela14 = Utilidades.formataDataSQL(dt);
        }
    }
    
    public BigDecimal getVlParcela15() {
        return vlParcela15;
    }

    public void setVlParcela15(BigDecimal vl) {
        vlParcela15 = vl;
    }

    public String getVlParcela15Str() {
        return Utilidades.formataMontetarioSTR(vlParcela15);
    }

    public void setVlParcela15(String vl) {
        vlParcela15 = Utilidades.formataMonetarioSQL(vl);
    }

    public Date getDtParcela15() {
        return dtParcela15;
    }

    public void setDtParcela15(Date dt) {
        dtParcela15 = dt;
    }

    public String getDtParcela15Str() {
        return Utilidades.formataDataSTR(dtParcela15);
    }

    public void setDtParcela15(String dt) {
        if (dt.equals("  /  /   ")) {
            dtParcela15 = null;
        } else {
            dtParcela15 = Utilidades.formataDataSQL(dt);
        }
    }
}
