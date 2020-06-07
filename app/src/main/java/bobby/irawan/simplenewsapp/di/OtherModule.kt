package bobby.irawan.simplenewsapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.data.firebase.FirebaseService
import bobby.irawan.simplenewsapp.data.firebase.FirebaseServiceImpl
import bobby.irawan.simplenewsapp.utils.Constants.SHARED_PREF_NAME
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 31/05/20.
 */

val googleSignInModule = module {

    fun provideGoogleSigninOption(context: Context) =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.resources.getString(R.string.default_web_client_id))
            .build()

    factory {
        provideGoogleSigninOption(androidContext())
    }
}

val sharedPreferenceModule = module {

    fun provideSharedPrefEditor(sharedPref: SharedPreferences) =
        sharedPref.edit()

    fun provideSharedPreference(androidApplication: Application) =
        androidApplication.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )

    single { provideSharedPreference(androidApplication()) }
    single { provideSharedPrefEditor(get()) }
}

val firebaseModule = module {
    single {
        FirebaseAuth.getInstance()
    }

    single<FirebaseService> {
        FirebaseServiceImpl(get())
    }
}
