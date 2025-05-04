package com.sports.ui.extentions

import java.text.NumberFormat
import java.util.Locale

fun Double.toTRYFormat(): String {
    return try {
        val formatter = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        formatter.format(this).replace("TRY", "₺").replace(".", ",").trim()
    } catch (e: Exception) {
        "₺0,00"
    }
}

private fun Double.format(digits: Int) = "%.${digits}f".format(this)
