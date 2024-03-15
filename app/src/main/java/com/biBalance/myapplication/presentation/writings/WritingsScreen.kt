package com.biBalance.myapplication.presentation.writings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimatedFab
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.Loading
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.BlueBlack100
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun WritingsScreen(viewModel: WritingsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is WritingsUIEffect.OnClickBack -> {
                navController.navigateUp()
            }
        }
    }
    WritingsContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WritingsContent(
    state: WritingsUIState,
    listener: WritingsInteractionListener
) {
    Scaffold(
        floatingActionButton = {
            BiAnimatedFab(state = true, onClick = {},)
        }
    ) { paddingValues ->
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
                    Row(modifier = Modifier.padding(end = 16.dp)) {
                        Text(
                            text = "Your Writings",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.book_saved),
                            contentDescription = "vector",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            content = {
                val writings = listOf(
                    "Things I am grateful for",
                    "Things I want to do in the future",
                    "notes i am saving for to share with others",
                )
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 38.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(writings) { index, writing ->
                        Card(
                            modifier = Modifier.size(width = 170.dp, height = 160.dp),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            colors = CardDefaults.cardColors(containerColor = White100)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = writing,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = BlueBlack100,
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        end = 16.dp,
                                        top = 26.dp
                                    )
                                        .align(Alignment.TopStart),
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "2023-3-10",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.book_mark),
                                        contentDescription = "vector",
                                        modifier = Modifier.size(16.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
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
    WritingsScreen()
}