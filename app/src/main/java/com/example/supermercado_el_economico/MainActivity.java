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

    // Método para realizar la solicitud de inicio de sesión
    private void login(String correo, String contraseña) throws IOException, JSONException {
        // URL de la API de inicio de sesión
        String url = "http://jaguilar0610-001-site1.anytempurl.com/api/Autenticacion/Login";

        // Crear el cuerpo de la solicitud JSON con el nombre de usuario y la contraseña
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\r\n  \"userName\": \"" + correo + "\",\r\n  \"password\": \"" + contraseña + "\"\r\n}";

        // Crear una instancia del cliente OkHttpClient
        OkHttpClient client = new OkHttpClient();

        // Crear la solicitud HTTP POST
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, requestBody))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic MTExNjM5NDE6NjAtZGF5ZnJlZXRyaWFs")
                .addHeader("UserName", "11163941")
                .addHeader("Password", "60-dayfreetrial")
                .build();


        // Ejecutar la solicitud y obtener la respuesta
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // La solicitud fue exitosa (código de estado HTTP en el rango 200-299)
                String responseData = response.body().string();
                JSONObject jsonObject = new JSONObject(responseData);
                boolean success = jsonObject.getBoolean("success");
                if (success) {
                    // Mostrar mensaje de bienvenida
                    showWelcomeDialog();
                    // Iniciar la siguiente actividad si el inicio de sesión fue exitoso
                    Intent intent = new Intent(getApplicationContext(), registro_user.class);
                    startActivity(intent);
                } else {
                    // Mostrar mensaje de error
                    showErrorDialog();
                    // Mostrar un mensaje de error si el inicio de sesión falló
                    String mensaje = jsonObject.getString("mensaje");
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
            } else {
                // La solicitud no fue exitosa (código de estado HTTP fuera del rango 200-299)
                showErrorDialog();
                Toast.makeText(MainActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para mostrar mensaje de bienvenida
    private void showWelcomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Bienvenido!");
        builder.setMessage("Has iniciado sesión exitosamente.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // Método para mostrar mensaje de error
    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Usuario o contraseña incorrectos.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}