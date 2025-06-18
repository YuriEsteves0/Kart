package com.yuri.marketplace.sessions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yuri.marketplace.model.UsuarioModel

object UserSession {
    var usuarioLogado by mutableStateOf<UsuarioModel?>(null)
}
