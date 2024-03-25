package com.biBalance.myapplication.presentation.writings

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface WritingsInteractionListener: BaseInteractionListener {
    fun onClickBack()
    fun onWritingsInputChange(text: String)
    fun onClickAddWriting()
    fun onClickBackFromWriting()
    fun onClickSaveWriting()

}