package com.biBalance.myapplication.presentation.password

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface PasswordInteractionListener: BaseInteractionListener {
    fun onClickBack()
    fun onOldPasswordInputChanged(password: CharSequence)
    fun onNewPasswordInputChanged(password: CharSequence)
    fun onRenewPasswordInputChanged(password: CharSequence)
    fun onClickChangePassword()
}