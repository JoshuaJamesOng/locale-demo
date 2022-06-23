package com.example.locale

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class AbstractActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, p1: Bundle?) = Unit

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
