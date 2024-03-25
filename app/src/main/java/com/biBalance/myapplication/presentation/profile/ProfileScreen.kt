package com.biBalance.myapplication.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.articles.navigateToArticlesScreen
import com.biBalance.myapplication.presentation.authentication.login.navigateToLogin
import com.biBalance.myapplication.presentation.authentication.password.navigateToPasswordScreen
import com.biBalance.myapplication.presentation.composables.AnimatedProgressBarCircular
import com.biBalance.myapplication.presentation.composables.BiAlertDialog
import com.biBalance.myapplication.presentation.composables.BiAnimatedContentState
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.Loading
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.presentation.profileActivities.navigateToProfileActivitiesScreen
import com.biBalance.myapplication.presentation.todo.navigateToTodoScreen
import com.biBalance.myapplication.presentation.writings.navigateToWritingsScreen
import com.biBalance.myapplication.ui.theme.Bink100
import com.biBalance.myapplication.util.roundToNearestHalf

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()
    LaunchedEffect(lifecycleOwner) {
        viewModel.getUserData()
    }
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ProfileUIEffect.OnClickLogout -> {
                navController.navigateToLogin()
            }

            ProfileUIEffect.OnClickActivities -> {
                navController.navigateToProfileActivitiesScreen()
            }

            ProfileUIEffect.OnClickWritings -> {
                navController.navigateToWritingsScreen()
            }

            ProfileUIEffect.OnClickTodo -> {
                navController.navigateToTodoScreen()
            }

            ProfileUIEffect.OnClickPassword -> {
                navController.navigateToPasswordScreen()
            }

            ProfileUIEffect.OnClickArticles -> {
                navController.navigateToArticlesScreen()
            }
        }
    }
    ProfileScreenContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreenContent(state: ProfileUIState, listener: ProfileInteractionListener) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    BiAnimatedContentState(state = showLogoutDialog) {
        BiAlertDialog(
            title = "Are you sure to log out?",
            confirmText = "Yes",
            onDismissButtonClick = { showLogoutDialog = false },
            onConfirmButtonClick = {
                showLogoutDialog = false
                listener.onClickLogout()
            })
    }
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = false,
            content = {
                Column {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(paddingValues),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(162.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AnimatedProgressBarCircular(
                                maxProgress = 32,
                                currentProgress = state.userData.totalScore,
                                strokeWidth = 8.dp,
                                radius = 80.dp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.profile_photo),
                                contentDescription = "profile image",
                                Modifier.size(142.dp),
                            )
                        }
                        Text(
                            text = "You have completed ${(state.userData.totalScore.toDouble() / 36f).roundToNearestHalf()}%",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top = 14.dp)
                            .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp)).shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp)
                            )
                            .padding(end = 20.dp, start = 20.dp),
                    ) {
                        Text(
                            text = state.userData.username,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 48.dp)
                        )
                        Row(modifier = Modifier.clickable { listener.onClickActivities() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ranking),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Activities",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Row(modifier = Modifier.clickable { listener.onClickTodo() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clipboard_tick),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Todo List",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Row(modifier = Modifier.clickable { listener.onClickWritings() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.book_saved),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Your Writings",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Row(modifier = Modifier.clickable { listener.onClickArticles() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.article),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Doctors Articles",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Row(modifier = Modifier.clickable { listener.onClickPassword() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 16.dp))
                        Row(modifier = Modifier.clickable { showLogoutDialog = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = "vector",
                                Modifier.size(32.dp),
                                tint = Bink100
                            )
                            Text(
                                text = "Logout",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Bink100,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            },
            loadingContent = { Loading(state = state.isLoading) }
        )
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}