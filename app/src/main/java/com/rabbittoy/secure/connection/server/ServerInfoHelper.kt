package com.rabbittoy.secure.connection.server

import com.rabbittoy.secure.connection.config.Local1011Config
import com.rabbittoy.secure.connection.entity.Server1011Entity

object ServerInfoHelper {
    fun get1011ServerList()=Local1011Config.localServerList

    fun createRandomServer()=Server1011Entity(rabbit_1011_country = "Super Fast Servers")

    fun getRandomServer()=Local1011Config.localServerList.random()

}