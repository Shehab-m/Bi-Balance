package com.biBalance.myapplication.presentation.composables

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    errorMessage: String = "",
    textColor: Color = MaterialTheme.colorScheme.primary,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    placeHolder: String = "",
    enabled: Boolean = true,
    singeLine: Boolean = true
) {
    var text by remember(value) { mutableStateOf(value) }
    Log.d("checkIf text: ", text)
    Column {
        OutlinedTextField(
            modifier = modifier.height(56.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedIndicatorColor = borderColor,
                unfocusedIndicatorColor = borderColor,
            ),
            shape = RoundedCornerShape(4.dp),
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            singleLine = singeLine,
            keyboardActions = keyboardActions,
            textStyle = MaterialTheme.typography.bodyMedium
        )
        AnimatedVisibility (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}