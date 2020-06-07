package bobby.irawan.simplenewsapp.data.firebase

import bobby.irawan.simplenewsapp.utils.Constants
import bobby.irawan.simplenewsapp.utils.Constants.Response
import bobby.irawan.simplenewsapp.utils.Constants.SocialMedia.FACEBOOK
import com.google.firebase.auth.*
import kotlinx.coroutines.tasks.await

/**
 * Created by bobbyirawan09 on 07/06/20.
 */
class FirebaseServiceImpl(val firebaseAuth: FirebaseAuth) : FirebaseService {

    override suspend fun onLoginWithEmail(email: String, password: String): Response {
        return try {
            val data: AuthResult?
            data = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }

    override suspend fun onLoginWithSocialMedia(
        userToken: String,
        socialMedia: Constants.SocialMedia
    ): Response {
        return try {
            val data: AuthResult?
            val credential: AuthCredential
            when (socialMedia) {
                FACEBOOK -> credential = FacebookAuthProvider.getCredential(userToken)
                else -> credential = GoogleAuthProvider.getCredential(userToken, null)
            }
            data = firebaseAuth.signInWithCredential(credential).await()
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }

    override suspend fun onSignUpWithEmail(email: String, password: String): Response {
        return try {
            val data: AuthResult?
            data = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun currentUser() = firebaseAuth.currentUser
}