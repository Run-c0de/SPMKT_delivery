package com.example.supermercado_el_economico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeRepartidor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_repartidor);


        ImageButton btnRepartidor = findViewById(R.id.btnRepartidor);


        btnRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad Lacteos
                Intent intent = new Intent(HomeRepartidor.this, PerfilRepartidor.class);
                startActivity(intent);
            }
        });
    }
}