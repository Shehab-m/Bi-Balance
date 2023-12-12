package com.biBalance.myapplication.presentation.home.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BiProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.secondary,
    strokeWidth: Dp = 10.dp,
    strokeColor: Color = MaterialTheme.colorScheme.outline
) {
    Box(
        modifier = modifier.height(strokeWidth).drawBehind {
                drawRoundRect(
                    color = trackColor,
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = progressColor,
                    cornerRadius = CornerRadius(100f),
                    size = Size(size.width * progress, size.height)
                )
                drawRoundRect(
                    strokeColor,
                    style = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round),
                    cornerRadius = CornerRadius(100f)
                )
            }
    )
}

@Composable
fun AnimatedProgressBar(
    modifier: Modifier = Modifier,
    maxProgress: Int = 40,
    currentProgress: Int = 30,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = currentProgress.toFloat() / maxProgress.toFloat(),
        animationSpec = tween(1000, easing = LinearEasing)
    )
    BiProgressBar(
        progress = animatedProgress,
        strokeWidth = 15.dp,
        modifier = modifier
    )
}


@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF421069)
@Composable
fun PreviewProgress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            currentProgress = 15
        )
    }
}