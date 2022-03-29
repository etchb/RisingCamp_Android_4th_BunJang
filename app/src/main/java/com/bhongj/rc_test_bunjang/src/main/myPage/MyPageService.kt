package com.bhongj.rc_test_bunjang.src.main.myPage

import com.bhongj.rc_test_bunjang.config.ApplicationClass
import com.bhongj.rc_test_bunjang.src.main.myPage.models.MyPageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyPageService(val layoutInterface: MyPageFragmentInterface) {

    fun tryGetData(userIdx: Int) {
        val retrofitInterface =
            ApplicationClass.sRetrofit.create(MyPageRetrofitInterface::class.java)
        retrofitInterface.getMyPageResponse(userIdx, 1)
            .enqueue(object : Callback<MyPageResponse> {
                override fun onResponse(
                    call: Call<MyPageResponse>,
                    response: Response<MyPageResponse>
                ) {
                    layoutInterface.onGetDataSuccess(response.body() as MyPageResponse)
                }

                override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                    layoutInterface.onGetDataFailure(t.message ?: "통신 오류")
                }
            })
    }
}
