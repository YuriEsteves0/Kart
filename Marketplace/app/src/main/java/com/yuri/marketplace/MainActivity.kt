package com.yuri.marketplace

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yuri.marketplace.controller.LoginController
import com.yuri.marketplace.model.UsuarioModel
import com.yuri.marketplace.ui.theme.MarketplaceTheme
import com.yuri.marketplace.view.CadastroScreen
import com.yuri.marketplace.view.ChatScreen
import com.yuri.marketplace.view.EditUserDataScreen
import com.yuri.marketplace.view.HomeScreen
import com.yuri.marketplace.view.LoginScreen
import com.yuri.marketplace.view.MainScreen
import com.yuri.marketplace.view.SplashScreen
import com.yuri.marketplace.view.dashboard.AddProdutoScreen
import com.yuri.marketplace.view.dashboard.MainScreenDashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketplaceTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splashScreen") {
                    composable("splashScreen"){
                        SplashScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("home") {

                        MainScreen(navController)
                    }
                    composable("cadastro") {
                        CadastroScreen(navController)
                    }
                    composable("editUserData") {
                        EditUserDataScreen(navController)
                    }

                    // DASHBOARD

                    composable("dashboard"){
                        MainScreenDashboard(navController)
                    }

                    composable("dashboardProdutos"){

                    }

                    composable("addProduto"){
                        AddProdutoScreen(navController)
                    }

                    composable("addVendas"){
                        AddProdutoScreen(navController)
                    }

                    composable("addPerguntas"){
                        AddProdutoScreen(navController)
                    }


                }
            }
        }
    }
}
