package com.example.supermercado_el_economico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosSeleccionadosAdapter;
import com.example.supermercado_el_economico.Login.SessionManager;
import com.example.supermercado_el_economico.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    private TextView textViewUserId;
    private TextView textViewUsername;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        textViewUserId = findViewById(R.id.textViewUserId);
        textViewUsername = findViewById(R.id.textViewUsername);
        session = new SessionManager(getApplicationContext());

        // Obtener y mostrar la información del usuario
        String userId = session.getUserId();
        String username = session.getUsername();

        textViewUserId.setText(userId);
        textViewUsername.setText(username);

        Button btnconfirmar = findViewById(R.id.btnconfirmar);
        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la lista de productos seleccionados desde SharedPreferences
                List<Producto> productosSeleccionados = SharedPreferencesHelper.loadProductos(CarritoActivity.this);
                List<Producto> DetallePedidoAdapter = SharedPreferencesHelper.loadProductos(CarritoActivity.this);
                // Verificar si la lista no está vacía
                if (!productosSeleccionados.isEmpty()) {
                    // Iniciar DetallePedidoActivity y pasar la lista de productos seleccionados
                    Intent intent = new Intent(CarritoActivity.this, DetallePedidoActivity.class);
                    intent.putParcelableArrayListExtra("productosSeleccionados", new ArrayList<>(productosSeleccionados));
                    startActivity(intent);
                } else {
                    Toast.makeText(CarritoActivity.this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Cargar la lista de productos seleccionados desde SharedPreferences
        List<Producto> productosSeleccionados = SharedPreferencesHelper.loadProductos(this);

        // Verificar si la lista está vacía
        if (productosSeleccionados.isEmpty()) {
            // La lista está vacía, mostrar un mensaje
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
        } else {
            // La lista no está vacía, configurar la vista para mostrar los productos seleccionados
            RecyclerView recyclerView = findViewById(R.id.recyclerViewProductosSeleccionados);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProductosSeleccionadosAdapter adapter = new ProductosSeleccionadosAdapter(productosSeleccionados);
            recyclerView.setAdapter(adapter);
        }
    }
}
