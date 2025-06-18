package com.yuri.marketplace.view

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import android.widget.ImageButton
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yuri.marketplace.R
import com.yuri.marketplace.controller.LoginController
import com.yuri.marketplace.helper.AnimatedGrowingBox
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var emailUser by remember { mutableStateOf("") }
    var senhaUser by remember{ mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorSenha by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val corroutine = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {SnackbarHost(snackbarHostState)}

    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AnimatedGrowingBox(
                endSize = 300.dp,
                color = Color.White
            ){
                Column (
                    modifier = Modifier
                        .shadow(10.dp, RoundedCornerShape(10.dp), clip = false)
                        .background(Color.White, RoundedCornerShape(10.dp))
                ){
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.padding(top=10.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Image(
                            painter = painterResource(R.drawable.kart),
                            contentDescription = "Kart Logo",
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    TextField(
                        value = emailUser,
                        onValueChange = {
                            emailUser = it
                            if (isErrorEmail && it.isNotBlank()) isErrorEmail = false
                        },
                        placeholder = { Text("Digite seu email", color = cinzaTexto) },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email",
                                tint = azulPrimario
                            )
                        },
                        isError = isErrorEmail,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = if (isErrorEmail) Color.Red else azulPrimario,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = if (isErrorEmail) Color.Red else azulPrimario
                        )
                    )

                    TextField(
                        value = senhaUser,
                        onValueChange = {
                            senhaUser = it
                            if (isErrorSenha && it.isNotBlank()) isErrorSenha = false
                        },
                        isError = isErrorSenha,
                        placeholder = { Text("Digite sua senha") },
                        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),

                        trailingIcon = {
                            val imagem = if (senhaVisivel) R.drawable.eye else R.drawable.hidden

                            IconButton(
                                onClick = {
                                    senhaVisivel = !senhaVisivel
                                }
                            ) {
                                Image(
                                    painter = painterResource(imagem),
                                    contentDescription = "Senha",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(azulPrimario)
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = if (isErrorSenha) Color.Red else azulPrimario,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = if (isErrorSenha) Color.Red else azulPrimario
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Text("Esqueceu sua senha?")
                        TextButton(
                            onClick = {
                                navController.navigate("cadastro")

                            },
                            contentPadding = PaddingValues(start = 0.dp)
                        ){
                            Text(" Clique aqui", color = laranjaPrimario)
                        }
                    }
                    ElevatedButton(
                        onClick = {
                            isErrorEmail = emailUser.isBlank()
                            isErrorSenha = senhaUser.isBlank()

                            if (!isErrorEmail && !isErrorSenha) {
                                corroutine.launch {
                                    val resultado = LoginController().loginUsuario(emailUser, senhaUser, navController, context)
                                    if (resultado == 0) {
                                        isErrorEmail = true
                                        isErrorSenha = true
                                        snackbarHostState.showSnackbar("Email/Senha incorretos")
                                    }
                                }
                            }
                        },
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = azulPrimario,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end=20.dp, bottom=20.dp, top = 0.dp)
                    ) {
                        Text("Entrar")
                    }
                }
            }

        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun LoginPreview(){
    val navController = NavController(context = LocalContext.current)
    LoginScreen(navController)
}

