package com.biBalance.myapplication.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.biBalance.myapplication.presentation.articles.profileArticlesRoute
import com.biBalance.myapplication.presentation.authentication.password.profilePasswordRoute
import com.biBalance.myapplication.presentation.profile.profileRoute
import com.biBalance.myapplication.presentation.profileActivities.profileActivitiesRoute
import com.biBalance.myapplication.presentation.todo.profileTodoRoute
import com.biBalance.myapplication.presentation.writings.profileWritingsRoute

fun NavGraphBuilder.profileNavGraph() {
    navigation(
        startDestination = Screens.ProfileScreen.route,
        route = Graph.PROFILE
    ) {
        profileRoute()
        profileActivitiesRoute()
        profileWritingsRoute()
        profileTodoRoute()
        profilePasswordRoute()
        profileArticlesRoute()
    }
}