package com.biBalance.myapplication.presentation.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : ViewModel() {

    private val _isLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn.asStateFlow()


    init {
        saveIsFirstTimeOpenApp()
    }

    private fun saveIsFirstTimeOpenApp() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.update { repository.getAccessToken() != null }
                Log.d("token user: ", "${repository.getAccessToken()}")
        }
    }

}
