package com.ufc.jordao.boaviagem.repository;

import com.ufc.jordao.boaviagem.model.Gasto;
import com.ufc.jordao.boaviagem.model.TipoGasto;
import com.ufc.jordao.boaviagem.model.TipoViagem;
import com.ufc.jordao.boaviagem.model.Viagem;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jordao on 15/09/17.
 */

public class ViagemRepository {
    private ArrayList<Viagem> listViagens;
    private static volatile ViagemRepository INSTANCE;

    private ViagemRepository(){
        listViagens = new ArrayList<Viagem>();
        test(); // coment this if the test phase ends
    }

    public static ViagemRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ViagemRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViagemRepository();
                }
            }
        }
        return INSTANCE;
    }

    public void addViagem(Viagem viagem){
        listViagens.add(viagem);
    }

    public void editViagem(Viagem velha, Viagem nova){
        listViagens.remove(velha);
        listViagens.add(nova);
    }

    public ArrayList<Viagem> getAll(){
        return this.listViagens;
    }

    public void addGasto(Gasto gasto, Viagem viagem){
        ArrayList<Gasto> gastos = viagem.getListGastos();
        gastos.add(gasto);
        viagem.setListGastos(gastos);
        Viagem aux = viagem;

        listViagens.remove(viagem);

        listViagens.add(aux);
    }

    public void removeViagem(Viagem viagem){
        listViagens.remove(viagem);
    }
    
    private void test(){
        /* FOR TESTS */
        Viagem viagem1 = new Viagem("Madalena", TipoViagem.LAZER, new Date(2017, 8, 10), new Date(2017, 8, 10), 1000.0, 5);
        Viagem viagem2 = new Viagem("Banabuiu", TipoViagem.NEGOCIOS, new Date(2017, 9, 11), new Date(2017, 9, 10), 1000.0, 3);
        Viagem viagem3 = new Viagem("Quixadá", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem5 = new Viagem("Fortaleza", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem6 = new Viagem("Canindé", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem7 = new Viagem("Horizonte", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem8 = new Viagem("Boa Viagem", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem9 = new Viagem("Quixeramobim", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem10 = new Viagem("Uruquê", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem11 = new Viagem("Capixaba", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);
        Viagem viagem12 = new Viagem("Ubaretama", TipoViagem.LAZER, new Date(2017, 7, 20), new Date(2017, 9, 18), 1200.0, 2);

        /* MAKE TESTS OF EXPENSES */

        Gasto gasto = new Gasto(TipoGasto.TRANSPORTE, 100.0, new Date(2017, 7, 20), "Teste1");
        Gasto gasto1 = new Gasto(TipoGasto.COMBUSTIVEL, 100.0, new Date(2017, 7, 20), "Teste2");
        Gasto gasto2 = new Gasto(TipoGasto.ALIMENTACAO, 100.0, new Date(2017, 7, 20), "Teste3");
        Gasto gasto3 = new Gasto(TipoGasto.OUTROS, 100.0, new Date(2017, 7, 20), "Teste4");
        Gasto gasto4 = new Gasto(TipoGasto.TRANSPORTE, 100.0, new Date(2017, 7, 20), "Teste5");
        Gasto gasto5 = new Gasto(TipoGasto.HOSPEDAGEM, 100.0, new Date(2017, 7, 20), "Teste6");

        listViagens.add(viagem1);
        listViagens.add(viagem2);
        listViagens.add(viagem3);
        listViagens.add(viagem5);
        listViagens.add(viagem6);
        listViagens.add(viagem7);
        listViagens.add(viagem8);
        listViagens.add(viagem9);
        listViagens.add(viagem10);
        listViagens.add(viagem11);
        listViagens.add(viagem12);

        addGasto(gasto, viagem1);
        addGasto(gasto1, viagem1);
        addGasto(gasto2, viagem2);
        addGasto(gasto3, viagem3);
        addGasto(gasto4, viagem6);
        addGasto(gasto5, viagem5);
    }
}
