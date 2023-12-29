package com.biBalance.myapplication.presentation.challenges

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiProgressBar
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.Beige100
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.LightGreen100
import com.biBalance.myapplication.ui.theme.LightPurple100

@Composable
fun ChallengesScreen(viewModel: ChallengesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ChallengesUIEffect.OnClickChallenge -> {}
        }
    }
    ChallengesScreenContent(state, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChallengesScreenContent(state: ChallengesUIState, listener: ChallengesInteractionListener) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = true,
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
                                IconButton(modifier = Modifier.size(24.dp),onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.notification),
                                        contentDescription = "notification",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Shady Alaa",
                                        style = MaterialTheme.typography.labelMedium,
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
                                Text(
                                    text = "${75}%",
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
                            BiProgressBar(
                                progressPercentage = 3/4f,
                                modifier = Modifier.fillMaxWidth(),
                            )
                            Text(
                                text = stringResource(R.string.challenges_text),
                                textAlign = TextAlign.End,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.fillMaxWidth().padding(top = 48.dp)
                            )
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Icon(
                                    painter = painterResource(id = R.drawable.group_873),
                                    contentDescription = "hi",
                                    modifier = Modifier.size(170.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                    items(4) { index ->
                        val colors = listOf(LightBlue100, Beige100, LightPurple100, LightGreen100)
                        val colorIndex = (index % colors.size)
                        val selectedColor = colors[colorIndex]
                        BiCardChallenge(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Emotional",
                            backgroundColor = selectedColor,
                            onClick = { listener.onClickLevel(1) },
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
    ChallengesScreen()
}