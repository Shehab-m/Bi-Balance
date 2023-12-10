package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.profile.profileRoute

fun NavGraphBuilder.profileNavGraph() {
    navigation(
        startDestination = Screens.ProfileScreen.route,
        route = Graph.PROFILE
    ) {
        profileRoute()
    }
}