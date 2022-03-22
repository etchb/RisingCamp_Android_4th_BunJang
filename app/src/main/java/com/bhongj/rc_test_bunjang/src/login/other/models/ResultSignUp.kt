package com.bhongj.rc_test_bunjang.src.login.other.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("idx") val idx: Int,
    @SerializedName("shopName") val shopName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userBirth") val userBirth: String,
    @SerializedName("jwt") val jwt: String
)