package com.example.supermercado_el_economico.Domain.Pedidos;

import com.google.gson.annotations.SerializedName;

import Domain.Clientes.Cliente;
import Domain.Repartidores.Repartidor;
import Domain.ValueObjects.Direccion;


public class Pedido {
    // Propiedades
    @SerializedName("pedidoId")
    public int pedidoId;
    @SerializedName("numPedido")
    public String numPedido;
    @SerializedName("clientes")
    public Cliente clientes;
    @SerializedName("repartidor")
    public Repartidor repartidor;
    @SerializedName("direccion")
    public Direccion direccion;

    // Constructor
    public Pedido() {}

    // Constructor con parámetros
    public Pedido(int pedidoId, String numPedido, Cliente clientes, Repartidor repartidor, Direccion direccion) {
        this.pedidoId = pedidoId;
        this.numPedido = numPedido;
        this.clientes = clientes;
        this.repartidor = repartidor;
        this.direccion = direccion;
    }

    // Getters y setters
    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
