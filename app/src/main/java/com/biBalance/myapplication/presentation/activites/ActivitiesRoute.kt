package com.biBalance.myapplication.presentation.activites

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ActivitiesScreen.route

fun NavController.navigateToActivitiesScreen(activitiesId:Int) {
    navigate("$ROUTE/$activitiesId")
}

fun NavController.navigateToActivitiesScreenFromActivity(activitiesId: Int) {
    navigate("$ROUTE/$activitiesId") {
        launchSingleTop = true
        popUpTo(ROUTE) {
            inclusive = true
        }
    }

    Log.d("navigateToActivitiesScreenFromActivity: ","runnnnnnnnnnnnn")
}

fun NavGraphBuilder.activitiesRoute() {
    composable(
        route = "$ROUTE/{${ActivitiesArgs.ACTIVITIES_ID}}",
        arguments = listOf(
            navArgument(name = ActivitiesArgs.ACTIVITIES_ID) {
                NavType.IntType
            }
        )
    )  { ActivitiesScreen() }
}

class ActivitiesArgs(savedStateHandle: SavedStateHandle) {
    val activitiesId: String = checkNotNull(savedStateHandle[ACTIVITIES_ID])

    companion object {
        const val ACTIVITIES_ID = "activitiesId"
    }
}