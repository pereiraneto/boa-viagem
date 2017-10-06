package com.ufc.jordao.boaviagem.model;

import java.util.Date;

/**
 * Created by jordao on 15/09/17.
 */

public class Gasto {
    private TipoGasto tipoGasto;
    private double valor;
    private Date data;
    private String descricao;

    public Gasto(TipoGasto tipoGasto, double valor, Date data, String descricao) {
        this.tipoGasto = tipoGasto;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao + "\n" +
                valor + "\n" +
                data;
    }
}
