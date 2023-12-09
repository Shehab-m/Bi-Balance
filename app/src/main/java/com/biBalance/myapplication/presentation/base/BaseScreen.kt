package com.biBalance.myapplication.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
abstract class BaseScreen<VM, S, E, I>
        where I : BaseInteractionListener, VM : BaseViewModel<S, E>, VM : I {

    @Composable
    fun initScreen(viewModel: VM) {
        val state: S by viewModel.state.collectAsState()
//        val navigator = LocalNavigator.currentOrThrow

        onRender(state, viewModel)
        Listen(viewModel.effect) {
//            onEffect(effect = it,
//                navigator = navigator
//            )
        }
    }

    @Composable
    abstract fun onRender(state: S, listener: I)

    @Composable
    private fun Listen(effect: Flow<E>, function: (E) -> Unit) {
        LaunchedEffect(Unit) {
            effect.collectLatest {
                it?.let { function(it) }
            }
        }
    }

//    abstract fun onEffect(effect: E, navigator: Navigator)
}
