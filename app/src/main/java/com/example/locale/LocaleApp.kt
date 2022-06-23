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
        if (VERSION.SDK_INT <= 32) {
            Api32AndBelow.init(this)
        }

        checkLocale()
    }


    private fun checkLocale() {
        handler.postDelayed(1000) {
            Log.d("JJO", "Locale? ${Locale.getDefault()}")
            checkLocale()
        }
    }

}
