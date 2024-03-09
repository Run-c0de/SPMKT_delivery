package com.example.supermercado_el_economico.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado_el_economico.ApiRest.AuthenticationApiMethods;
import com.example.supermercado_el_economico.Delivery.HomeRepartidor;
import com.example.supermercado_el_economico.Home;
import com.example.supermercado_el_economico.R;
import com.example.supermercado_el_economico.registro_user;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;




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
                String username = txtcorreoEn.getText().toString().trim();
                String password = txtpasswordEntrada.getText().toString().trim();
                loginUsuario(username, password);
               // loginUsuario("keyla.soriano", "12345");

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


    private void loginUsuario(String Usuario, String clave) {

        if (Usuario.isEmpty()) {
            showAlert("Error", "Por favor, ingresa un nombre de usuario");
            return;
        }

        if (clave.isEmpty()) {
            showAlert("Error", "Por favor, ingresa una contraseña");
            return;
        }


        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", Usuario);
            jsonObject.put("password", clave);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AuthenticationApiMethods.EndPointLogin,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject dataObject = response.getJSONObject("data");
                        int userId = dataObject.getInt("userId");
                        String message = dataObject.getString("message");
                        int status = dataObject.getInt("status");
                        String codVerificacion = dataObject.getString("codVerificacion");
                        String username = dataObject.getString("username");
                        String rol = dataObject.getString("rol");

                        if (codVerificacion.equals("")) {
                            if (rol.equals("Cliente")) {
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);

                            } else if (rol.equals("Repartidor"))  {
                                Intent intent = new Intent(getApplicationContext(), HomeRepartidor.class);
                                startActivity(intent);
                            }
                        } else {
                            //enviar a pantalla de validar token
                            Intent intent = new Intent(getApplicationContext(), Pantalla_verificacion.class);
                            startActivity(intent);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                       // Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NetworkError) {
                        showAlert("Error", "No se puede conectar al servidor. Verifique su conexión a internet.");
                    } else if (error instanceof ServerError) {
                        int status = error.networkResponse.statusCode;
                        if (status == 404) {
                            showAlert("Error", "Usuario Incorrecto ");
                        } else if (status == 401) {
                            showAlert("Error", "Contraseña incorrecta");
                        } else {
                            showAlert("Error", "Error del servidor: " + status);
                        }
                    }
                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            showAlert("Error", "Error al procesar la solicitud");
        }
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
}



