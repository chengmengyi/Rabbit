package com.demo.rabbit.page

import android.animation.ValueAnimator
import android.content.Intent
import android.view.KeyEvent
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.blankj.utilcode.util.ActivityUtils
import com.demo.rabbit.R
import com.demo.rabbit.base.Base1011Page
import kotlinx.android.synthetic.main.main_page.*

class Main1011Page:Base1011Page(R.layout.main_page) {
    private var animator:ValueAnimator?=null

    override fun view() {
        start()
    }

    private fun start(){
        animator = ValueAnimator.ofInt(0, 100).apply {
            duration = 3000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val progress = it.animatedValue as Int
                progress_main.progress = progress
            }
            doOnEnd { toHomePage() }
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