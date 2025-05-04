package com.sports.betting

import android.app.Application
import com.sports.ui.AppVersionProvider
import com.sports.ui.AppVersionProvider.getVersionCode
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppVersionProvider.setVersionCode(getVersionCode())
        Timber.plant(Timber.DebugTree())
    }
}
