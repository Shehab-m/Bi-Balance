package com.biBalance.myapplication.presentation.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.biBalance.myapplication.presentation.navigation.AppNavGraph
import com.biBalance.myapplication.presentation.navigation.BottomBar
import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
import com.biBalance.myapplication.ui.theme.BiBalanceTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
                BiBalanceTheme {
                    val bottomBarHeight = 64.dp
                    val bottomBarHeightPx =
                        with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
                    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
                    // connection to the nested scroll system and listen to the scroll
                    // happening inside child LazyColumn
                    val nestedScrollConnection = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(
                                available: Offset,
                                source: NestedScrollSource
                            ): Offset {

                                val delta = available.y
                                val newOffset = bottomBarOffsetHeightPx.value + delta
                                bottomBarOffsetHeightPx.value =
                                    newOffset.coerceIn(-bottomBarHeightPx, 0f)

                                return Offset.Zero
                            }
                        }
                    }
                    Scaffold(
                        modifier = Modifier.nestedScroll(nestedScrollConnection),
                        bottomBar = {

                            BottomBar(modifier = Modifier.offset {
                                IntOffset(
                                    x = 0,
                                    y = -bottomBarOffsetHeightPx.value.roundToInt()
                                )
                            })

                        },
                        contentWindowInsets = WindowInsets(0, 0, 0, 0)
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxSize()
                                .padding(innerPadding)
                        )
                        { AppNavGraph() }
                    }
                }
            }
        }
    }
}
