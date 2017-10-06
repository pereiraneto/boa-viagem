package com.ufc.jordao.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ufc.jordao.boaviagem.controller.ViagemController;
import com.ufc.jordao.boaviagem.model.Gasto;
import com.ufc.jordao.boaviagem.model.TipoGasto;
import com.ufc.jordao.boaviagem.model.TipoViagem;
import com.ufc.jordao.boaviagem.model.Viagem;

import java.util.Date;

/**
 * Created by jordao on 09/09/17.
 */

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void selecionarOpcao(View v){
        switch (v.getId()){
            case R.id.item_novo_gasto:
                Intent newExpenseActivity = new Intent(HomeActivity.this, NewExpenseActivity.class);
                startActivity(newExpenseActivity);
                break;
            case R.id.item_nova_viagem:
                Intent newTripActivity = new Intent(HomeActivity.this, NewTripActivity.class);
                startActivity(newTripActivity);
                break;
            case R.id.item_minhas_viagens:
                Intent tripListActivity = new Intent(HomeActivity.this, TripListActivity.class);
                startActivity(tripListActivity);
                break;
            case R.id.item_configuracoes:
                break;
        }
    }
}
