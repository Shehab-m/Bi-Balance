package com.biBalance.myapplication.presentation.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens("loginScreen")
    object SignupScreen : Screens("signupScreen")
    object HomeScreen : Screens("homeScreen")
    object ActivitiesScreen : Screens("activitiesScreen")
    object ActivityScreen : Screens("activityScreen")
    object ProfileScreen : Screens("profileScreen")
    object ProfileActivitiesScreen : Screens("ProfileActivitiesScreen")
    object WritingScreen : Screens("WritingScreen")
    object TodoScreen : Screens("TodoScreen")
    object PasswordScreen : Screens("PasswordScreen")
    object ChatScreen : Screens("chatScreen")
    object CommunityScreen : Screens("communityPanelScreen")
}
