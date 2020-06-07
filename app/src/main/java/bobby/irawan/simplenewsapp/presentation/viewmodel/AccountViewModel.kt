package bobby.irawan.simplenewsapp.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.presentation.model.UserModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import bobby.irawan.simplenewsapp.utils.Constants.SocialMedia.*
import bobby.irawan.simplenewsapp.utils.Constants
import bobby.irawan.simplenewsapp.utils.Constants.Response
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AccountViewModel(
    private val context: Context,
    private val repository: NewsRepositoryContract
) : ViewModel(),
    KoinComponent {

    private val sharedPref: SharedPreferences by inject()
    private val sharedPrefEdit: SharedPreferences.Editor by inject()
    private var account: GoogleSignInAccount? = null
    private var userModelView: UserModelView? = null

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String>
        get() = _toolbarTitle

    init {
        isUserLogin()
    }

    fun onHandleActivityResult(requestCode: Int, data: Intent?) {
        if (requestCode == Constants.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            var userToken: String? = null
            try {
                account = task.getResult()
                account?.let {
                    userToken = it.idToken
                    onLoginWithSocialMedia(userToken, GOOGLE)
                }
                onUpdateSignInStatus()
            } catch (e: Exception) {
                //Show error dialog
            }
        }
    }

    fun onLoginWithSocialMedia(userToken: String?, socialMedia: Constants.SocialMedia) {
        viewModelScope.launch(Main) {
            val response = repository.onLoginWithSocialMedia(userToken, socialMedia)
            when (response) {
                is Response.Success<*> -> userModelView = response.data as UserModelView
                is Response.Error -> response.errorMessage //show error message with toast atau snackbar
            }
        }
    }

    private fun onUpdateSignInStatus() {
        with(sharedPrefEdit) {
            putBoolean(Constants.KEY_IS_USER_LOG_IN, true)
            commit()
        }
    }

    private fun isUserLogin() {
        if (!sharedPref.getBoolean(Constants.KEY_IS_USER_LOG_IN, false)) {
            _toolbarTitle.value = context.getString(R.string.log_in_title)
        }
    }

    fun onLoginWithEmail(email: String, password: String) {
        viewModelScope.launch(Main) {
            val response = repository.onLoginWithEmail(email, password)
            when (response) {
                is Response.Success<*> -> userModelView = response.data as UserModelView
                is Response.Error -> response.errorMessage //show error message with toast atau snackbar
            }
        }
    }


}
