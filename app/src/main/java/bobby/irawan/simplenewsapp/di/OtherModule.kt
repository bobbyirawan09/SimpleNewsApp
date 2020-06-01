package bobby.irawan.simplenewsapp.di

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 31/05/20.
 */

val otherModule = module {

    fun provideGoogleSigninOption() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

    factory {
        provideGoogleSigninOption()
    }
}