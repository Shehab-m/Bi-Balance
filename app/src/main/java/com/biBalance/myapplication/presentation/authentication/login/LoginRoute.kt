package com.biBalance.myapplication.presentation.authentication.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.LoginScreen.route

fun NavController.navigateToLogin() {
    navigate(ROUTE)
}

fun NavGraphBuilder.loginRoute() {
    composable(ROUTE) { LoginScreen() }
}