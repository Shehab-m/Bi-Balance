package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()),
    ProfileInteractionListener {
    override fun onClickLevel(levelId: String) {

    }

}