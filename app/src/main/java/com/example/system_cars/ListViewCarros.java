package com.example.system_cars;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.system_cars.Adapter.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListViewCarros extends AppCompatActivity {

    private Button btnVoltar;
    private ListView listViewCarros;
    private Adapter adp;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_carros);

        btnVoltar = findViewById(R.id.btnVoltar);
        listViewCarros = findViewById(R.id.listViewCarros);

        Intent it = getIntent();

        if (it != null) {
            String marca = it.getStringExtra("marca");
            String modelo = it.getStringExtra("modelo");
            String anoFabStr = it.getStringExtra("anoFab");

            int anoFab = anoFabStr.isEmpty() ? 0 : Integer.parseInt(anoFabStr);

            List<ContentValues> lista = new ArrayList<>();
            lista = new DBHelper(this).pesquisarCarro();

            if (!(marca.isEmpty())) {
                lista = lista
                        .stream()
                        .filter(x -> x.getAsString("marca").equals(marca))
                        .collect(Collectors.toList());
            }

            if (!(modelo.isEmpty())) {
                lista = lista
                        .stream()
                        .filter(x -> x.getAsString("modelo").equals(modelo))
                        .collect(Collectors.toList());
            }

            if (anoFab != 0) {
                lista = lista
                        .stream()
                        .filter(x -> x.getAsInteger("anoFab") == anoFab)
                        .collect(Collectors.toList());
            }

            if (lista != null) {
                if (lista.size() > 0) {
                    adp = new Adapter(this, lista);
                    listViewCarros.setAdapter(adp);
                    adp.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListViewCarros.this, "Não foi encontrado nenhum carro!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}