package com.biBalance.myapplication.presentation.challenges

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ChallengesInteractionListener: BaseInteractionListener {
    fun onClickChallenge(levelId: Int)
}