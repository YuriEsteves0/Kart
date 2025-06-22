package com.yuri.marketplace.view.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yuri.marketplace.controller.Dashboard.AddProdutoController
import com.yuri.marketplace.helper.SharedPreferencesHelper
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaObjeto
import com.yuri.marketplace.ui.theme.laranjaPrimario

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProdutoScreen(navController: NavController) {

    var nomeProduto by remember { mutableStateOf("") }
    var precoProduto by remember { mutableStateOf(TextFieldValue("")) }
    val sharedPref = SharedPreferencesHelper(LocalContext.current)
    val idUsuario by remember { mutableStateOf(sharedPref.encontrarId("idUsu") ?: "",) }
    var stateSheet = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var mostrarSheet by remember { mutableStateOf(false) }

    if(mostrarSheet){
        ModalBottomSheet(
            onDismissRequest = {
                mostrarSheet = false
            },
            sheetState = stateSheet
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
                                    // TODO "FAZER UM BGL PRA ENVIAR PRO BD AQUI"
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

    Scaffold (
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Produto") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Voltar"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            mostrarSheet = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Suporte"
                        )
                    }
                }
            )
        }
    ){ paddingValues ->
        Column(
            Modifier.padding(paddingValues).padding(10.dp)
        ) {

            Spacer(Modifier.height(10.dp))

            TextField(
                value = nomeProduto,
                onValueChange = {
                    nomeProduto = it
                },
                maxLines = 1,
                placeholder = {Text("Nome do produto")},
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

            Row {
                TextField(
                    value = precoProduto,
                    onValueChange = { newValue ->

                        val clean = newValue.text.replace("""[^\d]""".toRegex(), "")
                        val formattedText = if (clean.isNotBlank()) {
                            val addProdutoController = AddProdutoController()
                            addProdutoController.formatCurrency(clean)
                        } else {
                            ""
                        }
                        precoProduto = TextFieldValue(
                            text = formattedText,
                            selection = TextRange(formattedText.length)
                        )
                    },
                    maxLines = 1,
                    placeholder = { Text("Preço do produto") },
                    modifier = Modifier
                        .weight(1f)
                        .background(color = Color.White, RoundedCornerShape(10.dp))
                        .padding(end = 5.dp)
                        .shadow(10.dp, RoundedCornerShape(10.dp), clip = false),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                TextField(
                    value = idUsuario,
                    onValueChange = {},
                    maxLines = 1,
                    enabled = false,
                    placeholder = {Text("IDUV")},
                    modifier = Modifier.weight(1f)
                        .background(color = Color.White, RoundedCornerShape(10.dp))
                        .shadow(10.dp, RoundedCornerShape(10.dp), clip = false)
                        .padding(start = 5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = cinzaObjeto,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )

            }
            Spacer(Modifier.height(10.dp))

            Row (
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
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
                        AddProdutoController().adicionarProduto(nomeProduto, precoProduto.text, idUsuario)
                        navController.popBackStack()
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

        }
    }
}

@Composable
@Preview
fun addProdutoPreview(){
    val navController = NavController(LocalContext.current);
    AddProdutoScreen(navController)
}