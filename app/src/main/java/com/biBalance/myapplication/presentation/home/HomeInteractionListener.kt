package com.biBalance.myapplication.presentation.home

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface HomeInteractionListener: BaseInteractionListener {
    fun onClickLevel(levelId: Int)
    fun getUserData()
    fun getHomeLevels()
}