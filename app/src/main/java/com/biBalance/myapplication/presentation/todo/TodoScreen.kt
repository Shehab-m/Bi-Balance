package com.biBalance.myapplication.presentation.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
import com.biBalance.myapplication.presentation.composables.Loading
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.ui.theme.Bink100
import com.biBalance.myapplication.ui.theme.DarkGrey100
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.Purple100

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            is TodoUIEffect.OnClickBack -> {
                navController.navigateUp()
            }

            TodoUIEffect.OnClickSaveTasks -> {
                navController.navigateUp()
            }
        }
    }
    TodoContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun TodoContent(
    state: TodoUIState,
    listener: TodoInteractionListener
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Scaffold() { paddingValues ->
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
                            text = "2024-3-13",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "vector",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 64.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TodoCard(
                        "Goal",
                        painterResource(id = R.drawable.arrow),
                        Bink100,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    TodoCard(
                        "Meds",
                        painterResource(id = R.drawable.meds),
                        LightBlue100,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    TodoCard(
                        "Activity",
                        painterResource(id = R.drawable.ball),
                        DarkGrey100,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    TodoCard(
                        "Event",
                        painterResource(id = R.drawable.calendar_img),
                        Purple100,
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    val checkedStateGoal = mutableStateOf(false)
                    BiTextField(
                        value = state.goalState,
                        onValueChange = listener::onGoalInputChange,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) },
                            onDone = { keyboardController?.hide() }
                        ),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp).shadow(2.dp),
                        borderColor = Color.Transparent,
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.arrow),
                                contentDescription = "vector",
                                modifier = Modifier.size(38.dp),
                            )
                        },
                        trailingIcon = {
                            Box(
                                modifier = Modifier.size(24.dp).clip(CircleShape)
                            ) {
                                Checkbox(
                                    checked = checkedStateGoal.value,
                                    onCheckedChange = { checkedStateGoal.value = !checkedStateGoal.value },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                )
                            }
                        },
                        placeHolder = "Write your goal here"
                    )
                    val checkedStateMed = mutableStateOf(false)
                    BiTextField(
                        value = state.medsState,
                        onValueChange = listener::onMedsInputChange,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) },
                            onDone = { keyboardController?.hide() }
                        ),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp).shadow(2.dp),
                        borderColor = Color.Transparent,
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.meds),
                                contentDescription = "vector",
                                modifier = Modifier.size(38.dp),
                            )
                        },
                        trailingIcon = {
                            Box(
                                modifier = Modifier.size(24.dp).clip(CircleShape)
                            ) {
                                Checkbox(
                                    checked = checkedStateMed.value,
                                    onCheckedChange = { checkedStateMed.value = !checkedStateMed.value },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                )
                            }
                        },
                        placeHolder = "Write your goal here"
                    )
                    val checkedStateActivity = mutableStateOf(false)
                    BiTextField(
                        value = state.activityState,
                        onValueChange = listener::onActivityChange,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) },
                            onDone = { keyboardController?.hide() }
                        ),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp).shadow(2.dp),
                        borderColor = Color.Transparent,
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.ball),
                                contentDescription = "vector",
                                modifier = Modifier.size(38.dp),
                            )
                        },
                        trailingIcon = {
                            Box(
                                modifier = Modifier.size(24.dp).clip(CircleShape)
                            ) {
                                Checkbox(
                                    checked = checkedStateActivity.value,
                                    onCheckedChange = { checkedStateActivity.value = !checkedStateActivity.value },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                )
                            }
                        },
                        placeHolder = "Write your goal here"
                    )
                    val checkedStateEvent = mutableStateOf(false)
                    BiTextField(
                        value = state.eventState,
                        onValueChange = listener::onEventInputChange,
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) },
                            onDone = { keyboardController?.hide() }
                        ),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp).shadow(2.dp),
                        borderColor = Color.Transparent,
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.calendar_img),
                                contentDescription = "vector",
                                modifier = Modifier.size(38.dp),
                            )
                        },
                        trailingIcon = {
                            Box(
                                modifier = Modifier.size(24.dp).clip(CircleShape)
                            ) {
                                Checkbox(
                                    checked = checkedStateEvent.value,
                                    onCheckedChange = { checkedStateEvent.value = !checkedStateEvent.value },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                )
                            }
                        },
                        placeHolder = "Write your goal here"
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth().height(64.dp),
                        onClick = listener::onClickSaveTasks,
                        shape =  RoundedCornerShape(4.dp),
                        enabled = true
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(30.dp),
                                color = MaterialTheme.colorScheme.primary, strokeWidth = 3.dp)
                        } else {
                            Text(
                                text = "Save Tasks",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.background),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            },
            loadingContent = { Loading(state = state.isLoading) }
        )
    }

}

@Composable
fun TodoCard(title: String, painter: Painter, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.size(width = 84.dp, height = 32.dp),
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp)
            )
            Image(
                painter = painter,
                contentDescription = "vector",
                modifier = Modifier.size(22.dp),
            )
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    TodoScreen()
}