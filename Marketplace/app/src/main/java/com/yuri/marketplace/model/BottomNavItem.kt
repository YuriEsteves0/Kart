package com.yuri.marketplace.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val label: String, val icon: ImageVector){
    object Home : BottomNavItem("Inicio", Icons.Default.Home)
    object Chats : BottomNavItem("Chats", Icons.Default.List)
    object Perfil : BottomNavItem("Inicio", Icons.Default.AccountCircle)
}