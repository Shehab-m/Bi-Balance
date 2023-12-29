package com.biBalance.myapplication.presentation.challenges

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ChallengesScreen.route

fun NavController.navigateToChallengesScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.challengesRoute() {
    composable(ROUTE) { ChallengesScreen() }
}