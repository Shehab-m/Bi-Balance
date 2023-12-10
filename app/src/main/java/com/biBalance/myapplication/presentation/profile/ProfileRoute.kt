package com.biBalance.myapplication.presentation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ProfileScreen.route

fun NavController.navigateToHomeScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileRoute() {
    composable(ROUTE) { ProfileScreen() }
}