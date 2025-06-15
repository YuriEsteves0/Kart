package com.yuri.marketplace.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
        ){
            
        }
    }
}

@Composable
@Preview
fun LoginPreview(){
    val navController = NavController(context = LocalContext.current)
    LoginScreen(navController)
}

