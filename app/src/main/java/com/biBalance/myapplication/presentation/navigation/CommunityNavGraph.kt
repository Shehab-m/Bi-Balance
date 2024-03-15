package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.community.communityRoute

fun NavGraphBuilder.communityNavGraph() {
    navigation(
        startDestination = Screens.CommunityScreen.route,
        route = Graph.COMMUNITY
    ) {
        communityRoute()
    }
}