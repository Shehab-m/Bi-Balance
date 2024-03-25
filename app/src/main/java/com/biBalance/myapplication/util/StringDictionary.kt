package com.biBalance.myapplication.util

interface StringDictionary {
    val validationString: Map<ValidationState, String>
    val requiredFieldsMessageString: String
}