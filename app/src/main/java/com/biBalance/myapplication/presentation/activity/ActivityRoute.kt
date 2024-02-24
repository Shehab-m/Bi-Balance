package com.biBalance.myapplication.presentation.activity

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ActivityScreen.route

fun NavController.navigateToActivityScreen(activityId:Int) {
    navigate("${ROUTE}/$activityId")
}

fun NavGraphBuilder.activityRoute() {
    composable(
        route = "${ROUTE}/{${ActivityArgs.ACTIVITY_ID}}",
        arguments = listOf(
            navArgument(name = ActivityArgs.ACTIVITY_ID) {
                NavType.IntType
            }
        )
    )  { ActivityScreen() }
}

class ActivityArgs(savedStateHandle: SavedStateHandle) {
    val activityId: String = checkNotNull(savedStateHandle[ACTIVITY_ID])

    companion object {
        const val ACTIVITY_ID = "activityId"
    }
}