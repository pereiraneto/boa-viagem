package com.ufc.jordao.boaviagem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ufc.jordao.boaviagem.controller.ViagemController;
import com.ufc.jordao.boaviagem.model.TipoGasto;
import com.ufc.jordao.boaviagem.model.Viagem;

public class NewExpenseActivity extends Activity implements View.OnClickListener {

    private Spinner categoriaSpinner;
    private Spinner locaisSpinner;
    private Button dataGastoBtn;
    private Date dataGasto;

    private EditText valorET;
    private EditText descricaoET;

    private ViagemController viagemController;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        viagemController = new ViagemController();

        ArrayAdapter<CharSequence> adapter = null;
        adapter = ArrayAdapter.createFromResource(this, R.array.categoria_gasto,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner = (Spinner) findViewById(R.id.categoria);
        categoriaSpinner.setAdapter(adapter);

        locaisSpinner = (Spinner) findViewById(R.id.locaisSpinner);
        ArrayAdapter<String> cidadesSpinner = new ArrayAdapter<String>(NewExpenseActivity.this, android.R.layout.simple_spinner_dropdown_item, viagemController.getDestinos());
        cidadesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locaisSpinner.setAdapter(cidadesSpinner);

        dataGastoBtn = (Button) findViewById(R.id.dataGasto);
        dataGastoBtn.setOnClickListener(this);

        valorET = (EditText) findViewById(R.id.valorEditText);
        descricaoET = (EditText) findViewById(R.id.descricaoEditText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(intent.hasExtra("pos")) {
            int posicao = bundle.getInt("pos");
            locaisSpinner.setSelection(posicao);
        }
    }

    public void salvarViagem(View view){
        TipoGasto tipoGasto = TipoGasto.OUTROS;// default
        switch (categoriaSpinner.getSelectedItem().toString()){
            case "Combustível":
                tipoGasto = TipoGasto.COMBUSTIVEL;
                break;
            case "Transporte":
                tipoGasto = TipoGasto.TRANSPORTE;
                break;
            case "Hospedagem":
                tipoGasto = TipoGasto.HOSPEDAGEM;
                break;
            case "Alimentação":
                tipoGasto = TipoGasto.ALIMENTACAO;
                break;
        }

        double valor = Double.parseDouble(valorET.getText().toString());
        String descricao = descricaoET.getText().toString();
        int indexViagem = locaisSpinner.getSelectedItemPosition();

        viagemController.addGasto(tipoGasto, valor, dataGasto, descricao, indexViagem);

        String message = "Gasto cadastrado com sucesso!";
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickBreadcrumb(View v){
        switch (v.getId()){
            case R.id.toHomeBreadcrumb:
                Intent newGastedActivity = new Intent(NewExpenseActivity.this, HomeActivity.class);
                startActivity(newGastedActivity);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case R.id.dataGasto:
                return new DatePickerDialog(this, dataGastoListener, ano, mes, dia);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dataGastoListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dataGasto = new Date(year - 1900, monthOfYear, dayOfMonth);
        SimpleDateFormat sd1 = new SimpleDateFormat("dd/MMM/yyyy");
        dataGastoBtn.setText(sd1.format(dataGasto));
        }
    };

    @Override
    public void onClick(View view) {
        if (view == dataGastoBtn)
            showDialog(R.id.dataGasto);
    }
}