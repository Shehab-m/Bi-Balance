package com.biBalance.myapplication.presentation.challenge

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ChallengeScreen.route

fun NavController.navigateToChallengeScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.challengeRoute() {
    composable(ROUTE) { ChallengeScreen() }
}