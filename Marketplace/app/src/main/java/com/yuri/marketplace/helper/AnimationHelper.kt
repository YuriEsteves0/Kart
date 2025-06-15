package com.yuri.marketplace.helper

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedGrowingBox (
    startSize: Dp = 0.dp,
    endSize: Dp = 200.dp,
    color: Color = Color.White,
    delayMili: Int = 0,
    content: @Composable () -> Unit = {}
)
{
    var startAnimacao by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayMili.toLong())
        startAnimacao = true
    }

    val animacaoCrescer by animateDpAsState(
        targetValue = if (startAnimacao) endSize else startSize,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = Modifier
            .width(animacaoCrescer)
            .background(color)
    ){
        content()
    }
}

