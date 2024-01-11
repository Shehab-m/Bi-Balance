package com.biBalance.myapplication.presentation.challenge

import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor() :
    BaseViewModel<ChallengeUIState, ChallengeUIEffect>(ChallengeUIState()),
    ChallengeInteractionListener {

    override fun onClickNext() {
        _state.update { it.copy(isStartScreenVisible = false) }
    }

    override fun onClickBack() {
        sendEffect(ChallengeUIEffect.OnClickBack)
    }

}