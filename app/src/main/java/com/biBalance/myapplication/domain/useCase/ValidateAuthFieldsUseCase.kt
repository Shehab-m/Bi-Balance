package com.biBalance.myapplication.domain.useCase

import com.biBalance.myapplication.util.ValidationState
import javax.inject.Inject

class ValidateAuthFieldsUseCase @Inject constructor() {
    fun validateEmail(email: String): ValidationState {
        if (email.isBlank()) {
            return ValidationState.BLANK_EMAIL
        }
        return ValidationState.VALID_EMAIL
    }
    fun validationPassword(password: String): ValidationState {
        return when {
            password.isBlank() -> {
                ValidationState.BLANK_PASSWORD
            }

            else -> ValidationState.VALID_PASSWORD
        }
    }
}