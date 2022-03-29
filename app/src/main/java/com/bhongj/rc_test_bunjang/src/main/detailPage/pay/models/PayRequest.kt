package com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models

data class PayRequest (
    var safetyTax: Int,
    var point: Int,
    var totalPaymentAmount: Int,
    var paymentMethod: Int,
    var transactionMethod: Int,
    var address: String,
)