package com.sizeof.libraries.compose.squircle

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.abs
import kotlin.math.pow

/***
 * Squircle Shape
 * @param smoothing for squircle radius
 */
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

    companion object {
        private fun createSquirclePath(size: Size, smoothing: Double) =
            Path().apply {
                val squircleRadius = (size.width / 2F).toInt()

                // power radius before for optimization
                val poweredRadius = squircleRadius
                    .toDouble()
                    .pow(smoothing)

                // generate Y coordinates for path
                val yCoordinates = (-squircleRadius..squircleRadius).map { x ->
                    Pair(
                        x.toFloat(),
                        evalSquircleFun(x, poweredRadius, smoothing)
                    )
                }

                // generate Y coordinates for mirror half of squircle shape
                val yMirroredCoordinates = yCoordinates.map { (x, y) -> Pair(x, -y) }

                var currentX = 0F
                var currentY = 0F

                // set path by using quadraticBezierTo
                (yCoordinates + yMirroredCoordinates).forEach { (x, y) ->
                    quadraticBezierTo(currentX, currentY, x, y)
                    currentX = x
                    currentY = y
                }

                close()

                // translate path to center
                translate(
                    Offset(
                        x = size.width / 2,
                        y = size.height / 2
                    )
                )
            }

        // squircle formula: | (r^smoothing) - |x|^5 | ^ (1 / smoothing)
        private fun evalSquircleFun(x: Int, poweredRadius: Double, smoothing: Double) =
            (poweredRadius - abs(x.toDouble().pow(smoothing))).pow(1 / smoothing).toFloat()
    }
}