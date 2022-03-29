package com.bhongj.rc_test_bunjang.src.main.detailPage

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.detailPage.models.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailService(val detailActivityInterface: DetailActivityInterface) {

    fun tryGetData(idx: Int) {
        val searchRetrofitInterface =
            ApplicationClass.sRetrofit.create(DetailRetrofitInterface::class.java)
        searchRetrofitInterface.getDetailResponse(
            idx
        )
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    detailActivityInterface.onGetDataSuccess(response.body() as DetailResponse)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    detailActivityInterface.onGetDataFailure(t.message ?: "통신 오류")
                }
            })
    }
}
