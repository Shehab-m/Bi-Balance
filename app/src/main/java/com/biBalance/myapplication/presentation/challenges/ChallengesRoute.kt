package com.biBalance.myapplication.presentation.challenges

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ChallengesScreen.route

fun NavController.navigateToChallengesScreen(activitiesId:Int) {
    navigate("$ROUTE/$activitiesId")
}

fun NavGraphBuilder.challengesRoute() {
    composable(
        route = "$ROUTE/{${ActivitiesArgs.ACTIVITIES_ID}}",
        arguments = listOf(
            navArgument(name = ActivitiesArgs.ACTIVITIES_ID) {
                NavType.IntType
            }
        )
    )  { ChallengesScreen() }
}

class ActivitiesArgs(savedStateHandle: SavedStateHandle) {
    val activitiesId: String = checkNotNull(savedStateHandle[ACTIVITIES_ID])

    companion object {
        const val ACTIVITIES_ID = "activitiesId"
    }
}