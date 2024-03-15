package com.biBalance.myapplication.presentation.writings

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WritingsViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<WritingsUIState, WritingsUIEffect>(WritingsUIState()),
    WritingsInteractionListener {

    init {
        getUserData()
    }

    override fun onClickBack() {
        sendEffect(WritingsUIEffect.OnClickBack)
    }

    private fun getUserData() {
    }


}