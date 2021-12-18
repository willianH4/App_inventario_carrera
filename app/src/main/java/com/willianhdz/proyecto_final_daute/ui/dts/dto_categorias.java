package com.willianhdz.proyecto_final_daute.ui.dts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dto_categorias {

    @SerializedName("id_categoria")
    @Expose
    int id_categoria;

    @SerializedName("nom_categoria")
    @Expose
    String nom_categoria;

    @SerializedName("estado_categoria")
    @Expose
    int estado_categoria;

    @SerializedName("estado")
    @Expose
    int estado;

    public dto_categorias(int id_categoria, String nom_categoria, int estado_categoria) {
        this.id_categoria = id_categoria;
        this.nom_categoria = nom_categoria;
        this.estado_categoria = estado_categoria;
    }

    public dto_categorias() {
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public int getEstado_categoria() {
        return estado_categoria;
    }

    public void setEstado_categoria(int estado_categoria) {
        this.estado_categoria = estado_categoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "dto_categorias{" +
                "id_categoria=" + id_categoria +
                ", nom_categoria='" + nom_categoria + '\'' +
                ", estado_categoria=" + estado_categoria +
                ", estado=" + estado +
                '}';
    }
}
