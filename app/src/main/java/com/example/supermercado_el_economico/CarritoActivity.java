package com.example.supermercado_el_economico;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.models.Producto;

import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Cargar la lista de productos seleccionados desde SharedPreferences
        List<Producto> productosSeleccionados = SharedPreferencesHelper.loadProductos(this);

        // Configurar la vista para mostrar los productos seleccionados, por ejemplo, utilizando un RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewProductosSeleccionados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductosSeleccionadosAdapter adapter = new ProductosSeleccionadosAdapter(productosSeleccionados);
        recyclerView.setAdapter(adapter);
    }

}
