package com.biBalance.myapplication.presentation.challenges.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.R
import com.biBalance.myapplication.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiCardChallenge(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.dimens.biCardRadius),
        colors = CardDefaults.cardColors(backgroundColor),
        onClick = { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title, style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
                textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.primary
            )
            Box(
                modifier = Modifier.size(42.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = "icon play",
                        modifier = Modifier.padding(11.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }
    }
}