package bobby.irawan.simplenewsapp.utils

import java.util.*

object Constants {

    const val BASE_API = "https://newsapi.org"
    const val API_KEY = "&apiKey=97876d0094ed46e28aef8d496de74ce0"
    const val CATEGORY_QUERY = "&category="
    const val BASE_NEWS_URL = "https://newsapi.org/v2/top-headlines?country=id"

    //Date constant
    const val DATE_FORMAT_DEFAULT = "dd-MM-yyyy"
    const val DATE_FORMAT_DAY = "EEEE, dd MMMM yyyy \u2022 HH:mm"
    const val DATE_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    const val RC_SIGN_IN = 1337
    const val SHARED_PREF_NAME = "SnapSharedPref"
    const val KEY_IS_USER_LOG_IN = "KeyIsUserLogIn"

    @JvmField
    val APPS_LOCALE = Locale("in", "ID")

    const val EMPTY = ""

    //Recycler view
    const val TYPE_HEADLINE = 1
    const val TYPE_LIST = 2

    const val NEWS_ARTICLE_ARGS = "newsArg"

    //For network call
    sealed class Response {
        data class Success<T>(val data: T?) : Response()
        data class Error(val errorMessage: String) : Response()
    }

    enum class SocialMedia {
        FACEBOOK,
        GOOGLE
    }

}