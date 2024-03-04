package com.example.supermercado_el_economico;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pantalla_Carnes_res_Aves extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carnes_res_aves);

        // Crear una lista de productos de ejemplo
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Pollo Entero Sin Menudo Congelado Pollo Rey x Unidad", "L 136.40", "L 122.76", R.drawable.polloe));
        listaProductos.add(new Producto("Carne de Cerdo para Asar Chipotle", "L 61.90", "L 0.00", R.drawable.carnedecerdo));
        listaProductos.add(new Producto("Pechuga De Pollo Rey Deshuesada Fresca Granel X Unidad", "L 75.90", "L 0.00", R.drawable.pechuga));


        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        productosAdapter = new ProductosAdapter(listaProductos);
        recyclerView.setAdapter(productosAdapter);
    }
}