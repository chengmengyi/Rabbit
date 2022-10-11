package com.demo.rabbit.app

import android.app.Application
import com.demo.rabbit.config.Read1011Config
import com.demo.rabbit.page.Home1011Page
import com.github.shadowsocks.Core
import com.tencent.mmkv.MMKV

class Rabbit:Application() {
    override fun onCreate() {
        super.onCreate()
        Core.init(this,Home1011Page::class)
        MMKV.initialize(this)
        if (!packageName.equals(processName(this))){
            return
        }
        RegisterActivity.register(this)
        Read1011Config.read1011Config()
    }
}