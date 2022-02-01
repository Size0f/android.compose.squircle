package com.sizeof.libraries.compose.squircle

import android.graphics.Path
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.graphics.scaleMatrix
import androidx.core.graphics.translationMatrix
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
        private const val OVERSAMPLING_MULTIPLIER = 4F
        private fun createSquirclePath(size: Size, smoothing: Double) =
            Path().apply {
                val oversize = size.width * OVERSAMPLING_MULTIPLIER
                val squircleRadius = (oversize / 2F).toInt()

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

                // set path by using quadraticBezier
                (yCoordinates + yMirroredCoordinates).forEach { (x, y) ->
                    quadTo(currentX, currentY, x, y)
                    currentX = x
                    currentY = y
                }

                close()

                // scale down to original size - for better corners without anti-alias
                transform(
                    scaleMatrix(
                        sx = 1 / OVERSAMPLING_MULTIPLIER,
                        sy = 1 / OVERSAMPLING_MULTIPLIER
                    )
                )

                // translate path to center
                transform(
                    translationMatrix(
                        tx = size.width / 2,
                        ty = size.height / 2
                    )
                )
            }.asComposePath()

        // squircle formula: | (r^smoothing) - |x|^5 | ^ (1 / smoothing)
        private fun evalSquircleFun(x: Int, poweredRadius: Double, smoothing: Double) =
            (poweredRadius - abs(x.toDouble().pow(smoothing))).pow(1 / smoothing).toFloat()
    }
}