package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize

@Composable
fun BiAnimationContent(
    state: Boolean,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit
) {
    AnimatedContent(
        targetState = false,
        transitionSpec = {
            fadeIn(animationSpec = tween(150, 150)) togetherWith
                    fadeOut(animationSpec = tween(150)) using
                    SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                IntSize(targetSize.width, initialSize.height) at 150
                                durationMillis = 300
                            }
                        } else {
                            keyframes {
                                IntSize(initialSize.width, targetSize.height) at 150
                                durationMillis = 300
                            }
                        }
                    }
        }, label = ""
    ) { isLoading ->
        if (isLoading) {
            loadingContent()
        } else {
            Column {
                topBar()
                content()
            }
        }
    }
}