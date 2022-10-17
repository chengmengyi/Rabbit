package com.demo.rabbit.page

import android.content.Intent
import android.net.VpnService
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.demo.rabbit.R
import com.demo.rabbit.ad.Load1011AdImpl
import com.demo.rabbit.ad.Show1011NativeAd
import com.demo.rabbit.ad.Show1011ScreenAd
import com.demo.rabbit.app.*
import com.demo.rabbit.base.Base1011Page
import com.demo.rabbit.server.ConnectHelper
import com.demo.rabbit.server.Time1011Helper
import com.github.shadowsocks.utils.StartService
import kotlinx.android.synthetic.main.home_content_layout.*
import kotlinx.android.synthetic.main.home_drawer_layout.*
import kotlinx.android.synthetic.main.home_page.*
import kotlinx.coroutines.*

class Home1011Page:Base1011Page(R.layout.home_page), ConnectHelper.IConnect1011Result,
    Time1011Helper.ITime {
    private var can1011Click=true
    private var permission=false
    private var connect=true
    private var checkConnectJob: Job?=null
    private val registerResult=registerForActivityResult(StartService()) {
        if (!it && permission) {
            permission = false
            connectServer()
        } else {
            can1011Click=true
            toast("Connected fail")
        }
    }

    private val showNativeAd by lazy { Show1011NativeAd(this,Load1011AdImpl.home) }

    private val showConnectAd by lazy { Show1011ScreenAd(this,Load1011AdImpl.connect){toResultPage()} }

    override fun view() {
        apply?.statusBarView(status_view)?.init()
        Time1011Helper.setTimeListener(this)
        ConnectHelper.create1011(this,this)

        iv_set.setOnClickListener {
            if (can1011Click&&!drawer_layout.isOpen){
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }
        iv_server_country.setOnClickListener {
            if (can1011Click&&!drawer_layout.isOpen){
                startActivityForResult(Intent(this,Server1011Page::class.java),1011)
            }
        }
        iv_connect_btn.setOnClickListener {
            if (can1011Click&&!drawer_layout.isOpen){
                can1011Click=false
                connectOrDisServer()
            }
        }
        ll_contact.setOnClickListener {
            if (canClickDrawer()){
                contact1011()
            }
        }
        ll_privacy.setOnClickListener {
            if (canClickDrawer()){
                startActivity(Intent(this,Load1011UrlPage::class.java))
            }
        }
        ll_update.setOnClickListener {
            if (canClickDrawer()){
                update1011()
            }
        }
        ll_share.setOnClickListener {
            if (canClickDrawer()){
                share1011()
            }
        }
        iv_connect_idle.setOnClickListener { iv_connect_btn.performClick() }
    }

    private fun canClickDrawer()=can1011Click&&drawer_layout.isOpen

    private fun connectOrDisServer(){
        val connected1011 = ConnectHelper.isConnected1011()
        if (connected1011){
            setStoppingUI()
            loopCheckConnectServer(false)
        }else{
            setTopRightServerLogo()
            if (getNet1011Status()==1){
                showNoNetDialog()
                can1011Click=true
                return
            }
            if (VpnService.prepare(this) != null) {
                permission = true
                registerResult.launch(null)
                return
            }
            connectServer()
        }

    }

    private fun connectServer(){
        setConnectingUI()
        loopCheckConnectServer(true)
    }

    private fun loopCheckConnectServer(connect:Boolean){
        this.connect=connect
        checkConnectJob=GlobalScope.launch(Dispatchers.Main) {
            var time = 0
            while (true) {
                if (!isActive) {
                    break
                }
                delay(1000)
                time++
                if (time==3){
                    if (connect){
                        ConnectHelper.connect()
                    }else{
                        ConnectHelper.disconnect()
                    }                }
                if (time in 3..9){
                    if(connectDisSuccess()){
                        showConnectAd.show1011ScreenAd {jump->
                            cancel()
                            checkServerFinish(toResult = jump)
                        }
                    }
                }

                if (time >= 10) {
                    cancel()
                    checkServerFinish()
                }
            }
        }
    }

    private fun connectDisSuccess()=if (connect) ConnectHelper.isConnected1011() else ConnectHelper.isStopped1011()

    private fun checkServerFinish(toResult:Boolean=true){
        if (connectDisSuccess()){
            if (connect){
                setConnectedSuccessUI()
            }else{
                setStoppedSuccessUI()
                setTopRightServerLogo()
            }
            if (toResult){
                toResultPage()
            }
        }else{
            setStoppedSuccessUI()
            toast(if (connect) "Connect Fail" else "Disconnect Fail")
        }
        can1011Click=true
    }

    private fun toResultPage(){
        if (RegisterActivity.front1011){
            val intent = Intent(this, Result1011Page::class.java)
            intent.putExtra("connect",connect)
            startActivity(intent)
        }
    }

    private fun setConnectingUI(){
        iv_connect_idle.showInvisible(false)
        lottie_view.show(true)
        tv_connect_text.text="Connecting..."
    }

    private fun setConnectedSuccessUI(){
        iv_connect_idle.showInvisible(true)
        lottie_view.show(false)
        tv_connect_text.text="Disconnect"
    }

    private fun setStoppedSuccessUI(){
        iv_connect_idle.showInvisible(true)
        lottie_view.show(false)
        tv_connect_text.text="Connect"
        tv_connect_time.text="00:00:00"
    }

    private fun setStoppingUI(){
        iv_connect_idle.showInvisible(false)
        lottie_view.show(true)
        tv_connect_text.text="Stopping..."
    }

    private fun setTopRightServerLogo(){
        iv_server_country.setImageResource(getServerFlag(ConnectHelper.server1011Entity.rabbit_1011_country))
    }

    private fun showNoNetDialog(){
        AlertDialog.Builder(this).apply {
            setMessage("You are not currently connected to the network")
            setPositiveButton("sure", null)
            show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (RegisterActivity.loadHomeAd){
            showNativeAd.has1011AdCache()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        checkConnectJob?.cancel()
        checkConnectJob=null
        showNativeAd.stopHas1011Cache()
        ConnectHelper.onDestroy()
        RegisterActivity.loadHomeAd=true
        Time1011Helper.setTimeListener(null)
    }

    override fun connect1011Success() {
        if (can1011Click){
            setStoppedSuccessUI()
        }
    }

    override fun disconnect1011Success() {
        setConnectedSuccessUI()
    }

    override fun connectTime(time: String) {
        tv_connect_time.text=time
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==1011){
            when(data?.getStringExtra("back")){
                "dis"->{
                    connectOrDisServer()
                }
                "con"->{
                    setTopRightServerLogo()
                    connectOrDisServer()
                }
            }
        }
    }
}