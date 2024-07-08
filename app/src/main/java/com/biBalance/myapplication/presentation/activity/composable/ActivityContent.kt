package com.biBalance.myapplication.presentation.activity.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.R
import com.biBalance.myapplication.ui.theme.OffWhite100
import com.biBalance.myapplication.ui.theme.White100

@Composable
fun ActivityContent(
    modifier: Modifier = Modifier,
    body: String,
    painter: Painter,
    onClickNext: () -> Unit
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = body,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = OffWhite100,
            )
            Image(
                painter = painter,
                contentDescription = "hi",
                modifier = Modifier.size(350.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onClickNext) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.next),
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelLarge,
                            color = White100,
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = "icon play",
                            tint = White100, modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}