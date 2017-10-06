package com.ufc.jordao.boaviagem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ufc.jordao.boaviagem.controller.ViagemController;
import com.ufc.jordao.boaviagem.model.TipoViagem;
import com.ufc.jordao.boaviagem.model.Viagem;

public class NewTripActivity extends Activity implements Button.OnClickListener {

    private Button dataChegadaButton;
    private Button dataSaidaButton;
    private Date dataSaida;
    private Date dataChegada;

    private EditText destinoET;
    private RadioGroup tipoRdGroup;
    private RadioButton tipoRdButton;
    private EditText orcamentoET;
    private EditText qtdPessoasET;

    private Viagem viagem;

    private ViagemController viagemController;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        dataChegadaButton = (Button) findViewById(R.id.dataChegada);
        dataSaidaButton = (Button) findViewById(R.id.dataSaida);
        dataChegadaButton.setOnClickListener(this);
        dataSaidaButton.setOnClickListener(this);

        destinoET = (EditText) findViewById(R.id.destinoEditText);
        tipoRdGroup = (RadioGroup) findViewById(R.id.tipoViagemRadioButton);
        orcamentoET = (EditText) findViewById(R.id.orcamentoEditText);
        qtdPessoasET = (EditText) findViewById(R.id.qtdPessoasEditText);

        viagemController = new ViagemController();

        /* Verifica se é edição ou cadastro */
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        viagem = null;

        if(intent.hasExtra("pos")){
            int posicao = bundle.getInt("pos");
            viagem = viagemController.getByIndex(posicao);
            destinoET.setText(viagem.getDestino());
            dataChegadaButton.setText(viagem.getDataChegada().toString());
            dataSaidaButton.setText(viagem.getDataSaida().toString());
            orcamentoET.setText(""+viagem.getOrcamento());
            qtdPessoasET.setText(""+viagem.getQtdPessoas());
        }
    }

    public void salvarViagem(View view){
        int selectedId = tipoRdGroup.getCheckedRadioButtonId();

        tipoRdButton = (RadioButton) findViewById(selectedId);

        String destino = destinoET.getText().toString();

        TipoViagem tipoViagem = TipoViagem.LAZER;// Default
        switch (tipoRdButton.getText().toString()){
            case "Lazer":
                tipoViagem = TipoViagem.LAZER;
                break;
            case "Negócios":
                tipoViagem = TipoViagem.NEGOCIOS;
                break;
        }

        double orcamento = Double.parseDouble(orcamentoET.getText().toString());
        int qtdPessoas = Integer.parseInt(qtdPessoasET.getText().toString());

        if(viagem == null) {
            viagemController.add(destino, tipoViagem, dataChegada, dataSaida, orcamento, qtdPessoas);

            String message = "Viagem cadastrada com sucesso!";
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            viagemController.editViagem(viagem, destino, tipoViagem, dataChegada, dataSaida, orcamento, qtdPessoas);

            String message = "Viagem editada com sucesso!";
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }

    public void onClickBreadcrumb(View v){
        switch (v.getId()){
            case R.id.toHomeBreadcrumb:
                Intent newTripActivity = new Intent(NewTripActivity.this, HomeActivity.class);
                startActivity(newTripActivity);
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
            case R.id.dataChegada:
                return new DatePickerDialog(this, dataChegadaListener, ano, mes, dia);
            case R.id.dataSaida:
                return new DatePickerDialog(this, dataSaidaListener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dataSaidaListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataSaida = new Date(year - 1900, monthOfYear, dayOfMonth);
            SimpleDateFormat sd1 = new SimpleDateFormat("dd/MMM/yyyy");
            dataSaidaButton.setText(sd1.format(dataSaida));
        }
    };

    private DatePickerDialog.OnDateSetListener dataChegadaListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataChegada = new Date(year - 1900, monthOfYear, dayOfMonth);
            SimpleDateFormat sd1 = new SimpleDateFormat("dd/MMM/yyyy");
            dataChegadaButton.setText(sd1.format(dataChegada));
        }
    };

    @Override
    public void onClick(View v) {
        if (v == dataChegadaButton)
            showDialog(R.id.dataChegada);
        else if (v == dataSaidaButton)
            showDialog(R.id.dataSaida);
    }
}
