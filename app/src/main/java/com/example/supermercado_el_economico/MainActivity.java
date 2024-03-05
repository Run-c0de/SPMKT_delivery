package com.example.supermercado_el_economico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    // Variables para los componentes de Material Design
    private TextInputEditText txtcorreoEn;
    private TextInputEditText txtpasswordEntrada;
    private MaterialButton btnentrar, btnCrear, btnestablecer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcorreoEn = (TextInputEditText) findViewById(R.id.txtcorreoEn);
        txtpasswordEntrada = (TextInputEditText) findViewById(R.id.txtpasswordEntrada);
        btnentrar = (MaterialButton) findViewById(R.id.btnlogin);
        btnCrear = (MaterialButton) findViewById(R.id.btnCrear);
        btnestablecer = (MaterialButton) findViewById(R.id.btnrestablecer);

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtcorreoEn.getText().toString().trim();
                String contraseña = txtpasswordEntrada.getText().toString().trim();

                // Realizar la solicitud de inicio de sesión
                try {
                    login(correo, contraseña);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnestablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Restablecer_contra.class);
                startActivity(intent);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registro_user.class);
                startActivity(intent);
            }
        });
    }

}


