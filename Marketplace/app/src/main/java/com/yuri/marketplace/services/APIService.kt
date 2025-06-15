package com.yuri.marketplace.services

import com.yuri.marketplace.model.UsuarioModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @FormUrlEncoded
    @POST("insertUser.php")
    suspend fun inserirUsuario(
        @Field("nome") nome: String,
        @Field("email") email: String,
        @Field("senha") senha: String
    ): RespostaInsertUsuario

}