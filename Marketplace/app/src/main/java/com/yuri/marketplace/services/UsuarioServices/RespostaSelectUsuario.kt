package com.yuri.marketplace.services.UsuarioServices

import com.yuri.marketplace.model.UsuarioModel

data class RespostaSelectUsuario(
    val success: Boolean,
    val message: String,
    val dados: UsuarioModel?
)
