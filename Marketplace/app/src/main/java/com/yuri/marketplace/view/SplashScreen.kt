package com.yuri.marketplace.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.yuri.marketplace.controller.SplashController
import com.yuri.marketplace.helper.SharedPreferencesHelper
import com.yuri.marketplace.sessions.UserSession
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")

    @Composable
    fun SplashScreen(navController: NavController){
        val context = LocalContext.current
        val sharedPref = SharedPreferencesHelper(context)
        val idUsu = sharedPref.encontrarId("idUsu")
        val idUsuInt = idUsu?.toIntOrNull()
        val splashScreenController = SplashController()

        LaunchedEffect(Unit) {
            if (idUsu != null) {
                val usuario = splashScreenController.verificarUsuarioLogado(idUsuInt)
                UserSession.usuarioLogado = usuario

                navController.navigate("home") {
                    popUpTo("splashScreen") { inclusive = true }
                }
            } else {
                navController.navigate("login") {
                    popUpTo("splashScreen") { inclusive = true }
                }
            }
        }


}