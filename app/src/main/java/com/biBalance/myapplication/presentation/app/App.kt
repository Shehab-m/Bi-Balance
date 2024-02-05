package com.biBalance.myapplication.presentation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.biBalance.myapplication.presentation.navigation.AppNavGraph
import com.biBalance.myapplication.presentation.navigation.Graph

@Composable
fun App(viewModel: AppViewModel = hiltViewModel()) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    isLoggedIn?.let {
        AppNavGraph(if (isLoggedIn == true) Graph.HOME  else Graph.LOGIN)
    }

}