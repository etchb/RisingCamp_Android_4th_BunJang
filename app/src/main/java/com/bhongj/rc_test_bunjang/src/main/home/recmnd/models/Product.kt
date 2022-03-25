package com.bhongj.rc_test_bunjang.src.main.home.recmnd.models

data class Product(
    val idx: Int = 0,
    val img: Int = 0,
    val bungaePayEnabled: Boolean = true,
    var imgHeartCheck: Boolean = false,
    val price: String = "0000원",
    val title: String = "이거 팔아요",
    val region: String = "서울시",
    val heartCnt: Int = 3,
    val time: Int = 0,
)
