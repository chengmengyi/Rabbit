package com.demo.rabbit.ad

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.blankj.utilcode.util.SizeUtils
import com.demo.rabbit.R
import com.demo.rabbit.app.RegisterActivity
import com.demo.rabbit.app.print1011
import com.demo.rabbit.app.show
import com.demo.rabbit.base.Base1011Page
import com.demo.rabbit.config.Read1011Config
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import kotlinx.coroutines.*

class Show1011NativeAd(
    private val base1011Page: Base1011Page,
    private val type:String
) {

    private var lastAd: NativeAd?=null
    private var hasJob:Job?=null

    fun has1011AdCache(){
        Load1011AdImpl.canLoad1011Ad(type)
        stopHas1011Cache()
        hasJob = GlobalScope.launch(Dispatchers.Main) {
            delay(300L)
            if (!base1011Page.resume1011){
                return@launch
            }
            while (true) {
                if (!isActive) {
                    break
                }
                val adCache = Load1011AdImpl.getAdCache(type)
                if (base1011Page.resume1011&&null != adCache) {
                    cancel()
                    lastAd?.destroy()
                    if (adCache is NativeAd){
                        lastAd = adCache
                        print1011("start show $type ad")
                        start1011ShowNative(adCache)
                    }
                }
                delay(1000L)
            }
        }
    }

    private fun start1011ShowNative(nativeAd:NativeAd){
        val findViewById = base1011Page.findViewById<NativeAdView>(R.id.ad_1011_view)
        findViewById.iconView=base1011Page.findViewById(R.id.iv_1011_logo)
        (findViewById.iconView as ImageFilterView).setImageDrawable(nativeAd.icon?.drawable)

        findViewById.callToActionView=base1011Page.findViewById(R.id.tv_1011_install)
        (findViewById.callToActionView as AppCompatTextView).text=nativeAd.callToAction

        findViewById.mediaView=base1011Page.findViewById(R.id.iv_1011_cover)
        if (null!=nativeAd.mediaContent){
            findViewById.mediaView?.apply {
                setMediaContent(nativeAd.mediaContent)
                setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                outlineProvider = provider
            }
        }

        findViewById.bodyView=base1011Page.findViewById(R.id.tv_1011_desc)
        (findViewById.bodyView as AppCompatTextView).text=nativeAd.body

        findViewById.headlineView=base1011Page.findViewById(R.id.tv_1011_title)
        (findViewById.headlineView as AppCompatTextView).text=nativeAd.headline

        findViewById.setNativeAd(nativeAd)
        base1011Page.findViewById<AppCompatImageView>(R.id.iv_cover).show(false)
        Read1011Config.saveShow()
        Load1011AdImpl.removeCache(type)
        Load1011AdImpl.canLoad1011Ad(type)
        if (Load1011AdImpl.home==type){
            RegisterActivity.loadHomeAd=false
        }
    }

    private val provider=object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            if (view == null || outline == null) return
            outline.setRoundRect(
                0,
                0,
                view.width,
                view.height,
                SizeUtils.dp2px(10F).toFloat()
            )
            view.clipToOutline = true
        }
    }

    fun stopHas1011Cache(){
        hasJob?.cancel()
        hasJob=null
    }
}