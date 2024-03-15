package com.biBalance.myapplication.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.R
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun BiAnimatedFab(state: Boolean, onClick: ()-> Unit) {
    AnimatedVisibility(visible = state, enter = scaleIn(), exit = scaleOut(),) {
        FloatingActionButton(
            modifier = Modifier.size(70.dp),
            shape = RoundedCornerShape(100.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                tint = White100,
                contentDescription = ""
            )
        }
    }  
}
