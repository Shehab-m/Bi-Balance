package com.biBalance.myapplication.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopShadow(alpha: Float = 0.07f, height: Dp = 3.dp) {
    Box(modifier = Modifier
        .fillMaxWidth().height(height).background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Black.copy(alpha = alpha),
                ),
                tileMode = TileMode.Decal
            )
        )
    )
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF421069)
@Composable
fun TopShadowPreview() {
    TopShadow()
}