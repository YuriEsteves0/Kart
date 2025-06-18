package com.yuri.marketplace.services

import com.yuri.marketplace.services.UsuarioServices.RespostaInsertUsuario
import com.yuri.marketplace.services.UsuarioServices.RespostaSelectUsuario
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @FormUrlEncoded
    @POST("insertUser.php")
    suspend fun inserirUsuario(
        @Field("nome") nome: String,
        @Field("email") email: String,
        @Field("senha") senha: String
    ): RespostaInsertUsuario

    @FormUrlEncoded
    @POST("selectUserById.php")
    suspend fun selectUserById(
        @Field("id") id: Int?,
    ): RespostaSelectUsuario

    @FormUrlEncoded
    @POST("selectUserByEmailAndPassword.php")
    suspend fun selectUserByEmailAndPassword(
        @Field("email") email: String,
        @Field("senha") senha: String
    ): RespostaSelectUsuario

}