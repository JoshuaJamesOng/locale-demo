package com.example.locale

import android.app.Application
import android.os.Build.VERSION
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import java.util.*

class LocaleApp : Application() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()

        // Appcompat delegates to a 33+ implementation where everything _just_ works.
        // For older API versions, we still have to cover cases like the language being changed in OS settings
        // Whether this is a bug or a limitation, I don't know. It hasn't worked in any of the 1.6.0 releases.
        if (VERSION.SDK_INT <= 32) {
            Api32AndBelow.init(this)
        }

        // Prints `Locale#getDefault` to Logcat, every second, to observe changes whilst the app is not visible
        logLocale()
    }

    private fun logLocale() {
        handler.postDelayed(1000) {
            Log.d("JJO", "Locale? ${Locale.getDefault()}")
            logLocale()
        }
    }

}
