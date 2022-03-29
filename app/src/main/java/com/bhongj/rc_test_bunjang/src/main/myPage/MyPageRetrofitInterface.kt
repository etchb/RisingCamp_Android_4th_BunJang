package com.bhongj.rc_test_bunjang.src.main.myPage

import com.bhongj.rc_test_bunjang.src.main.myPage.models.MyPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageRetrofitInterface {
    @GET("/app/users/{userIdx}/{process}")
    fun getMyPageResponse(
        @Path("userIdx") userIdx: Int,
        @Path("process") process: Int
    ): Call<MyPageResponse>
}
