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

@Composable
fun Squircle(
    sizeInDp: Dp,
    @FloatRange(from = 1.0, to = 10.0) smoothing: Double,
    backgroundColor: Color = Color.Transparent,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        Modifier
            .size(sizeInDp)
            .clip(SquircleShape(smoothing))
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}