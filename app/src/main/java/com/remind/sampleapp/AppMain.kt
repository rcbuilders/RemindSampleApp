package com.remind.sampleapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppMain: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppMain)
            modules(AppModules.modules)
        }

    }
}