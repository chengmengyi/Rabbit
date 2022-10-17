package com.rabbittoy.secure.connection.server

import com.rabbittoy.secure.connection.app.transTime
import kotlinx.coroutines.*

object Time1011Helper {
    private var time=0L
    private var time1011Job:Job?=null
    private var iTime:ITime?=null

    fun setTime(){
        time=0L
    }

    fun start1011Time(){
        if (null!= time1011Job) return
        time1011Job = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                iTime?.connectTime(transTime(time))
                time++
                delay(1000L)
            }
        }
    }

    fun stop1011Time(){
        time1011Job?.cancel()
        time1011Job=null
    }

    fun setTimeListener(iTime:ITime?){
        this.iTime=iTime
    }

    interface ITime{
        fun connectTime(time:String)
    }
}