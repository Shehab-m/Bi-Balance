package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BiAnimationContent(
    modifier: Modifier = Modifier,
    state: Boolean,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        exit = fadeOut(animationSpec = tween(durationMillis = 500))+ slideOutHorizontally(),
        enter = fadeIn(animationSpec = tween(durationMillis = 500))+ slideInHorizontally(),
    ) {
        if (state) {
            loadingContent()
        } else {
            Column(modifier = modifier) {
                topBar()
                content()
            }
        }
    }
}