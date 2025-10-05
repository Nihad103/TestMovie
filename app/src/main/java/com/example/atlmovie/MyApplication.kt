package com.example.atlmovie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.atlmovie.di.appModules
import com.example.atlmovie.utils.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
        }
        val isDark = Prefs.isDarkMode(this)
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
