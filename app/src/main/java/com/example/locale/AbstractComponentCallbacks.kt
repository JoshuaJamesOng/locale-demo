package com.example.locale

import android.content.ComponentCallbacks
import android.content.res.Configuration

abstract class AbstractComponentCallbacks : ComponentCallbacks {

    override fun onConfigurationChanged(configuration: Configuration) = Unit

    override fun onLowMemory() = Unit
}
