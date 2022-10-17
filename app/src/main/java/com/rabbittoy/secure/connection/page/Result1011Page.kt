package com.rabbittoy.secure.connection.page

import com.rabbittoy.secure.connection.R
import com.rabbittoy.secure.connection.ad.Load1011AdImpl
import com.rabbittoy.secure.connection.ad.Show1011NativeAd
import com.rabbittoy.secure.connection.base.Base1011Page
import kotlinx.android.synthetic.main.result_page.*

class Result1011Page:Base1011Page(R.layout.result_page){
    private val showNativeAd by lazy { Show1011NativeAd(this, Load1011AdImpl.result) }


    override fun view() {
        apply?.statusBarView(status_view)?.init()

        val booleanExtra = intent.getBooleanExtra("connect", false)
        tv_result.isSelected=booleanExtra
        tv_result.text=if (booleanExtra) "Connection succeed" else "Disconnection succeed"

        iv_back.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        showNativeAd.has1011AdCache()
    }

    override fun onDestroy() {
        super.onDestroy()
        showNativeAd.stopHas1011Cache()
    }
}