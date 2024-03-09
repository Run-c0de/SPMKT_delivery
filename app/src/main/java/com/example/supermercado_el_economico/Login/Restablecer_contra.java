package com.example.supermercado_el_economico.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supermercado_el_economico.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Restablecer_contra extends AppCompatActivity {

    // Variables para los componentes de Material Design
    private TextInputEditText txtCorreoRe;

    private MaterialButton btnenviartocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_contra);
        txtCorreoRe =(TextInputEditText) findViewById(R.id.txtCorreoRecu);
        btnenviartocket =(MaterialButton) findViewById(R.id.btnenviarT);

        btnenviartocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restablecer_contra.this, Pantalla_verificacion.class);
               startActivity(intent);
            }
        });


    }
}