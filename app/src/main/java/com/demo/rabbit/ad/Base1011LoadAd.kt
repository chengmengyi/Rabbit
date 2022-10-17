package com.demo.rabbit.ad

import com.demo.rabbit.app.mRabbit
import com.demo.rabbit.app.print1011
import com.demo.rabbit.config.Read1011Config
import com.demo.rabbit.entity.Ad1011Entity
import com.demo.rabbit.entity.Ad1011ResultEntity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAdOptions

abstract class Base1011LoadAd {

    fun load1011AdByType(type:String,ad1011Entity: Ad1011Entity,loadComplete:(entity:Ad1011ResultEntity)->Unit){
        print1011("start load $type ad...${ad1011Entity.toString()}")
        when(ad1011Entity.type_rabbit_1011){
            "kp"->load1011K(ad1011Entity.id_rabbit_1011,loadComplete)
            "cp"->load1011C(ad1011Entity.id_rabbit_1011,loadComplete)
            "ys"->load1011Y(type,ad1011Entity.id_rabbit_1011,loadComplete)
        }
    }

    private fun load1011K(idRabbit1011: String, loadComplete: (entity:Ad1011ResultEntity) -> Unit) {
        AppOpenAd.load(
            mRabbit,
            idRabbit1011,
            AdRequest.Builder().build(),
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback(){
                override fun onAdLoaded(p0: AppOpenAd) {
                    loadComplete.invoke(Ad1011ResultEntity(ad = p0, time = System.currentTimeMillis()))
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    loadComplete.invoke(Ad1011ResultEntity(msg = p0.message))
                }
            }
        )
    }

    private fun load1011C(idRabbit1011: String, loadComplete: (entity:Ad1011ResultEntity) -> Unit) {
        InterstitialAd.load(
            mRabbit,
            idRabbit1011,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    loadComplete.invoke(Ad1011ResultEntity(msg = p0.message))
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    loadComplete.invoke(Ad1011ResultEntity(ad = p0, time = System.currentTimeMillis()))
                }
            }
        )
    }

    private fun load1011Y(type: String,idRabbit1011: String, loadComplete: (entity:Ad1011ResultEntity) -> Unit) {
        AdLoader.Builder(
            mRabbit,
            idRabbit1011
        ).forNativeAd {
            loadComplete.invoke(Ad1011ResultEntity(ad = it, time = System.currentTimeMillis()))
        }
            .withAdListener(object : AdListener(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    loadComplete.invoke(Ad1011ResultEntity(msg = p0.message))
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    Read1011Config.saveClick()
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    .setAdChoicesPlacement(
                        if (type==Load1011AdImpl.home){
                            NativeAdOptions.ADCHOICES_BOTTOM_LEFT
                        }else{
                            NativeAdOptions.ADCHOICES_TOP_LEFT
                        }
                    )
                    .build()
            )
            .build()
            .loadAd(AdRequest.Builder().build())
    }
}