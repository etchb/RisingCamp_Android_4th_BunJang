package com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models

data class PayResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: PayResult
)