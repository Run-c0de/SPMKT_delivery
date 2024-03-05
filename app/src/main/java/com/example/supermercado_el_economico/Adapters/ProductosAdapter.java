package com.example.supermercado_el_economico.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.R;
import com.example.supermercado_el_economico.models.Producto;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder> {

    private List<Producto> listaProductos;
    private Context context;

    public ProductosAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.imageViewProduct.setImageResource(producto.getImageResourceId());
        holder.textViewProductName.setText(producto.getNombre());
        holder.textViewPriceReal.setText(producto.getPrecioReal());
        holder.textViewPriceDiscount.setText(producto.getPrecioDescuento());
        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para agregar el producto al carrito
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
        TextView textViewPriceDiscount;
        Button buttonAddToCart;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewPriceReal = itemView.findViewById(R.id.textViewPriceReal);
            textViewPriceDiscount = itemView.findViewById(R.id.textViewPriceDiscount);
            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
        }
    }
}
