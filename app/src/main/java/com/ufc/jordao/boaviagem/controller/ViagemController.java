package com.ufc.jordao.boaviagem.controller;

import com.ufc.jordao.boaviagem.model.Gasto;
import com.ufc.jordao.boaviagem.model.TipoGasto;
import com.ufc.jordao.boaviagem.model.TipoViagem;
import com.ufc.jordao.boaviagem.model.Viagem;
import com.ufc.jordao.boaviagem.repository.ViagemRepository;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jordao on 16/09/17.
 */

public class ViagemController {
    private ViagemRepository repository;

    public ViagemController(){
        repository = ViagemRepository.getInstance();
    }

    public void addGasto(TipoGasto tipoGasto, double valor, Date data, String descricao, int indexViagem){
        Gasto gasto = new Gasto(tipoGasto, valor, data, descricao);
        Viagem viagem = getByIndex(indexViagem);

        repository.addGasto(gasto, viagem);
    }

    public void addGasto(Gasto gasto, Viagem  viagem){
        repository.addGasto(gasto, viagem);
    }

    public void add(String destino, TipoViagem tipo, Date dataChegada, Date dataSaida, double orcamento, int qtdPessoas){
        Viagem viagem = new Viagem(destino, tipo, dataChegada, dataSaida, orcamento, qtdPessoas);
        repository.addViagem(viagem);
    }

    public void editViagem(Viagem viagem, String destino, TipoViagem tipo, Date dataChegada, Date dataSaida, double orcamento, int qtdPessoas){
        Viagem novaViagem = new Viagem(destino, tipo, dataChegada, dataSaida, orcamento, qtdPessoas);
        repository.editViagem(viagem, novaViagem);
    }

    public void add(Viagem viagem){
        repository.addViagem(viagem);
    }

    public ArrayList<Viagem> getAll(){
        return repository.getAll();
    }

    public ArrayList<String> getDestinos(){
        ArrayList<String> destinos = new ArrayList<String>();
        for (Viagem viagem : repository.getAll()) {
            destinos.add(viagem.getDestino());
        }
        return destinos;
    }

    public Viagem getByIndex(int index){
        return repository.getAll().get(index);
    }

    public void removeViagem(int index){
        Viagem viagem = getByIndex(index);
        repository.removeViagem(viagem);
    }
}
