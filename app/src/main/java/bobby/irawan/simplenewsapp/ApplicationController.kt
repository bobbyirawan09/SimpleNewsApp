package bobby.irawan.simplenewsapp

import android.app.Application
import bobby.irawan.simplenewsapp.di.databaseModule
import bobby.irawan.simplenewsapp.di.repositoryModule
import bobby.irawan.simplenewsapp.di.serviceModule
import bobby.irawan.simplenewsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationController : Application() {

    private lateinit var instance: ApplicationController

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@ApplicationController)
            modules(
                listOf(
                    serviceModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }

    fun getInstance(): ApplicationController = instance

}