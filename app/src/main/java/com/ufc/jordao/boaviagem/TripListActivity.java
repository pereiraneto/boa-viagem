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
import com.ufc.jordao.boaviagem.model.Viagem;
import com.ufc.jordao.boaviagem.repository.ViagemRepository;

import java.util.ArrayList;
import java.util.List;

public class TripListActivity extends Activity implements MenuDialogFragment.NotificarEscutadorDoDialog, AdapterView.OnItemClickListener, SimpleAdapter.ViewBinder{

    ViagemController viagemController;
    ArrayAdapter<Viagem> adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        viagemController = new ViagemController();

        ArrayList<Viagem> listaViagens = viagemController.getAll();
        lista = (ListView) findViewById(R.id.lista);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listaViagens);

        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
    }

    public void onClickBreadcrumb(View v){
        switch (v.getId()){
            case R.id.toHomeBreadcrumb:
                Intent tripListActivity = new Intent(TripListActivity.this, HomeActivity.class);
                startActivity(tripListActivity);
                break;
        }
    }

    @Override
    public void onDialogEditarClick(int posicao) {
        Intent intent = new Intent(TripListActivity.this, NewTripActivity.class);
        intent.putExtra("pos", posicao);
        startActivity(intent);
    }

    @Override
    public void onDialogNovoGastoClick(int posicao) {
        Intent intent = new Intent(TripListActivity.this, NewExpenseActivity.class);
        intent.putExtra("pos", posicao);
        startActivity(intent);
    }

    @Override
    public void onDialogGastosRealizadosClick(int posicao) {
        Intent intent = new Intent(TripListActivity.this, ExpenseListActivity.class);
        intent.putExtra("pos", posicao);
        startActivity(intent);
    }

    @Override
    public void onDialogExcluiClick(int posicao) {
        viagemController.removeViagem(posicao);
        lista.setAdapter(adapter);
        lista.refreshDrawableState();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        MenuDialogFragment fragmento = new MenuDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragmento.setArguments(bundle);
        fragmento.show(this.getFragmentManager(), "confirma");
    }

    @Override
    public boolean setViewValue(View view, Object o, String s) {
        return false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        lista.refreshDrawableState();
    }
}
