package com.biBalance.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph() {
    val navController = LocalNavigationProvider.current
    NavHost(
        navController = navController,
        startDestination = Graph.HOME
    ) {
        homeNavGraph()
    }
}

object Graph {
    const val HOME = "home_graph"
    const val PROFILE = "profile_graph"
    const val LOGIN = "login_graph"
    const val SIGNUP = "signup_graph"
}