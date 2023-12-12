package com.biBalance.myapplication.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.biBalance.myapplication.presentation.base.BaseScreen

class HomeScreen :
    BaseScreen<HomeViewModel, HomeUIState, HomeUIEffect, HomeInteractionListener>() {

    @Composable
    override fun Screen() {
        initScreen(viewModel = hiltViewModel())
    }

    override fun onEffect(effect: HomeUIEffect, navController: NavHostController) {
        when(effect){
            is HomeUIEffect.OnClickLevel -> {}
        }
    }

    @Composable
    override fun ScreenContent(state: HomeUIState, listener: HomeInteractionListener) {
        Text(text = "ejnenfinefnei", modifier = Modifier.fillMaxSize())
    }

}

@Preview
@Composable
fun HomeScreenPreview() {

}