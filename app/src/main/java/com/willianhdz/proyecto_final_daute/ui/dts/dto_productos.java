package com.willianhdz.proyecto_final_daute.ui.dts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dto_productos {

    @SerializedName("id_producto")
    @Expose
    int id_producto;

    @SerializedName("nom_producto")
    @Expose
    String nom_producto;

    @SerializedName("des_producto")
    @Expose
    String des_producto;

    @SerializedName("stock")
    @Expose
    double stock;

    @SerializedName("precio")
    @Expose
    double precio;

    @SerializedName("unidad_de_medida")
    @Expose
    String unidad_de_medida;

    @SerializedName("estado_producto")
    @Expose
    int estado_producto;

    @SerializedName("categoria")
    @Expose
    int categoria;

    @SerializedName("fecha_entrada")
    @Expose
    String fecha_entrada;

    @SerializedName("estado")
    @Expose
    int estado;

    @SerializedName("nom_categoria")
    @Expose
    String nom_categoria;

    public dto_productos() {
    }

    public dto_productos(int id_producto, String nom_producto, String des_producto, double stock, double precio, String unidad_de_medida, int estado_producto, int categoria, String fecha_entrada) {
        this.id_producto = id_producto;
        this.nom_producto = nom_producto;
        this.des_producto = des_producto;
        this.stock = stock;
        this.precio = precio;
        this.unidad_de_medida = unidad_de_medida;
        this.estado_producto = estado_producto;
        this.categoria = categoria;
        this.fecha_entrada = fecha_entrada;
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public String getDes_producto() {
        return des_producto;
    }

    public void setDes_producto(String des_producto) {
        this.des_producto = des_producto;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUnidad_de_medida() {
        return unidad_de_medida;
    }

    public void setUnidad_de_medida(String unidad_de_medida) {
        this.unidad_de_medida = unidad_de_medida;
    }

    public int getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(int estado_producto) {
        this.estado_producto = estado_producto;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    @Override
    public String toString() {
        return "dto_productos{" +
                "id_producto=" + id_producto +
                ", nom_producto='" + nom_producto + '\'' +
                ", des_producto='" + des_producto + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", unidad_de_medida='" + unidad_de_medida + '\'' +
                ", estado_producto=" + estado_producto +
                ", categoria=" + categoria +
                ", fecha_entrada='" + fecha_entrada + '\'' +
                ", estado=" + estado +
                ", nom_categoria='" + nom_categoria + '\'' +
                '}';
    }
}