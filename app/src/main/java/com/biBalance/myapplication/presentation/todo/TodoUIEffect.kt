package com.biBalance.myapplication.presentation.todo

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class TodoUIEffect: BaseUiEffect {
    object OnClickBack: TodoUIEffect()
    object OnClickSaveTasks: TodoUIEffect()
}
