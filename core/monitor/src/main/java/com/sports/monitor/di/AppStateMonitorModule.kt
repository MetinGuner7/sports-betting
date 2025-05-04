package com.sports.monitor.di

import com.sports.monitor.AppStateMonitor
import com.sports.monitor.impl.DefaultAppStateMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppStateMonitorModule {
    @Binds
    internal abstract fun bindsAppStateMonitor(
        appStateMonitor: DefaultAppStateMonitor
    ): AppStateMonitor
}
