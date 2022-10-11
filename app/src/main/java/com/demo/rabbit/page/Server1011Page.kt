package com.demo.rabbit.page

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.rabbit.R
import com.demo.rabbit.adapter.Server1011Adapter
import com.demo.rabbit.base.Base1011Page
import com.demo.rabbit.entity.Server1011Entity
import com.demo.rabbit.server.ConnectHelper
import kotlinx.android.synthetic.main.server_page.*

class Server1011Page:Base1011Page(R.layout.server_page) {
    override fun view() {
        apply?.statusBarView(status_view)?.init()

        recycler_view.apply {
            layoutManager=LinearLayoutManager(this@Server1011Page)
            adapter=Server1011Adapter(this@Server1011Page){
                itemClick(it)
            }
        }

        iv_back.setOnClickListener { finish() }
    }

    private fun itemClick(server1011Entity: Server1011Entity){
        val current = ConnectHelper.server1011Entity
        val connected1011 = ConnectHelper.isConnected1011()
        if (connected1011&&current.rabbit_1011_host!=server1011Entity.rabbit_1011_host){
            showTipsDialog {
                chooseBack("dis",server1011Entity)
            }
        }else{
            if (connected1011){
                chooseBack("",server1011Entity)
            }else{
                chooseBack("con",server1011Entity)
            }
        }
    }

    private fun chooseBack(back:String,server1011Entity: Server1011Entity){
        ConnectHelper.server1011Entity=server1011Entity
        setResult(1011, Intent().apply {
            putExtra("back",back)
        })
        finish()
    }

    private fun showTipsDialog(sure:()->Unit){
        AlertDialog.Builder(this).apply {
            setMessage("You are currently connected and need to disconnect before manually connecting to the server.")
            setPositiveButton("sure") { _, _ ->
                sure.invoke()
            }
            setNegativeButton("cancel",null)
            show()
        }
    }
}