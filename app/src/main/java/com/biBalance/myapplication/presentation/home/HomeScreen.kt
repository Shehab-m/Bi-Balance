package com.biBalance.myapplication.presentation.home

import androidx.compose.runtime.Composable
import com.biBalance.myapplication.presentation.base.BaseScreen

class HomeScreen :
    BaseScreen<HomeViewModel, HomeUIState, HomeUIEffect, HomeInteractionListener>() {

    @Composable
    override fun onRender(state: HomeUIState, listener: HomeInteractionListener) {

    }

}