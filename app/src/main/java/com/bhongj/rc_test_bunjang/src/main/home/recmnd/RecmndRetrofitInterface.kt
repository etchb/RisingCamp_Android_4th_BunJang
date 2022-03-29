package com.bhongj.rc_test_bunjang.src.main.home.recmnd

import com.bhongj.rc_test_bunjang.src.main.home.recmnd.models.HomeRecmndResponse
import retrofit2.Call
import retrofit2.http.GET

interface RecmndRetrofitInterface {
    @GET("/app/home")
    fun getRecmndResponse(): Call<HomeRecmndResponse>
}
