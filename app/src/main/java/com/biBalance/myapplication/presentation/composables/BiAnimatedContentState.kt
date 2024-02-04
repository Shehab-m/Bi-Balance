package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BiAnimatedContentState(
    state: Boolean,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = state,
        exit = fadeOut(animationSpec = tween(durationMillis = 500)) + slideOutHorizontally(),
        enter = fadeIn(animationSpec = tween(durationMillis = 500)) + slideInHorizontally(),
    ) {
        if (state) {
            Box(modifier = modifier) {
                content()
            }
        } else {
            loadingContent()
        }
    }
}