package com.biBalance.myapplication.data.source.remote.model

fun decodeUnicode(input: String): String {
    val regex = "\\\\u([0-9a-fA-F]{4})".toRegex()
    return regex.replace(input) {
        it.groupValues[1].toInt(16).toChar().toString()
    }
}

fun main() {
    val encodedText = "\u0633\u0646\u0642\u0648\u0645 \u0628\u0639\u0645\u0644 \u062a\u0645\u0631\u064a\u0646 \u0627\u0644\u064a\u0648\u062c\u0627 \u0627\u0644\u064a\u0648\u0645"

    val decodedText = decodeUnicode(encodedText)
    println(decodedText)
}