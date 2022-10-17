package com.rabbittoy.secure.connection.entity

class Ad1011ResultEntity(
    val time:Long=0,
    val ad:Any?=null,
    var msg:String=""
) {

    fun adIsExpired()=(System.currentTimeMillis() - time) >=3600000L
}