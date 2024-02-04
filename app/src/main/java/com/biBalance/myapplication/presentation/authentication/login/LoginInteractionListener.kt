package com.biBalance.myapplication.presentation.authentication.login

interface LoginInteractionListener {
    fun onClickLogin()
    fun onEmailInputChange(email: CharSequence)
    fun onPasswordInputChanged(password: CharSequence)
    fun onClickSignup()
}