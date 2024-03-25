package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.Marron70
import com.biBalance.myapplication.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiCardLevel(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit = {},
    isActive: Boolean = true,
    score: Int
) {
    if (isActive) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(MaterialTheme.dimens.biCardRadius),
            colors = CardDefaults.cardColors(backgroundColor),
            onClick = { onClick() }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title, style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
                    textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primary
                )
                Box(
                    modifier = Modifier.size(42.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedProgressBarCircular(
                        maxProgress = 4,
                        currentProgress = score,
                        strokeWidth = 2.dp
                    )
                    Card(
                        modifier = Modifier.size(35.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            var percentage by rememberSaveable { mutableStateOf(0f) }
                            val animatedProgress by animateFloatAsState(
                                targetValue = percentage,
                                animationSpec = tween(1000, easing = LinearEasing),
                                label = "progress"
                            )
                            LaunchedEffect(key1 = score) {
                                percentage = if (score >0 ) score.toFloat() / 4f * 100f else 0f
                            }
                            Text(
                                text = "${animatedProgress.toInt()}%",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    } else {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(MaterialTheme.dimens.biCardRadius),
            colors = CardDefaults.cardColors(backgroundColor),
        ) {
            Column(
                modifier = Modifier.background(Marron70),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title, style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
                    textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primary
                )
                Box(
                    modifier = Modifier.size(42.dp),
                )
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }

}

@Preview
@Composable
fun BiCardPreview() {
    BiCardLevel(title = "Level One", backgroundColor = LightBlue100, score = 1)
}