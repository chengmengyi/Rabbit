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

    const val localAd="""{
            "rabbit_1011_click":15,
    "rabbit_1011_show":50,
    "rabbit_1011_open": [
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/3419835294",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 1
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/3419835294A",
            "rabbit_1011_type": "kp",
            "rabbit_1011_sort": 2
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/3419835294AA",
            "rabbit_1011_type": "kp",
            "rabbit_1011_sort": 3
        }
    ],
    "rabbit_1011_home": [
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 2
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110AAA",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 1
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110AA",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 3
        }
    ],
    "rabbit_1011_result": [
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110A",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 2
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 1
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/2247696110AA",
            "rabbit_1011_type": "ys",
            "rabbit_1011_sort": 3
        }
    ],
    "rabbit_1011_connect": [
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/8691691433A",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 2
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/1033173712",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 1
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/1033173712AA",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 3
        }
    ],
    "rabbit_1011_back": [
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/1033173712A",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 2
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/8691691433",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 1
        },
        {
            "rabbit_1011_source": "admob",
            "rabbit_1011_id": "ca-app-pub-3940256099942544/1033173712AA",
            "rabbit_1011_type": "cp",
            "rabbit_1011_sort": 3
        }
    ]
}"""
}