package com.biBalance.myapplication.presentation.articles

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.data.source.remote.model.Post
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiBackButton
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ArticlesUIEffect.OnClickBack -> {
                navController.navigateUp()
            }
        }
    }
    ArticlesScreenContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticlesScreenContent(state: ArticlesUIState, listener: ArticlesInteractionListener) {
    Scaffold { paddingValues ->
        BiAnimationContent(
            state = false,
            content = {
                Column(Modifier.padding(paddingValues)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BiBackButton(listener::onClickBack,Modifier.padding(end = 20.dp))
                        Row(
                            modifier = Modifier.padding(end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    val posts = listOf(
                        Post(
                            1,
                            "Dr/Osama",
                            "Maintain a regular schedule for sleeping and waking.",
                            53
                        ),
                        Post(
                            2,
                            "Dr/Sara",
                            "Engage in regular physical activity for at least 30 minutes daily.",
                            32
                        ),
                        Post(
                            3,
                            "Dr/Shady",
                            "Practice relaxation techniques such as deep breathing or meditation.",
                            12
                        ),
                        Post(
                            4,
                            "Dr/Ali",
                            "Stay connected with supportive friends and family members.",
                            2
                        ),
                        Post(
                            5,
                            "Dr/Maya",
                            "Keep a mood journal to track emotions and identify triggers.",
                            25
                        ),
                        Post(
                            6,
                            "Dr/Ahmed",
                            "Limit caffeine and alcohol consumption to stabilize mood.",
                            40
                        ),
                        Post(
                            7,
                            "Dr/Layla",
                            "Ensure a balanced diet rich in fruits, vegetables, and whole grains.",
                            38
                        ),
                        Post(
                            8,
                            "Dr/Hassan",
                            "Seek professional help if experiencing persistent mood swings or depressive episodes.",
                            47
                        )
                    )
                    items(posts) { post ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            elevation = CardDefaults.cardElevation(3.dp),
                            colors = CardDefaults.cardColors(containerColor = White100)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp,
                                        vertical = 16.dp
                                    ),
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
                                        .padding(bottom = 16.dp)
                                )
                            }
                        }
                    }
                }
            },
            loadingContent = { LoadingProgress() }
        )
    }
}