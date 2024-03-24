package com.example.supermercado_el_economico.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado_el_economico.Delivery.HomeRepartidor;
import com.example.supermercado_el_economico.Home;
import com.example.supermercado_el_economico.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
                String username = txtCorreoRe.getText().toString().trim();
                if (validar()) {
                    enviarSolicitudRestablecer(username);
                }
            }
        });
    }

    private void enviarSolicitudRestablecer(String username) {
        ProgressDialog progressDialog = new ProgressDialog(Restablecer_contra.this);
        progressDialog.setMessage("Enviando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {

                RequestQueue requestQueue = Volley.newRequestQueue(Restablecer_contra.this);

                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("usuario", username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST,
                        "https://delivery-service.azurewebsites.net/api/Autenticacion/ReestablcerPassword?usuario="+username, requestBody,

                        new com.android.volley.Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progressDialog.dismiss(); // Ocultar el diálogo de progreso
                                try {
                                    JSONObject dataObject = response.getJSONObject("data");
                                    String user = dataObject.getString("userName");
                                    String message = dataObject.getString("message");
                                    String claveTemp = dataObject.getString("claveTemporal");

                                    if(claveTemp != ""){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Restablecer_contra.this);
                                        builder.setMessage(message)
                                                .setTitle("Clave temporal")
                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                                                    public void onClick(DialogInterface dialog, int id){
                                                        dialog.dismiss();
                                                    }

                                                });
                                    }
                                    //if (status == 200) { // Inicio de sesión exitoso
//                                        String welcomeMessage = "¡Bienvenido, " + username + "!";
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                                        builder.setMessage(welcomeMessage)
//                                                .setTitle("Inicio de sesión exitoso")
//                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        // Cerrar el diálogo o realizar alguna acción adicional si es necesario
//                                                        dialog.dismiss();
//                                                        // Iniciar la actividad correspondiente después de mostrar el mensaje de bienvenida
//                                                        if (codVerificacion.equals("")) {
//                                                            if (rol.equals("Cliente")) {
//                                                                Intent intent = new Intent(getApplicationContext(), Home.class);
//                                                                startActivity(intent);
//                                                            } else if (rol.equals("Repartidor")) {
//                                                                Intent intent = new Intent(getApplicationContext(), HomeRepartidor.class);
//                                                                startActivity(intent);
//                                                            }
//                                                        } else {
//                                                            // Guardar el código de verificación y el ID del usuario en SharedPreferences
//                                                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                                                            editor.putString("codVerificacion", codVerificacion);
//                                                            editor.putInt("userId", userId);
//                                                            editor.apply();
//
//                                                            // Ir a la pantalla de verificación
//                                                            Intent intent = new Intent(getApplicationContext(), Pantalla_verificacion.class);
//                                                            startActivity(intent);
//
//                                                        }
//                                                    }
//                                                });
//                                        AlertDialog dialog = builder.create();
//                                        dialog.show();
//                                    } else {
//                                        // Otro estado no manejado, muestra un mensaje genérico
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                                        builder.setMessage("Usuario o contraseña incorrectos.")
//                                                .setTitle("Error de inicio de sesión")
//                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        // Cerrar el diálogo o realizar alguna acción adicional si es necesario
//                                                        dialog.dismiss();
//                                                    }
//                                                });
//                                        AlertDialog dialog = builder.create();
//                                        dialog.show();
//                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss(); // Ocultar el diálogo de progreso
                        // Manejar el fallo de la solicitud de inicio de sesión
                        AlertDialog.Builder builder = new AlertDialog.Builder(Restablecer_contra.this);
                        builder.setMessage("Usuario o contraseña incorrectos.")
                                .setTitle("Error de inicio de sesión")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Cerrar el diálogo o realizar alguna acción adicional si es necesario
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                requestQueue.add(jsonObjectRequest);
            }
        }).start();
//        String correo = txtCorreoRe.getText().toString().trim();
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("usuario", correo);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String url = "https://tu_servidor.com/api/ReestablecerPassword";
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONObject data = response.getJSONObject("data");
//                            String userName = data.getString("userName");
//                            String claveTemporal = data.getString("claveTemporal");
//                            String message = data.getString("message");
//
//                            // Maneja la respuesta del servidor y redirige a la pantalla de verificación
//                            Intent intent = new Intent(Restablecer_contra.this, Pantalla_verificacion.class);
//                            intent.putExtra("userName", userName);
//                            intent.putExtra("claveTemporal", claveTemporal);
//                            intent.putExtra("message", message);
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            // Manejar el error de análisis JSON
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Manejar errores de la solicitud
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);
    }

    private boolean validar() {
        String correo = txtCorreoRe.getText().toString().trim();
        if (correo.isEmpty()) {
            txtCorreoRe.setError("Por favor ingrese su correo electrónico");
            txtCorreoRe.requestFocus();
            return false;
        }
        return true;
    }
}