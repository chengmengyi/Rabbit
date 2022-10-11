package com.demo.rabbit.server

import com.demo.rabbit.config.Local1011Config
import com.demo.rabbit.entity.Server1011Entity

object ServerInfoHelper {
    fun get1011ServerList()=Local1011Config.localServerList

    fun createRandomServer()=Server1011Entity(rabbit_1011_country = "Super Fast Servers")

    fun getRandomServer()=Local1011Config.localServerList.random()

}