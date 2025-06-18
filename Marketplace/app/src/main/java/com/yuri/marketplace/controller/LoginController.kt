package com.yuri.marketplace.controller

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.yuri.marketplace.helper.APIHelper
import com.yuri.marketplace.helper.SharedPreferencesHelper
import com.yuri.marketplace.sessions.UserSession
import kotlinx.coroutines.launch

class LoginController {
    var mensagem by mutableStateOf("")

    suspend fun loginUsuario(email: String, senha: String, navController: NavController, context: android.content.Context) : Int{

        var apiHelper: APIHelper = APIHelper()
        val usuarioExistente = apiHelper.verificarUsuarioByEmailAndPassword( email, senha)

        if(usuarioExistente != null){
            Log.d("TAG", "usuario existente: " + usuarioExistente)
            UserSession.usuarioLogado = usuarioExistente

            val sharedPreferencesHelper = SharedPreferencesHelper(context)
            sharedPreferencesHelper.salvar("idUsu", usuarioExistente.id.toString())

            navController.navigate("home"){
                popUpTo("login"){
                    inclusive = true
                }
            }

            return 1;
        }else{
            Log.d("TAG", "erro usuario existente é nulo")
            return 0;
        }

    }
}
