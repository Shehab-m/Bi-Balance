package com.biBalance.myapplication.presentation.chat

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.presentation.activites.navigateToActivitiesScreen
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ChatUIEffect.OnClickLevel -> {
                navController.navigateToActivitiesScreen(effect.id)
            }
        }
    }
    ChatScreenContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreenContent(state: ChatUIState, listener: ChatInteractionListener) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = false,
            content = {

            },
            loadingContent = { LoadingProgress() }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ChatScreen()
}