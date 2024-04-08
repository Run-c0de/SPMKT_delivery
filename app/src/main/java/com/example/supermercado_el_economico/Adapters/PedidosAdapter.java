package com.example.supermercado_el_economico.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationdemo.AddressLocation;
import com.example.bottomnavigationdemo.Helpers.FragmentReplacer;
import com.example.bottomnavigationdemo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import Domain.Pedidos.Pedido;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder> {

    private ArrayList<Pedido> pedidos;
    private Context context;
    private FragmentManager _fragmentManager;

    public PedidosAdapter(ArrayList<Pedido> recyclerDataArrayList, FragmentManager f) {
        this.pedidos = recyclerDataArrayList;
        _fragmentManager = f;
    }

    @NonNull
    @Override
    public PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.pedido_item, parent, false);
        return new PedidosViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosViewHolder holder, int position) {
       Pedido pedido = pedidos.get(position);

       String clenteText = "Cliente: " + pedido.clientes.nombres;
        String telefonoText = "Telefono: " + pedido.clientes.telefono;
        String direccionText = "Direccion: " + pedido.direccion.referencia;
        String pedidoText = "Pedido #" + pedido.pedidoId;


        holder.textOrderNumber.setText(pedidoText);
        holder.clienteTextView.setText(clenteText);
        holder.telefonoTextView.setText(telefonoText);
        holder.direccionTextView.setText(direccionText);

        // Set button click listeners (optional)
        holder.lookUpLocation.setOnClickListener(v -> {
                FragmentReplacer.replaceFragmentWithBack( new AddressLocation(_fragmentManager), _fragmentManager);
        });

        holder.completeOrder.setOnClickListener(v -> {
            // Handle "Completar" button click for the specific pedido
        });
    }

    public  class PedidosViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imageViewProduct;
        MaterialTextView textOrderNumber;
        MaterialTextView clienteTextView;
        MaterialTextView telefonoTextView;
        MaterialTextView direccionTextView;
        MaterialButton lookUpLocation, completeOrder;

        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);

            textOrderNumber = itemView.findViewById(R.id.orderNumber);
            clienteTextView = itemView.findViewById(R.id.tvCliente1);
            telefonoTextView = itemView.findViewById(R.id.tvTelefono1);
            direccionTextView = itemView.findViewById(R.id.tvDireccion1);
            lookUpLocation = itemView.findViewById(R.id.lookUpLocation);
            completeOrder = itemView.findViewById(R.id.completeOrder);

        }


    }
}
