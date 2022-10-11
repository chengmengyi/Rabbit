package com.demo.rabbit.app

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.demo.rabbit.page.Home1011Page
import com.demo.rabbit.page.Main1011Page
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object RegisterActivity {
    var front1011=true
    var loadHomeAd=true
    private var jumpToMain=false
    private var job: Job?=null


    fun register(application: Application){
        application.registerActivityLifecycleCallbacks(callback)
    }

    private val callback=object : Application.ActivityLifecycleCallbacks{
        private var pages=0
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

        override fun onActivityStarted(activity: Activity) {
            pages++
            job?.cancel()
            job=null
            if (pages==1){
                front1011=true
                if (jumpToMain){
                    if (ActivityUtils.isActivityExistsInStack(Home1011Page::class.java)){
                        activity.startActivity(Intent(activity, Main1011Page::class.java))
                    }
                }
                jumpToMain=false
            }
        }

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStopped(activity: Activity) {
            pages--
            if (pages<=0){
                front1011=false
                loadHomeAd=true
                job= GlobalScope.launch {
                    delay(3000L)
                    jumpToMain=true
//                    ActivityUtils.finishActivity(Main0914Activity::class.java)
//                    ActivityUtils.finishActivity(AdActivity::class.java)
                }
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {}
    }
}