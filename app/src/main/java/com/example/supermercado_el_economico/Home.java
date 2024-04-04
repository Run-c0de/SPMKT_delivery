package com.example.supermercado_el_economico;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
    Button BtnCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout = findViewById(R.id.layout);
        Button BtnCarrito = findViewById(R.id.BtnCarrito);
        new FetchCategoriesTask().execute("https://delivery-service.azurewebsites.net/api/Categorias");

        BtnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, CarritoActivity.class);
                startActivity(intent);
            }
        });
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
                            int categoryId = category.getInt("categoriaId");
                            if (categoryId >= 2) { // Comienza desde el ID 2
                                String imageUrl = category.getString("foto");
                                String description = category.getString("descripcion");
                                createImageButton(imageUrl, description, categoryId);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createImageButton(String imageUrl, String description, int categoryId) {


        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        container.setLayoutParams(containerParams);
        container.setPadding(16, 16, 16, 16); // Ajustar el padding para separar los elementos

        ImageButton imageButton = new ImageButton(this);
        LinearLayout.LayoutParams imageButtonParams = new LinearLayout.LayoutParams(300, 280);
        imageButton.setLayoutParams(imageButtonParams);
        container.addView(imageButton);

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

        layout.addView(container);

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

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // productos por id
                int selectedCategoryId = categoryId;

                Intent intent = new Intent(Home.this, PantallaProductos.class);
                intent.putExtra("categoryId", selectedCategoryId);
                startActivity(intent);
            }
        });
    }
}