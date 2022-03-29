package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayRequest
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PayService(val payActivityInterface: PayActivityInterface) {

    fun tryGetData(idx: Int, payRequest: PayRequest) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(PayRetrofitInterface::class.java)
        retrofitInterface.postResponse(idx, payRequest)
            .enqueue(object : Callback<PayResponse> {
                override fun onResponse(
                    call: Call<PayResponse>,
                    response: Response<PayResponse>
                ) {
                    payActivityInterface.onGetDataSuccess(response.body() as PayResponse)
                }

                override fun onFailure(call: Call<PayResponse>, t: Throwable) {
                    payActivityInterface.onGetDataFailure(t.message ?: "통신 오류")
                }
            })
    }
}
