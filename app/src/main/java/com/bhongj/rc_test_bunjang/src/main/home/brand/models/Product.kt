package com.bhongj.rc_test_bunjang.src.main.home.brand.models

data class Product(
    val img: Int = 0,
    val bungaePayEnabled: Boolean = true,
    var imgHeartCheck: Boolean = false,
    val price: String = "0000원",
    val title: String = "이거 팔아요",
)
