package bobby.irawan.simplenewsapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class ApplicationController: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@ApplicationController)
            modules()
        }
    }

}