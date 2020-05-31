package bobby.irawan.simplenewsapp.utils

import android.content.Context.MODE_PRIVATE
import bobby.irawan.simplenewsapp.ApplicationController

/**
 * Created by bobbyirawan09 on 27/05/20.
 */
object SharedPreferenceUtils {

    const val KEY_IS_USER_LOG_IN = "KeyIsUserLogIn"
    const val SHARED_PREF_NAME = "SnapSharedPref"

    private val context = ApplicationController.context

    val sharedPref = context?.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
    val editorPref = sharedPref?.edit()

    fun setUserLogInStatus(status: Boolean) {
        editorPref?.putBoolean(KEY_IS_USER_LOG_IN, status)
    }

}