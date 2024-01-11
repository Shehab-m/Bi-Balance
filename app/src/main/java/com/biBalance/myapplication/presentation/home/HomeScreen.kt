package com.biBalance.myapplication.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.challenges.navigateToChallengesScreen
import com.biBalance.myapplication.presentation.composables.AnimatedProgressBar
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiCardLevel
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.Beige100
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.LightGreen100
import com.biBalance.myapplication.ui.theme.LightPurple100

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is HomeUIEffect.OnClickLevel -> {
                navController.navigateToChallengesScreen()
            }
        }
    }
    HomeScreenContent(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenContent(state: HomeUIState, listener: HomeInteractionListener) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            contentState = false,
            content = {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(modifier = Modifier.size(24.dp), onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.notification),
                                        contentDescription = "notification",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Shady Alaa",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.profile_photo),
                                        contentDescription = "profile image",
                                        Modifier.size(30.dp)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                var percentage by rememberSaveable { mutableStateOf(0f) }
                                val animatedProgress by animateFloatAsState(
                                    targetValue = percentage,
                                    animationSpec = tween(1000, easing = LinearEasing),
                                    label = "progress"
                                )
                                LaunchedEffect(key1 = true) {
                                    percentage = 3f / 4f * 100f
                                }
                                Text(
                                    text = "${animatedProgress.toInt()}%",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Progress",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            AnimatedProgressBar(
                                maxProgress = 40, currentProgress = 30,
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Text(
                                text = stringResource(R.string.welcome_text),
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 48.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.group_873),
                                    contentDescription = "hi",
                                    modifier = Modifier.size(170.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                        }
                    }
                    items(10) { index ->
                        val colors = listOf(LightBlue100, Beige100, LightPurple100, LightGreen100)
                        val colorIndex = (index % colors.size)
                        val selectedColor = colors[colorIndex]
                        BiCardLevel(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Level One",
                            backgroundColor = selectedColor,
                            onClick = { listener.onClickLevel(1) },
                            isActive = colorIndex == 0
                        )
                    }
                }
            },
            loadingContent = {}
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}