package com.rabbittoy.secure.connection.config

import com.rabbittoy.secure.connection.entity.Server1011Entity

object Local1011Config {

    val email1011="thomaslee.ppl@gmail.com"
    val url1011="https://sites.google.com/view/rabbittoy/home"

    const val local_rao_rabbit_1011="""{
    "state":1,
    "name":[
    
    ]
}"""

    val localServerList= arrayListOf(
        Server1011Entity(
            rabbit_1011_host = "51.161.131.222",
            rabbit_1011_pwd = "yFQi6JGKDOoLWdiWfKde",
            rabbit_1011_country = "Australia",
            rabbit_1011_city = "Sydney",
            rabbit_1011_port = 2076,
            rabbit_1011_method = "chacha20-ietf-poly1305"
        ),
        Server1011Entity(
            rabbit_1011_host = "31.13.213.195",
            rabbit_1011_pwd = "yFQi6JGKDOoLWdiWfKde",
            rabbit_1011_country = "UnitedStates",
            rabbit_1011_city = "Atlanta",
            rabbit_1011_port = 2076,
            rabbit_1011_method = "chacha20-ietf-poly1305"
        )
    )
}