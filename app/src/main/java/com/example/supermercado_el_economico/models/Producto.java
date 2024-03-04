package com.example.supermercado_el_economico.models;

public class Producto {
    private String nombre;
    private String precioReal;
    private String precioDescuento;
    private int imageResourceId;

    public Producto(String nombre, String precioReal, String precioDescuento, int imageResourceId) {
        this.nombre = nombre;
        this.precioReal = precioReal;
        this.precioDescuento = precioDescuento;
        this.imageResourceId = imageResourceId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioReal() {
        return precioReal;
    }

    public void setPrecioReal(String precioReal) {
        this.precioReal = precioReal;
    }

    public String getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(String precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
