package com.yuri.marketplace.controller

import android.util.Log
import androidx.navigation.NavController
import com.yuri.marketplace.helper.SharedPreferencesHelper
import com.yuri.marketplace.sessions.UserSession
import kotlin.system.exitProcess

class ProfileController {

    fun logoutUsuario(idUsu: Int, context: android.content.Context, navController: NavController){
        var sharedPreferencesHelper = SharedPreferencesHelper(context)
        val excluirSP = sharedPreferencesHelper.excluir("idUsu")

        if(excluirSP){
            UserSession.usuarioLogado = null

        }else{
            Log.d("profile", "nao foi possivel excluir")
        }
    }

}