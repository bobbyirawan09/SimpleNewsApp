package bobby.irawan.simplenewsapp.data.firebase

import bobby.irawan.simplenewsapp.utils.Constants
import com.google.firebase.auth.FirebaseUser

/**
 * Created by bobbyirawan09 on 07/06/20.
 */
interface FirebaseService {

    suspend fun onLoginWithEmail(email: String, password: String): Constants.Response
    suspend fun onLoginWithSocialMedia(
        userToken: String,
        socialMedia: Constants.SocialMedia
    ): Constants.Response

    suspend fun onSignUpWithEmail(email: String, password: String): Constants.Response
    suspend fun logout()
    suspend fun currentUser(): FirebaseUser?
}