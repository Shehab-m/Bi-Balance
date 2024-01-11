package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.ui.theme.GreyMedium100

@Composable
fun AnimatedProgressBarCircular(
    modifier: Modifier = Modifier,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = GreyMedium100,
    strokeWidth: Dp = 5.dp,
    radius: Dp = 50.dp,
    maxProgress: Int = 4,
    currentProgress: Int = 3,
    animationDuration: Int = 1000,
) {
    var animationPlayed by rememberSaveable { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) (360f * (currentProgress.toFloat() / maxProgress.toFloat())) else 0f,
        animationSpec = tween(animationDuration, easing = LinearEasing), label = "progress"
    )
    LaunchedEffect(true) { animationPlayed = true }
    Box(
        modifier = modifier.size(radius * 2)
    ) {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = animatedProgress,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF421069)
@Composable
fun PreviewProgressCircular() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedProgressBarCircular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            currentProgress = 15
        )
    }
}