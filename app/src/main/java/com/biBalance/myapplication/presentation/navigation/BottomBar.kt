package com.biBalance.myapplication.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.biBalance.myapplication.presentation.composables.BiNavigationBar
import com.biBalance.myapplication.presentation.composables.BiNavigationBarItem
import com.biBalance.myapplication.presentation.composables.exitinstion.drawTopIndicator
import com.biBalance.myapplication.presentation.composables.exitinstion.toPx

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val navController = LocalNavigationProvider.current
    val screens = listOf(
        BottomBarItems.Home,
        BottomBarItems.Chat,
        BottomBarItems.ControlPanel,
        BottomBarItems.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var xIndicatorOffset by remember { mutableStateOf(Float.NaN) }
    val xOffsetAnimated by animateFloatAsState(targetValue = xIndicatorOffset)
    val indicatorWidthPx = 40.dp.toPx()
    val iconSizePx = 24.dp.toPx()
    val bottomNavState = navBackStackEntry?.let { checkBottomBarState(it) }
    AnimatedVisibility(
        visible = (bottomNavState != null && bottomNavState.value),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BiNavigationBar(
                modifier = modifier.drawTopIndicator(xOffsetAnimated).background(MaterialTheme.colorScheme.background)
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                screens.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    val icon = if (selected) screen.selectedIcon else screen.unSelectedIcon
                    BiNavigationBarItem(
                        icon = { color ->
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = null,
                                tint = color,
                                modifier = Modifier
                                    .size(24.dp)
                                    .onGloballyPositioned {
                                        if (selected) {
                                            xIndicatorOffset =
                                                it.positionInRoot().x + (iconSizePx - indicatorWidthPx) / 2
                                        }
                                    }
                            )
                        },
                        label = { Text(text = stringResource(screen.label), style = it) },
                        selected = selected,
                        onClick = { onClickBottomNavItem(navController, screen) },
                    )
                }
            }
        }
    )
}

@Composable
private fun checkBottomBarState(navBackStackEntry: NavBackStackEntry): MutableState<Boolean> {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val bottomBarScreens = listOf(
        Screens.HomeScreen.route,
        Screens.ChallengesScreen.route,
        Screens.ProfileScreen.route,
        Screens.ChatScreen.route,
        Screens.ControlPanelScreen.route,
    )
    when (navBackStackEntry.destination.route) {
        in bottomBarScreens -> {
            bottomBarState.value = true
        }

        else -> {
            bottomBarState.value = false
        }
    }
    return bottomBarState
}


fun onClickBottomNavItem(navController: NavHostController, screen: BottomBarItems) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    when (screen) {
        BottomBarItems.Home -> {
            navController.popBackStack(Screens.HomeScreen.route, false)
        }

        BottomBarItems.Chat -> {
            navController.popBackStack(Screens.ChatScreen.route, false)
        }

        BottomBarItems.ControlPanel -> {
            navController.popBackStack(
                Screens.ControlPanelScreen.route,
                false
            )
        }

        BottomBarItems.Profile -> {
            navController.popBackStack(Screens.ProfileScreen.route, false)
        }
    }
}