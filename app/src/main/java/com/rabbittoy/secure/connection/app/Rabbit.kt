package com.rabbittoy.secure.connection.app

import android.app.Application
import com.rabbittoy.secure.connection.config.Read1011Config
import com.rabbittoy.secure.connection.page.Home1011Page
import com.github.shadowsocks.Core
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.tencent.mmkv.MMKV

class Rabbit:Application() {
    override fun onCreate() {
        super.onCreate()
        Core.init(this,Home1011Page::class)
        MMKV.initialize(this)
        if (!packageName.equals(processName(this))){
            return
        }
        Firebase.initialize(this)
        RegisterActivity.register(this)
        Read1011Config.read1011Config()
    }
}