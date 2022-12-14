package com.demo.rabbit.page

import com.demo.rabbit.R
import com.demo.rabbit.base.Base1011Page
import kotlinx.android.synthetic.main.result_page.*

class Result1011Page:Base1011Page(R.layout.result_page){
    override fun view() {
        apply?.statusBarView(status_view)?.init()

        val booleanExtra = intent.getBooleanExtra("connect", false)
        tv_result.isSelected=booleanExtra
        tv_result.text=if (booleanExtra) "Connection succeed" else "Disconnection succeed"

        iv_back.setOnClickListener { finish() }
    }
}