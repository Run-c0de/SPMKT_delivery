package com.example.supermercado_el_economico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado_el_economico.Config.RestApiMethods;
import com.example.supermercado_el_economico.Config.User;
import com.example.supermercado_el_economico.Login.MainActivity;
import com.example.supermercado_el_economico.Login.Pantalla_verificacion;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class registro_user extends AppCompatActivity {

    static final int REQUEST_IMAGE = 1;
    static final int ACCESS_CAMERA = 101;


    private TextInputEditText nombre, apellido, telefono, direccion, correo, usuarios, pass, confiPass;

    String FotoPath;
    ImageView imageView;

    //private RequestQueue requestQueue;
    //Button btnSiguiente, btnFoto;
    private MaterialButton btnSiguiente, btnFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        btnSiguiente = findViewById(R.id.btnSiguiente);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnFoto= findViewById(R.id.btnFoto);

        nombre = findViewById(R.id.txtnombre);
        apellido = findViewById(R.id.txtApellido);
        telefono = findViewById(R.id.txtTelefono);
        direccion = findViewById(R.id.txtDireccion);
        correo = findViewById(R.id.txtCorreo);
        usuarios = findViewById(R.id.txtUsuario);
        pass = findViewById(R.id.txtPass);
        confiPass = findViewById(R.id.txtConfirmarPass);


        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(registro_user.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    PermisosCamera();
                } else {
                    PermisosCamera();
                }
            }
        });



        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });


    }


    //VALIDA QUE QUEDEN CAMPOS VACIOS
    private void validarDatos() {
        btnSiguiente.setEnabled(false);
        if(!confiPass.getText().toString().equals(pass.getText().toString())){
            showAlert("Advertencia", "La contraseña no coincide");
        } else if (nombre.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir su nombre");
        } else if (apellido.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir sus apellidos");
        } else if (telefono.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir su telefono");
        } else if (direccion.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir su direccion");
        } else if (correo.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir su correo");
        } else if (usuarios.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir un usuario");
        } else if (pass.getText().toString().equals("")) {
            showAlert("Datos Inválido", "Debe de escribir su contraseña");
        } else {
            showAlert("Exito", "Datos correctos para guardar");
            SendData();
        }
        btnSiguiente.setEnabled(true);
    }


    private void SendData() {
        ProgressDialog progressDialog = new ProgressDialog(registro_user.this);
        progressDialog.setMessage("Creando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(registro_user.this);
                User usuario = new User();

                usuario.setUsuarioId(0);
                usuario.setNombre(nombre.getText().toString());
                usuario.setApellido(apellido.getText().toString());
                usuario.setTelefono(telefono.getText().toString());
                usuario.setDireccion(direccion.getText().toString());
                usuario.setCorreo(correo.getText().toString());
                usuario.setUsuario(usuarios.getText().toString());
                usuario.setPass(pass.getText().toString());
                usuario.setFoto(ConvertImageBase64(FotoPath));
                usuario.setVerificado(false);
                usuario.setActivo(true);

                JSONObject requestBody = new JSONObject();
                try{
                    requestBody.put("usuario", usuario.getUsuario());
                    requestBody.put("password", usuario.getPass());
                    requestBody.put("nombres", usuario.getNombre());
                    requestBody.put("apellidos", usuario.getApellido());
                    requestBody.put("telefono", usuario.getTelefono());
                    requestBody.put("direccion", usuario.getDireccion());
                    requestBody.put("correo", usuario.getCorreo());
                    requestBody.put("foto", "");
                    requestBody.put("imgBase64", usuario.getFoto());
                    requestBody.put("esRepartidor", false);

                    /*requestBody.put("usuarioId", 0);
                    requestBody.put("usuario", "zeus");
                    requestBody.put("password","123");
                    requestBody.put("nombres", "dios");
                    requestBody.put("apellidos", "cielo");
                    requestBody.put("telefono", "4848448");
                    requestBody.put("direccion", "sps");
                    requestBody.put("correo", "correo");
                    requestBody.put("foto", "");
                    requestBody.put("imgBase64", usuario.getFoto());
                    requestBody.put("esRepartidor", false);
                    requestBody.put("activo", usuario.isActivo());*/
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        RestApiMethods.EndPointCreatePerson, requestBody,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                btnSiguiente.setEnabled(true);
                                progressDialog.dismiss();
                                try{
                                    JSONObject dataObject = response.getJSONObject("data");
                                    int userId =  dataObject.getInt("usuarioId");

                                    if(userId > 0){
                                        showAlert("Exito", "Se ha creado con exito el usuario");
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        showAlert("Advertencia", "No se creo el usuario");
                                    }

                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                    showAlert("Error", e.getMessage().toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                btnSiguiente.setEnabled(true);
                                progressDialog.dismiss();
                                String errorResponse = new String(error.networkResponse.data);
                                try{
                                    JSONObject jsonObjectError = new JSONObject(errorResponse);
                                    JSONArray errorsArray = jsonObjectError.getJSONArray("errors");
                                    JSONObject errorObject = errorsArray.getJSONObject(0);
                                    String detailMessage = errorObject.getString("detail");
                                    showAlert("Error", detailMessage.toString());
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }

    private void limpiarCampos()
    {
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        direccion.setText("");
        correo.setText("");
        usuarios.setText("");
        pass.setText("");
        imageView.setImageDrawable(null);
    }

    private String ConvertImageBase64(String path)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imagearray = byteArrayOutputStream.toByteArray();
        String base64Fotografia = Base64.encodeToString(imagearray, Base64.DEFAULT);
        return "data:image/png;base64," + base64Fotografia;

    }

    private void PermisosCamera() {
        // Metodo para obtener los permisos requeridos de la aplicacion
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, ACCESS_CAMERA);
        } else {
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ACCESS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getApplicationContext(), "se necesita el permiso de la camara", Toast.LENGTH_LONG).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        FotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.toString();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.supermercado_el_economico.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK)
        {
            try {
                File foto = new File(FotoPath);
                if (foto.exists()) {
                    imageView.setImageURI(Uri.fromFile(foto));
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error al cargar la imagen: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}