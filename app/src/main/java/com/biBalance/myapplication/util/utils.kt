package com.biBalance.myapplication.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentFormattedDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(formatter)
}

//fun Double.roundToNearestHalf(): Double {
//    val rounded = when {
//        this % 1 == 0.0 -> this // No need to round if it's already a whole number
//        this % 1 <= 0.25 -> floor(this) // Round down to nearest 0.0
//        this % 1 >= 0.75 -> ceil(this) // Round up to nearest whole number
//        else -> floor(this) + 0.5 // Round to nearest 0.5
//    }
//    return if (rounded == 0.0) 0.5 else rounded
//}
//
//fun Double.roundToNearestHalf(): Double {
//    return (kotlin.math.floor(this * 2) / 2)
//}

fun Double.roundToNearestHalf(): Double {
    return "%.2f".format(this).toDouble()
}