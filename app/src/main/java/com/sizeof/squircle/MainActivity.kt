package com.sizeof.squircle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sizeof.libraries.compose.squircle.Squircle

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
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                Modifier
                    .width(48.dp)
                    .height(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Squircle(
                    sizeInDp = 48.dp,
                    smoothing = 4.0,
                    backgroundColor = Color.Cyan
                ) {
                    Text(text = "42")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Squircle(
                    sizeInDp = 96.dp,
                    smoothing = 3.0,
                    backgroundColor = Color.Cyan
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "some image"
                    )
                    Text(
                        fontSize = 12.sp,
                        text = "Image inside"
                    )
                }
            }
        }
    }
}