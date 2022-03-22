package com.bhongj.rc_test_bunjang.src.login.other.models

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
)
