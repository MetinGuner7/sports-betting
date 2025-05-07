package com.sports.ui.extentions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.withStyle
import androidx.core.content.ContextCompat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Pattern

fun String.parseBold(fontWeight: FontWeight = FontWeight.Bold): AnnotatedString {
    val parts = this.split("<b>", "</b>")
    return buildAnnotatedString {
        var bold = false
        for (part in parts) {
            if (bold) {
                withStyle(style = SpanStyle(fontWeight = fontWeight)) { append(part) }
            } else {
                append(part)
            }
            bold = !bold
        }
    }
}

fun String.containsEmoji() =
    Pattern.compile("\\p{So}+", Pattern.CASE_INSENSITIVE).matcher(this).find()

fun String.containsOnlyLettersAndSpaces(): Boolean = !contains(Regex("[^\\p{L}\\s]"))

fun String?.isValidEmail(): Boolean {
    if (this.isNullOrBlank()) return false

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return this.matches(emailRegex)
}

fun String.isValidEmailCharacter(): Boolean {
    val validCharactersRegex = "^[a-zA-Z0-9._+-@]*$".toRegex()
    return this.all { it.toString().matches(validCharactersRegex) }
}

fun String.copyToClipboard(context: Context) {
    val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("Copied Text", this)
    clipboard?.setPrimaryClip(clip)
}

fun String.toReadableDateTime(
    targetFormatPattern: String = "dd MMMM yyyy HH:mm",
    targetZoneId: ZoneId = ZoneId.systemDefault()
): String {
    return try {
        val instant = Instant.parse(this)
        val zonedDateTime = instant.atZone(targetZoneId)
        val formatter = DateTimeFormatter.ofPattern(targetFormatPattern, java.util.Locale.ENGLISH)
        zonedDateTime.format(formatter)
    } catch (e: DateTimeParseException) {
        this
    } catch (e: Exception) {
        this
    }
}
