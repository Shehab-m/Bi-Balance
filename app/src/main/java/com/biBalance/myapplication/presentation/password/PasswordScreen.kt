package com.biBalance.myapplication.presentation.password

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
import com.biBalance.myapplication.presentation.composables.Loading
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler

@Composable
fun PasswordScreen(viewModel: PasswordViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    EventHandler(viewModel.effect) { effect, navController ->
        when (effect) {
            PasswordUIEffect.OnClickBack -> {
                navController.navigateUp()
            }
            PasswordUIEffect.ShowToastEffect -> {
                Log.d( "checkIfUserLoggedIn: ", "toast")
                Toast.makeText(context, state.validationToast.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    PasswordContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun PasswordContent(
    state: PasswordUIState,
    listener: PasswordInteractionListener
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
                            text = "Change Password",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = "vector",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(46.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = "user",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp).padding(end = 8.dp)
                            )
                            Text(
                                text = "Old Password",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
                                textAlign = TextAlign.Center,
                            )
                        }
                        var showPassword by remember { mutableStateOf(false) }
                        val passwordIcon =
                            if (showPassword) R.drawable.eye else R.drawable.closeeye
                        BiTextField(
                            value = state.oldPasswordState.value,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                                onDone = { keyboardController?.hide() }
                            ),
                            onValueChange = listener::onOldPasswordInputChanged,
                            errorMessage = state.oldPasswordState.errorMessage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp),
                            trailingIcon = {
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(
                                        painter = painterResource(id = passwordIcon),
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = "user",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp).padding(end = 8.dp)
                            )
                            Text(
                                text = "New Password",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
                                textAlign = TextAlign.Center,
                            )
                        }
                        var showPassword by remember { mutableStateOf(false) }
                        val passwordIcon =
                            if (showPassword) R.drawable.eye else R.drawable.closeeye
                        BiTextField(
                            value = state.newPasswordState.value,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                                onDone = { keyboardController?.hide() }
                            ),
                            onValueChange = listener::onNewPasswordInputChanged,
                            errorMessage = state.newPasswordState.errorMessage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp),
                            trailingIcon = {
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(
                                        painter = painterResource(id = passwordIcon),
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = "user",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp).padding(end = 8.dp)
                            )
                            Text(
                                text = "Re-enter Password",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
                                textAlign = TextAlign.Center,
                            )
                        }
                        var showPassword by remember { mutableStateOf(false) }
                        val passwordIcon =
                            if (showPassword) R.drawable.eye else R.drawable.closeeye
                        BiTextField(
                            value = state.renewPasswordState.value,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                                onDone = { keyboardController?.hide() }
                            ),
                            onValueChange = listener::onRenewPasswordInputChanged,
                            errorMessage = state.renewPasswordState.errorMessage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp),
                            trailingIcon = {
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(
                                        painter = painterResource(id = passwordIcon),
                                        contentDescription = null
                                    )
                                }
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
                        )
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(top = 64.dp, bottom = 12.dp),
                        onClick = listener::onClickChangePassword,
                        shape =  RoundedCornerShape(4.dp),
                        enabled = state.isButtonEnabled
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(30.dp),
                                color = MaterialTheme.colorScheme.primary, strokeWidth = 3.dp)
                        } else {
                            Text(
                                text = "Change Password",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.background),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
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
    PasswordScreen()
}