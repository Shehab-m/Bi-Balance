package com.biBalance.myapplication.presentation.authentication.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.composables.BiAnimationContent
import com.biBalance.myapplication.presentation.composables.BiTextField
import com.biBalance.myapplication.presentation.composables.LoadingProgress
import com.biBalance.myapplication.presentation.composables.exitinstion.EventHandler
import com.biBalance.myapplication.presentation.home.navigateToHomeScreen

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    Log.d( "checkIfUserLoggedIn: ", "screen")
    EventHandler(effect = viewModel.effect) { effect, navController ->
        when(effect) {
            LoginUiEffect.ClickLoginEffect -> {
                Log.d( "checkIfUserLoggedIn login: ", "login")
                navController.navigateToHomeScreen()
            }
            LoginUiEffect.ClickSignUpEffect -> {
                navController.navigateToHomeScreen()
                Log.d( "checkIfUserLoggedIn: ", "signup")

            }
            LoginUiEffect.ShowToastEffect -> {
                Log.d( "checkIfUserLoggedIn: ", "toast")
                Toast.makeText(context, state.validationToast.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    LoginContent(listener = viewModel, state = state)
}

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun LoginContent(
    listener: LoginInteractionListener,
    state: LoginUiState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
   BiAnimationContent(
       state = false,
       content = {
           Scaffold(
               modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
           ) { paddingValues->
               Column(
                   modifier = Modifier.fillMaxSize().padding(paddingValues)
                       .verticalScroll(rememberScrollState()),
                   horizontalAlignment = Alignment.CenterHorizontally,
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.character_yoga),
                       contentDescription ="" ,
                       modifier = Modifier.size(393.dp, 93.dp).padding(top = 24.dp)
                   )
                   Text(
                       text = stringResource(R.string.Login),
                       style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary),
                       textAlign = TextAlign.Center,
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(top = 46.dp, bottom = 58.dp)
                   )
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.user),
                           contentDescription = "user",
                           tint = MaterialTheme.colorScheme.primary,
                           modifier = Modifier.padding(end = 8.dp)
                       )
                       Text(
                           text = stringResource(R.string.User_name),
                           style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
                           textAlign = TextAlign.Center,
                       )
                   }
                   BiTextField(
                       singeLine = true,
                       value = state.emailState.value,
                       onValueChange = listener::onEmailInputChange,
                       errorMessage = state.emailState.errorMessage,
                       keyboardOptions = KeyboardOptions.Default.copy(
                           keyboardType = KeyboardType.Email,
                           imeAction = ImeAction.Next
                       ),
                       keyboardActions = KeyboardActions(
                           onNext = { focusManager.moveFocus(FocusDirection.Next) },
                           onDone = { keyboardController?.hide() }
                       ),
                       modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
                       textColor = MaterialTheme.colorScheme.primary
                   )
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.lock),
                           contentDescription = "user",
                           tint = MaterialTheme.colorScheme.primary,
                           modifier = Modifier.padding(end = 8.dp)
                       )
                       Text(
                           text = stringResource(R.string.password),
                           style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
                           textAlign = TextAlign.Center,
                       )
                   }
                   var showPassword by remember { mutableStateOf(false) }
                   val passwordIcon =
                       if (showPassword) R.drawable.eye else R.drawable.closeeye
                   BiTextField(
                       value = state.passwordState.value,
                       keyboardOptions = KeyboardOptions.Default.copy(
                           keyboardType = KeyboardType.Password,
                           imeAction = ImeAction.Done
                       ),
                       keyboardActions = KeyboardActions(
                           onNext = { focusManager.moveFocus(FocusDirection.Next) },
                           onDone = { keyboardController?.hide() }
                       ),
                       onValueChange = listener::onPasswordInputChanged,
                       errorMessage = state.passwordState.errorMessage,
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
                   Button(
                       modifier = Modifier.fillMaxWidth().padding(top = 64.dp, bottom = 12.dp),
                       onClick = listener::onClickLogin,
                       shape =  RoundedCornerShape(4.dp),
                       enabled = state.isButtonEnabled
                   ) {
                       if (state.isLoading) {
                           CircularProgressIndicator(modifier = Modifier.size(30.dp),
                               color = MaterialTheme.colorScheme.primary, strokeWidth = 3.dp)
                       } else {
                           Text(
                               text = stringResource(R.string.Login),
                               style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.background),
                               textAlign = TextAlign.Center,
                               modifier = Modifier.fillMaxSize()
                           )
                       }
                   }
//                   Row(verticalAlignment = Alignment.CenterVertically) {
//                       Text(
//                           text = stringResource(R.string.dont_have_account),
//                           style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
//                           textAlign = TextAlign.Center,
//                       )
//                       Text(
//                           text = stringResource(R.string.create_account),
//                           style = MaterialTheme.typography.bodyMedium.copy(
//                               color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.W700
//                           ),
//                           textDecoration = TextDecoration.Underline,
//                           textAlign = TextAlign.Center,
//                           modifier = Modifier.clickable { listener.onClickSignup() }
//                       )
//                   }
               }
           }
       },
       loadingContent = { LoadingProgress() }
   )
}


