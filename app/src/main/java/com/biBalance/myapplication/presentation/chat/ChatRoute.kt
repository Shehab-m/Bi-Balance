package com.biBalance.myapplication.presentation.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ChatScreen.route

fun NavController.navigateToChatScreen() {
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
    }
}

fun NavGraphBuilder.chatRoute() {
    composable(ROUTE) { ChatScreen() }
}