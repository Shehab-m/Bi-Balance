package com.biBalance.myapplication.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.HomeScreen.route

fun NavController.navigateToHomeScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.homeRoute() {
    composable(ROUTE) { HomeScreen() }
}