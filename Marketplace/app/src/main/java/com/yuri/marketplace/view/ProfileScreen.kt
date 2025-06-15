package com.yuri.marketplace.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario
import com.yuri.marketplace.ui.theme.transparente

@Composable
fun ProfileScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(

            ){
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    tint = cinzaTexto,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                )
                IconButton (
                    onClick = {
                        navController.navigate("editUserData")
                    },
                    modifier = Modifier
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(100.dp), clip = false)
                        .background(Color.White, shape = RoundedCornerShape(100.dp))
                        .align(Alignment.BottomEnd)
                        .width(40.dp)
                        .height(40.dp)
                        .padding(5.dp)
                ){
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "Editar foto",
                        tint = azulPrimario,
                    )
                }
            }

            Spacer(Modifier.height(10.dp))
            Text("Nome de Usuário", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {

                },
                enabled = false,
                colors = ButtonColors(
                    containerColor = laranjaPrimario,
                    contentColor = Color.White,
                    disabledContainerColor = transparente,
                    disabledContentColor = laranjaPrimario
                ),
                border = BorderStroke(1.dp, laranjaPrimario)
            ) {
                Text("Sair",)
            }
        }
    }
}