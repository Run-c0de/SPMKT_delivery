package com.example.supermercado_el_economico;

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

public class Pantalla_Lacteos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_lacteos);

        Button btnatras = findViewById(R.id.btnatras);

        // Crear una lista de productos de ejemplo
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Queso Seco La Olanchana 1 Lb", "L 104.90", "L 0.00", R.drawable.queso));
        listaProductos.add(new Producto("Mantequilla Chayitos Prolaga X Lb", "L 64.90", "L 0.00", R.drawable.mantequilla));
        listaProductos.add(new Producto("Quesillo Jugo Prolaga X Lb", "L 67.90", "L 0.00", R.drawable.quesillo));


        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar y configurar el adaptador
        productosAdapter = new ProductosAdapter(listaProductos);
        recyclerView.setAdapter(productosAdapter);

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Lacteos.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
