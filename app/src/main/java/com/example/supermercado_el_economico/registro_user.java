package com.example.supermercado_el_economico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado_el_economico.Config.RestApiMethods;
import com.example.supermercado_el_economico.Config.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registro_user extends AppCompatActivity {

    static final int REQUEST_IMAGE = 1;
    static final int ACCESS_CAMERA = 101;


    private TextInputEditText nombre, apellido, telefono, direccion, correo, usuarios, pass;

    String FotoPath;
    ImageView imageView;

    private RequestQueue requestQueue;
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

                //validarDatos();
                SendData();
            }
        });


    }


    //VALIDA QUE QUEDEN CAMPOS VACIOS


    private void SendData() {
        requestQueue = Volley.newRequestQueue(this);
        User usuario = new User();

        usuario.setUsuarioId(0);
        usuario.setNombre(nombre.getText().toString());
        usuario.setApellido(apellido.getText().toString());
        usuario.setTelefono(Integer.parseInt(telefono.getText().toString()));
        usuario.setDireccion(direccion.getText().toString());
        usuario.setCorreo(correo.getText().toString());
        usuario.setUsuario(usuarios.getText().toString());
        usuario.setPass(pass.getText().toString());
        usuario.setFoto(ConvertImageBase64(FotoPath));

        usuario.setVerificado(false); // Por ejemplo, establecer como no verificado
        usuario.setActivo(true); // Por ejemplo, establecer como no verificado




        JSONObject jsonperson = new JSONObject();

        try {
            jsonperson.put("usuarioId", usuario.getUsuarioId());
            jsonperson.put("usuario", usuario.getUsuario());
            jsonperson.put("password", usuario.getPass());
            jsonperson.put("nombres", usuario.getNombre());
            jsonperson.put("apellidos", usuario.getApellido());
            jsonperson.put("telefono", usuario.getTelefono());
            jsonperson.put("direccion", usuario.getDireccion());
            jsonperson.put("correo", usuario.getCorreo());
            jsonperson.put("foto", usuario.getFoto());
            // Agregar el campo "verificado" al objeto JSON
            jsonperson.put("verificado", usuario.isVerificado());
            jsonperson.put("activo", usuario.isActivo());



        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                RestApiMethods.EndPointCreatePerson, jsonperson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mensaje = response.getString("messaje");
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
        // limpiarCampos();
    }

    private String ConvertImageBase64(String path)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imagearray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagearray, Base64.DEFAULT);

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