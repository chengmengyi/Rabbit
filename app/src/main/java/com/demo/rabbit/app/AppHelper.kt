package com.demo.rabbit.app

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.demo.rabbit.R
import com.demo.rabbit.config.Local1011Config
import java.lang.Exception

fun getServerFlag(country:String)=when(country){
    "Australia"->R.drawable.australia
    "UnitedStates"->R.drawable.unitedstates
    else-> R.drawable.icon_fast
}

fun Context.toast(s: String){
    Toast.makeText(this, s, Toast.LENGTH_LONG).show()
}

fun Context.getNet1011Status(): Int {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
        if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
            return 2
        } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return 0
        }
    } else {
        return 1
    }
    return 1
}

fun processName(applicationContext: Application): String {
    val pid = android.os.Process.myPid()
    var processName = ""
    val manager = applicationContext.getSystemService(Application.ACTIVITY_SERVICE) as ActivityManager
    for (process in manager.runningAppProcesses) {
        if (process.pid === pid) {
            processName = process.processName
        }
    }
    return processName
}

fun transTime(t:Long):String{
    try {
        val shi=t/3600
        val fen= (t % 3600) / 60
        val miao= (t % 3600) % 60
        val s=if (shi<10) "0${shi}" else shi
        val f=if (fen<10) "0${fen}" else fen
        val m=if (miao<10) "0${miao}" else miao
        return "${s}:${f}:${m}"
    }catch (e: Exception){}
    return "00:00:00"
}

fun View.show(show:Boolean){
    visibility=if (show) View.VISIBLE else View.GONE
}

fun View.showInvisible(show:Boolean){
    visibility=if (show) View.VISIBLE else View.INVISIBLE
}

fun Context.contact1011(){
    try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data= Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, Local1011Config.email1011)
        startActivity(intent)
    }catch (e: Exception){
        toast("Contact us by email：${Local1011Config.email1011}")
    }
}

fun Context.update1011(){
    val packName = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).packageName
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(
            "https://play.google.com/store/apps/details?id=$packName"
        )
    }
    startActivity(intent)
}

fun Context.share1011(){
    val pm = packageManager
    val packageName=pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).packageName
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_TEXT,
        "https://play.google.com/store/apps/details?id=${packageName}"
    )
    startActivity(Intent.createChooser(intent, "share"))
}