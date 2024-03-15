package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.chat.chatRoute

fun NavGraphBuilder.chatNavGraph() {
    navigation(
        startDestination = Screens.ChatScreen.route,
        route = Graph.CHAT
    ) {
        chatRoute()
    }
}