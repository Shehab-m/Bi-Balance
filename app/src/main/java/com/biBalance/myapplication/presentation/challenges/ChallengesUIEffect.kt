package com.biBalance.myapplication.presentation.challenges

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ChallengesUIEffect: BaseUiEffect {
    data class OnClickChallenge(val id:Int): ChallengesUIEffect()
}
