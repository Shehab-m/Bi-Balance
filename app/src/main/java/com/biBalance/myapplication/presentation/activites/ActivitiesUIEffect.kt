package com.biBalance.myapplication.presentation.activites

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ActivitiesUIEffect: BaseUiEffect {
    data class OnClickActivity(val id:Int): ActivitiesUIEffect()
}
