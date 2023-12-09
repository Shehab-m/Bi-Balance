package com.biBalance.myapplication.presentation.home

import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()),
    HomeInteractionListener {
    override fun onClickLevel(levelId: String) {

    }

}