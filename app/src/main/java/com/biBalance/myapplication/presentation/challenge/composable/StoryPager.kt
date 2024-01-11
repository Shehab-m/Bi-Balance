package com.biBalance.myapplication.presentation.challenge.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.ui.theme.BlueBlack100
import com.biBalance.myapplication.ui.theme.GreyMedium100

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryPager(modifier: Modifier = Modifier, pagerState: PagerState, indicatorHeight: Dp = 5.dp) {
    Row(modifier = modifier) {
        for (i in 0.. pagerState.pageCount - 1) {
            val animatedProgress by animateFloatAsState(
                targetValue = if (i <= pagerState.currentPage) 1f else 0f,
                animationSpec = tween(140, easing = LinearEasing),
                label = "progress"
            )
            Box(
                modifier = Modifier.height(indicatorHeight).weight(1f)
                    .padding(horizontal = 4.dp)
                    .drawBehind {
                        drawRoundRect(
                            color = GreyMedium100,
                            cornerRadius = CornerRadius(100f)
                        )
                        drawRoundRect(
                            color = BlueBlack100,
                            cornerRadius = CornerRadius(100f),
                            size = Size(
                                size.width * animatedProgress, size.height
                            )
                        )
                    }
            )
        }
    }
}