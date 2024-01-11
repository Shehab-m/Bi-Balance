package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.challenge.challengeRoute
import com.biBalance.myapplication.presentation.challenges.challengesRoute

fun NavGraphBuilder.challengesNavGraph() {
    navigation(
        startDestination = Screens.ChallengesScreen.route,
        route = Graph.CHALLENGES
    ) {
        challengesRoute()
        challengeRoute()
    }
}