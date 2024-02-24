package com.biBalance.myapplication.presentation.activity

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ActivityInteractionListener: BaseInteractionListener {
    fun onClickNextStartScreen()
    fun onClickNextActivity()
    fun onClickNextFinishScreen()
    fun onClickBack()
}