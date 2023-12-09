package com.biBalance.myapplication.presentation.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens("loginScreen")
    object SignupScreen : Screens("signupScreen")
    object HomeScreen : Screens("homeScreen")
}
