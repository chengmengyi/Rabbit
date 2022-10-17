package com.rabbittoy.secure.connection.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.rabbittoy.secure.connection.entity.Server1011Entity
import com.tencent.mmkv.MMKV

object Read1011Config {

    fun read1011Config(){
        writeRaoStr(Local1011Config.local_rao_rabbit_1011)
        writeServer(Local1011Config.localServerList)

        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                writeRaoStr(remoteConfig.getString("rao_rabbit_1011"))
            }
        }
    }

    private fun writeRaoStr(string: String){
        MMKV.mmkvWithID("rao").encode("rao_rabbit_1011",string)
    }

    private fun writeServer(list:List<Server1011Entity>){
        list.forEach {
            it.writeProfileId()
        }
    }
}