package com.biBalance.myapplication.presentation.activity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.activity.composable.ActivityContent
import com.biBalance.myapplication.presentation.activity.composable.FinishScreen
import com.biBalance.myapplication.presentation.activity.composable.StartScreen
import com.biBalance.myapplication.presentation.activity.composable.StoryPager
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.Beige100
import com.biBalance.myapplication.ui.theme.BlueMedium100
import com.biBalance.myapplication.ui.theme.LightGreen100
import com.biBalance.myapplication.ui.theme.LightPurple100
import com.biBalance.myapplication.ui.theme.White100
import kotlinx.coroutines.launch

@Composable
fun ActivityScreen(viewModel: ActivityViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ActivityUIEffect.OnClickBack -> {
                navController.navigateUp()
            }

            ActivityUIEffect.GoToActivitiesScreen -> {
                navController.navigateUp()
            }
        }
    }
    ActivityScreenContent(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ActivityScreenContent(state: ActivityUIState, listener: ActivityInteractionListener) {
    val backgroundColor = when (state.activityStateId){
        1 -> LightGreen100
        2 -> BlueMedium100
        3 -> Beige100
        4 -> LightPurple100
        5 -> LightGreen100
        6 -> BlueMedium100
        7 -> Beige100
        8 -> LightPurple100
        9 -> LightGreen100
        10 -> BlueMedium100
        11 -> Beige100
        12 -> LightPurple100
        else -> LightGreen100
    }
    val imageId = when (state.activityStateId){
        1 -> R.drawable.character_physical
        2 -> R.drawable.character_mental
        3 -> R.drawable.character_emotional
        4 -> R.drawable.character_social
        else -> R.drawable.character_hi
    }
    val activityPainter = painterResource(id = imageId)
    Scaffold(containerColor = backgroundColor) { paddingValues ->
        BiAnimationContent(
            state = state.isLoading,
            content = {
                    StartScreen(
                        modifier = Modifier.background(backgroundColor).padding(paddingValues),
                        state = state.isStartScreenVisible && !state.isLoading,
                        title =  state.activityTitle,
                        listener = listener
                    )
                    FinishScreen(
                        modifier = Modifier.background(backgroundColor).padding(paddingValues),
                        state = state.isFinishScreenVisible,
                        title =  "لقد احرزت تقدما رائع اليوم",
                        listener = listener
                    )
                    BiAnimationContent(
                        modifier = Modifier.background(backgroundColor).padding(paddingValues),
                        state = state.isActivityContentVisible && state.isLoading,
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
                            Column(modifier = Modifier.background(backgroundColor)) {
                                val pagerState = rememberPagerState(
                                    initialPage = 0,
                                    pageCount = { state.activityDescription.count() }
                                )
                                val coroutineScope = rememberCoroutineScope()
                                StoryPager(Modifier.fillMaxWidth().padding(horizontal = 16.dp), pagerState)
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    HorizontalPager(
                                        modifier = Modifier,
                                        state = pagerState,
                                        userScrollEnabled = true,
                                    ) { page ->
                                        ActivityContent(
                                            body = state.activityDescription[page],
                                            painter = activityPainter,
                                            onClickNext = {
                                                if (page == pagerState.pageCount -1){
                                                    listener.showFinishScreen()
                                                } else {
                                                    coroutineScope.launch {
                                                        pagerState.scrollToPage(page+1)
                                                    }
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        },
                        loadingContent = { }
                    )
            },
            loadingContent = { LoadingProgress() }
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    ActivityScreen()
}