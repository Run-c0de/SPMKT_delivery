package com.example.supermercado_el_economico.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.supermercado_el_economico.AddressLocation;
import com.example.supermercado_el_economico.Domain.Pedidos.Pedido;
import com.example.supermercado_el_economico.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


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



    public static void replaceFragmentWithBack(Fragment fragment, FragmentManager context) {
        FragmentManager fragmentManager = context;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.view_pager, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
        holder.completeOrder.setOnClickListener(v -> {
                replaceFragmentWithBack( new AddressLocation(_fragmentManager), _fragmentManager);
        });


    }

    public  class PedidosViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imageViewProduct;
        MaterialTextView textOrderNumber;
        MaterialTextView clienteTextView;
        MaterialTextView telefonoTextView;
        MaterialTextView direccionTextView;
        MaterialButton  completeOrder;

        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);

            textOrderNumber = itemView.findViewById(R.id.orderNumber);
            clienteTextView = itemView.findViewById(R.id.tvCliente1);
            telefonoTextView = itemView.findViewById(R.id.tvTelefono1);
            direccionTextView = itemView.findViewById(R.id.tvDireccion1);

            completeOrder = itemView.findViewById(R.id.completeOrder);

        }


    }
}
