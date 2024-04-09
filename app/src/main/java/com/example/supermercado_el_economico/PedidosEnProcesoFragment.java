package com.example.supermercado_el_economico;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermercado_el_economico.Adapters.PedidosAdapter;
import com.example.supermercado_el_economico.ApiRest.RetrofitAPI;
import com.example.supermercado_el_economico.Domain.Pedidos.Pedido;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PedidosEnProcesoFragment extends Fragment   {


    Context _context;
    FragmentManager _fragmentManager;
    RecyclerView recyclerView;
    private PedidosAdapter pedidosAdapter;
    private ArrayList<Pedido> recyclerDataArrayList;
    private ProgressBar progressBar;
    View rootLayout;

    public PedidosEnProcesoFragment(Context context, FragmentManager f){
        _context = context;
        _fragmentManager = f;
    }

    private void getAllOrders() {
        String url = "https://delivery-service.azurewebsites.net";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<ArrayList<Pedido>> call = retrofitAPI.getPedidos();

        call.enqueue(new Callback<ArrayList<Pedido>>() {

            @Override
            public void onResponse(Call<ArrayList<Pedido>> call, Response<ArrayList<Pedido>> response) {

                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    recyclerDataArrayList = response.body();

                    pedidosAdapter = new PedidosAdapter( recyclerDataArrayList, _fragmentManager);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());

                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(pedidosAdapter);


                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Pedido>> call, @NonNull Throwable t) {
                Toast.makeText(_context, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {

            rootLayout = inflater.inflate(R.layout.fragment_pedidos_en_proceso, container, false);
            recyclerView = rootLayout.findViewById(R.id.pedidosView);
            progressBar = rootLayout.findViewById(R.id.idPBLoading);
            recyclerDataArrayList = new ArrayList<>();
            getAllOrders();

            return rootLayout;
        }


}
