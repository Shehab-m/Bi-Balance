package com.biBalance.myapplication.presentation.profileActivities

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ProfileActivitiesScreen.route

fun NavController.navigateToProfileActivitiesScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileActivitiesRoute() {
    composable(ROUTE) { ProfileActivitiesScreen() }
}