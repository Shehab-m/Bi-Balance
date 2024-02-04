package com.biBalance.myapplication.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.util.ErrorHandler
import com.biBalance.myapplication.util.GeneralException
import com.biBalance.myapplication.util.NetworkException
import com.biBalance.myapplication.util.TokenException
import com.biBalance.myapplication.util.UserException
import com.biBalance.myapplication.util.handelGeneralException
import com.biBalance.myapplication.util.handelNetworkException
import com.biBalance.myapplication.util.handelTokenException
import com.biBalance.myapplication.util.handelUserException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.io.IOException

abstract class BaseViewModel<S, E>(initialState: S) : ViewModel(), BaseInteractionListener {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _effect = MutableSharedFlow<E?>()
    val effect = _effect.asSharedFlow().throttleFirst(500).mapNotNull { it }

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (t: ErrorHandler) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            handleException(
                onError
            ) {
                val result = function()
                Log.d("tryToExecute:","$result ")
                onSuccess(result)
            }
        }
    }

    private suspend fun <T> handleException(
        onError: (t: ErrorHandler) -> Unit,
        action: suspend () -> T
    ) {
        try {
            action()
        } catch (exception: Exception) {
            Log.d("tryToExecute error:"," $exception")
            when (exception) {
                is UserException -> handelUserException(exception, onError)
                is GeneralException -> handelGeneralException(exception, onError)
                is TokenException -> handelTokenException(exception, onError)
                is NetworkException -> handelNetworkException(exception, onError)
                is IOException -> onError(ErrorHandler.NoConnection)
                else -> onError(ErrorHandler.InvalidData)
            }
        }
    }

    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit,
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope
    ): Job {
        return runWithErrorCheck(onError, inScope) {
            function()
                .distinctUntilChanged()
                .collectLatest {
                    Log.e("Log","tryToExecute: $it")
                    onNewValue(it)
                }
        }
    }

    protected fun launchDelayed(duration: Long, block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            delay(duration)
            block()
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(effect)
        }
    }

    private fun runWithErrorCheck(
        onError: (ErrorState) -> Unit,
        inScope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        function: suspend () -> Unit
    ): Job {
        return inScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: Exception) {
                when (exception) {
                    is NoInternetException -> onError(ErrorState.NoInternet)
                    is PermissionDenied -> onError(ErrorState.HasNoPermission)
                    is ServerSideException -> onError(ErrorState.RequestFailed)
                    is UserNotFoundException -> onError(
                        ErrorState.UserNotExist(
                            exception.message.toString()
                        )
                    )

                    is WrongPasswordException -> onError(
                        ErrorState.WrongPassword(
                            exception.message.toString()
                        )
                    )

                    is InvalidCredentialsException -> onError(
                        ErrorState.InvalidCredentials(
                            exception.message.toString()
                        )
                    )

                    is UnknownErrorException -> onError(ErrorState.UnknownError(exception.message.toString()))
                    else -> onError(ErrorState.UnknownError(exception.message.toString()))
                }
            }
        }
    }

    private fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
        require(periodMillis > 0)
        return flow {
            var lastTime = 0L
            collect { value ->
                val currentTime = Clock.System.now().toEpochMilliseconds()
                if (currentTime - lastTime >= periodMillis) {
                    lastTime = currentTime
                    emit(value)
                }
            }
        }
    }
}