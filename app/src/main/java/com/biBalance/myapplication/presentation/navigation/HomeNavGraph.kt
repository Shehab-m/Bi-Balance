package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.home.homeRoute

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        startDestination = Screens.HomeScreen.route,
        route = Graph.HOME
    ) {
        homeRoute()
    }
}