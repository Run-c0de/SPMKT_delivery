package com.example.supermercado_el_economico.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado_el_economico.Login.Pantalla_Nueva_Password;
import com.example.supermercado_el_economico.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.example.supermercado_el_economico.ApiRest.AuthenticationApiMethods;

import org.json.JSONException;
import org.json.JSONObject;

public class Pantalla_verificacion extends AppCompatActivity {
    // Variables para los componentes de Material Design
    private TextInputEditText txtcodigo;

    private MaterialButton btnreenviar, btnverificarcodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_verificacion);
        txtcodigo = (TextInputEditText) findViewById(R.id.txtcodigo);
        btnreenviar = (MaterialButton) findViewById(R.id.btnlogin);
        btnverificarcodigo = (MaterialButton) findViewById(R.id.btnverificarcodigo);

        btnverificarcodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtcodigo.getText().toString().trim();
                verificarCodigo(codigo);
            }
        });

        btnreenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // reenviarCodigo();
            }
        });
    }

    private void verificarCodigo(String codigo) {

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("codVerificacion", codigo);

            // Crea una solicitud JSON utilizando  para enviar el código de verificación a la API
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AuthenticationApiMethods.EndPointVerificarCodigo,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        boolean codigoValido = response.getBoolean("codVerificacion");

                        if (codigoValido) {
                            Toast.makeText(getApplicationContext(), "Código verificado correctamente", Toast.LENGTH_SHORT).show();
                            // Realiza alguna acción adicional después de verificar el código, como iniciar otra actividad
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            showAlert("Error", "El código de verificación no es válido");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Maneja los errores de la solicitud, como errores de red o errores en el servidor
                    if (error.networkResponse != null) {
                        int statusCode = error.networkResponse.statusCode;
                        showAlert("Error", "Error en la solicitud: " + statusCode); // Muestra el código de estado del error
                    } else {
                        showAlert("Error", "Error en la solicitud"); // Muestra un mensaje de error genérico
                    }
                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            showAlert("Error", "Error al procesar la solicitud");
        }
    }
    //reenvio de codigo logica//
//    private void reenviarCodigo(int userId, String username, String rol) {
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        JSONObject jsonObject = new JSONObject(); // Crea un objeto JSON para almacenar cualquier dato necesario para reenviar el código
//
//        try {
//            jsonObject.put("userId", userId); // Agrega el ID de usuario
//            jsonObject.put("username", username); // Agrega el nombre de usuario
//            jsonObject.put("rol", rol); // Agrega el rol del usuario
//
//            // Crea una solicitud JSON utilizando JsonObjectRequest para reenviar el código de verificación a la API
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AuthenticationApiMethods.EndPointReenviarCodigo,
//                    jsonObject, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//
//                    Toast.makeText(getApplicationContext(), "Código de verificación reenviado ,Revise su correo Electronico", Toast.LENGTH_SHORT).show();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // Maneja los errores de la solicitud, como errores de red o errores en el servidor
//                    if (error.networkResponse != null) {
//                        int statusCode = error.networkResponse.statusCode;
//                        showAlert("Error", "Error en la solicitud: " + statusCode);
//                    } else {
//                        showAlert("Error", "Error en la solicitud");
//                    }
//                }
//            });
//
//            queue.add(jsonObjectRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            showAlert("Error", "Error al procesar la solicitud");
//        }
//    }

        //  cuadro de diálogo de alerta con un título y un mensaje
        private void showAlert(String title, String message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Aceptar", null)
                    .create()
                    .show();
        }
    }