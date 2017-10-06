package com.ufc.jordao.boaviagem.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jordao on 15/09/17.
 */

public class Viagem {
    private String destino;
    private TipoViagem tipo;
    private Date dataChegada;
    private Date dataSaida;
    private double orcamento;
    private int qtdPessoas;
    private ArrayList<Gasto> listGastos;

    public Viagem(String destino, TipoViagem tipo, Date dataChegada, Date dataSaida, double orcamento, int qtdPessoas) {
        this.destino = destino;
        this.tipo = tipo;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.orcamento = orcamento;
        this.qtdPessoas = qtdPessoas;
        this.listGastos = new ArrayList<Gasto>();
    }

    public ArrayList<Gasto> getListGastos() {
        return listGastos;
    }

    public void setListGastos(ArrayList<Gasto> listGastos) {
        this.listGastos = listGastos;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public TipoViagem getTipo() {
        return tipo;
    }

    public void setTipo(TipoViagem tipo) {
        this.tipo = tipo;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public double getGastoTotal(){
        double total = 0;
        for (Gasto gasto: listGastos) {
            total += gasto.getValor();
        }
        return total;
    }

    @Override
    public String toString() {
        return destino + "\n"
                + dataSaida + " a " + dataChegada + "\n"
                + "Gasto total: " + getGastoTotal();
    }
}
