//package com.biBalance
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.biBalance.myapplication.R
//import com.biBalance.myapplication.presentation.app.App
//import com.biBalance.myapplication.ui.theme.LightBlue100
//import com.biBalance.myapplication.ui.theme.dimens
//
//private class code {
//    @Composable
//    fun BiCard(
//        modifier: Modifier = Modifier, title: String, backgroundColor: Color,
//        content: @Composable (modifier: Modifier) -> Unit = {}
//    ) {
//        Card(
//            modifier = modifier,
//            shape = RoundedCornerShape(MaterialTheme.dimens.biCardRadius),
//            colors = CardDefaults.cardColors(backgroundColor)
//        ) {
//            Column(
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = title, style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 22.dp, bottom = 16.dp, end = 8.dp, start = 8.dp),
//                    textAlign = TextAlign.Center
//                )
//                content(Modifier.padding(bottom = 26.dp))
//            }
//        }
//    }
//
//
//    @Preview
//    @Composable
//    fun BiCardPreview() {
//        BiCard(title = "Level One", backgroundColor = LightBlue100) {
//            Card(modifier = it, shape = CircleShape) {
//                Text(
//                    text = "0%", style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier.padding(12.dp),
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//        IconButton(modifier = Modifier.size(24.dp), onClick = { }) {
//            Icon(
//                painter = painterResource(id = R.drawable.notification),
//                contentDescription = "notification",
//                tint = MaterialTheme.colorScheme.primary,
//            )
//        }
//    }
//}
//
//package com.biBalance.myapplication.presentation.app
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutVertically
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
//import androidx.compose.ui.input.nestedscroll.NestedScrollSource
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.unit.dp
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
//import androidx.navigation.compose.rememberNavController
//import com.biBalance.myapplication.presentation.navigation.BottomBar
//import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
//import com.biBalance.myapplication.ui.theme.BiBalanceTheme
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
//    @SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        installSplashScreen()
//        setContent {
//            CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
//                BiBalanceTheme {
//                    val bottomBarHeight = 64.dp
//                    val bottomBarHeightPx =
//                        with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
//                    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
//                    // connection to the nested scroll system and listen to the scroll
//                    // happening inside child LazyColumn
//                    val nestedScrollConnection = remember {
//                        object : NestedScrollConnection {
//                            override fun onPreScroll(
//                                available: Offset,
//                                source: NestedScrollSource
//                            ): Offset {
//                                val delta = available.y
//                                val newOffset = bottomBarOffsetHeightPx.value + delta
//                                bottomBarOffsetHeightPx.value =
//                                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
//                                return Offset.Zero
//                            }
//                        }
//                    }
//                    Scaffold(
//                        modifier = Modifier.nestedScroll(nestedScrollConnection),
//                    ) {
//                        Box(
//                            modifier = Modifier.background(MaterialTheme.colorScheme.background)
//                                .fillMaxSize(),
//                            contentAlignment = Alignment.BottomCenter
//                        ) {
//                            App()
//                            AnimatedVisibility(
//                                visible = bottomBarOffsetHeightPx.value in -60f..0f,
//                                enter = slideInVertically(initialOffsetY = { it }),
//                                exit = slideOutVertically(targetOffsetY = { it }),
//                            ) {
//                                BottomBar()
//                            }
//                            Log.d("onCreate: ", bottomBarOffsetHeightPx.value.toString())
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
