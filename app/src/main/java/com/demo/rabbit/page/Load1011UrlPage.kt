package com.demo.rabbit.page

import com.demo.rabbit.R
import com.demo.rabbit.base.Base1011Page
import com.demo.rabbit.config.Local1011Config
import kotlinx.android.synthetic.main.load_url_page.*

class Load1011UrlPage:Base1011Page(R.layout.load_url_page) {
    override fun view() {
        apply?.statusBarView(status_view)?.init()
        webview.apply {
            settings.javaScriptEnabled=true
            loadUrl(Local1011Config.url1011)
        }
        iv_back.setOnClickListener { finish() }
    }
}