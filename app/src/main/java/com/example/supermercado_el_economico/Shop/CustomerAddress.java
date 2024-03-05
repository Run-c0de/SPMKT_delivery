package com.example.supermercado_el_economico.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supermercado_el_economico.R;
import com.example.supermercado_el_economico.Restablecer_contra;

public class CustomerAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);


        findViewById(R.id.RegistrarDireccionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MapView.class);
                startActivity(intent);
            }
        });
    }
}