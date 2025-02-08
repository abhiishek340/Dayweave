package com.lifesyncpro.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LifeSyncProApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Firebase initialization temporarily disabled
    }
} 