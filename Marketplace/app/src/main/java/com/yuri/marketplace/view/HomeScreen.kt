package com.yuri.marketplace.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yuri.marketplace.R
import com.yuri.marketplace.model.ProdutoModel
import com.yuri.marketplace.model.UsuarioModel
import com.yuri.marketplace.ui.theme.laranjaPrimario
import com.yuri.marketplace.ui.theme.pretoSecundario
import com.yuri.marketplace.ui.theme.transparente
import com.yuri.marketplace.ui.theme.verdePrimario

@Composable
fun HomeScreen(paddingValues: PaddingValues = PaddingValues()){
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues),
    ) {
        item {
            Column() {
                Box(
                    modifier = Modifier.padding(vertical=15.dp).shadow(10.dp, RoundedCornerShape(10.dp), clip=false).clip(RoundedCornerShape(10.dp))
                ){
                    Image(
                        painter = painterResource(R.drawable.anunciops),
                        contentDescription = "Anuncio Ps5",
                    )
                }
                Spacer(Modifier.height(30.dp))

                Text(text = "Ofertas do dia", style = MaterialTheme.typography.titleLarge)

                Spacer(Modifier.height(10.dp))

                Row (
                    Modifier.horizontalScroll(rememberScrollState())
                ){
                    repeat(10){
                        var usuarios = criarUsuarios();
                        var produtos = criarProdutos(usuarios);
                        Card(produtos)
                    }
                }
                Spacer(Modifier.height(30.dp))

                Text(text = "Produtos que talvez te interessem", style = MaterialTheme.typography.titleLarge)

                Spacer(Modifier.height(10.dp))

                Row (
                    Modifier.horizontalScroll(rememberScrollState())
                ){
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

fun criarUsuarios() : MutableList<UsuarioModel>{
    var usuarios = mutableListOf<UsuarioModel>()
    var usuario01 = UsuarioModel(1, "Yuri", "yuri@gmail.com", "123123", true, "A")
    var usuario02 = UsuarioModel(2, "João", "joao@gmail.com", "123123", false, "U")
    var usuario03 = UsuarioModel(3, "Maria", "maria@gmail.com", "123123", false, "U")
    var usuario04 = UsuarioModel(4, "Pedro", "pedro@gmail.com", "123123", true, "A")


    usuarios.add(usuario01)
    usuarios.add(usuario02)
    usuarios.add(usuario03)
    usuarios.add(usuario04)

    return usuarios
}

fun criarProdutos(usuarios: MutableList<UsuarioModel>) : MutableList<ProdutoModel>{
    var usuarioAleatorio01 = usuarios.random()
    var usuarioAleatorio02 = usuarios.random()
    var usuarioAleatorio03 = usuarios.random()

    var produtos = mutableListOf<ProdutoModel>()
    var produto01 = ProdutoModel("Playstation 5", 5000.00, R.drawable.ps5, usuarioAleatorio01)
    var produto02 = ProdutoModel("Camisa Twenty One Pilots", 350.00, R.drawable.camisatopjpeg, usuarioAleatorio02)
    var produto03 = ProdutoModel("Jogo GTA V - Xbox One", 80.00, R.drawable.gtav, usuarioAleatorio03)

    produtos.add(produto01)
    produtos.add(produto02)
    produtos.add(produto03)

    return produtos

}
@Composable
fun Card(produtos: MutableList<ProdutoModel>) {
    val produto = produtos.random()

    Box(
        modifier = Modifier
            .width(180.dp)
            .height(260.dp)
            .padding(vertical = 20.dp, horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .shadow(10.dp, RoundedCornerShape(10.dp), clip = false)
                .background(Color.White, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .padding(top = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Image(
                    painter = painterResource(produto.foto),
                    contentDescription = "Imagem do produto",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                )

                if(produto.usuarioModel.assinante){
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .background(pretoSecundario, RoundedCornerShape(100.dp))
                            .shadow(10.dp, RoundedCornerShape(100.dp), clip = false)
                            .clip(RoundedCornerShape(100.dp))
                    ) {
                        Image(
                            painter = painterResource(R.drawable.kartlaranja),
                            contentDescription = "Usuário Verificado",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                                .padding(4.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxHeight()
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = produto.nome,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2
                )
                Spacer(Modifier.height(5.dp))

                Text(
                    text = "R$ ${produto.preco}",
                    color = verdePrimario,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(5.dp))

                Text(
                    text = produto.usuarioModel.nome,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }
}
