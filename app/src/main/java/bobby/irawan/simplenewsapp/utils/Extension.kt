package bobby.irawan.simplenewsapp.utils

import bobby.irawan.simplenewsapp.utils.Constants.APPS_LOCALE
import bobby.irawan.simplenewsapp.utils.Constants.DATE_FORMAT_API
import bobby.irawan.simplenewsapp.utils.Constants.DATE_FORMAT_DEFAULT
import bobby.irawan.simplenewsapp.utils.Constants.EMPTY
import java.text.SimpleDateFormat

fun String.parseServerDateFormatToString(oldFormat: String = DATE_FORMAT_API, newFormat: String = DATE_FORMAT_DEFAULT): String {
    try {
        val oldDateFormat = SimpleDateFormat(oldFormat, APPS_LOCALE)
        val newDate = oldDateFormat.parse(this)
        val newDateFormat = SimpleDateFormat(newFormat, APPS_LOCALE)
        return newDateFormat.format(newDate)
    } catch (e: Exception) {
        return EMPTY
    }
}