package com.rabbittoy.secure.connection.app

import android.app.Application
import com.rabbittoy.secure.connection.config.Read1011Config
import com.rabbittoy.secure.connection.page.Home1011Page
import com.github.shadowsocks.Core
import com.google.android.gms.ads.MobileAds
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.tencent.mmkv.MMKV

lateinit var mRabbit: Rabbit
class Rabbit:Application() {
    override fun onCreate() {
        super.onCreate()
        mRabbit=this
        Core.init(this,Home1011Page::class)
        MMKV.initialize(this)
        if (!packageName.equals(processName(this))){
            return
        }
        Firebase.initialize(this)
        MobileAds.initialize(this)
        RegisterActivity.register(this)
        Read1011Config.read1011Config()
    }
}