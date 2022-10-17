package com.rabbittoy.secure.connection.ad

import com.rabbittoy.secure.connection.app.print1011
import com.rabbittoy.secure.connection.base.Base1011Page
import com.rabbittoy.secure.connection.config.Read1011Config
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd

class Show1011ScreenAd(
    private val base1011Page: Base1011Page,
    private val type:String,
    private val closeAd:()->Unit
) {

    fun show1011ScreenAd(cannotShow:(jump:Boolean)->Unit){
        val adCache = Load1011AdImpl.getAdCache(type)
        if (adCache==null&&Read1011Config.hasLimit()){
            cannotShow.invoke(true)
        }else{
            if (adCache!=null){
                if (Load1011AdImpl.screenAdIsShowing||!base1011Page.resume1011){
                    cannotShow.invoke(false)
                }else{
                    cannotShow.invoke(false)
                    print1011("start show $type ad")
                    when(adCache){
                        is AppOpenAd->{
                            showAppOpenAd(adCache)
                        }
                        is InterstitialAd->{
                            showInterstitialAd(adCache)
                        }
                    }
                }
            }
        }
    }

    private fun showAppOpenAd(appOpenAd:AppOpenAd){
        appOpenAd.fullScreenContentCallback=ScreenAdCallback(type,base1011Page, closeAd)
        appOpenAd.show(base1011Page)
    }

    private fun showInterstitialAd(interstitialAd:InterstitialAd){
        interstitialAd.fullScreenContentCallback=ScreenAdCallback(type,base1011Page, closeAd)
        interstitialAd.show(base1011Page)
    }
}