package com.biBalance.myapplication.presentation.activity

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ActivityInteractionListener: BaseInteractionListener {
    fun showActivityContent()
    fun onClickNextActivity()
    fun showFinishScreen()
    fun onClickNextFinishScreen()
    fun onClickBack()
}