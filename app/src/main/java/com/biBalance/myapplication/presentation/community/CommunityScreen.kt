package com.biBalance.myapplication.presentation.community

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.biBalance.myapplication.data.source.remote.model.Post
import com.biBalance.myapplication.presentation.activites.navigateToActivitiesScreen
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityScreenContent(state: CommunityUIState, listener: CommunityInteractionListener) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = false,
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
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(modifier = Modifier.size(24.dp), onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.notification),
                                        contentDescription = "notification",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
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
                    val posts = listOf(
                        Post(1, "Dr/Osama", "this is a post by doctor osama", 53),
                        Post(2, "Dr/Sara", "this is a post by doctor Sara", 32),
                        Post(3, "Dr/Shady", "this is a post by doctor Shady", 12),
                        Post(4, "Dr/Ali", "this is a post by doctor Ali", 2),
                    )
                    items(posts) {post ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            colors = CardDefaults.cardColors(containerColor = White100)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.profile_photo),
                                        contentDescription = "profile image",
                                        Modifier.size(30.dp)
                                    )
                                    Text(
                                        text = post.name,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                                Text(
                                    text = post.writing,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                        .padding(top = 16.dp, bottom = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.heart),
                                        contentDescription = "profile image",
                                        Modifier.size(10.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = "${post.likesCount} likes",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                                Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = LightGrey100)
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)
                                        .clickable {  },
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
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CommunityScreen()
}