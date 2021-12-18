package com.willianhdz.proyecto_final_daute.ui.user.services;

import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoryService {

    @GET("buscar_categorias.php")
    Call<List<dto_categorias>> getCategories();

    //TODO: Funcional
    @POST("guardar_categoria.php")
    @FormUrlEncoded
    Call<dto_categorias> addCategory(@Field("nom_categoria") String nom_categoria, @Field("estado_categoria") int estado_categoria);

    //TODO: Funcional
    @POST("actualizar_categoria.php")
    @FormUrlEncoded
    Call<dto_categorias> updateCategory(@Field("id_categoria") int id_categoria, @Field("nom_categoria") String nom_categoria, @Field("estado_categoria") int estado_categoria);

    //TODO: Funcional
    @POST("eliminar_categoria.php")
    @FormUrlEncoded
    Call<dto_categorias> deleteCategory(@Field("id_categoria") int id_categoria);

    //TODO: Funcional
    @GET("buscar_categoria_id.php")
    @FormUrlEncoded
    Call<dto_categorias> buscarCategoryForId(@Field("id_categoria") int id_categoria);

}
