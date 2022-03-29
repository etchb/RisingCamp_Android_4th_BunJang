package com.bhongj.rc_test_bunjang.src.main.itemRegistration.models

import com.google.gson.annotations.SerializedName

data class PostRegistrationRequest(
    @SerializedName("imageUrl") val imageUrl: List<String>,
    @SerializedName("tagName") val tagName: List<String>,
    @SerializedName("categoryIdx") val categoryIdx: Int,
    @SerializedName("productName") val productName: String,
    @SerializedName("productDesc") val productDesc: String,
    @SerializedName("productCondition") val productCondition: Int,
    @SerializedName("saftyPay") val saftyPay: Int,
    @SerializedName("isExchange") val isExchange: Int,
    @SerializedName("amount") val amount: Int,
    @SerializedName("includeFee") val includeFee: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("directtrans") val directtrans: String,
)