package com.yuri.marketplace.controller

import android.util.Log
import com.yuri.marketplace.helper.APIHelper
import com.yuri.marketplace.model.UsuarioModel

class SplashController {
    suspend fun verificarUsuarioLogado(idUsu: Int?): UsuarioModel?{
        var apiHelper = APIHelper()
        val usuario = apiHelper.verificarUsuarioPeloId(idUsu)
        if(usuario != null){
            return usuario
        }else{
            Log.d("TAG", "verificarUsuarioLogado: usuario é nulo vey")
            return null
        }
    }
}