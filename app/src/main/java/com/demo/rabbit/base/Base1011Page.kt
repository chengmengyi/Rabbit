package com.demo.rabbit.base

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class Base1011Page(private val id:Int):AppCompatActivity() {
    var resume1011=false
    protected var apply:ImmersionBar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hei1011ght()
        setContentView(id)
        apply = ImmersionBar.with(this).apply {
            statusBarAlpha(0f)
            autoDarkModeEnable(true)
            statusBarDarkFont(false)
            init()
        }
        view()
    }

    override fun onResume() {
        super.onResume()
        resume1011=true
    }

    override fun onPause() {
        super.onPause()
        resume1011=false
    }

    override fun onStop() {
        super.onStop()
        resume1011=false
    }

    abstract fun view()

    private fun hei1011ght(){
        val metrics: DisplayMetrics = resources.displayMetrics
        val td = metrics.heightPixels / 760f
        val dpi = (160 * td).toInt()
        metrics.density = td
        metrics.scaledDensity = td
        metrics.densityDpi = dpi
    }
}