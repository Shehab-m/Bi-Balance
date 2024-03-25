package com.biBalance.myapplication.presentation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.biBalance.myapplication.R

@Composable
fun BiBackButton(onClick: () -> Unit,modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "icon play",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}