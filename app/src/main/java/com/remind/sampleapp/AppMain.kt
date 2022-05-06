package com.remind.sampleapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppMain: Application(), LifecycleEventObserver {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppMain)
            modules(AppModules.modules)
        }

        Stetho.initializeWithDefaults(this)

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_PAUSE -> {
                Log.d("AppMain", "onAppBackgrounded!!")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.d("AppMain", "onAppForegrounded!!")
            }
            else -> Log.d("AppMain", "onStateChanged(): event=$event")
        }
    }

}