package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.activites.activitiesRoute
import com.biBalance.myapplication.presentation.activity.activityRoute

fun NavGraphBuilder.activitiesNavGraph() {
    navigation(
        startDestination = Screens.ActivitiesScreen.route,
        route = Graph.CHALLENGES
    ) {
        activitiesRoute()
        activityRoute()
    }
}