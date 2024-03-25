package com.biBalance.myapplication.presentation.authentication.password

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface PasswordInteractionListener: BaseInteractionListener {
    fun onClickBack()
    fun onOldPasswordInputChanged(password: CharSequence)
    fun onNewPasswordInputChanged(password: CharSequence)
    fun onConfirmationPasswordInputChanged(password: CharSequence)
    fun onClickChangePassword()
}