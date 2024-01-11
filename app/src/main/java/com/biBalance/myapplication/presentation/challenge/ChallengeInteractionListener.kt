package com.biBalance.myapplication.presentation.challenge

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ChallengeInteractionListener: BaseInteractionListener {
    fun onClickNext()
    fun onClickBack()
}