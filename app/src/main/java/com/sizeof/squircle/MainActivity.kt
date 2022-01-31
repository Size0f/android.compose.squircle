package com.sizeof.squircle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sizeof.libraries.compose.squircle.Squircle
import com.sizeof.squircle.PreviewerConstants.SQUIRCLE_SIZE_MAX_VALUE
import com.sizeof.squircle.PreviewerConstants.SQUIRCLE_SIZE_MIN_VALUE
import com.sizeof.squircle.PreviewerConstants.SQUIRCLE_SMOOTHING_MAX_VALUE
import com.sizeof.squircle.PreviewerConstants.SQUIRCLE_SMOOTHING_MIN_VALUE
import com.sizeof.squircle.PreviewerConstants.SQUIRCLE_TEXT
import kotlin.math.floor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun Content() {
    MaterialTheme(
        colors = Colors(
            primary = Color(0xFF1F295B),
            primaryVariant = Color(0xFF2F3763),
            secondary = Color(0xFF8C5BE9),
            secondaryVariant = Color(0xFF819DAD),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF8C5BE9),
            background = Color(0xFF1F295B),
            onBackground = Color(0xFF1F295B),
            onError = Color(0xFFCE81C7),
            surface = Color(0xFF2F3763),
            onSurface = Color(0xFF819DAD),
            error = Color(0xFFCE8181),
            isLight = false
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var squircleSize by remember { mutableStateOf(SQUIRCLE_SIZE_MIN_VALUE) }
            var squircleSmoothing by remember { mutableStateOf(SQUIRCLE_SMOOTHING_MAX_VALUE) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Squircle(
                    sizeInDp = squircleSize.dp,
                    smoothing = floor(squircleSmoothing.toDouble()),
                    backgroundColor = MaterialTheme.colors.secondary
                ) {
                    Text(
                        text = SQUIRCLE_TEXT,
                        fontSize = (squircleSize / 4).sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Size: ${squircleSize.toInt()} dp")
                        Slider(
                            modifier = Modifier.fillMaxWidth(),
                            value = squircleSize,
                            onValueChange = { squircleSize = it },
                            valueRange = SQUIRCLE_SIZE_MIN_VALUE..SQUIRCLE_SIZE_MAX_VALUE,
                            steps = 5,
                            colors = SliderDefaults.colors(
                                thumbColor = MaterialTheme.colors.secondary,
                                activeTrackColor = MaterialTheme.colors.secondary
                            )
                        )
                        Text(text = "Smoothing: ${floor(squircleSmoothing.toDouble())}")
                        Slider(
                            modifier = Modifier.fillMaxWidth(),
                            value = squircleSmoothing,
                            onValueChange = { squircleSmoothing = it },
                            valueRange = SQUIRCLE_SMOOTHING_MIN_VALUE..SQUIRCLE_SMOOTHING_MAX_VALUE,
                            steps = 5,
                            colors = SliderDefaults.colors(
                                thumbColor = MaterialTheme.colors.secondary,
                                activeTrackColor = MaterialTheme.colors.secondary
                            )
                        )
                    }
                }
            }
        }
    }
}

object PreviewerConstants {
    const val SQUIRCLE_TEXT = "AB"
    const val SQUIRCLE_SIZE_MIN_VALUE = 64F
    const val SQUIRCLE_SIZE_MAX_VALUE = 164F
    const val SQUIRCLE_SMOOTHING_MIN_VALUE = 2F
    const val SQUIRCLE_SMOOTHING_MAX_VALUE = 10F
}