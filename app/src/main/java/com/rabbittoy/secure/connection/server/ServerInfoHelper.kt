package com.rabbittoy.secure.connection.server

import com.rabbittoy.secure.connection.config.Local1011Config
import com.rabbittoy.secure.connection.config.Read1011Config
import com.rabbittoy.secure.connection.entity.Server1011Entity

object ServerInfoHelper {
    fun get1011ServerList()=Read1011Config.server.ifEmpty { Local1011Config.localServerList }

    fun createRandomServer()=Server1011Entity(rabbit_1011_country = "Super Fast Servers")

    fun getRandomServer():Server1011Entity{
        val serverList = get1011ServerList()
        if (!Read1011Config.city.isNullOrEmpty()){
            val filter = serverList.filter { Read1011Config.city.contains(it.rabbit_1011_city) }
            if (!filter.isNullOrEmpty()){
                return filter.random()
            }
        }
        return serverList.random()
    }

}