package com.biBalance.myapplication.presentation.writings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.WritingScreen.route

fun NavController.navigateToWritingsScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileWritingsRoute() {
    composable(ROUTE) { WritingsScreen() }
}