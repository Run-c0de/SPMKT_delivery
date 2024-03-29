package com.example.supermercado_el_economico;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosAdapter;
import com.example.supermercado_el_economico.models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PantallaProductos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_productos);

        Button btnatras = findViewById(R.id.btnatras);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int categoryId = getIntent().getIntExtra("categoryId", -1);
        if (categoryId != -1) {
            String apiUrl = "https://delivery-service.azurewebsites.net/api/Productos?categoriaId=" + categoryId;
            new ObtenerDatosJsonTask().execute(apiUrl);
        }

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private class ObtenerDatosJsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return obtenerDatosDesdeUrl(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    List<Producto> listaProductos = parsearJson(result);
                    if (listaProductos != null && !listaProductos.isEmpty()) {
                        productosAdapter = new ProductosAdapter(listaProductos);
                        recyclerView.setAdapter(productosAdapter);
                    } else {
                        Toast.makeText(PantallaProductos.this, "La lista de productos está vacía", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(PantallaProductos.this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(PantallaProductos.this, "Error al obtener datos JSON", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String obtenerDatosDesdeUrl(String urlString) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
    }

    private List<Producto> parsearJson(String json) throws JSONException {
        List<Producto> listaProductos = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject productoObject = jsonArray.getJSONObject(i);
            int productoId = productoObject.getInt("productoId");
            String nombre = productoObject.getString("nombre");
            double precio = productoObject.getDouble("precio");
            double isv = productoObject.getDouble("isv");
            int categoriaId = productoObject.getInt("categoriaId");
            int medidaId = productoObject.getInt("medidaId");
            String foto = productoObject.getString("foto");
            boolean activo = productoObject.getBoolean("activo");

            Producto producto = new Producto(productoId, nombre, precio, isv, categoriaId, medidaId, foto, activo);
            listaProductos.add(producto);
        }

        return listaProductos;
    }
}
