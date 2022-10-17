package com.demo.rabbit.ad

import com.demo.rabbit.app.print1011
import com.demo.rabbit.config.Read1011Config
import com.demo.rabbit.entity.Ad1011Entity
import com.demo.rabbit.entity.Ad1011ResultEntity
import org.json.JSONObject

object Load1011AdImpl:Base1011LoadAd() {
    const val open="rabbit_1011_open"
    const val home="rabbit_1011_home"
    const val connect="rabbit_1011_connect"
    const val result="rabbit_1011_result"
    const val back="rabbit_1011_back"
    var screenAdIsShowing=false
    
    private val is1011LoadingAd= arrayListOf<String>()
    private val adCache= hashMapOf<String,Ad1011ResultEntity>()

    fun preLoadAllAd(){
        canLoad1011Ad(open)
        canLoad1011Ad(home)
        canLoad1011Ad(connect)
        canLoad1011Ad(result)
    }
    
    fun canLoad1011Ad(type:String,loadOpenAgain:Boolean=true){
        if (is1011LoadingAd.contains(type)){
            print1011("$type loading")
            return
        }
        if (adCache.containsKey(type)){
            val ad1011ResultEntity = adCache[type]
            if (null!=ad1011ResultEntity?.ad){
                if (ad1011ResultEntity.adIsExpired()){
                    removeCache(type)
                }else{
                    print1011("$type has cache")
                    return
                }
            }
        }
        if (Read1011Config.hasLimit()){
            print1011("all load limit")
            return
        }

        val get1011AdList = get1011AdList(type)
        if (get1011AdList.isEmpty()){
            return
        }
        is1011LoadingAd.add(type)
        loop1011LoadAd(type,get1011AdList.iterator(),loadOpenAgain)
    }

    private fun loop1011LoadAd(type: String, iterator: Iterator<Ad1011Entity>, loadOpenAgain: Boolean){
        val ad1011Entity = iterator.next()
        load1011AdByType(type,ad1011Entity){
            if (it.msg.isNotEmpty()){
                if (iterator.hasNext()){
                    loop1011LoadAd(type,iterator,loadOpenAgain)
                }else{
                    is1011LoadingAd.remove(type)
                    if (type== open&&loadOpenAgain){
                        canLoad1011Ad(type,loadOpenAgain = false)
                    }
                }
            }else{
                print1011("load $type success")
                is1011LoadingAd.remove(type)
                adCache[type]=it
            }
        }
    }
    
    private fun get1011AdList(type: String):List<Ad1011Entity>{
        val list= arrayListOf<Ad1011Entity>()
        try {
            val jsonArray = JSONObject(Read1011Config.getConfigAdStr()).getJSONArray(type)
            for (index in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(index)
                list.add(
                    Ad1011Entity(
                        jsonObject.optString("rabbit_1011_id"),
                        jsonObject.optInt("rabbit_1011_sort"),
                        jsonObject.optString("rabbit_1011_type"),
                        jsonObject.optString("rabbit_1011_source"),
                    )
                )
            }
        }catch (e:Exception){}
        return list.filter { it.source_rabbit_1011 == "admob" }.sortedByDescending { it.sort_rabbit_1011 }
    }

    fun getAdCache(type: String)= adCache[type]?.ad
    
    fun removeCache(type: String){
        adCache.remove(type)   
    }
}