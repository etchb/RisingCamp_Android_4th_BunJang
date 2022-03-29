package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayResponse

interface PayActivityInterface {

    fun onGetDataSuccess(response: PayResponse)

    fun onGetDataFailure(message: String)
}