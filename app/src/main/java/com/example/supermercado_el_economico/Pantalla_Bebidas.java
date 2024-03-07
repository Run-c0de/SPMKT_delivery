package com.example.supermercado_el_economico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.ProductosAdapter;
import com.example.supermercado_el_economico.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pantalla_Bebidas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    Button btnatras;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bebidas);


        Button btnatras = findViewById(R.id.btnatras);

        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Bebida Energizante Raptor Zero Sin Azúcar Lata 473 Ml", "L 24.90", "L 19.92", R.drawable.producto1));
        listaProductos.add(new Producto("Bebida Con Jugo Fristy De Naranja 1.89 Lt", "L 31.90", "L 25.00", R.drawable.producto2));
        listaProductos.add(new Producto("Refresco Fresca Toronja 1.1 Lt", "L 27.90", "L 20.90", R.drawable.fresca));


        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        productosAdapter = new ProductosAdapter(listaProductos);
        recyclerView.setAdapter(productosAdapter);

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Bebidas.this, Home.class);
                startActivity(intent);
            }
        });
    }


}