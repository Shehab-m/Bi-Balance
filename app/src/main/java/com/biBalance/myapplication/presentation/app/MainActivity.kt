package com.biBalance.myapplication.presentation.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.biBalance.myapplication.presentation.navigation.BottomBar
import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
import com.biBalance.myapplication.ui.theme.BiBalanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
                BiBalanceTheme {
                    val bottomBarHeight = 80.dp
                    Scaffold(
                        modifier = Modifier,
                        bottomBar = {
                            BottomBar(modifier = Modifier.height(bottomBarHeight))
                        }
                    ) {
                        Box(
                            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                                .fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            App()
                        }
                    }
                }
            }
        }
    }
}
