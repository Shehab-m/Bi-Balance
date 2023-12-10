package com.biBalance.myapplication.presentation.navigation

import com.biBalance.myapplication.R

sealed class BottomBarItems(
    val route: String,
    val label: Int,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
) {
    object Home : BottomBarItems(
        route = Graph.HOME,
        label = R.string.home,
        selectedIcon = R.drawable.home,
        unSelectedIcon = R.drawable.home,
    )
    object Profile : BottomBarItems(
        route = Graph.PROFILE,
        label = R.string.profile,
        selectedIcon = R.drawable.profile_circle,
        unSelectedIcon = R.drawable.profile_circle
    )
    object Chat : BottomBarItems(
        route = Graph.CHAT,
        label = R.string.chat,
        selectedIcon = R.drawable.messages,
        unSelectedIcon = R.drawable.messages
    )
    object ControlPanel : BottomBarItems(
        route = Graph.CONTROL_PANEL,
        label = R.string.control_panel,
        selectedIcon = R.drawable.activity,
        unSelectedIcon = R.drawable.activity
    )
}