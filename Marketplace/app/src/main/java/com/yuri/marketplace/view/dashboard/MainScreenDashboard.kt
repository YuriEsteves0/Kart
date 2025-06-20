package com.yuri.marketplace.view.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenDashboard(navController: NavController){

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var telaAtual by remember{ mutableStateOf("home") }

    val itensDrawer = listOf(
        "Inicio" to "HomeScreenDashboard",
        "Produtos" to "dashboardProdutos",
        "Vendas" to "dashboardVendas",
        "Perguntas" to "dashboardPerguntas",
        "Sair" to "home"
    )

    ModalNavigationDrawer(
        drawerState =drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(30.dp))
                Text("Menu", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                itensDrawer.forEach { (item, rota) ->
                    NavigationDrawerItem(
                        label = { Text(item)},
                        selected = false,
                        onClick = {
                            telaAtual = rota
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Kart dashboard") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn {
                item {
                    Column(
                        Modifier.padding(paddingValues)
                            .padding(10.dp)
                    ) {
                        when (telaAtual) {
                            "home" -> HomeScreenDashboard(navController)
                            "dashboardProdutos" -> MainScreenDashboard(navController)
                            "dashboardVendas" -> MainScreenDashboard(navController)
                            "dashboardPerguntas" -> MainScreenDashboard(navController)
                            else -> navController.popBackStack()
                        }
                    }
                }
            }
        }
    }

}

@Composable
@Preview
fun MainScreenDashboardPreview(){
    val navController = NavController(LocalContext.current)
    MainScreenDashboard(navController)
}