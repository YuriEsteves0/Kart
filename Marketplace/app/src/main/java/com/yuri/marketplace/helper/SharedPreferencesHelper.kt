package com.yuri.marketplace.helper

import android.content.Context

class SharedPreferencesHelper(val context: Context){
    val sharedPref = context.getSharedPreferences("pref_usuario", Context.MODE_PRIVATE)

    //o id ta como IdUsu
    fun salvar(chave: String, valor: String){
        sharedPref.edit().putString(chave, valor).apply()
    }

    fun excluir(chave: String): Boolean{
        try{
            sharedPref.edit().remove(chave).commit()
            return true
        }catch (e: Exception){
            return false
        }
    }

    fun encontrarId(chave: String): String?{
        return sharedPref.getString(chave, null)
    }
}