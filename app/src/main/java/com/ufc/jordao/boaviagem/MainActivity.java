package com.ufc.jordao.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText userEditText;
    private EditText passEditText;
    private static String user = "leitor";
    private static String pass = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userEditText = (EditText) findViewById(R.id.userEditText);
        this.passEditText = (EditText) findViewById(R.id.passEditText);
    }

    public void login(View v){
        String formUser = userEditText.getText().toString();
        String formPass = passEditText.getText().toString();
        if(formUser.equals(this.user) && formPass.equals(this.pass)){
            Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivity);
        } else {
            String errorMessage = "Usuário ou senha inválidos";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
