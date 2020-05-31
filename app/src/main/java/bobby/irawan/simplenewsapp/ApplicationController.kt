package bobby.irawan.simplenewsapp

import android.app.Application
import android.content.Context
import bobby.irawan.simplenewsapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationController : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidLogger()
            androidContext(this@ApplicationController)
            modules(
                listOf(
                    serviceModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule,
                    retrofitModule
                )
            )
        }
    }

}