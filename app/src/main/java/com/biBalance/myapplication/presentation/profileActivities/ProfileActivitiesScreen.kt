package com.biBalance.myapplication.presentation.profileActivities

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiCardActivity
import com.biBalance.myapplication.presentation.composables.Loading
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.Beige100
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.LightGreen100
import com.biBalance.myapplication.ui.theme.LightGrey100
import com.biBalance.myapplication.ui.theme.LightPurple100
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun ProfileActivitiesScreen(viewModel: ProfileActivitiesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ProfileActivitiesUIEffect.OnClickBack -> {
                navController.navigateUp()
            }
        }
    }
    ProfileActivitiesContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileActivitiesContent(
    state: ProfileActivitiesUIState,
    listener: ProfileActivitiesInteractionListener
) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = state.isLoading,
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(paddingValues),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { listener.onClickBack() }, modifier = Modifier) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "icon play",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Activities",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                    )
                    IconButton(onClick = { }, modifier = Modifier) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "icon play",
                            tint = White100
                        )
                    }
                }
            },
            content = {
                Column(modifier = Modifier.padding(top = 42.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.stars),
                        contentDescription = "hi",
                        modifier = Modifier.size(120.dp).scale(5.5f).fillMaxWidth()
                            .padding(start = 55.dp, bottom = 16.dp, top = 0.dp),
                        tint = LightGrey100
                    )
                    val totalScore =
                        state.scores.physicalScore + state.scores.mentalScore + state.scores.socialScore + state.scores.emotionalScore
                    Text(
                        text = "You have finished $totalScore Activity to get better and improve",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 32.dp, end = 20.dp, start = 20.dp),
                        textAlign = TextAlign.Center
                    )
                }
                val activities = listOf(
                    LevelActivities.LevelActivity(1, "${state.scores.physicalScore} Physical"),
                    LevelActivities.LevelActivity(2, "${state.scores.mentalScore} Mental"),
                    LevelActivities.LevelActivity(3, "${state.scores.emotionalScore} Emotional"),
                    LevelActivities.LevelActivity(4, "${state.scores.socialScore} Social"),
                )
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(activities) { index, activity ->
                        val colors = listOf(LightGreen100, LightBlue100, Beige100, LightPurple100)
                        val colorIndex = (index % colors.size)
                        val selectedColor = colors[colorIndex]
                        BiCardActivity(
                            modifier = Modifier.padding(top = 16.dp),
                            title = activity.typeName,
                            backgroundColor = selectedColor,
                            onClick = { },
                        )
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
    ProfileActivitiesScreen()
}