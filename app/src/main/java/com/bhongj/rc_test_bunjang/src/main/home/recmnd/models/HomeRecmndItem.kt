package com.bhongj.rc_test_bunjang.src.main.home.recmnd.models

data class HomeRecmndItem (
    val idx: Int,
    val imageUrl: String,
    val saftyPay: Int,
    var myLike: Int,
    val price: Int,
    val productName: String,
    val directtrans: String,
    val productLike: Int,
    val createAt: String,
)