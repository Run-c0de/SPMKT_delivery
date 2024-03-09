package com.example.supermercado_el_economico.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supermercado_el_economico.Login.MainActivity;
import com.example.supermercado_el_economico.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Pantalla_Nueva_Password extends AppCompatActivity {
    // Variables para los componentes de Material Design
    private TextInputEditText txtpassNew;
    private TextInputEditText txtpassVeriNew;
    private MaterialButton btncanbiarcont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_nueva_password);

        txtpassNew =(TextInputEditText) findViewById(R.id.txtpassNew);
        txtpassVeriNew =(TextInputEditText) findViewById(R.id.txtpassVeriNew);
        btncanbiarcont =(MaterialButton) findViewById(R.id.btnCambiarContra);

        btncanbiarcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}