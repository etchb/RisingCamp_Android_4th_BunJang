package com.bhongj.rc_test_bunjang.src.main.home.recmnd.models

data class Product(
    val img: Int = 0,
    val bungaePayEnabled: Boolean = true,
    val price: String = "0000원",
    val title: String = "이거 팔아요",
    val region: String = "서울시",
    val time: Int = 0,
)
