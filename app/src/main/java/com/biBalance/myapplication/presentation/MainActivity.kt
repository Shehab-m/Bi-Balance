package com.biBalance.myapplication.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
import com.biBalance.myapplication.presentation.navigation.MainNavGraph
import com.biBalance.myapplication.ui.theme.BiBalanceTheme

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
                BiBalanceTheme {
                    Scaffold(
                        bottomBar = { },
                        contentWindowInsets = WindowInsets(0, 0, 0, 0)
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                                .fillMaxSize().padding(innerPadding)
                        )
                        { MainNavGraph() }
                    }
                }
            }
        }
    }
}
