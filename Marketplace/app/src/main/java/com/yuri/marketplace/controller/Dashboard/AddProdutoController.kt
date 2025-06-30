package com.yuri.marketplace.controller.Dashboard

import android.content.Context
import android.net.Uri
import com.yuri.marketplace.helper.APIHelper
import kotlinx.coroutines.launch
import java.io.File
import java.text.NumberFormat
import java.util.Locale

class AddProdutoController {
    fun formatCurrency(input: String): String {
        val cleanString = input.replace("""[R$,\s.]""".toRegex(), "").replace(",", ".")
        val parsed = cleanString.toDoubleOrNull() ?: 0.0
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return formatter.format(parsed / 100)
    }

    fun adicionarProduto(
        context: Context,
        nomeProduto: String,
        precoProduto: String,
        idUsuario: String,
        imageUri: Uri?
    ) {
        val helper = APIHelper()
        val path = imageUri?.let { getFileFromUri(context, it).absolutePath }

        if (path != null) {
            kotlinx.coroutines.GlobalScope.launch {
                helper.adicionarProdutoComImagem(nomeProduto, precoProduto, idUsuario, path)
            }
        }
    }

    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)!!
        val file = File(context.cacheDir, "temp_image.jpg")
        val outputStream = file.outputStream()
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()
        return file
    }
}