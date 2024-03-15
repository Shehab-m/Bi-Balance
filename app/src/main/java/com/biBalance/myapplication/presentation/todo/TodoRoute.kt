package com.biBalance.myapplication.presentation.todo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.TodoScreen.route

fun NavController.navigateToTodoScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.profileTodoRoute() {
    composable(ROUTE) { TodoScreen() }
}