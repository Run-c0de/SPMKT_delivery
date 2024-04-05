package com.example.supermercado_el_economico;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosSeleccionadosAdapter;
import com.example.supermercado_el_economico.models.Producto;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Cargar la lista de productos seleccionados desde SharedPreferences
        List<Producto> productosSeleccionados = SharedPreferencesHelper.loadProductos(this);

// Verificar si la lista está vacía
        if (productosSeleccionados.isEmpty()) {
            // La lista está vacía, mostrar un mensaje
            Toast.makeText(this, "El carrito esta vacío", Toast.LENGTH_SHORT).show();
        } else {
            // La lista no está vacía, configurar la vista para mostrar los productos seleccionados
            RecyclerView recyclerView = findViewById(R.id.recyclerViewProductosSeleccionados);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ProductosSeleccionadosAdapter adapter = new ProductosSeleccionadosAdapter(productosSeleccionados);
            recyclerView.setAdapter(adapter);
        }

    }

}
