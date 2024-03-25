package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ProfileInteractionListener: BaseInteractionListener {
    fun onClickLogout()
    fun onClickActivities()
    fun onClickWritings()
    fun onClickPassword()
    fun onClickTodo()
    fun getUserData()
    fun onClickArticles()
}