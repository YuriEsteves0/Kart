package com.yuri.marketplace.view

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yuri.marketplace.R
import com.yuri.marketplace.controller.HomeScreenController
import com.yuri.marketplace.model.BottomNavItem
import com.yuri.marketplace.model.UsuarioModel
import com.yuri.marketplace.ui.theme.azulPrimario
import com.yuri.marketplace.ui.theme.cinzaObjeto
import com.yuri.marketplace.ui.theme.cinzaTexto
import com.yuri.marketplace.ui.theme.laranjaPrimario

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {

    var triggerBtnSearch by remember { mutableStateOf(false) }
    var textoPesquisa by remember { mutableStateOf("") }

    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val searchWidth by animateDpAsState(
        targetValue = if (triggerBtnSearch) screenWidth else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )

    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Chats,
        BottomNavItem.Perfil
    )
    var selectedItem by remember { mutableStateOf(2) }

    Scaffold(
        containerColor = cinzaObjeto,
        bottomBar = {
            NavigationBar(
                tonalElevation = 5.dp,
                containerColor = Color.White,
            ) {
                bottomItems.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        onClick = {selectedItem = index},
                        selected = selectedItem == index,
                        icon = {
                            Icon(bottomNavItem.icon, contentDescription = bottomNavItem.label)
                        },
                        label = null,
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(selectedTextColor = Color.White, selectedIconColor = azulPrimario, indicatorColor = Color.White, unselectedIconColor = cinzaTexto, unselectedTextColor = cinzaTexto)
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit){
                    detectTapGestures(onTap = {
                        triggerBtnSearch = false
                    })
                }
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(triggerBtnSearch){
                        val focusRequester = remember { FocusRequester() }
                        val focusManager = LocalFocusManager.current

                        LaunchedEffect(triggerBtnSearch) {
                            if (triggerBtnSearch) {
                                focusRequester.requestFocus()
                            }
                        }

                        TextField(
                            value = textoPesquisa,
                            onValueChange = { textoPesquisa = it },
                            placeholder = { Text("Procure por itens...", color = cinzaTexto) },
                            textStyle = TextStyle(fontSize = 16.sp),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    HomeScreenController().pesquisar(textoPesquisa)
                                    focusManager.clearFocus()
                                }
                            ),
                            modifier = Modifier
                                .width(searchWidth)
                                .height(50.dp)
                                .focusRequester(focusRequester)
                        )
                    }else{
                        AnimatedVisibility(
                            visible = !triggerBtnSearch,
                            exit = fadeOut(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Image(
                                    painter = painterResource(R.drawable.kart),
                                    contentDescription = "Logo Kart",
                                    modifier = Modifier.height(40.dp).width(40.dp)
                                )

                                Row {

                                    IconButton(
                                        onClick = {
                                            abrirLink(context)
                                        },
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.gh),
                                            contentDescription = "Pesquisar",
                                            colorFilter = ColorFilter.tint(cinzaTexto),
                                            modifier = Modifier.height(30.dp).width(30.dp)
                                        )
                                    }
                                    if(selectedItem == 2){
                                        IconButton(
                                            onClick = {
                                                // TODO "FAZER O MÉTODO DE ENTRAR NA DASHBOARD"
                                            },
                                        ) {
                                            Image(
                                                painter = painterResource(R.drawable.dashboard),
                                                contentDescription = "Dashboard",
                                                colorFilter = ColorFilter.tint(cinzaTexto),
                                                modifier = Modifier.height(25.dp).width(25.dp)
                                            )

                                        }
                                    }else{
                                        IconButton(
                                            onClick = { triggerBtnSearch = true },
                                        ) {
                                            Icon(Icons.Rounded.Search, contentDescription = "Pesquisar", tint = cinzaTexto)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(30.dp).width(300.dp))

                if(selectedItem == 0) { // HOMEPAGE
                    if(!triggerBtnSearch){
                        HomeScreen()
                    }
                }

                if(selectedItem == 1){ // CHATS
                    if(!triggerBtnSearch){

                    }
                }

                if(selectedItem == 2){ // PERFIL
                    if(!triggerBtnSearch){
                        ProfileScreen(navController)
                    }
                }

            }

        }

    }
}

fun abrirLink(context: android.content.Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/YuriEsteves0"))
    context.startActivity(intent)
}


@Preview
@Composable
fun mainScreenPreview(){
    val navController = NavController(LocalContext.current)
    MainScreen(navController)
}