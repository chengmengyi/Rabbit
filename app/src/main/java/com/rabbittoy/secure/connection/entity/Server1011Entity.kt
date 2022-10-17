package com.rabbittoy.secure.connection.entity

import com.github.shadowsocks.database.Profile
import com.github.shadowsocks.database.ProfileManager

class Server1011Entity(
    val rabbit_1011_method:String="",
    val rabbit_1011_pwd:String="",
    val rabbit_1011_host:String="",
    val rabbit_1011_country:String="",
    val rabbit_1011_port:Int=0,
    val rabbit_1011_city:String=""
) {

    fun isRandomServer() = rabbit_1011_host=="" &&rabbit_1011_country=="Super Fast Servers"

    fun getProfileId():Long{
        ProfileManager.getActiveProfiles()?.forEach {
            if (it.host==rabbit_1011_host&&it.remotePort==rabbit_1011_port){
                return it.id
            }
        }
        return 0L
    }

    fun writeProfileId(){
        val profile = Profile(
            id = 0L,
            name = "$rabbit_1011_country - $rabbit_1011_city",
            host = rabbit_1011_host,
            remotePort = rabbit_1011_port,
            password = rabbit_1011_pwd,
            method = rabbit_1011_method
        )

        var id:Long?=null
        ProfileManager.getActiveProfiles()?.forEach {
            if (it.remotePort==profile.remotePort&&it.host==profile.host){
                id=it.id
                return@forEach
            }
        }
        if (null==id){
            ProfileManager.createProfile(profile)
        }else{
            profile.id=id!!
            ProfileManager.updateProfile(profile)
        }
    }
}