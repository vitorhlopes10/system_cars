package com.example.system_cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnPesquisar;
    private EditText txtMarca;
    private EditText txtModelo;
    private EditText txtAnoFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnPesquisar = findViewById(R.id.btnPesquisar);

        txtMarca = findViewById(R.id.searchEditTextMarca);
        txtModelo = findViewById(R.id.searchEditTextModelo);
        txtAnoFab = findViewById(R.id.searchEditTextAnoFab);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarTelaCadastrar();
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarTelaPesquisar();
            }
        });
    }

    private void mudarTelaCadastrar() {
        Intent it = new Intent(this, FormCarro.class);
        startActivity(it);
    }

    private void mudarTelaPesquisar() {
        Intent it = new Intent(this, ListViewCarros.class);
        it.putExtra("marca", txtMarca.getText().toString());
        it.putExtra("modelo", txtModelo.getText().toString());
        it.putExtra("anoFab", txtAnoFab.getText().toString());
        startActivity(it);
    }

    private void limparCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnoFab.setText("");
    }
}