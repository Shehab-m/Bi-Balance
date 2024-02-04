package com.biBalance.myapplication.util

import android.content.Context
import com.biBalance.myapplication.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringResources @Inject constructor(
    private val context: Context
) : StringDictionary {
    private fun getStringFromFile(fileId: Int): String {
        return context.resources.getString(fileId)
    }

    override val validationString: Map<ValidationState, String>
        get() = mapOf(
            ValidationState.BLANK_EMAIL to getStringFromFile(R.string.email_should_not_be_empty),
            ValidationState.BLANK_PASSWORD to getStringFromFile(R.string.password_should_not_be_empty),
        )

    override val requiredFieldsMessageString: String
        get() = getStringFromFile(R.string.please_fill_required_fields)
    override val errorString: Map<ErrorHandler, String>
        get() = mapOf(
            ErrorHandler.AccountAlreadyExist to getStringFromFile(R.string.account_already_exist),
            ErrorHandler.NoConnection to getStringFromFile(R.string.something_went_wrong_please_try_again),
            ErrorHandler.InvalidUserNameOrPassword to getStringFromFile(R.string.Invalid_username_or_password),
        )
}