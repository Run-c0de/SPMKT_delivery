package com.example.supermercado_el_economico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Variables para los componentes de Material Design
    private TextInputEditText txtcorreoEn;
    private TextInputEditText txtpasswordEntrada;
    private MaterialButton btnentrar,btnCrear,btnestablecer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcorreoEn =(TextInputEditText) findViewById(R.id.txtcorreoEn);
        txtpasswordEntrada =(TextInputEditText) findViewById(R.id.txtpasswordEntrada);
        btnentrar =(MaterialButton) findViewById(R.id.btnlogin);
        btnCrear =(MaterialButton) findViewById(R.id.btnCrear);
        btnestablecer =(MaterialButton) findViewById(R.id.btnrestablecer);
        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtcorreoEn.getText().toString().trim();
                String password = txtpasswordEntrada.getText().toString().trim();

                // Realizar la solicitud de inicio de sesión
             //   login(correo, password);
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

//    private void login(final String correo, final String password) {
//        String url = "http://jaguilar0610-001-site1.anytempurl.com/api/Autenticacion/Login";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Procesar la respuesta del servidor
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//                            String message = jsonObject.getString("message");
//
//                            if (success) {
//                                // Inicio de sesión exitoso, realizar acciones necesarias (por ejemplo, navegar a otra actividad)
//                                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MainActivity.this, registro_user.class);
//                                startActivity(intent);
//                            } else {
//                                // Inicio de sesión fallido, mostrar un mensaje de error
//                                Toast.makeText(MainActivity.this, "Inicio de sesión Incorrecto", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Error en la solicitud, mostrar un mensaje de error
//                        error.printStackTrace(); // Agregar esta línea para imprimir el error en el registro
//                        Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("correo", correo);
//                params.put("password", password);
//                return params;
//            }
//        };
//
//        // Agregar la solicitud a la cola de solicitudes de Volley
//        Volley.newRequestQueue(this).add(stringRequest);
//   }

    }