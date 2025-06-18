package com.yuri.marketplace.helper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yuri.marketplace.model.UsuarioModel
import com.yuri.marketplace.services.RetrofitClient
import kotlinx.coroutines.launch

class APIHelper {
    var mensagem by mutableStateOf("")

    suspend fun salvarUsuario(nome: String, email: String, senha: String) : Boolean {
        try{
            val resposta = RetrofitClient.apiService.inserirUsuario(nome, email, senha)
            mensagem = resposta.message

            if(resposta.success == true){
                return true
            }else{
                return false;
            }
        }catch (e: Exception){
            return false;
        }
    }

    suspend fun verificarUsuarioPeloId(idUsu: Int?) : UsuarioModel?{
        try{
            val resposta = RetrofitClient.apiService.selectUserById(idUsu)
            if(resposta.success){
                mensagem = resposta.message
                return resposta.dados
            }else{
                mensagem = resposta.message
                return null
            }
        }catch (e: Exception){
            return null
        }
    }

    suspend fun verificarUsuarioByEmailAndPassword(email: String, senha: String) : UsuarioModel?{
        try{
            val resposta = RetrofitClient.apiService.selectUserByEmailAndPassword(email, senha)
            if(resposta.success == true){
                mensagem = resposta.message
                return resposta.dados
            }else{
                mensagem = resposta.message
                return null
            }
        }catch (e: Exception){
            return null
        }
    }
}