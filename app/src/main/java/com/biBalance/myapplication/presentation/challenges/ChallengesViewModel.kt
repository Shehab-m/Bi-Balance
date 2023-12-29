package com.biBalance.myapplication.presentation.challenges

import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor() : BaseViewModel<ChallengesUIState, ChallengesUIEffect>(ChallengesUIState()),
    ChallengesInteractionListener {
    override fun onClickLevel(levelId: Int) {

    }

}