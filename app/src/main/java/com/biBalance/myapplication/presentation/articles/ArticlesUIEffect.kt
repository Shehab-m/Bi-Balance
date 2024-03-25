package com.biBalance.myapplication.presentation.articles

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ArticlesUIEffect: BaseUiEffect {
    object OnClickBack: ArticlesUIEffect()
}
