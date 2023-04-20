package com.notgodzilla.android.myapplication

import android.app.Application
import com.notgodzilla.android.myapplication.api.PreferencesRepository

class EmployeeDirectoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.initialize(this)
    }
}