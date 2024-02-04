package com.biBalance.myapplication.presentation.challenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.challenge.composable.StartScreen
import com.biBalance.myapplication.presentation.challenge.composable.StoryPager
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.BlueMedium100
import com.biBalance.myapplication.ui.theme.White100
import kotlinx.coroutines.launch

@Composable
fun ChallengeScreen(viewModel: ChallengeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ChallengeUIEffect.OnClickBack -> {
                navController.navigateUp()
            }
        }
    }
    ChallengeScreenContent(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChallengeScreenContent(state: ChallengeUIState, listener: ChallengeInteractionListener) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().background(BlueMedium100)) {
            StartScreen(
                modifier = Modifier.background(BlueMedium100).padding(paddingValues),
                state = state.isStartScreenVisible,
                title = "hfifeinenfen",
                body = "iejijfiejfvregr g egergergeg ergerg erg  eg erg eg er g",
                listener = listener
            )
            BiAnimationContent(
                modifier = Modifier.background(BlueMedium100).padding(paddingValues),
                state = state.isStartScreenVisible,
                topBar = {
                    Row(modifier = Modifier.padding(top = 20.dp, bottom = 24.dp)) {
                        IconButton(onClick = { listener.onClickBack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "icon play",
                                tint = White100
                            )
                        }
                    }
                },
                content = {
                    Column(modifier = Modifier.background(BlueMedium100)) {
                        val pagerState = rememberPagerState(
                            initialPage = 0,
                            pageCount = { 3 }
                        )
                        val coroutineScope = rememberCoroutineScope()
                        StoryPager(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp), pagerState
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            HorizontalPager(
                                modifier = Modifier,
                                state = pagerState,
                                userScrollEnabled = true,
                            ) { page ->
                                Column {
                                    Text(
                                        text = page.toString(),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 48.dp)
                                    )
                                    Card(
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                                        onClick = {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(page = page + 1)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.play),
                                            contentDescription = "icon play",
                                            modifier = Modifier.padding(11.dp)
                                        )
                                    }
                                }

                            }
                        }
                    }
                },
                loadingContent = {}
            )
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    ChallengeScreen()
}