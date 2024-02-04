package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.authentication.login.loginRoute

fun NavGraphBuilder.loginNavGraph() {
    navigation(
        startDestination = Screens.LoginScreen.route,
        route = Graph.LOGIN
    ) {
        loginRoute()
    }
}