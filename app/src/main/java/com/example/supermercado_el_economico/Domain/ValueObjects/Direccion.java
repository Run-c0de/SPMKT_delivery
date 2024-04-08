package com.example.supermercado_el_economico.Domain.ValueObjects;

import com.google.gson.annotations.SerializedName;

import Domain.Usuarios.Usuario;

public class Direccion {
    // Propiedades
    @SerializedName("direccionId")
    public int direccionId;
    @SerializedName("usuarioId")
    public int usuarioId;
    @SerializedName("nombre")
    public String nombre;
    @SerializedName("referencia")
    public String referencia;
    @SerializedName("longitud")
    public String longitud;
    @SerializedName("latitud")
    public String latitud;
    @SerializedName("usuarios")
    public Usuario usuarios;

    // Constructor
    public Direccion() {}

    // Constructor con parámetros
    public Direccion(int direccionId, int usuarioId, String nombre, String referencia, String longitud, String latitud, Usuario usuarios) {
        this.direccionId = direccionId;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.referencia = referencia;
        this.longitud = longitud;
        this.latitud = latitud;
        this.usuarios = usuarios;
    }

    // Getters y setters
    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

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

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }
}
