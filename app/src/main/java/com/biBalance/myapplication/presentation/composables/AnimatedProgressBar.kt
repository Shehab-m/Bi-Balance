package com.biBalance.myapplication.presentation.composables

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.ui.theme.GreyMedium100

@Composable
fun AnimatedProgressBar(
    modifier: Modifier = Modifier,
    maxProgress: Int,
    currentProgress: Int,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = GreyMedium100,
    strokeWidth: Dp = 10.dp,
    animationDuration: Int = 1000
) {
    var animationPlayed by rememberSaveable { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed && currentProgress > 0) ((currentProgress.toFloat() / maxProgress.toFloat())) else 0f,
        animationSpec = tween(animationDuration, easing = LinearEasing), label = "progress"
    )
    LaunchedEffect(true) { animationPlayed = true }
    BiProgressBar(modifier,animatedProgress, progressColor,trackColor,strokeWidth)
}

@Composable
fun BiProgressBar(
    modifier: Modifier = Modifier,
    progressPercentage: Float,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = GreyMedium100,
    strokeWidth: Dp = 10.dp,
) {
    Box(
        modifier = modifier.height(strokeWidth)
            .drawBehind {
                drawRoundRect(
                    color = trackColor,
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = progressColor,
                    cornerRadius = CornerRadius(100f),
                    size = Size(size.width * progressPercentage, size.height)
                )
            }
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
            currentProgress = 15,
            maxProgress = 100
        )
    }
}