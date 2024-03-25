package com.biBalance.myapplication.presentation.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.activites.navigateToActivitiesScreen
import com.biBalance.myapplication.presentation.composables.BiAnimatedContentState
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.BlueBlack100
import com.biBalance.myapplication.ui.theme.BlueMedium100
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is ChatUIEffect.OnClickLevel -> {
                navController.navigateToActivitiesScreen(effect.id)
            }
        }
    }
    ChatScreenContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreenContent(state: ChatUIState, listener: ChatInteractionListener) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Scaffold(containerColor = BlueMedium100) { paddingValues ->
        BiAnimationContent(
            state = state.isLoading,
            content = {
                Box(
                    Modifier.fillMaxSize().padding(paddingValues),
//                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        contentPadding = PaddingValues(bottom = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(state.chatResponse.chatHistory) { chat ->
                            val isUser = chat.role == "user"
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = if (isUser) Arrangement.Start else Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = if (isUser) BlueBlack100 else White100),
                                    shape = RoundedCornerShape(
                                        topStart = 6.dp,
                                        topEnd = 6.dp,
                                        bottomStart = if (isUser) 0.dp else 6.dp,
                                        bottomEnd = if (isUser) 6.dp else 0.dp
                                    )
                                ) {
                                    Box {
                                        Text(
                                            text = chat.text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (isUser) White100 else Color.Black,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }

                            }
                        }
                        item {
                            BiAnimatedContentState(
                                state = state.isChatLoading,
                                content = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Card(
                                            colors = CardDefaults.cardColors(containerColor = White100),
                                            shape = RoundedCornerShape(
                                                topStart = 6.dp,
                                                topEnd = 6.dp,
                                                bottomStart = 6.dp,
                                                bottomEnd = 0.dp
                                            )
                                        ) {
                                            Box{
                                                Icon(
                                                    painter = painterResource(id = R.drawable.dots),
                                                    contentDescription = "notification",
                                                    tint = Color.Black,
                                                    modifier = Modifier.size(32.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 56.dp).align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BiTextField(
                            value = state.writing,
                            onValueChange = listener::onWritingsInputChange,
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                                onDone = { keyboardController?.hide() }
                            ),
                            modifier = Modifier.fillMaxWidth().height(60.dp).clip(RoundedCornerShape(0.dp)),
                            textColor = Color.Black,
                            placeHolder = "اكتب هنا ما تريد السؤال عنه",
                            trailingIcon = {
                                IconButton(
                                    modifier = Modifier.size(32.dp),
                                    onClick = {
                                        keyboardController?.hide()
                                        listener.onClickSend()
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.send),
                                        contentDescription = "notification",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            },
                            borderColor = Color.Transparent
                        )
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
    ChatScreen()
}