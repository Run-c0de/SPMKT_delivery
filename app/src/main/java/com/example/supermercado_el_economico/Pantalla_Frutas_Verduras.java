package com.example.supermercado_el_economico;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosAdapter;
import com.example.supermercado_el_economico.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pantalla_Frutas_Verduras extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_frutas_verduras);

        // Crear una lista de productos de ejemplo
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Fresa La Carreta Bandeja 1Lb", "L 100.90", "L 0.00", R.drawable.fresas));
        listaProductos.add(new Producto("Banano Verde X Unidad", "L 1.90", "L 0.00", R.drawable.banano));
        listaProductos.add(new Producto("Yuca Blanca Parafinada X Lb", "L 14.90", "L 0.00", R.drawable.yuca));


        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        productosAdapter = new ProductosAdapter(listaProductos);
        recyclerView.setAdapter(productosAdapter);
    }
}