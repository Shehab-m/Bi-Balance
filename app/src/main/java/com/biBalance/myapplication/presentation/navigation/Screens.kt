package com.biBalance.myapplication.presentation.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens("loginScreen")
    object SignupScreen : Screens("signupScreen")
    object HomeScreen : Screens("homeScreen")
    object ChallengesScreen : Screens("challengesScreen")
    object ChallengeScreen : Screens("challengeScreen")
    object ProfileScreen : Screens("profileScreen")
    object ChatScreen : Screens("chatScreen")
    object ControlPanelScreen : Screens("controlPanelScreen")
}
