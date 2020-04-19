package bobby.irawan.simplenewsapp.utils

import java.util.*

object Constants {

    const val API_KEY = "&apiKey=97876d0094ed46e28aef8d496de74ce0"
    const val BASE_HEAD_LINE_NEWS_URL = "http://newsapi.org/v2/top-headlines?country=id"

    //Date constant
    const val DATE_FORMAT_DEFAULT = "dd-MM-yyyy"
    const val DATE_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    @JvmField
    val APPS_LOCALE = Locale("in", "ID")

    const val EMPTY = ""

    //Recycler view
    const val TYPE_HEADLINE = 1
    const val TYPE_LIST = 2

    const val NEWS_ARTICLE_ARGS = "newsArg"

}