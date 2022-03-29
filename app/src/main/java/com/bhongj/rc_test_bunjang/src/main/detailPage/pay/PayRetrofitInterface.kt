package com.bhongj.rc_test_bunjang.src.main.detailPage.pay

import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayRequest
import com.bhongj.rc_test_bunjang.src.main.detailPage.pay.models.PayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PayRetrofitInterface{
    @POST("/app/products/{productIdx}/payment")
    fun postResponse(
        @Path("productIdx") productIdx: Int,
        @Body payRequest: PayRequest,
    ): Call<PayResponse>
}
