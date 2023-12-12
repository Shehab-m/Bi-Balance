package com.biBalance.myapplication.presentation.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.biBalance.myapplication.presentation.base.BaseScreen

class ProfileScreen :
    BaseScreen<ProfileViewModel, ProfileUIState, ProfileUIEffect, ProfileInteractionListener>() {

    @Composable
    override fun ScreenContent(state: ProfileUIState, listener: ProfileInteractionListener) {

    }

    @Composable
    override fun Screen() {
        TODO("Not yet implemented")
    }

    override fun onEffect(effect: ProfileUIEffect, navController: NavHostController) {
        TODO("Not yet implemented")
    }

}