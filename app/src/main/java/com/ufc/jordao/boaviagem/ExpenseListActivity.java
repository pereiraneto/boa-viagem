package com.ufc.jordao.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ufc.jordao.boaviagem.controller.ViagemController;
import com.ufc.jordao.boaviagem.model.Gasto;
import com.ufc.jordao.boaviagem.model.Viagem;

import java.util.ArrayList;

public class ExpenseListActivity extends Activity {

    ViagemController viagemController;
    ArrayAdapter<Gasto> adapter;
    ListView listViewGastos;
    Viagem viagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        viagemController = new ViagemController();

        /* Recuperar viagem passada */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int posicao = bundle.getInt("pos");
        viagem = viagemController.getByIndex(posicao);

        ArrayList<Gasto> listaGastos = viagem.getListGastos();
        listViewGastos = (ListView) findViewById(R.id.listaGastos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaGastos);

        listViewGastos.setAdapter(adapter);
    }

    public void onClickBreadcrumb(View v){
        switch (v.getId()){
            case R.id.toHomeBreadcrumb:
                Intent homeActivity = new Intent(ExpenseListActivity.this, HomeActivity.class);
                startActivity(homeActivity);
                break;
            case R.id.toExpenseList:
                Intent expenseListActivity = new Intent(ExpenseListActivity.this, TripListActivity.class);
                startActivity(expenseListActivity);
                break;
        }
    }
}
