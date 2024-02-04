package com.biBalance.myapplication.presentation.composables.exitinstion

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.biBalance.myapplication.presentation.base.BaseUiEffect
import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.transform

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun <E: BaseUiEffect> EventHandler(effect: Flow<E>, function: (E, NavHostController) -> Unit) {
    val navController = LocalNavigationProvider.current
    LaunchedEffect(key1 = Unit) {
        effect.catch {
            Log.d("EventHandler: ", "error")
        }.collectLatest { effect ->
            function(effect, navController)
        }
    }
}

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> Flow<T>.throttleFirst(windowDurationMillis: Long): Flow<T> {
    var lastEmittedTimestamp = 0L
    return transform { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmittedTimestamp >= windowDurationMillis) {
            lastEmittedTimestamp = currentTime
            emit(value)
        }
    }
}