package com.example.supermercado_el_economico.ApiRest;

import java.util.ArrayList;

import Domain.Pedidos.Pedido;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("api/Pedidos")
    Call<ArrayList<Pedido>> getAllCourses();
}
