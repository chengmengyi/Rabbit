package com.rabbittoy.secure.connection.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.rabbittoy.secure.connection.app.saveKey
import com.rabbittoy.secure.connection.entity.Server1011Entity
import com.tencent.mmkv.MMKV
import org.json.JSONObject

object Read1011Config {

    var maxClick=15
    var maxShow=50
    var currentClick=0
    var currentShow=0

    val city= arrayListOf<String>()
    val server= arrayListOf<Server1011Entity>()

    fun read1011Config(){
        writeRaoStr(Local1011Config.local_rao_rabbit_1011)
        writeServer(Local1011Config.localServerList)
        readCurrentNum()

        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful){
                writeRaoStr(remoteConfig.getString("rao_rabbit_1011"))
                readCity(remoteConfig.getString("city_rabbit_1011"))
                readServer(remoteConfig.getString("server_rabbit_1011"))
                readConfigAd(remoteConfig.getString("ad_rabbit_1011"))
            }
        }
    }

    private fun readCity(json: String){
        try {
            val jsonArray = JSONObject(json).getJSONArray("city_rabbit_1011")
            for (index in 0 until jsonArray.length()){
                city.add(jsonArray.optString(index))
            }
        }catch (e:Exception){

        }
    }

    private fun readServer(json: String){
        try {
            val jsonArray = JSONObject(json).getJSONArray("server_rabbit_1011")
            for (index in 0 until jsonArray.length()){
                val json0914Object = jsonArray.getJSONObject(index)
                server.add(
                    Server1011Entity(
                        json0914Object.optString("rabbit_1011_method"),
                        json0914Object.optString("rabbit_1011_pwd"),
                        json0914Object.optString("rabbit_1011_host"),
                        json0914Object.optString("rabbit_1011_country"),
                        json0914Object.optInt("rabbit_1011_port"),
                        json0914Object.optString("rabbit_1011_city"),
                    )
                )
            }
            writeServer(server)
        }catch (e:Exception){

        }
    }

    private fun readConfigAd(json:String){
        try {
            val jsonObject = JSONObject(json)
            maxShow=jsonObject.optInt("rabbit_1011_show")
            maxClick=jsonObject.optInt("rabbit_1011_click")
        }catch (e:Exception){

        }
        MMKV.defaultMMKV().encode("ad_rabbit_1011",json)
    }

    fun getConfigAdStr():String{
        val decodeString = MMKV.defaultMMKV().decodeString("ad_rabbit_1011", "")
        return if (decodeString.isNullOrEmpty()) {
            Local1011Config.localAd
        }else{
            decodeString
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

    private fun readCurrentNum(){
        currentClick=MMKV.defaultMMKV().decodeInt(saveKey("currentClick"),0)
        currentShow=MMKV.defaultMMKV().decodeInt(saveKey("currentShow"),0)
    }

    fun saveClick(){
        currentClick++
        MMKV.defaultMMKV().encode(saveKey("currentClick"), currentClick)
    }

    fun saveShow(){
        currentShow++
        MMKV.defaultMMKV().encode(saveKey("currentShow"), currentShow)
    }

    fun hasLimit()= currentShow>= maxShow|| currentClick>= maxClick
}