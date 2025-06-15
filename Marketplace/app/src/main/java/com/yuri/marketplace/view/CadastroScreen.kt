package com.yuri.marketplace.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import com.yuri.marketplace.R
import com.yuri.marketplace.controller.CadastroController
import com.yuri.marketplace.helper.AnimatedGrowingBox
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(navController: NavController) {

    var nomeUser by remember{ mutableStateOf("") }
    var emailUser by remember { mutableStateOf("") }
    var senhaUser by remember{ mutableStateOf("") }
    var senhaUser2 by remember{ mutableStateOf("") }

    var senhaVisivel by remember { mutableStateOf(false) }
    var senhaVisivel2 by remember { mutableStateOf(false) }

    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorSenha by remember { mutableStateOf(false) }
    var isErrorSenha2 by remember { mutableStateOf(false) }
    var isErrorNome by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
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
                        value = nomeUser,
                        onValueChange = {
                            nomeUser = it
                            if (isErrorNome && it.isNotBlank()) isErrorNome = false
                        },
                        placeholder = { Text("Digite seu nome", color = cinzaTexto) },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Email",
                                tint = azulPrimario
                            )
                        },
                        isError = isErrorNome,
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

                    TextField(
                        value = senhaUser2,
                        onValueChange = {
                            senhaUser2 = it
                            if (isErrorSenha2 && it.isNotBlank()) isErrorSenha2 = false
                        },
                        isError = isErrorSenha2,
                        placeholder = { Text("Repita sua senha") },
                        visualTransformation = if (senhaVisivel2) VisualTransformation.None else PasswordVisualTransformation(),

                        trailingIcon = {
                            val imagem = if (senhaVisivel2) R.drawable.eye else R.drawable.hidden

                            IconButton(
                                onClick = {
                                    senhaVisivel2 = !senhaVisivel2
                                }
                            ) {
                                Image(
                                    painter = painterResource(imagem),
                                    contentDescription = "Repita a Senha",
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
                        Text("Já tem uma conta?")
                        TextButton(
                            onClick = {
                                navController.navigate("login")

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
                            isErrorSenha2 = senhaUser.isBlank()

                            if (!isErrorEmail && !isErrorSenha && !isErrorSenha2) {
                                var cadastroController = CadastroController()
                                cadastroController.cadastrarUsuario(nomeUser, emailUser, senhaUser)
                                navController.navigate("login")
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

@Composable
@Preview
fun CadastroPreview(){
    var navController = NavController(context = LocalContext.current)
    CadastroScreen(navController)
}