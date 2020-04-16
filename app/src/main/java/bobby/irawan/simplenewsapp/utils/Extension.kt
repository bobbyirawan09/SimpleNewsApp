package bobby.irawan.simplenewsapp.utils

import bobby.irawan.simplenewsapp.utils.Constants.APPS_LOCALE
import bobby.irawan.simplenewsapp.utils.Constants.DATE_FORMAT_API
import bobby.irawan.simplenewsapp.utils.Constants.DATE_FORMAT_DEFAULT
import bobby.irawan.simplenewsapp.utils.Constants.EMPTY
import java.text.SimpleDateFormat

fun String.parseServerDateFormatToString(): String {
    try {
        val oldDateFormat = SimpleDateFormat(DATE_FORMAT_API, APPS_LOCALE)
        val newDate = oldDateFormat.parse(this)
        val newDateFormat = SimpleDateFormat(DATE_FORMAT_DEFAULT, APPS_LOCALE)
        return newDateFormat.format(newDate)
    } catch (e: Exception) {
        return EMPTY
    }
}