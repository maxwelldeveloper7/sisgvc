/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmax.sisgvc.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxwell
 */
public class SaidaBean {
    private Integer id;
    private EquipeBean equipe;
    private Date dataSaida;
    private Date dataChegada;
    private Integer kmSaida;
    private Integer kmChegada;
    private Integer qtPedidos;
    private BigDecimal cobranca;
    private BigDecimal aluguelHospedagem;
    private BigDecimal abastecimento;
    private BigDecimal comissao;
    private List<DestinoBean> destinos = new ArrayList<DestinoBean>();

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

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataSaidaSTR() {
        return Utilidades.formataDataSTR(dataSaida);
    }

    public void setDataSaidaSTR(String dataSaida) {
        this.dataSaida = Utilidades.formataDataSQL(dataSaida);
    }

    public Integer getKmSaida() {
        return kmSaida;
    }

    public void setKmSaida(Integer kmSaida) {
        this.kmSaida = kmSaida;
    }

    public Integer getKmChegada() {
        return kmChegada;
    }

    public void setKmChegada(Integer kmChegada) {
        this.kmChegada = kmChegada;
    }

    public BigDecimal getCobranca() {
        return cobranca;
    }

    public void setCobranca(BigDecimal cobranca) {
        this.cobranca = cobranca;
    }

    public String getCobrancaSTR() {
        return Utilidades.formataMontetarioSTR(cobranca);
    }

    public void setCobrancaSTR(String cobranca) {
        this.cobranca = Utilidades.formataMonetarioSQL(cobranca);
    }

    public BigDecimal getAluguelHospedagem() {
        return aluguelHospedagem;
    }

    public void setAluguelHospedagem(BigDecimal aluguelHospedagem) {
        this.aluguelHospedagem = aluguelHospedagem;
    }

    public String getAluguelHospedagemSTR() {
        return Utilidades.formataMontetarioSTR(aluguelHospedagem);
    }

    public void setAluguelHospedagemSTR(String aluguelHospedagem) {
        this.aluguelHospedagem = Utilidades.formataMonetarioSQL(aluguelHospedagem);
    }
    
    public BigDecimal getAbastecimento() {
        return abastecimento;
    }

    public void setAbastecimento(BigDecimal abastecimento) {
        this.abastecimento = abastecimento;
    }

    public String getAbastecimentoSTR() {
        return Utilidades.formataMontetarioSTR(abastecimento);
    }

    public void setAbastecimentoSTR(String abastecimento) {
        this.abastecimento = Utilidades.formataMonetarioSQL(abastecimento);
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public String getComissaoSTR() {
        return Utilidades.formataMontetarioSTR(comissao);
    }

    public void setComissaoSTR(String comissao) {
        this.comissao = Utilidades.formataMonetarioSQL(comissao);
    }

    public List<DestinoBean> getDestinos() {
        return destinos;
    }

    public String getDestinosSTR(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < destinos.size(); i++) {
            sb.append(destinos.get(i).getCidade().getCidade());
            if(destinos.size()!=i+1){
                sb.append(" - ");
            }
        }
        return sb.toString();
    }

    public void setDestinos(List<DestinoBean> destinos) {
        this.destinos = destinos;
    }

    public Integer getQtPedidos() {
        return qtPedidos;
    }

    public void setQtPedidos(Integer qtPedidos) {
        this.qtPedidos = qtPedidos;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getDataChegadaSTR() {
        return Utilidades.formataDataSTR(dataChegada);
    }

    public void setDataChegadaSTR(String dataChegada) {
        this.dataChegada = Utilidades.formataDataSQL(dataChegada);
    }
}
