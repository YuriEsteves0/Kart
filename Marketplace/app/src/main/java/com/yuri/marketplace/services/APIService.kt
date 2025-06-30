package com.yuri.marketplace.services

import com.yuri.marketplace.services.ProdutoServices.RespostaInsertProduto
import com.yuri.marketplace.services.UsuarioServices.RespostaInsertUsuario
import com.yuri.marketplace.services.UsuarioServices.RespostaSelectUsuario
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @Multipart
    @POST("insertProduto.php")
    suspend fun inserirProdutoComImagem(
        @Part("nome") nome: RequestBody,
        @Part("preco") preco: RequestBody,
        @Part("usuarioID") usuarioID: RequestBody,
        @Part foto: MultipartBody.Part
    ): RespostaInsertProduto
}