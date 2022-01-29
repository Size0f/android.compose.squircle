package com.sizeof.libraries.compose.squircle

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlin.math.abs
import kotlin.math.pow

@Composable
fun Squircle(
    sizeInDp: Dp,
    @FloatRange(from = 1.0, to = 10.0) smoothing: Double,
    backgroundColor: Color = Color.Transparent,
    content: @Composable BoxScope.() -> Unit
) {
    val squircleModifier = Modifier
        .size(sizeInDp)
        .clip(SquircleShape(smoothing))
        .background(color = backgroundColor)

    Box(
        squircleModifier,
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

internal fun evalSquircleFun(x: Int, radius: Double, smoothing: Double) =
    (radius - abs(x.toDouble().pow(smoothing))).pow(1 / smoothing).toFloat()