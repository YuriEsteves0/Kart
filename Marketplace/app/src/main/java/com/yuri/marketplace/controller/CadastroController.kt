package com.yuri.marketplace.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuri.marketplace.services.RetrofitClient
import kotlinx.coroutines.launch

class CadastroController : ViewModel(){

    var mensagem by mutableStateOf("")

    fun cadastrarUsuario(nome: String, email: String, senha: String) {
        viewModelScope.launch {
            try{
                val resposta = RetrofitClient.apiService.inserirUsuario(nome, email, senha)
                mensagem = resposta.message
            }catch (e: Exception){
                mensagem = "Error: ${e.message}"
            }

            println(mensagem);
        }
    }
}