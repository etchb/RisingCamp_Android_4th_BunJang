package com.bhongj.rc_test_bunjang.src.main.detailPage

import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailRetrofitInterface {
    @GET("/app/products/{productIdx}")
    fun getDetailResponse(
        @Path("productIdx") productIdx: Int
    ): Call<DetailResponse>
}
