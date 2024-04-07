package com.example.supermercado_el_economico;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.supermercado_el_economico.Adapters.DetallePedidoAdapter;
import com.example.supermercado_el_economico.models.Producto;
import com.example.supermercado_el_economico.Login.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetallePedidoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Producto> productosSeleccionados;
    private Spinner combodireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        combodireccion = findViewById(R.id.combodireccion);

        // Recuperar la lista de productos seleccionados del intent
        productosSeleccionados = getIntent().getParcelableArrayListExtra("productosSeleccionados");

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.recycler_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DetallePedidoAdapter adapter = new DetallePedidoAdapter(productosSeleccionados);
        recyclerView.setAdapter(adapter);

        double subtotalPagar = calcularSubTotalPagar((ArrayList<Producto>) productosSeleccionados);
        TextView textViewSubTotalPagar = findViewById(R.id.total_subtotal);
        textViewSubTotalPagar.setText(String.valueOf(subtotalPagar));

        // Calcular ISV y mostrarlo
        double isv = calcularISV((ArrayList<Producto>) productosSeleccionados);
        TextView textViewIsv = findViewById(R.id.total_isv);
        textViewIsv.setText(String.valueOf(isv));

        // Calcular total a pagar (subtotal + ISV) y mostrarlo
        double totalPagar = subtotalPagar + isv;
        TextView textViewTotalPagar = findViewById(R.id.total);
        textViewTotalPagar.setText(String.valueOf(totalPagar));

        // Obtener y mostrar las direcciones del usuario en el Spinner
        obtenerYMostrarDirecciones();
    }

    // Método para obtener y mostrar las direcciones del usuario en el Spinner
    private void obtenerYMostrarDirecciones() {
        SessionManager session = new SessionManager(getApplicationContext());
        String userId = session.getUserId();

        OkHttpClient client = new OkHttpClient();
        String url = "https://delivery-service.azurewebsites.net/api/Direccion?usuarioId=" + userId;
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<String> direccionList = parseDireccionData(responseData);
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(DetallePedidoActivity.this, android.R.layout.simple_spinner_item, direccionList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            combodireccion.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }

    // Método para analizar la respuesta JSON y obtener la lista de direcciones
    private List<String> parseDireccionData(String json) {
        List<String> direccionList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject addressObject = dataArray.getJSONObject(i);
                String nombre = addressObject.getString("nombre");
                String referencia = addressObject.getString("referencia");
                direccionList.add(nombre + " - " + referencia);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return direccionList;
    }

    // Método para calcular el total a pagar
    private double calcularSubTotalPagar(ArrayList<Producto> productos) {
        double subtotal = 0;
        for (Producto producto : productos) {
            subtotal += (producto.getPrecio() * producto.getCantidad()); // Multiplicar por la cantidad
        }
        return subtotal;
    }

    private double calcularISV(ArrayList<Producto> productos) {
        double subtotal = calcularSubTotalPagar(productos);
        double isv = subtotal * 0.15; // Calcula el 15% del subtotal como ISV
        return isv;
    }
}
