package com.willianhdz.proyecto_final_daute.ui.user.remote;

import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductService {

    @GET("buscar_productos.php")
    Call<List<dto_productos>> getProducts();

    //TODO: Funcional
    @POST("guardar_producto.php")
    @FormUrlEncoded
    Call<dto_productos> addProduct(@Field("nom_producto") String nom_producto, @Field("des_producto") String des_producto,
                               @Field("stock") double stock, @Field("precio") double precio, @Field("unidad_de_medida") String unidad_de_medida,
                               @Field("estado_producto") int estado_producto, @Field("categoria") int categoria);


    //TODO: Funcional
    @POST("actualizar_producto.php")
    @FormUrlEncoded
    Call<dto_productos> updateProduct(@Field("id_producto") int id_producto, @Field("nom_producto") String nom_producto, @Field("des_producto") String des_producto,
                                      @Field("stock") double stock, @Field("precio") double precio, @Field("unidad_de_medida") String unidad_de_medida,
                                      @Field("estado_producto") int estado_producto, @Field("categoria") int categoria
                                      );

    //TODO: Funcional
    @POST("eliminar_producto.php")
    @FormUrlEncoded
    Call<dto_productos> deleteProduct(@Field("id_producto") int id_producto);

    //TODO: Funcional
    @GET("buscar_producto_id.php")
    @FormUrlEncoded
    Call<dto_productos> buscarProductForId(@Field("id_producto") int id_producto);

}
