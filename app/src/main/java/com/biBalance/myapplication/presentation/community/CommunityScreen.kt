package com.biBalance.myapplication.presentation.community

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.activites.navigateToActivitiesScreen
import com.biBalance.myapplication.presentation.composables.BiAnimatedContentState
import com.biBalance.myapplication.presentation.composables.BiAnimatedFab
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.BlueMedium37
import com.biBalance.myapplication.ui.theme.LightGrey100
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun CommunityScreen(viewModel: CommunityViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is CommunityUIEffect.OnClickLevel -> {
                navController.navigateToActivitiesScreen(effect.id)
            }
        }
    }
    CommunityScreenContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun CommunityScreenContent(state: CommunityUIState, listener: CommunityInteractionListener) {
    Scaffold(
        floatingActionButton = {
            Box(modifier = Modifier.padding(bottom = 65.dp)) {
                BiAnimatedFab(state = !state.isWritingScreenVisible, onClick = { listener.onClickAddWriting() })
                AnimatedVisibility(
                    visible = state.isWritingScreenVisible,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
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
        }
    ) { paddingValues ->
        BiAnimationContent(
            state = state.isLoading,
            content = {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 20.dp, bottom = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = state.userName,
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
                        }
                    }
//                    val posts = listOf(
//                        Post(1, "Dr/Osama", "this is a post by doctor osama", 53),
//                        Post(2, "Dr/Sara", "this is a post by doctor Sara", 32),
//                        Post(3, "Dr/Shady", "this is a post by doctor Shady", 12),
//                        Post(4, "Dr/Ali", "this is a post by doctor Ali", 2),
//                    )
                    items(state.posts) { post ->
                        val postLikes = remember { mutableStateOf(post.likesCount) }
                        val isPostLiked = remember { mutableStateOf(false) }
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            colors = CardDefaults.cardColors(containerColor = White100)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = post.body,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                                )
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                        .padding(top = 8.dp, bottom = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.heart),
                                        contentDescription = "profile image",
                                        Modifier.size(10.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = "${postLikes.value} likes",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                                Divider(
                                    modifier = Modifier.fillMaxWidth(),
                                    thickness = 1.dp,
                                    color = LightGrey100
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                        .background(
                                            color = if (!isPostLiked.value) {
                                                White100
                                            } else {
                                                BlueMedium37
                                            }
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                        .clickable {
                                            listener.onClickPostLike(post.id, isPostLiked.value)
                                            if (!isPostLiked.value) {
                                                postLikes.value++
                                                isPostLiked.value = !isPostLiked.value
                                            } else {
                                                postLikes.value--
                                                isPostLiked.value = !isPostLiked.value
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Like",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.character_like),
                                        contentDescription = "image",
                                        Modifier.size(40.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            },
            loadingContent = { LoadingProgress() }
        )
        BiAnimatedContentState(
            state = state.isWritingScreenVisible,
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth().background(White100).padding(paddingValues),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { listener.onClickBackFromWriting() },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "icon play",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            content = {
                Column(Modifier.fillMaxSize().background(White100)) {
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
fun HomeScreenPreview() {
    CommunityScreen()
}