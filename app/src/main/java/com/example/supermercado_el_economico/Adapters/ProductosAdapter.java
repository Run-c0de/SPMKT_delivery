package com.example.supermercado_el_economico.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.CarritoActivity;
import com.example.supermercado_el_economico.R;
import com.example.supermercado_el_economico.models.Producto;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private List<Producto> productosSeleccionados; // Lista para almacenar productos seleccionados
    private Context context;

    public ProductosAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
        this.productosSeleccionados = new ArrayList<>(); // Inicializar la lista de productos seleccionados
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.productoxcategoria, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        Glide.with(context)
                .load(producto.getFoto())
                .into(holder.imageViewProduct);
        holder.textViewProductName.setText(producto.getNombre());
        holder.textViewPriceReal.setText(String.valueOf(producto.getPrecio()));
        holder.textViewIVS.setText(String.valueOf(producto.getIsv()));
        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto productoSeleccionado = listaProductos.get(holder.getAdapterPosition());
                productosSeleccionados.add(productoSeleccionado);
                Intent intent = new Intent(context, CarritoActivity.class);
                intent.putExtra("productos_seleccionados", new ArrayList<>(productosSeleccionados));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewPriceReal;
        TextView textViewIVS;
        Button buttonAddToCart;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewPriceReal = itemView.findViewById(R.id.textViewPriceReal);
            textViewIVS = itemView.findViewById(R.id.textViewIVS);
            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
        }
    }
}
