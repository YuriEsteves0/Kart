package com.yuri.marketplace.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yuri.marketplace.R
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaObjeto
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserDataScreen(navController: NavController) {
    val estadoSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var mostrarSheet by remember { mutableStateOf(false) }

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senha2 by remember { mutableStateOf("") }

    var tela by remember { mutableStateOf(2) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    if(mostrarSheet){
        ModalBottomSheet(
            onDismissRequest = {
                mostrarSheet = false
            },
            sheetState = estadoSheet
        ) {
            LazyColumn {
                item{
                    Column(
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Reportar Problema", style = MaterialTheme.typography.titleLarge)
                        }

                        Spacer(Modifier.height(30.dp))
                        Text("Você está prestes a entrar em contato com o suporte. Para facilitar o atendimento, seu nome e e-mail serão registrados no nosso banco de dados. Essas informações serão usadas exclusivamente para ajudar a resolver o seu problema de forma eficiente e personalizada. Se você concordar, podemos prosseguir com o envio da sua solicitação.")
                        Spacer(Modifier.height(30.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    mostrarSheet = false
                                },
                                colors = ButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = laranjaPrimario,
                                    disabledContainerColor = Color.Transparent,
                                    disabledContentColor = laranjaPrimario
                                )
                            ) {
                                Text("Cancelar")
                            }

                            Button(
                                onClick = {
                                    mostrarSheet = false
                                },
                                colors = ButtonColors(
                                    containerColor = laranjaPrimario,
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Transparent,
                                    disabledContentColor = laranjaPrimario
                                )
                            ) {
                                Text("Enviar")
                            }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            Modifier.padding(paddingValues).padding(top = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    modifier = Modifier.padding(10.dp)
                )

                Text("Editar Dados", style = MaterialTheme.typography.titleMedium)

                IconButton(
                    onClick = {
                        mostrarSheet = true
                    }
                ){
                    Icon(

                        imageVector = Icons.Default.Warning,
                        contentDescription = "Eroo",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(paddingValues)
                    .padding(30.dp)
            ) {
                Row {
                    Spacer(Modifier.weight(0.9f).height(2.dp).background(if(tela == 1) azulPrimario else cinzaObjeto))
                    Spacer(Modifier.weight(0.1f).height(2.dp).background(Color.Transparent))
                    Spacer(Modifier.weight(0.9f).height(2.dp).background(if(tela == 2) azulPrimario else cinzaObjeto))
                }

                Spacer(Modifier.height(30.dp))

                if(tela == 1){
                    TextField(
                        value = nome,
                        onValueChange = {
                            nome = it
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Email",
                                tint = azulPrimario
                            )
                        },
                        placeholder = {Text("Nome")},
                        modifier = Modifier.fillMaxWidth()
                            .background(color = Color.White, RoundedCornerShape(10.dp))
                            .shadow(10.dp, RoundedCornerShape(10.dp), clip = false),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = azulPrimario
                            )
                        },
                        placeholder = {Text("Email")},
                        modifier = Modifier.fillMaxWidth()
                            .background(color = Color.White, RoundedCornerShape(10.dp))
                            .shadow(10.dp, RoundedCornerShape(10.dp), clip = false),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        value = senha,
                        onValueChange = {
                            senha = it
                        },
                        trailingIcon = {
                            Image(
                                painter = painterResource(R.drawable.eye),
                                contentDescription = "Senha",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(22.dp),
                                colorFilter = ColorFilter.tint(azulPrimario)
                            )
                        },
                        placeholder = {Text("Repita a senha")},
                        modifier = Modifier.fillMaxWidth()
                            .background(color = Color.White, RoundedCornerShape(10.dp))
                            .shadow(10.dp, RoundedCornerShape(10.dp), clip = false),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        value = senha2,
                        onValueChange = {
                            senha2 = it
                        },
                        trailingIcon = {
                            Image(
                                painter = painterResource(R.drawable.eye),
                                contentDescription = "Repita a senha",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(22.dp),
                                colorFilter = ColorFilter.tint(azulPrimario)
                            )
                        },
                        placeholder = {Text("Senha")},
                        modifier = Modifier.fillMaxWidth()
                            .background(color = Color.White, RoundedCornerShape(10.dp))
                            .shadow(10.dp, RoundedCornerShape(10.dp), clip = false),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(
                            onClick = {
                                navController.popBackStack()
                            },
                            colors = ButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = laranjaPrimario,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = laranjaPrimario
                            ),
                            border = BorderStroke(1.dp, laranjaPrimario)
                        ) {
                            Text("Cancelar")
                        }
                        Spacer(Modifier.width(10.dp))
                        Button(
                            onClick = {
                                tela = 2
                            },
                            colors = ButtonColors(
                                containerColor = laranjaPrimario,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = laranjaPrimario

                            )
                        ) {
                            Text("Proximo")
                        }
                    }
                }else if(tela == 2){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                launcher.launch("image/*")
                            },
                            modifier = Modifier.width(150.dp).height(150.dp)
                        ) {
                            if(imageUri == null){
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = "Perfil",
                                    tint = cinzaTexto,
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(150.dp),
                                )
                            }else{
                                imageUri.let {
                                    AsyncImage(
                                        model = it,
                                        contentDescription = "Imagem do usuario",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.width(150.dp).height(150.dp)
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(20.dp))

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ){
                            Button(
                                onClick = {
                                    tela = 1
                                },
                                colors = ButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = laranjaPrimario,
                                    disabledContainerColor = Color.Transparent,
                                    disabledContentColor = laranjaPrimario
                                ),
                                border = BorderStroke(1.dp, laranjaPrimario)
                            ) {
                                Text("Voltar")
                            }
                            Spacer(Modifier.width(10.dp))
                            Button(
                                onClick = {
                                    // TODO "FAZER ATUALIZAR OS DADOS AQUI"
                                },
                                colors = ButtonColors(
                                    containerColor = laranjaPrimario,
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Transparent,
                                    disabledContentColor = laranjaPrimario

                                )
                            ) {
                                Text("Atualizar")
                            }
                        }
                    }
                }else{
                    navController.popBackStack()
                }
            }
        }
    }
}


@Preview
@Composable
fun EditUserPreview(){
    val navController = NavController(LocalContext.current)
    EditUserDataScreen(navController)
}