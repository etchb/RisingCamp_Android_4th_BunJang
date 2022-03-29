package com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models

data class PayResult(
    val address: String,
    val buyerIdx: Int,
    val idx: Int,
    val paymentMethod: Int,
    val point: Int,
    val productIdx: Int,
    val safetyTax: Int,
    val totalPaymentAmount: Int,
    val transactionMethod: Int
)