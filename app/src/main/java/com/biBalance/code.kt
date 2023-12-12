package com.biBalance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.ui.theme.LightBlue100
import com.biBalance.myapplication.ui.theme.dimens

private class code {
    @Composable
    fun BiCard(
        modifier: Modifier = Modifier, title: String, backgroundColor: Color,
        content: @Composable (modifier: Modifier) -> Unit = {}
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(MaterialTheme.dimens.biCardRadius),
            colors = CardDefaults.cardColors(backgroundColor)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title, style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
                    textAlign = TextAlign.Center
                )
                content(Modifier.padding(bottom = 26.dp))
            }
        }
    }


    @Preview
    @Composable
    fun BiCardPreview() {
        BiCard(title = "Level One", backgroundColor = LightBlue100) {
            Card(modifier = it, shape = CircleShape) {
                Text(
                    text = "0%", style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}