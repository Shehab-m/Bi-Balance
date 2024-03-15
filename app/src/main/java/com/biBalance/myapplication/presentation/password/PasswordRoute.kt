package com.biBalance.myapplication.presentation.password

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.PasswordScreen.route

fun NavController.navigateToPasswordScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profilePasswordRoute() {
    composable(ROUTE) { PasswordScreen() }
}