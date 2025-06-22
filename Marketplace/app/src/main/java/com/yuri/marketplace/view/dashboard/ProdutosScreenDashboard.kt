package com.yuri.marketplace.view.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutosScreenDashboard(navController: NavController) {
    var pesquisaProduto by remember { mutableStateOf("") }
    var focusManager = LocalFocusManager.current

    Text("Dashboard -> Produtos", style = MaterialTheme.typography.titleMedium)

    Spacer(Modifier.height(30.dp))

    TextField(
        value = pesquisaProduto,
        onValueChange = { pesquisaProduto = it},
        placeholder = { Text("Pesquise pelo seu produto") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            disabledIndicatorColor = Color.White
        ),
        trailingIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Search, contentDescription = "Pesquisar")
            }
        }
    )

    Box(

    ){

        Column(
            Modifier
                .shadow(10.dp, RoundedCornerShape(10.dp), clip = false)
                .background(Color.White, RoundedCornerShape(10.dp))
        ) {
            Spacer(Modifier.height(30.dp))

            Row {
                Text("ID", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(vertical = 20.dp, horizontal = 10.dp))
                Text("Nome", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(top = 20.dp), textAlign = TextAlign.Center)
                Text("Estoque", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(vertical = 20.dp, horizontal = 10.dp), textAlign = TextAlign.End)
            }

            repeat(10){
                Row(
                    Modifier.background(Color.White, RoundedCornerShape(10.dp))
                ){
                    Text("1", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(vertical = 20.dp, horizontal = 10.dp))
                    Text("Camiseta", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(top = 20.dp), textAlign = TextAlign.Center)
                    Text("10", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f).padding(vertical = 20.dp, horizontal = 10.dp), textAlign = TextAlign.End)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProdutosScreenDashboardPreview(){
    val navController = NavController(LocalContext.current)
    ProdutosScreenDashboard(navController)
}
