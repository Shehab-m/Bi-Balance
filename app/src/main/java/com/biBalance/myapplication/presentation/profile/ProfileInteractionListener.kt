package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ProfileInteractionListener: BaseInteractionListener {
    fun onClickLevel(levelId: String)
}