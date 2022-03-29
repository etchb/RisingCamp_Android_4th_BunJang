package com.bhongj.rc_test_bunjang.src.main.myPage.models

data class MyPageResult(
    val count: Int,
    val posteddate: String,
    val productImage: String,
    val productName: String,
    val productPrice: Int,
    val reviewrate: Double,
    val saftyPay: Int,
    val userFavCount: Int,
    val userFollowingCount: Int,
    val userFollwerCount: Int,
    val userProfileImage: String,
    val userReviewCount: Int,
    val userShopName: String
)