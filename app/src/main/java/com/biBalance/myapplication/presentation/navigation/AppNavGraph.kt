package com.biBalance.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(startDestination: String) {
    val navController = LocalNavigationProvider.current
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginNavGraph()
        homeNavGraph()
        profileNavGraph()
        challengesNavGraph()
    }
}

object Graph {
    const val HOME = "home_graph"
    const val CHALLENGES = "challenges_graph"
    const val PROFILE = "profile_graph"
    const val CONTROL_PANEL = "control_panel_graph"
    const val CHAT = "chat_graph"
    const val LOGIN = "login_graph"
    const val SIGNUP = "signup_graph"
}