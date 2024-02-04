package com.biBalance.myapplication.util

interface StringDictionary {
    val validationString: Map<ValidationState, String>
    val errorString: Map<ErrorHandler, String>
    val requiredFieldsMessageString: String
}