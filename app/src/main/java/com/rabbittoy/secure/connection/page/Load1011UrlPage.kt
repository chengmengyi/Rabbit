package com.rabbittoy.secure.connection.page

import com.rabbittoy.secure.connection.R
import com.rabbittoy.secure.connection.base.Base1011Page
import com.rabbittoy.secure.connection.config.Local1011Config
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