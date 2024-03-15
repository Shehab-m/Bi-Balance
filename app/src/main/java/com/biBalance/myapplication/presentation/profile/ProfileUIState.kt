package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.data.source.remote.model.UserData

data class ProfileUIState(
    val isLoading: Boolean = true,
    val userData: UserData = UserData("ali",0),

)