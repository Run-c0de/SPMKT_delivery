package com.example.supermercado_el_economico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton btnLacteos = findViewById(R.id.btnLacteos);
        ImageButton btnPanaderia = findViewById(R.id.btnPanaderia);
        ImageButton btnBebidas = findViewById(R.id.btnbebidas);
        ImageButton btnCarnes = findViewById(R.id.btncarnes);
        ImageButton btnfrutas = findViewById(R.id.btnfrutas);
        ImageButton btnMasVendido = findViewById(R.id.btnMasVendido);


        btnLacteos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Lacteos
                Intent intent = new Intent(Home.this, Pantalla_Lacteos.class);
                startActivity(intent);
            }
        });

        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Bebidas
                Intent intent = new Intent(Home.this, Pantalla_Bebidas.class);
                startActivity(intent);
            }
        });

        btnCarnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Carnes
                Intent intent = new Intent(Home.this, Pantalla_Carnes_res_Aves.class);
                startActivity(intent);
            }
        });

        btnfrutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Carnes
                Intent intent = new Intent(Home.this, Pantalla_Frutas_Verduras.class);
                startActivity(intent);
            }
        });

        btnMasVendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Carnes
                Intent intent = new Intent(Home.this, MasVendidos.class);
                startActivity(intent);
            }
        });

        btnPanaderia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Lacteos
                Intent intent = new Intent(Home.this, Pantalla_Panaderia.class);
                startActivity(intent);
            }
        });
    }
}
