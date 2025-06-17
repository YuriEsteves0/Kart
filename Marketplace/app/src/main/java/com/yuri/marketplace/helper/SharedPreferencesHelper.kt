package com.yuri.marketplace.helper

import android.content.Context

class SharedPreferencesHelper(val context: Context){
    val sharedPref = context.getSharedPreferences("pref_usuario", Context.MODE_PRIVATE)

    fun salvar(chave: String, valor: String){
        sharedPref.edit().putString(chave, valor).apply()
    }

    fun excluir(chave: String){
        sharedPref.edit().remove(chave).apply()
    }

    fun encontrarId(chave: String): String?{
        return sharedPref.getString(chave, null)
    }
}