package com.bhongj.rc_test_bunjang.src.main.detailPage.models

data class DetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: DetailResult
)