package com.example.supermercado_el_economico.ApiRest;

import com.example.supermercado_el_economico.Domain.Pedidos.Pedido;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("api/Pedidos")
    Call<ArrayList<Pedido>> getPedidos();
}
