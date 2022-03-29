package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecmndService(val recmndFragmentInterface: RecmndFragmentInterface) {

    fun tryGetRestaurantData() {
        val searchRetrofitInterface =
            ApplicationClass.sRetrofit.create(RecmndRetrofitInterface::class.java)
        searchRetrofitInterface.getRestaurantResponse()
            .enqueue(object : Callback<HomeRecmndResponse> {
                override fun onResponse(
                    call: Call<HomeRecmndResponse>,
                    response: Response<HomeRecmndResponse>
                ) {
                    recmndFragmentInterface.onGetDataSuccess(response.body() as HomeRecmndResponse)
                }

                override fun onFailure(call: Call<HomeRecmndResponse>, t: Throwable) {
                    recmndFragmentInterface.onGetDataFailure(t.message ?: "통신 오류")
                }
            })
    }
}
