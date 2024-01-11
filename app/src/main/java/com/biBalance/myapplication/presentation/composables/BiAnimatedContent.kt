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
    contentState: Boolean,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
    loadingContent: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        exit = fadeOut(animationSpec = tween(durationMillis = 500))+ slideOutHorizontally(),
        enter = fadeIn(animationSpec = tween(durationMillis = 500))+ slideInHorizontally(),
    ) {
        if (contentState) {
            loadingContent()
        } else {
            Column(modifier = modifier) {
                topBar()
                content()
            }
        }
    }
//    AnimatedContent(
//        targetState = state,
//        transitionSpec = {
//            fadeIn(animationSpec = tween(150, 150)) togetherWith
//                    fadeOut(animationSpec = tween(150)) using
//                    SizeTransform { initialSize, targetSize ->
//                        if (targetState) {
//                            keyframes {
//                                IntSize(targetSize.width, initialSize.height) at 150
//                                durationMillis = 300
//                            }
//                        } else {
//                            keyframes {
//                                IntSize(initialSize.width, targetSize.height) at 150
//                                durationMillis = 300
//                            }
//                        }
//                    }
//        }
//    ) { isLoading ->
//        if (isLoading) {
//            loadingContent()
//        } else {
//            content()
//        }
//    }
}