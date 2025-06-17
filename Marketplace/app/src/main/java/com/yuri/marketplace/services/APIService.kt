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
    @POST("selectUser.php")
    suspend fun selectUser(
        @Field("email") email: String,
        @Field("senha") senha: String
    ): RespostaSelectUsuario

}