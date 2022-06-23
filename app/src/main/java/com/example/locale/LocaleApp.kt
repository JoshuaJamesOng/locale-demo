package com.example.locale

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import java.util.*

class LocaleApp : Application() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()
        checkLocale()
    }

    private fun checkLocale() {
        handler.postDelayed(1000) {
            Log.d("JJO", "Locale? ${Locale.getDefault()}")
            checkLocale()
        }
    }

}
