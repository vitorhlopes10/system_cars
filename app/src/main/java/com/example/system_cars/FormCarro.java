package com.example.system_cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class FormCarro extends AppCompatActivity {

    private Button btnCancelar;
    private Button btnSalvar;
    private EditText txtMarca;
    private EditText txtModelo;
    private EditText txtAnoFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_carro);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnSalvar = findViewById(R.id.btnSalvar);

        txtMarca = findViewById(R.id.formEditTextMarca);
        txtModelo = findViewById(R.id.formEditTextModelo);
        txtAnoFab = findViewById(R.id.formEditTextAnoFab);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean prosseguir = validarCampos();

                if (prosseguir)
                    salvar();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private boolean validarCampos() {
        boolean prosseguir = true;

        if (txtMarca.getText().toString().trim().equals("")) {
            txtMarca.setError("Campo de preenchimento obrigatório!");
            prosseguir = false;
        }

        if (txtModelo.getText().toString().trim().equals("")) {
            txtModelo.setError("Campo de preenchimento obrigatório!");
            prosseguir = false;
        }

        if (txtAnoFab.getText().toString().trim().equals("")) {
            txtAnoFab.setError("Campo de preenchimento obrigatório!");
            prosseguir = false;
        }

        return prosseguir;
    }

    private void salvar() {

        ContentValues cv = new ContentValues();

        cv.put("marca", txtMarca.getText().toString().trim());
        cv.put("modelo", txtModelo.getText().toString().trim());
        cv.put("anoFab", txtAnoFab.getText().toString().trim());

        DBHelper bd = new DBHelper(FormCarro.this);
        String msg = "";

        if (bd.inserirCarro(cv) > 0) {
            msg = "O Carro foi inserido com sucesso!";
            limparCampos();

            List<ContentValues> cvList = bd.pesquisarCarro();

            for (ContentValues item : cvList) {
                Log.i("ID", item.getAsString("id"));
                Log.i("Marca", item.getAsString("marca"));
                Log.i("Modelo", item.getAsString("modelo"));
                Log.i("Ano de Fabricação", item.getAsString("anoFab"));
            }

        } else {
            msg = "Ocorreu um erro ao tentar inserir o Carro.";
        }

        Toast.makeText(FormCarro.this, msg, Toast.LENGTH_LONG).show();
    }

    private void limparCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnoFab.setText("");
    }
}