package com.yuri.marketplace.controller.Dashboard

import com.yuri.marketplace.helper.APIHelper
import java.text.NumberFormat
import java.util.Locale

class AddProdutoController {
    fun formatCurrency(input: String): String {
        val cleanString = input.replace("""[R$,\s.]""".toRegex(), "").replace(",", ".")
        val parsed = cleanString.toDoubleOrNull() ?: 0.0
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return formatter.format(parsed / 100)
    }

    fun adicionarProduto(nomeProduto: String, precoProduto: String, idUsuario: String) {
        // TODO "FAZER ADICIONAR O PRODUTO NO BD"
        val apiHelper = APIHelper()
    }
}