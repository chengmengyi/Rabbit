package com.rabbittoy.secure.connection.page

import android.animation.ValueAnimator
import android.content.Intent
import android.view.KeyEvent
import android.view.animation.LinearInterpolator
import com.blankj.utilcode.util.ActivityUtils
import com.rabbittoy.secure.connection.R
import com.rabbittoy.secure.connection.ad.Load1011AdImpl
import com.rabbittoy.secure.connection.ad.Show1011ScreenAd
import com.rabbittoy.secure.connection.base.Base1011Page
import kotlinx.android.synthetic.main.main_page.*

class Main1011Page:Base1011Page(R.layout.main_page) {
    private var animator:ValueAnimator?=null
    private val showOpenAd by lazy { Show1011ScreenAd(this,Load1011AdImpl.open){toHomePage()} }

    override fun view() {
        Load1011AdImpl.preLoadAllAd()
        start()
    }

    private fun start(){
        animator = ValueAnimator.ofInt(0, 100).apply {
            duration = 10000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val progress = it.animatedValue as Int
                progress_main.progress = progress
                val pro = (10 * (progress / 100.0F)).toInt()
                if (pro in 2..9){
                    showOpenAd.show1011ScreenAd {jump->
                        stop()
                        progress_main.progress = 100
                        if (jump){
                            toHomePage()
                        }
                    }
                }else if (pro>=10){
                    toHomePage()
                }
            }
            start()
        }
    }

    private fun toHomePage(){
        if (!ActivityUtils.isActivityExistsInStack(Home1011Page::class.java)){
            startActivity(Intent(this,Home1011Page::class.java))
        }
        finish()
    }

    private fun stop(){
        animator?.removeAllUpdateListeners()
        animator?.cancel()
        animator=null
    }


    override fun onResume() {
        super.onResume()
        animator?.resume()
    }

    override fun onPause() {
        super.onPause()
        animator?.pause()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
    }
}