package com.example.supermercado_el_economico;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Home extends AppCompatActivity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout = findViewById(R.id.layout);

        new FetchCategoriesTask().execute("https://delivery-service.azurewebsites.net/api/Categorias");
    }

    private class FetchCategoriesTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject category = data.getJSONObject(i);
                        boolean isActive = category.getBoolean("activo");
                        if (isActive) {
                            String imageUrl = category.getString("foto");
                            String description = category.getString("descripcion");
                            createImageButton(imageUrl, description, i);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createImageButton(String imageUrl, String description, int index) {
        // Corregir la URL de la imagen si tiene una extensión duplicada

        // Crear un contenedor lineal horizontal para cada ImageButton y su descripción
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        container.setLayoutParams(containerParams);
        container.setPadding(16, 16, 16, 16); // Ajustar el padding para separar los elementos

        // Crear el ImageButton
        ImageButton imageButton = new ImageButton(this);
        LinearLayout.LayoutParams imageButtonParams = new LinearLayout.LayoutParams(300, 280);
        imageButton.setLayoutParams(imageButtonParams);
        container.addView(imageButton);

        // Crear el TextView para la descripción
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textViewParams.gravity = Gravity.CENTER_VERTICAL; // Centrar el texto verticalmente
        textView.setLayoutParams(textViewParams);
        textView.setText(description);
        textView.setPadding(16, 0, 0, 0); // Ajustar el padding para separar el texto del botón
        container.addView(textView);

        // Agregar el contenedor al layout principal
        layout.addView(container);

        // Carga de imagen asíncrona utilizando Glide con manejo de errores
        Glide.with(this)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Error al cargar la imagen: " + e.getMessage());
                        return false; // Devuelve false para que Glide maneje el error
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageButton);

        // Asignar un listener de clic para todos los botones
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes realizar acciones específicas para cada botón si es necesario
                // Por ejemplo, puedes abrir diferentes actividades dependiendo del botón presionado
                Intent intent;
                switch (index) {
                    case 0:
                        intent = new Intent(Home.this, Pantalla_Carnes_res_Aves.class);
                        break;
                    case 1:
                        intent = new Intent(Home.this, Pantalla_Bebidas.class);
                        break;
                    case 2:
                        intent = new Intent(Home.this, Pantalla_Lacteos.class);
                        break;
                    case 3:
                        intent = new Intent(Home.this, Pantalla_Bebidas.class);
                        break;
                    // Agrega más casos según sea necesario para otros índices
                    default:
                        intent = new Intent(Home.this, Pantalla_Panaderia.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
