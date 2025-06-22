package com.yuri.marketplace.view.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yuri.marketplace.R
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaObjeto
import com.yuri.marketplace.ui.theme.laranjaPrimario
import com.yuri.marketplace.view.Card
import com.yuri.marketplace.view.criarProdutos
import com.yuri.marketplace.view.criarUsuarios

@Composable
fun HomeScreenDashboard(navController: NavController) {
    val scrollState = rememberScrollState()
    val iconesGrafico = listOf(
        "Receita" to R.drawable.coin,
        "Vendas" to R.drawable.vendas,
        "Denuncias" to R.drawable.denuncias,
        "Alcance" to R.drawable.eye
    )

    Text("Dashboard -> Inicio", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(30.dp))

    Row {
        Button(
            onClick = {
                navController.navigate("addProduto")
            },
            Modifier.weight(1f)
                .padding(end = 4.dp),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = azulPrimario,
                disabledContainerColor = azulPrimario,
                disabledContentColor = Color.White
            )
        ) {
            Text("Novo produto")
        }

        Button(
            onClick = {},
            Modifier.weight(1f)
                .padding(start = 4.dp),
            colors = ButtonColors(
                contentColor = azulPrimario,
                containerColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = azulPrimario
            ),
            border = BorderStroke(1.dp, azulPrimario)
        ) {
            Text("Falar com o suporte")
        }
    }

    Spacer(Modifier.height(30.dp))
    Row {
        Box(
            Modifier
                .weight(0.95f)
                .height(150.dp)
                .background(cinzaObjeto),
        ){
            Text("Grafico de vendas por mês")
        }

        Spacer(Modifier.weight(0.1f))

        Box(
            Modifier
                .weight(0.95f)
                .height(150.dp)
                .background(cinzaObjeto)
        ){
            Text("Grafico de categorias mais vendidas")
        }
    }

    Spacer(Modifier.height(30.dp))

    Row {
        iconesGrafico.forEachIndexed { index, (nomeIcone, icone) ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = if (index == 0) 0.dp else 4.dp,
                        end = if (index == iconesGrafico.lastIndex) 0.dp else 4.dp
                    )
                    .height(64.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .background(cinzaObjeto, RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(icone),
                        contentDescription = nomeIcone,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(24.dp),
                        colorFilter = ColorFilter.tint(if(index == 0 || index == 2) azulPrimario else laranjaPrimario)
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("+ 0,5%")
                }
            }
        }
    }

    Spacer(Modifier.height(30.dp))

    Text("Produtos mais procurados", style = MaterialTheme.typography.titleLarge)

    Spacer(Modifier.height(30.dp))

    Row (
        Modifier.horizontalScroll(enabled = true, state = scrollState)
    ){
        repeat(10){
            var usuarios = criarUsuarios();
            var produtos = criarProdutos(usuarios);
            Card(produtos)
        }
    }

    Spacer(Modifier.height(30.dp))

    Text("Produtos mais vendidos", style = MaterialTheme.typography.titleLarge)

    Spacer(Modifier.height(30.dp))

    Row (
        Modifier.horizontalScroll(enabled = true, state = scrollState)
    ){
        repeat(10){
            var usuarios = criarUsuarios();
            var produtos = criarProdutos(usuarios);
            Card(produtos)
        }
    }

}
