package com.demo.rabbit.config

import com.demo.rabbit.entity.Server1011Entity

object Local1011Config {

    val email1011=""
    val url1011=""

    const val local_rao_rabbit_1011="""{
    "state":0,
    "name":[
        "com.UCMobile"
    ]
}"""

    val localServerList= arrayListOf(
        Server1011Entity(
            rabbit_1011_host = "100.223.52.0",
            rabbit_1011_pwd = "123456",
            rabbit_1011_country = "Japan",
            rabbit_1011_city = "Tokyo",
            rabbit_1011_port = 100,
            rabbit_1011_method = "chacha20-ietf-poly1305"
        ),
        Server1011Entity(
            rabbit_1011_host = "100.223.52.78",
            rabbit_1011_pwd = "123456",
            rabbit_1011_country = "unitedStates",
            rabbit_1011_city = "Tokyo",
            rabbit_1011_port = 100,
            rabbit_1011_method = "chacha20-ietf-poly1305"
        )
    )
}