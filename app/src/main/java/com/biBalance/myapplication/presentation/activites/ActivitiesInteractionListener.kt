package com.biBalance.myapplication.presentation.activites

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ActivitiesInteractionListener: BaseInteractionListener {
    fun onClickActivity(levelId: Int)
    fun getUserData()
    fun onClickBack()
}