package com.yuri.marketplace

import android.os.Bundle
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuri.marketplace.ui.theme.MarketplaceTheme
import com.yuri.marketplace.view.CadastroScreen
import com.yuri.marketplace.view.ChatScreen
import com.yuri.marketplace.view.EditUserDataScreen
import com.yuri.marketplace.view.HomeScreen
import com.yuri.marketplace.view.LoginScreen
import com.yuri.marketplace.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketplaceTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login"){
                    composable("home"){
                        MainScreen(navController)
                    }
                    composable("login"){
                        LoginScreen(navController)
                    }
                    composable("cadastro"){
                        CadastroScreen(navController)
                    }
                    composable("editUserData"){
                        EditUserDataScreen()
                    }
                }
            }
        }
    }
}