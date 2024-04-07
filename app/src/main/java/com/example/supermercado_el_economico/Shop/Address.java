package com.example.supermercado_el_economico.Shop;

public class Address {
    private int usuarioId;
    private String nombre;
    private String referencia;
    private double latitud;
    private double longitud;

    // Constructor
    public Address (int usuarioId, String nombre, String referencia, double latitud, double longitud) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.referencia = referencia;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
