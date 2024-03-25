package com.biBalance.myapplication.presentation.articles

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.biBalance.myapplication.presentation.navigation.Screens

private val ROUTE = Screens.ArticlesScreen.route

fun NavController.navigateToArticlesScreen() {
    navigate(ROUTE){

    }
}

fun NavGraphBuilder.profileArticlesRoute() {
    composable(ROUTE) { ArticlesScreen() }
}