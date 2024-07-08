package com.biBalance.myapplication.presentation.writings

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimatedContentState
import com.biBalance.myapplication.presentation.composables.BiAnimatedFab
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
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
            BiAnimatedFab(state = !state.isWritingScreenVisible, onClick = {listener.onClickAddWriting()})
            AnimatedVisibility(visible = state.isWritingScreenVisible, enter = scaleIn(), exit = scaleOut(),) {
                FloatingActionButton(
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = listener::onClickSaveWriting
                ) {
                    Text(
                        text = "Save notes",
                        style = MaterialTheme.typography.bodyMedium,
                        color = White100,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        BiAnimationContent(
            state = state.isLoading || state.isWritingScreenVisible,
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
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 38.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.notes) { note ->
                        Card(
                            modifier = Modifier.size(width = 170.dp, height = 160.dp),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            colors = CardDefaults.cardColors(containerColor = White100)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = note.notes,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = BlueBlack100,
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        end = 16.dp,
                                        top = 26.dp
                                    ).align(Alignment.TopStart),
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = note.creationDate.substring(0..9),
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
        BiAnimatedContentState(
            state = state.isWritingScreenVisible,
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(paddingValues),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { listener.onClickBackFromWriting() }, modifier = Modifier) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "icon play",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            content = {
                Column(Modifier.fillMaxSize()) {
                    BiTextField(
                        value = state.writings,
                        onValueChange = listener::onWritingsInputChange,
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        borderColor = Color.Transparent,
                        placeHolder = "Write your notes here",
                        singeLine = false
                    )
                }
            },
            loadingContent = { }
        )
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    WritingsScreen()
}