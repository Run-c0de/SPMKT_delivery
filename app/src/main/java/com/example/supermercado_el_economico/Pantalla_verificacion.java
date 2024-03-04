package com.example.supermercado_el_economico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Pantalla_verificacion extends AppCompatActivity {
    // Variables para los componentes de Material Design
    private TextInputEditText txtcodigo;

    private MaterialButton btnreenviar,btnverificarcodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_verificacion);
        txtcodigo =(TextInputEditText) findViewById(R.id.txtcodigo);
        btnreenviar =(MaterialButton) findViewById(R.id.btnlogin);
        btnverificarcodigo =(MaterialButton) findViewById(R.id.btnverificarcodigo);

        btnverificarcodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pantalla_Nueva_Password.class);
                startActivity(intent);
            }
        });

    }
}