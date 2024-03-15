package com.biBalance.myapplication.presentation.community

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.CommunityScreen.route

fun NavController.navigateToCommunityScreen() {
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
    }
}

fun NavGraphBuilder.communityRoute() {
    composable(ROUTE) { CommunityScreen() }
}