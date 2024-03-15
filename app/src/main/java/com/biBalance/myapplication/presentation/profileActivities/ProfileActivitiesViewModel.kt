package com.biBalance.myapplication.presentation.profileActivities

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileActivitiesViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ProfileActivitiesUIState, ProfileActivitiesUIEffect>(ProfileActivitiesUIState()),
    ProfileActivitiesInteractionListener {

    init {
        getUserData()
    }

    override fun onClickBack() {
        sendEffect(ProfileActivitiesUIEffect.OnClickBack)
    }

    private fun getUserData() {
    }


}