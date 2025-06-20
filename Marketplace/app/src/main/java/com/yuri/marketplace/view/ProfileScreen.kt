package com.yuri.marketplace.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yuri.marketplace.controller.ProfileController
import com.yuri.marketplace.model.UsuarioModel
import com.yuri.marketplace.sessions.UserSession
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario
import com.yuri.marketplace.ui.theme.transparente
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController){

    var usuarioLogado by remember { mutableStateOf<UsuarioModel?>(null) }

    LaunchedEffect(Unit) {
        usuarioLogado = UserSession.usuarioLogado
    }

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()

    LazyColumn {
        item {
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                        if (usuarioLogado != null) {
                            Text(usuarioLogado!!.nome)
                        } else {
                            Text("U.N.I")
                        }
                        Spacer(Modifier.height(10.dp))

                        Button(
                            onClick = {
                                val profileController = ProfileController()
                                profileController.logoutUsuario(usuarioLogado!!.id, context, navController)
                            },
                            colors = ButtonColors(
                                containerColor = transparente,
                                contentColor = laranjaPrimario,
                                disabledContainerColor = transparente,
                                disabledContentColor = laranjaPrimario
                            ),
                            border = BorderStroke(1.dp, laranjaPrimario)
                        ) {
                            Text("Sair",)
                        }

                    }
                    Spacer(Modifier.height(30.dp))

                    Text(text = "Recomendações", style = MaterialTheme.typography.titleLarge)

                    Row(
                        Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        repeat(10){
                            var usuarios = criarUsuarios();
                            var produtos = criarProdutos(usuarios);
                            Card(produtos)
                        }
                    }

                    Spacer(Modifier.height(30.dp))

                    Text(text = "Ofertas do dia", style = MaterialTheme.typography.titleLarge)

                    Row(
                        Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        repeat(10){
                            var usuarios = criarUsuarios();
                            var produtos = criarProdutos(usuarios);
                            Card(produtos)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun profilePreview(){
    val navController = NavController(LocalContext.current)
    ProfileScreen(navController)
}