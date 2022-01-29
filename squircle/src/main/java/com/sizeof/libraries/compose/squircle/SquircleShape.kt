package com.sizeof.libraries.compose.squircle

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.pow

internal class SquircleShape(
    private val smoothing: Double
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        path = createSquirclePath(size, smoothing)
    )

    private fun createSquirclePath(size: Size, smoothing: Double) =
        Path().apply {

            val squircleRadius = (size.width / 2F).toInt()
            val poweredRadius = squircleRadius
                .toDouble()
                .pow(smoothing)

            val yCoordinates = (-squircleRadius..squircleRadius).map { x ->
                Pair(
                    x.toFloat(),
                    evalSquircleFun(x, poweredRadius, smoothing)
                )
            }

            val yMirroredCoordinates = yCoordinates.map { (x, y) -> Pair(x, -y) }

            var currentX = 0F
            var currentY = 0F

            (yCoordinates + yMirroredCoordinates).forEach { (x, y) ->
                quadraticBezierTo(currentX, currentY, x, y)
                currentX = x
                currentY = y
            }

            close()

            translate(
                Offset(
                    x = size.width / 2,
                    y = size.height / 2
                )
            )
        }
}