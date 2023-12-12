package com.biBalance.myapplication.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ProfileUIEffect -> {}
        }
    }
    ProfileScreenContent(state, viewModel)
}


@Composable
fun ProfileScreenContent(state: ProfileUIState, listener: ProfileInteractionListener) {
    Box(Modifier.fillMaxSize().background(Color.Black)) {
        LazyColumn {
            items(100) {
                Text(text = "ejnenfinefnei", modifier = Modifier.fillMaxSize(),
                )

            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}