package com.example.supermercado_el_economico.Domain.Clientes;

import com.google.gson.annotations.SerializedName;

public class Cliente {
    // Propiedades
    @SerializedName("usuarioId")
    public int usuarioId;
    @SerializedName("usuario")
    public String usuario;
    @SerializedName("password")
    public String password;
    @SerializedName("nombres")
    public String nombres;
    @SerializedName("apellidos")
    public String apellidos;
    @SerializedName("telefono")
    public String telefono;
    @SerializedName("direccion")
    public String direccion;
    @SerializedName("correo")
    public String correo;
    @SerializedName("foto")
    public String foto;
    @SerializedName("verificado")
    public boolean verificado;
    @SerializedName("activo")
    public boolean activo;
    @SerializedName("imageBase64")
    public String imageBase64;

    // Constructor
    public Cliente() {}

    // Constructor con parámetros
    public Cliente(int usuarioId, String usuario, String password, String nombres, String apellidos, String telefono, String direccion, String correo, String foto, boolean verificado, boolean activo, String imageBase64) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.foto = foto;
        this.verificado = verificado;
        this.activo = activo;
        this.imageBase64 = imageBase64;
    }

    // Getters y setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
