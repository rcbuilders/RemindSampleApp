package com.remind.sampleapp

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class AppMain: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppMain)
            modules(AppModules.modules)
        }

        Stetho.initializeWithDefaults(this)
    }
}