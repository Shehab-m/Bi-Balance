package com.biBalance.myapplication.presentation.activity.composable

import android.annotation.SuppressLint
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.biBalance.myapplication.R
import com.biBalance.myapplication.presentation.activity.ActivityInteractionListener
import com.biBalance.myapplication.presentation.composables.ContentVisibility
import com.biBalance.myapplication.ui.theme.White100

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    state: Boolean,
    title: String,
//    body: String,
    listener: ActivityInteractionListener
) {
    ContentVisibility(
        state = state
    ){
        Column(modifier = modifier) {
            Row(modifier = Modifier.padding(top = 20.dp, bottom = 24.dp)) {
                IconButton(onClick = { listener.onClickBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "icon play",
                        tint = White100
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = body,
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.labelMedium,
//                    color = OffWhite100,
//                )
                Image(
                    painter = painterResource(id = R.drawable.character_hi),
                    contentDescription = "hi",
                    modifier = Modifier.size(350.dp).fillMaxWidth(),
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { listener.showActivityContent() }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier,
                                text = "Next",
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
}

/*
@Preview
@Composable
fun HomeScreenPreview() {
    StartScreen(Modifier,true,"","",object :ActivityInteractionListener{
        override fun onClickNext() {}
        override fun onClickBack() {}
    })
}*/
