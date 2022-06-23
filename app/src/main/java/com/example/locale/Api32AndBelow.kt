package com.example.locale

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

object Api32AndBelow {
    fun init(application: Application) = with(application) {
        registerComponentCallbacks(object : AbstractComponentCallbacks() {
            override fun onConfigurationChanged(configuration: Configuration) {
                syncLocale()
            }
        })

        registerActivityLifecycleCallbacks(object : AbstractActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                super.onActivityCreated(activity, p1)
                activity.resetTitle()
                syncLocale()
            }
            
            // https://github.com/YarikSOffice/lingver/blob/master/library/src/main/java/com/yariksoffice/lingver/Extensions.kt
            private fun Activity.resetTitle() {
                try {
                    val info = packageManager.getActivityInfo(
                        componentName,
                        PackageManager.GET_META_DATA
                    )
                    if (info.labelRes != 0) {
                        setTitle(info.labelRes)
                    }
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun syncLocale() {
        val expectedLocale = AppCompatDelegate.getApplicationLocales().get(0)
        val currentLocale = Locale.getDefault()
        if (expectedLocale != null && currentLocale != expectedLocale) {
            Log.d(
                "JJO",
                "Manually syncing Locale from $currentLocale to $expectedLocale"
            )
            Locale.setDefault(expectedLocale)
        }
    }
}
