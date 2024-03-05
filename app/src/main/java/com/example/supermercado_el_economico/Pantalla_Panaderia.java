package com.example.supermercado_el_economico;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosAdapter;
import com.example.supermercado_el_economico.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pantalla_Panaderia extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_panaderia);

        // Crear una lista de productos de ejemplo
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Pan Monarca Integral De Harina De Trigo 440 Gr", "L 50.90", "L 0.00", R.drawable.panintegral));
        listaProductos.add(new Producto("Pan Dulce Magdalena Vainilla Breadco", "L 120.90", "L 0.00", R.drawable.pandulce));
        listaProductos.add(new Producto("Semita De Orillas Breadco 10 Un", "L 105.90", "L 0.00", R.drawable.semita));
        listaProductos.add(new Producto("Quequito Breadco Popular 12 Un", "L 78.90", "L 0.00", R.drawable.quequito));


        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        productosAdapter = new ProductosAdapter(listaProductos);
        recyclerView.setAdapter(productosAdapter);
    }
}