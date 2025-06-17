package com.yuri.marketplace.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuri.marketplace.helper.APIHelper
import com.yuri.marketplace.services.RetrofitClient
import kotlinx.coroutines.launch

class CadastroController : ViewModel(){

    var mensagem by mutableStateOf("")

    suspend fun cadastrarUsuario(nome: String, email: String, senha: String): Boolean {
        try{
            val servicoAPI = APIHelper().salvarUsuario(nome, email, senha)
            if(servicoAPI){
                return true
            }else{
                return false;
            }
        }catch(e: Exception){
            return false
        }

    }
}