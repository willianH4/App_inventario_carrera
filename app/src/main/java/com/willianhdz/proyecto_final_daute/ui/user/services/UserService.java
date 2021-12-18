package com.willianhdz.proyecto_final_daute.ui.user.services;

import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("buscar_all_usuario1.php")
    Call<List<dto_usuarios>> getUsers();

    //TODO: Funcional
    @POST("guardar_usuario.php")
    @FormUrlEncoded
    Call<dto_usuarios> addUser(@Field("nombre") String nombre, @Field("apellido") String apellido,
                               @Field("correo") String correo, @Field("usuario") String usuario, @Field("clave") String clave,
                               @Field("tipo") int tipo, @Field("estado") int estado, @Field("pregunta") String pregunta,
                               @Field("respuesta") String respuesta);

    //TODO: Funcional
    @POST("validar_usuario.php")
    @FormUrlEncoded
    Call<dto_usuarios> validarUser(@Field("usuario") String usuario, @Field("password") String password);

    //TODO: Funcional
    @POST("actualizar_usuario.php")
    @FormUrlEncoded
    Call<dto_usuarios> updateUser(@Field("id") int id, @Field("nombre") String nombre, @Field("apellido") String apellido,
                                  @Field("correo") String correo, @Field("usuario") String usuario, @Field("clave") String clave,
                                  @Field("tipo") int tipo, @Field("estado") int estado, @Field("pregunta") String pregunta,
                                  @Field("respuesta") String respuesta);

    //TODO: Funcional
    @POST("eliminar_usuario.php")
    @FormUrlEncoded
    Call<dto_usuarios> deleteUser(@Field("id") int id);

    //TODO: Funcional
    @GET("buscar_usuario_id.php")
    @FormUrlEncoded
    Call<dto_usuarios> buscarUserForId(@Field("id") int id);
}
