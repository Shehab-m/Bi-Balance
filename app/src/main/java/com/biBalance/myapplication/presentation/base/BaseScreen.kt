package com.biBalance.myapplication.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.biBalance.myapplication.presentation.navigation.LocalNavigationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.transform

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
abstract class BaseScreen<VM, S, E, I>
        where I : BaseInteractionListener, VM : BaseViewModel<S, E>, VM : I, E: BaseUiEffect {

    @Composable
    fun initScreen(viewModel: VM) {
        val state: S by viewModel.state.collectAsState()
        ScreenContent(state, viewModel)
        EventHandler(viewModel.effect){ effect, nav ->
            onEffect(effect,nav)
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    @Composable
    fun EventHandler(effect: Flow<E>, function: (E, NavHostController) -> Unit) {
        val navController = LocalNavigationProvider.current
        LaunchedEffect(key1 = Unit) {
            effect.throttleFirst(500).collectLatest { effect ->
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

    @Composable
    abstract fun Screen()
    abstract fun onEffect(effect: E, navController: NavHostController)

    @Composable
    abstract fun ScreenContent(state: S, listener: I)
}
