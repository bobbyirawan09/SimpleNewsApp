package bobby.irawan.simplenewsapp

import android.app.Application
import bobby.irawan.simplenewsapp.di.repositoryModule
import bobby.irawan.simplenewsapp.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationController)
            modules(
                listOf(
                    serviceModule,
                    repositoryModule
                )
            )
        }
    }

}