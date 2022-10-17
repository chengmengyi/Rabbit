package com.demo.rabbit.ad

import com.demo.rabbit.base.Base1011Page
import com.demo.rabbit.config.Read1011Config
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenAdCallback(
    private val type:String,
    private val base1011Page: Base1011Page,
    private val closeAd:()->Unit
): FullScreenContentCallback() {
    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        Load1011AdImpl.screenAdIsShowing=false
        showFinish()
    }

    override fun onAdShowedFullScreenContent() {
        super.onAdShowedFullScreenContent()
        Load1011AdImpl.screenAdIsShowing=true
        Read1011Config.saveShow()
        Load1011AdImpl.removeCache(type)
    }

    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
        super.onAdFailedToShowFullScreenContent(p0)
        Load1011AdImpl.screenAdIsShowing=false
        Load1011AdImpl.removeCache(type)
        showFinish()
    }

    override fun onAdClicked() {
        super.onAdClicked()
        Read1011Config.saveClick()
    }

    private fun showFinish(){
        if (type==Load1011AdImpl.connect){
            Load1011AdImpl.canLoad1011Ad(type)
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(200L)
            if (base1011Page.resume1011){
                closeAd.invoke()
            }
        }
    }
}